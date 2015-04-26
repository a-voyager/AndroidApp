package com.light.mobilesafe.service;

import com.light.mobilesafe.R;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;

public class SmsReciver extends BroadcastReceiver {

	private static final String TAG = "SMSRECIVER";
	private SharedPreferences sp;
	private DevicePolicyManager dpm;

	@Override
	public void onReceive(Context context, Intent intent) {
		sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		dpm = (DevicePolicyManager) context.getSystemService(context.DEVICE_POLICY_SERVICE);
		
		//�ֻ���������
		if(sp.getBoolean("openService", false)){
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			if ("#*location*#".equals(body)) {
				Log.e(TAG, "����ֻ�λ�õ�ַ");
				Intent locationIntent = new Intent(context, LocationService.class);
				context.startService(locationIntent);
				
				
				sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
				String location = sp.getString("lastLocation", null);
				//���Ͷ���
				SmsManager smsManager = SmsManager.getDefault();
				if(!TextUtils.isEmpty(location)){
				smsManager.sendTextMessage(sender, null, location, null, null);
				} else {
					System.out.println("���ڻ�ȡλ��");
					smsManager.sendTextMessage(sender, null, "���ڻ�ȡλ��", null, null);
				}
				abortBroadcast();
			} 
			
			else if ("#*alarm*#".equals(body)) {
				Log.e(TAG, "�ֻ�����");
				MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
				mediaPlayer.setLooping(true);
				mediaPlayer.setVolume(1.0F, 1.0F);
				mediaPlayer.start();
				abortBroadcast();
			} 
			
			else if (body.startsWith("#*lockscreen*#%")) {
				Log.e(TAG, "Զ������");
				String pwd = body.substring(15);
				System.out.println("pwd="+pwd);
				dpm.resetPassword(pwd, 0);
				dpm.lockNow();
				abortBroadcast();
			} 
			
			else if ("#*wipedata*#".equals(body)) {
				Log.e(TAG, "�������");
				dpm.wipeData(0);
				abortBroadcast();
			}

		}

	}
	}
	
	
}
