package com.love.business.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

/**
 * 加密算法
 * @author Administrator
 *
 */
public class AESUtil {
	public static String encrypt(String data, String key, String encoding) throws Exception {
		try {
			String characterEncode = StringUtils.isBlank(encoding) ? "utf-8" : encoding;

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(1, getKey(key));

			byte[] cipherByte = cipher.doFinal(data.getBytes(characterEncode));
			String result = byte2hex(cipherByte);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	public static String decrypt(String data, String key, String encoding) throws Exception {
		try {
			String characterEncode = StringUtils.isBlank(encoding) ? "utf-8" : encoding;

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(2, getKey(key));

			byte[] cipherByte = cipher.doFinal(hex2byte(data));
			String result = new String(cipherByte, characterEncode);
			return result;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public static Key getKey(String key) throws Exception {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");

			new SecureRandom();
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			random.setSeed(key.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			return new SecretKeySpec(enCodeFormat, "AES");
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	private static String byte2hex(byte[] buf) {
		if (buf == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	private static byte[] hex2byte(String hex) {
		if ((null == hex) || (hex.length() % 2 != 0)) {
			return null;
		}
		byte[] result = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length() / 2; i++) {
			int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String jiami = encrypt("admin", "abc", null);
		System.out.println(jiami);
		String jiemi = decrypt("0709C66FC6AB7A0DC52690EA733C44CF", "abc", null);
		System.out.println(jiemi);
	}
}
