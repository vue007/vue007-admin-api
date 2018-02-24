package com.vue007.admin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil extends StringUtils{
	
	public static boolean isColor(String str){
		if(StringUtil.isNotBlank (str)){
			str = str.toLowerCase ();
			String regEx="#[0123456789abcdef]{6}";
			return str.matches(regEx);
		}
		else{
			return false;
		}
	}
	
	public static boolean isMobi(String str){
		if(StringUtil.isNotBlank (str)){
			String regEx="\\d*";
			return str.matches(regEx);
		}
		else{
			return false;
		}
	}
	

	public static boolean isAlign(String str){
		if(StringUtil.isNotBlank (str)){
			str = str.toLowerCase ();
			return str.equals ("left")||str.equals ("center")||str.equals ("right");
		}
		else{
			return false;
		}
	}
	
	public static String replaceStr(String str) {
		if (!StringUtils.isEmpty(str)) {
			str = str.replaceAll("%", "");
			str = str.replaceAll("'", "");
			str = str.replaceAll("&", "");
			str = str.replaceAll("_", "");
			str = str.replaceAll("~", "");
			str = str.replaceAll("@", "");
			str = str.replaceAll("#", "");
			str = str.replaceAll("$", "");
			str = str.replaceAll("^", "");
			str = str.replaceAll("\\*", "");
			str = str.replaceAll("\\(", "");
			str = str.replaceAll("\\)", "");
			str = str.replaceAll("\\?", "");
			str = str.replaceAll(",", "");
			str = str.replaceAll("\\.", "");
			str = str.replaceAll("/", "");
			str = str.replaceAll("\\\\", "");
			str = str.replaceAll("\\|", "");
			str = str.replaceAll("\\[", "");
			str = str.replaceAll("\\]", "");
			str = str.replaceAll("\\{", "");
			str = str.replaceAll("\\}", "");
			str = str.replaceAll("[^\u4E00-\u9FA5|0-9|a-z|A-Z]","");
		}
		return str;
	}
	
	/**
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hex
	 * @return
	 */
	private static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * char字符转换成byte字节
	 * 
	 * @param c
	 * @return
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	private static final String bytesToHexString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(bArray.length*2);
		String sTemp;
		for (byte b : bArray) {
			sTemp = Integer.toHexString(0xFF & b);
			if (sTemp.length() < 2)
				sb.append('0');
			sb.append(sTemp);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 *  描述:变成16进制字符串
	 * 
	 * @param str
	 *            字串
	 * @return 16进制字符串
	 */
	public static final String toHex(String str) {
		return bytesToHexString(str.getBytes());
	}

	/**
	 *  描述:从16进制字符串变成字串
	 * 
	 * @param str
	 *            16进制字符串
	 * @return 字串
	 */
	public static final String fromHex(String str) {
		return new String(hexStringToByte(str));
	}

	/**
	 *  描述:从16进制字符串变成字串
	 * 
	 * @param str
	 *            16进制字符串
	 * @param charset
	 *            字符集
	 * @return 字串
	 */
	public static final String fromHex(String str, String charset) {
		try {
			return new String(hexStringToByte(str), charset);
		} catch (UnsupportedEncodingException e) {
			return new String(hexStringToByte(str));
		}
	}
	
	private static final Random RAND=new Random();
	private StringUtil() {

	}
	

	/**
	 * Splits this string around matches of the given separator
	 * 
	 * @param value
	 * @param separator
	 * @return String[]
	 */
	public static String[] splitString(String value, String separator) {
		ArrayList<String> rtn = new ArrayList<String>();
		int pos = 0;
		int pos2 = 0;
		int tagLen = separator.length();
		String temp = "";
		int i = 0;
		Vector<Integer> vpos = new Vector<Integer>();
		if (value == null || value.equals("")) {
			rtn.add("");
			//return (String[]) rtn.toArray();
			return (String[])rtn.toArray(new String[rtn.size()]);
		}
		if (value.indexOf(separator) < 0) {
			rtn.add(value);
		} else {
			while (true) {
				if (i == 0) {
					pos = value.indexOf(separator, 0);
				} else {
					pos = value.indexOf(separator, tagLen + pos);
				}
				i++;
				if (pos >= 0) {
					vpos.add(new Integer(pos));
				} else {
					vpos.add(new Integer(value.length()));
					break;
				}
			}
			pos = 0;
			for (int j = 0; j < vpos.size(); j++) {
				pos2 = vpos.get(j).intValue();
				int te = pos2 - pos;
				if (te == 0) {
					temp = "";
				} else {
					if (j == 0) {
						temp = value.substring(pos, pos2);
					} else {
						temp = value.substring(pos + tagLen, pos2);
					}
				}
				rtn.add(temp);
				pos = pos2;
			}
		}
		//return (String[]) rtn.toArray();
		return (String[])rtn.toArray(new String[rtn.size()]);
	}

	/**
	 * Splits this string around matches of the given separator
	 * 
	 * @param value
	 * @param separator
	 * @return String[]
	 */
	public static ArrayList<String> divideString(String value, String separator) {
		ArrayList<String> rtn = new ArrayList<String>();
		int pos = 0;
		int pos2 = 0;
		int tagLen = separator.length();
		String temp = "";
		int i = 0;
		Vector<Integer> vpos = new Vector<Integer>();
		if (value == null || value.equals("")) {
			rtn.add("");
			return rtn;
		}
		if (value.indexOf(separator) < 0) {
			rtn.add(value);
		} else {
			while (true) {
				if (i == 0) {
					pos = value.indexOf(separator, 0);
				} else {
					pos = value.indexOf(separator, tagLen + pos);
				}
				i++;
				if (pos >= 0) {
					vpos.add(new Integer(pos));
				} else {
					vpos.add(new Integer(value.length()));
					break;
				}
			}
			pos = 0;
			for (int j = 0; j < vpos.size(); j++) {
				pos2 = vpos.get(j).intValue();
				int te = pos2 - pos;
				if (te == 0) {
					temp = "";
				} else {
					if (j == 0) {
						temp = value.substring(pos, pos2);
					} else {
						temp = value.substring(pos + tagLen, pos2);
					}
				}
				rtn.add(temp);
				pos = pos2;
			}
		}
		return rtn;
	}

	/**
	 * Returns a new string resulting from replacing all occurrences of
	 * oldString in this string with newString
	 * 
	 * @param value
	 * @return String
	 */
	public static String replaceString(String value, String oldString,
			String newString) {
		if (value == null || newString == null || oldString == null)
			return null;
		StringBuffer sbf = new StringBuffer();
		sbf.append(value);
		int len = newString.length();
		int l = oldString.length();
		int pos = 0 - len;
		String temp = "";
		if (value.equals("")) {
			return value;
		} else {
			do {
				temp = sbf.toString();
				pos = temp.indexOf(oldString, pos + len);
				if (pos >= 0) {
					sbf.replace(pos, pos + l, newString);
				}
			} while (pos >= 0);
			String aa = new String(sbf);
			return aa;
		}
	}

	/**
	 * change null to empty string
	 * 
	 * @param src
	 *            input string
	 * @return empty string
	 */
	public static String null2EmptyString(String src) {
		String s = src;
		if (src == null)
			s = "";
		return s;
	}

	/** Quote metacharacters in HTML. */
	public static String HTMLCharFilter(String s) {
		if (s == null)
			return "";
		else {
			// deal with ampersands first so we can ignore the ones we add later
			int c, oldC = -1;
			while ((c = s.substring(oldC + 1).indexOf('&')) != -1) {
				c += oldC + 1; // adjust back to real string start
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#38;"));
				oldC = c;
			}
			while ((c = s.indexOf('\\')) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#92;"));
			while ((c = s.indexOf("\'")) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#39;"));
			while ((c = s.indexOf('"')) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#34;"));
			while ((c = s.indexOf('<')) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#60;"));
			while ((c = s.indexOf('>')) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#62;"));
			while ((c = s.indexOf(' ')) != -1)
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&#32;"));

			return s;
		}

	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 *            字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 *            obj
	 * @return 是否为空
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		} else {
			return false;
		}
	}
	public static String formatStringLength(char prefix, int value,
			int dstLength) {
		String valueStr = String.valueOf(value);
		int length = valueStr.length();
		if (length < dstLength) {
			return StringUtil.repeatString(String.valueOf(prefix), dstLength
					- length)
					+ value;
		} else if (length == dstLength) {
			return valueStr;
		} else {
			String msg = "\"value\"'s length is greater than dstLength!";
			throw new IllegalArgumentException(msg);
		}
	}

	public static String repeatString(String value, int count) {
		String result = "";
		for (int i = 0; i <= count - 1; i++) {
			result += value;
		}

		return result;
	}

	public static String toGB2312(String source) {
		String retValue = "";

		if ((source == null) || (source.trim().length() == 0))

			return retValue;

		try {
			retValue = new String(source.getBytes("iso8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			return source;
		}

		return retValue;
	}

	/**
	 * 将字符串从GB2312转换为iso8859
	 * 
	 * @param source
	 *            String
	 * @return String
	 */
	public static String toISO8859(String source) {
		String retValue = "";
		if ((source == null) || (source.trim().length() == 0))

			return retValue;

		try {
			retValue = new String(source.getBytes("GB2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			return source;

		}
		return retValue;
	}

	/**
	 * 将字符串从iso8859转换为utf8
	 * 
	 * @param source
	 *            String
	 * @return String
	 */
	public static String toUTF8(String source) {

		String retValue = "";

		if ((source == null) || (source.trim().length() == 0))

			return retValue;

		try {
			retValue = new String(source.getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return source;
		}

		return retValue;
	}

	/**
	 * 返回类似139*****123的格式
	 * 
	 * @param mobile
	 * @return
	 */
	public static String formatMobile(String mobile) {
		if (mobile != null && mobile.length() == 11) {
			return mobile.substring(0, 3) + "*****" + mobile.substring(8);
		} else {
			return mobile;
		}
	}

	/**
	 * 针对WNG的link “&” 转换；
	 * 
	 * @param s
	 * @return
	 */
	public static String formatHtmlUrl(String s) {
		if (s == null)
			return "";
		else {
			// deal with ampersands first so we can ignore the ones we add later
			int c, oldC = -1;
			while ((c = s.substring(oldC + 1).indexOf('&')) != -1) {
				c += oldC + 1; // adjust back to real string start
				s = new String((new StringBuffer(s)).replace(c, c + 1, "&amp;"));
				oldC = c;
			}

			return s;
		}

	}

	/**
	 * 取出一个指定长度大小的随机正整数
	 * @param range 随机一个区间内的值 
	 * @return int 返回生成的随机数 介于 0到<range之间
	 */
	public static int buildRandom(int range) 
	{
		return RAND.nextInt(range);
	}
	
	/**
	 * 获取四位数的随机验证码
	 * @return
	 */
	public static String buildMobileAuthCode(){
		//验证码随机
		String code = "";
		
		for(int i=0;i<4;i++){
			code += buildRandom(10);
		}
		
		return code;
	}
	
	
	public static String getRandomString(int length, int type) { //length表示生成字符串的长度  
	    String base0 = "0123456789";
	    String base1 = "abcdefghijklmnopqrstuvwxyz";
	    String base2 = "abcdefghijklmnopqrstuvwxyz0123456789";
	    String base = "";
	    if(type == 0)
	    {
	    	base = base0;
	    }
	    if(type == 1)
	    {
	    	base = base1;
	    }
	    if(type == 2)
	    {
	    	base = base2;
	    }
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }

	/**
	 * 转义SQL特殊字符，如(','''')(%,\\%)....
	 * 
	 * @param value
	 * @return
	 */
	public static String escapeSQL(String value) {
		if (value == null) {
			return null;
		}
		return value.replace("'", "''''").replace("%", "\\%");
	}
	
	/**
	 * 生成一个SQL里in的字符串
	 * @param values {"aaa","bbb","cccc" | 1,2,3,4}
	 * @return	('aaa','bbbb','cccc')
	 */
	public static String genInSQL(Object[] values){
		if(values==null || values.length==0){
			return "";
		}
		StringBuilder sql= new StringBuilder("(");
		for (Object id : values) {
			if(id instanceof String){
				sql.append("'").append(escapeSQL((String)id)).append("',");
			}else{
				sql.append(id).append(",");
			}
		}
		return sql.substring(0,sql.length()-1)+")";
	}
	
	public static String getOrSQL(String param,Object[] values){
		if(values==null || values.length==0){
			return "1=1";
		}
		
		StringBuffer sql = new StringBuffer("(");
		for (Object id : values) {
			if(id instanceof String){
				sql.append(param).append("='").append(id).append("'");
			}else{
				sql.append(param).append("=").append(id);
			}
			sql.append(" or ");
		}
		
		return sql.substring(0,sql.length()-3)+")";
	}
	
	/**
	 * 是否有效的uuid验证，也可作md5 32位验证
	 * @param uuid 32位uuid
	 * @return true(有效)/false(无效) 
	 */
	public static boolean validUUID(String uuid){
		if(isEmpty(uuid)){
			return false;
		}
		return (32==uuid.length());
	}
	
	/**
	 * 取得一级域名
	 * @param url url地址(http://wwww.iadmob.com/new.jsp)
	 * @return 返回 http://www.iadmob.com
	 */
	public static String getURLBaseDomain(String url){
		if(url==null){
			return "";
		}
		int end=url.length();
		if(url.indexOf('/',8)!=-1){
			if(url.matches("https?://.*")){
				end=url.indexOf('/',8);
			}else{
				end=url.indexOf('/');
			}
		}
		return url.substring(0,end);
	}
	
	/**
	 * 超过指定长度的字符串用指定值替换
	 * <p>
	 * Examples:
     * <blockquote><pre>
	 * strReplace("aaaaa",4,"...") returns "aaaa..."
	 * strReplace("aaa",4,"...") returns "aaa"
	 * strReplace("aaa",3,"...") returns "aaa"
     * </pre></blockquote>
	 * @param str 原始值
	 * @param pos 位数
	 * @param replaceStr 替换值
	 * @see {@link StringUtil#strReplace(String, int, String, boolean)}
	 * @return 如果str为空返回空否则,返回str超出pos后用replaceStr替换后的值
	 */
	public static String strReplace(String str,int pos,String replaceStr){
		return strReplace(str, pos, replaceStr,false);
	}
	/**
	 * 超过指定长度的字符串用指定值替换,如果incChinese为true算2个长度
	 * <p>
	 * Examples:
	 * <blockquote><pre>
	 * strReplace("aaaaa",4,"...") returns "aaaa..."
	 * strReplace("aaa",4,"...") returns "aaa"
	 * strReplace("aaa",3,"...") returns "aaa"
	 * </pre></blockquote>
	 * @param str 原始值
	 * @param pos 位数
	 * @param replaceStr 替换值
	 * @param incChinese 是否将中文作为3个长度计算
	 * @return 如果str为空返回空否则,返回str超出pos后用replaceStr替换后的值,如果str.length大于pos取str.length个数
	 */
	public static String strReplace(String str,int pos,String replaceStr,boolean incChinese){
		if(isEmpty(str)){
			return null;
		}
		
		if(incChinese){
			try
			{
				char[] chars=str.toCharArray();
				char[] results=new char[str.length()];
				int index=0;
				int count=0;
				
				//长度计算
				for(int j=0;j<str.length();j++){
					int len = (""+chars[index]).getBytes("GBK").length;
					count+=len;
				}
				
				//截取
				for(int i=0;i<pos;){
					if(str.length()<=index){
						break;
					}
					int len = (""+chars[index]).getBytes("GBK").length;
					if(len==1){
						i++;
					}
					if(len==2){
						i+=2;
					}
					results[index]=chars[index];
					index++;
				}
				if(count>pos){
					return new String(results).trim()+replaceStr;
				}
				return new String(results).trim();
			}catch (UnsupportedEncodingException e){return null;}
		}else{
			if(str.length()>pos){
				return str=str.substring(0,pos)+replaceStr;
			}
			return str;
		}
	}
	
	/**
	 * 查找key在serach第一次出现的索引
	 * @param serach 查找内容
	 * @param key 要查找的key
	 * @return -1 或者索引
	 */
	public static int search(String[] serach,String key)
	{
		int index=-1;
		if(serach==null || key==null)
		{
			return index;
		}
		
		for(int i=0;i<serach.length;i++)
		{
			if(serach[i].equals(key))
			{
				index=i;
				break;
			}
		}
		return index;
	}
	
	
	/**
	 * 两个字符串比较是否相同
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean equals(String s1,String s2){
		if((s1==null && s2==null)||(s1!=null && s1.equals(s2))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据IMSI获取运营商
	 * @param imsi SIM卡号
	 * @return CMCC(移动)/UNICOM(联通)/TELECOM(电信)/OTHER(未知)
	 */
	public static String getOperator(String imsi)
	{
		if (StringUtil.isEmpty(imsi))
		{
			return "OTHER";
		}

		if ((imsi.startsWith("46000")) || 
			(imsi.startsWith("46002")) || 
			(imsi.startsWith("46007")))
		{
			return "CMCC";
		}
		else if (imsi.startsWith("46001"))
		{
			return "UNICOM";
		}
		else if (imsi.startsWith("46003"))
		{
			return "TELECOM";
		}
		return "OTHER";
	}
	
	
	/**
	 * url特殊字符编码
	 * @param name
	 * @return
	 */
	public static String urlEncodeUTF8(String name){
		if(isEmpty(name)) return "";
		
		try {
			name = URLEncoder.encode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
		
		return name;
		
	}
	
	/**
	 * 字符串格式化处理去掉标点
	 * @param speak
	 * @return
	 */
	public static String formatSpeakString(String speak) {
		if(speak!=null&&speak.length()>0)
		{
			String []replace = {",","，",".","。","!","！","?","？",":","：","'","‘","’","\"","“","”",";","；"};
			speak = speak.trim();
			String str;
			for(int i=0;i<replace.length;i++) {
				str = replace[i];
				speak = speak.replace(str, "");
			}
		}
		return speak;
	}
	
	/**
	 * 给一个最小和最大值，随机取一个
	 * @param min
	 * @param max
	 * @return
	 */
	public static long rondomLong(long min,long max)
	{
		return Math.round(Math.random()*(max-min)+min);
	}
	

	
	/**
	 * 打印数组{"aaa","bbb"}
	 * @param array 需要打印的数组
	 * @return aaa,bbb,ccc
	 */
	public static String arrayPrint(Object[] array)
	{
		if(array==null || array.length==0)
		{
			return "";
		}
		StringBuilder sb=new StringBuilder();
		for (Object obj : array)
		{
			sb.append(obj).append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	
	public static boolean contains(String str,String subStr){
		if(StringUtil.isEmpty(str) || StringUtil.isEmpty(subStr)){
			return false;
		}else{
			return str.contains(subStr);
		}
	}
	
	
	/**
	 * 获取目标对象的toString
	 * 
	 * @param object
	 * @return
	 */
	public static String toString(Object object) {
		if (object == null) {
			Logger logger = LoggerFactory.getLogger(StringUtil.class);
			logger.warn("目标对象为空：object[" + object + "]");
			return null;
		}
		return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
	/**
	 * 截取字符串 如果超过指定长度则截取
	 * @param source
	 * @param length
	 * @return
	 */
	public static String cutString(String source,int length){
		if(!StringUtil.isEmpty(source) && source.length()>length){
			return source.substring(0, length);
		}else{
			return source;
		}
		
	}

}
