package com.light.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	
	private MessageDigest digest;
	private StringBuffer buffer;

	public String coding(String password){
	try {
		digest = MessageDigest.getInstance("md5");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	buffer = new StringBuffer();
	byte [] result = digest.digest(password.getBytes());
	for(byte b : result){
         //0xff��ʮ�����ƣ�ʮ����Ϊ255

		int nuber =  b & 0xff;
		String str = Integer.toHexString(nuber);
		if(str.length()==1){
			buffer.append("0");
		}
		buffer.append(str);
		
	}
	//MD5���ܵõ���ֵ
	return buffer.toString();
	}
}
