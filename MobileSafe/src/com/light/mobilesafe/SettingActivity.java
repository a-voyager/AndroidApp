package com.light.mobilesafe;

import com.light.mobilesafe.service.CallIntercept;
import com.light.mobilesafe.service.ShowAddressService;
import com.light.mobilesafe.ui.SettingItem;
import com.light.mobilesafe.utils.ServiceUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {

	private SettingItem item;
	private SharedPreferences sp;
	private Editor spEditor;

	private SettingItem si_showAddress;
	private Intent showAddressServiceIntent;

	private SettingItem si_intercept;
	private Intent interceptIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		sp = getSharedPreferences("config", MODE_PRIVATE);

		//ɧ������
		si_intercept = (SettingItem) findViewById(R.id.si_callsafe_intercept);
		si_intercept.setSIVchecked(sp.getBoolean("callIntercept", false));
		interceptIntent = new Intent(SettingActivity.this, CallIntercept.class);
		si_intercept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editor editor = sp.edit();
				if(si_intercept.getCbStatus()){
					si_intercept.setSIVchecked(false);
					editor.putBoolean("callIntercept", false);
					stopService(interceptIntent);
					System.out.println("startService(interceptIntent);");
				} else {
					si_intercept.setSIVchecked(true);
					editor.putBoolean("callIntercept", true);
					startService(interceptIntent);
					System.out.println("stopService(interceptIntent);");
				}
				editor.commit();
			}
		});
		
		//�����������ʾ
		si_showAddress = (SettingItem) findViewById(R.id.si_setting_show_address);
		si_showAddress.setSIVchecked((sp.getBoolean("showAddress", false)));
		showAddressServiceIntent = new Intent(SettingActivity.this,
				ShowAddressService.class);
		si_showAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Editor editor = sp.edit();
				if (si_showAddress.getCbStatus()) {
					si_showAddress.setSIVchecked(false);
					stopService(showAddressServiceIntent);
					System.out.println("stop(showAddressServiceIntent);");
					editor.putBoolean("showAddress", false);
				} else {
					si_showAddress.setSIVchecked(true);
					startService(showAddressServiceIntent);
					System.out
							.println("startService(showAddressServiceIntent);");
					editor.putBoolean("showAddress", true);
				}
				editor.commit();
			}
		});

		item = (SettingItem) findViewById(R.id.si_setting_autoUpdate);
		item.setTvTitle();
		readSettings();
		item.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (item.getCbStatus()) {
					// �Ѿ�ѡ�У�������Ϊδѡ��
					setUpdateItemStatus(false);
				} else {
					setUpdateItemStatus(true);
				}
			}
		});
	}

	// ��ʾ��Boolean �� boolean ���д��������

	/**
	 * ���ø���״̬�����⡢���ݣ�������
	 */
	private void setUpdateItemStatus(boolean bool) {
		item.setCbStatus(bool);
		spEditor = sp.edit();
		spEditor.putBoolean("AutoUpdate", bool);
		String a = null;
		if (bool) {
			// a = "����";
			item.setCheckedTvContent();
		} else {
			// a = "�ر�";
			item.setCheckedTvContent();
		}
		spEditor.commit();
		// item.setTvContent("�Զ�������"+a);
	}

	/**
	 * ��ȡ��������
	 */
	private void readSettings() {
		if (sp.getBoolean("AutoUpdate", true)) {
			setUpdateItemStatus(true);
		} else {
			setUpdateItemStatus(false);
		}

	}

}
