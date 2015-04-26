package com.light.mobilesafe.utils;

import android.text.TextUtils;

public class EmojiFilter {

	/**
	 * ����Ƿ���emoji�ַ�
	 * 
	 * @param source
	 * @return һ�����о��׳�
	 */
	public static boolean containsEmoji(String source) {
		if (TextUtils.isEmpty(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing���жϵ������������ȷ���б����ַ�
				return true;
			}
		}

		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
				|| (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * ����emoji ���� �������������͵��ַ�
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {

		if (!containsEmoji(source)) {
			return source;// �����������ֱ�ӷ���
		}
		// ��������������
		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source;// ���û���ҵ� emoji���飬�򷵻�Դ�ַ���
		} else {
			if (buf.length() == len) {// ������������ھ������ٵ�toString����Ϊ�����������ַ���
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}

	}
}
