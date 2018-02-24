package com.vue007.admin.util;

import java.security.MessageDigest;

/**
 * 来源于 上个版本 由于已有数据存在，暂时不便重构
 */
public class MD5 {

	private static MD5 md5 = null;

	/**
	 * 初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public static MD5 getInstance() throws Exception {
		if (md5 == null)
			md5 = new MD5();
		return md5;
	}

	/**
	 * 进行MD5数字摘要
	 * 
	 * @param buf
	 *            byte[] 要进行MD5加密的内容
	 * @return byte[]
	 */
	public static byte[] digest(byte[] buf) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(buf);
			return md.digest();
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	/**
	 * 取得16进制的MD5值
	 * 
	 * @param text
	 *            String
	 * @return String
	 */
	public static String getHexMD5(String text) {
		return getHexMD5(text.getBytes());
	}

	/**
	 * 取得16进制的MD5值
	 * 
	 * @param buf
	 *            byte[] 要进行MD5加密的内容
	 * @return String
	 */
	public static String getHexMD5(byte[] buf) {
		return Hex.encode(digest(buf));
	}

}

/**
 * 来源于 上个版本，简单重构
 */
class Hex {

	/**
	 * 转化成十六进制
	 * @param buffer byte[]
	 * @return String
	 */
	public static String encode(byte[] buffer) {
		String dump = "";
		try {
			int dataLen = buffer.length;
			for (int i = 0; i < dataLen; i++) {
				dump += Character.forDigit( (buffer[i] >> 4) & 0x0f, 16);
				dump += Character.forDigit(buffer[i] & 0x0f, 16);
			}
		}
		catch (Throwable t) {
		}
		return dump.toLowerCase();
	}



	/**
	 * 将十六进制的转换成可见的字符串
	 * @param hexStr String
	 * @return String
	 */
	public static byte[] decode(String hexStr) {
		byte[] bytes = new byte[hexStr.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) (0xff & Integer.parseInt(hexStr.substring(
					i * 2, i * 2 + 2), 16));
		}
		return bytes;
	}


}
