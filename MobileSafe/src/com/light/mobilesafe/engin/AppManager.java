package com.light.mobilesafe.engin;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.light.mobilesafe.domain.AppInfo;

public class AppManager {

	/**
	 * @param context
	 * @return �����Ѱ�װӦ�õ�ͼ�꣬���ƣ��Ƿ�ΪϵͳӦ�ã��Ƿ�װ���ڲ�����
	 */
	public static List<AppInfo> getAppInfo(Context context) {
		List<AppInfo> appInfos = new ArrayList<AppInfo>();
		PackageManager packageManager = context.getPackageManager();
		List<ApplicationInfo> applications = packageManager
				.getInstalledApplications(0);
		for (ApplicationInfo applicationInfo : applications) {
			AppInfo appInfo = new AppInfo();
			appInfo.setIcon(applicationInfo.loadIcon(packageManager));
			appInfo.setSysApp((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
			appInfo.setInRom((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == 0);
			appInfo.setAppName(applicationInfo.loadLabel(packageManager)
					.toString());
			appInfo.setPackName(applicationInfo.packageName);
			System.out.println(appInfo);
			appInfos.add(appInfo);
		}
		return appInfos;
	}
}
