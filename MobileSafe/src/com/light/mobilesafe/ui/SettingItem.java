package com.light.mobilesafe.ui;

import com.light.mobilesafe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingItem extends RelativeLayout {

	private CheckBox cb;
	private TextView tv_title;
	private TextView tv_content;
	private String title;
	private String content_checked;
	private String content_unchecked;

	public SettingItem(Context context) {
		super(context);
		inflatView(context);
	}

	public SettingItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		inflatView(context);
		setByAttrs(attrs);
	}

	/**
	 * @param attrs
	 *            ͨ�������ļ����ÿؼ���Ϣ
	 */
	private void setByAttrs(AttributeSet attrs) {
		title = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.light.mobilesafe",
				"title");
		content_checked = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.light.mobilesafe",
				"content_checked");
		content_unchecked = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.light.mobilesafe",
				"content_unchecked");

	}

	public SettingItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflatView(context);
		setByAttrs(attrs);
	}

	/**
	 * inflat���пؼ�
	 */
	private void inflatView(Context context) {
		View.inflate(context, R.layout.activity_setting_item, SettingItem.this);
		tv_title = (TextView) this.findViewById(R.id.tv_setting_item_title);
		tv_content = (TextView) this.findViewById(R.id.tv_setting_item_content);
		cb = (CheckBox) this.findViewById(R.id.cb_setting_item);

	}

	public void setTvTitle() {
		tv_title.setText(title);
	}

	public void setCheckedTvContent() {
		tv_content.setText(content_checked);
	}

	public void setUncheckedTvContent() {
		tv_content.setText(content_unchecked);
	}

	/**
	 * ����Ƿ�ѡ��
	 */
	public boolean getCbStatus() {
		return cb.isChecked();
	}

	/**
	 * ����CheckBox״̬
	 */
	public void setCbStatus(boolean status) {
		cb.setChecked(status);
	}

	/**
	 * ������Ŀ����
	 */
	public void setTvTitle(String text) {
		tv_title.setText(text);
	}

	/**
	 * ������Ŀ����
	 */
	public void setTvContent(String text) {
		tv_content.setText(text);
	}

	/**
	 * @param bool
	 * =============����==============��
	 * �����ı�����ѡ״̬
	 */
	public void setSIVchecked(boolean bool){
		setTvTitle();
		setCbStatus(bool);
		if(bool){
			setCheckedTvContent();
		} else {
			setUncheckedTvContent();
		}
	}
	
}
