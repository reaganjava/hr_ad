package com.dotoyo.buildjob.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

/**
 * Tool class of String
 *
 * @author wisdy.xiao
 *
 */
public final class StringUtil {
	/**
	 * Enforce Singleton pattern.
	 */
	private StringUtil() {
	}

	private final static class helper {
		private static StringUtil util;

		private static StringUtil fetchPojo() {
			if (util == null) {
				util = new StringUtil();
			}
			return util;
		}
	}

	public String sNullObj(Object obj) {
		return obj == null ? null : obj.toString().trim();
	}
	//天数操作
    public Date Ds(int days) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        Date cc = calendar.getTime();
        return cc;
    }
    //月数操作
    public static String MonthAdd(int days) {
	    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM");
	    Calendar calendar = Calendar.getInstance();
	    int day = calendar.get(Calendar.MONTH);
	    calendar.set(Calendar.MONTH, day + days);
	    Date cc = calendar.getTime();
	    return form.format(cc);
    }
	/**
	 * Convert price to a number which have two digit.
	 *
	 * @param price
	 * @return
	 */
	public String convertPrice(String price) {
		if (isDouble(price,true)) {
			DecimalFormat myformat = new DecimalFormat("#####0.00");
			price = myformat.format(Double.valueOf(price));
		}
		return price;
	}

	/**
	 * Convert sum
	 *
	 * @param price
	 * @return
	 */
	public String convertNum(String num, int per) {
		String s = "";
		for (int i = 0; i < per; i++) {
			s = s + "0";
		}
		String aftersum = "";
		Double d = Math.abs(Double.valueOf(num));
		DecimalFormat myformat = new DecimalFormat("#####0." + s);
		aftersum = myformat.format(d);
		if (Double.valueOf(num) < 0) {
			aftersum = "-" + aftersum;
		}
		return aftersum;
	}

	/**
	 * Override method
	 *
	 * @param num
	 * @param per
	 * @return
	 */
	public String convertNum(double num, int per) {
		String s = String.valueOf(num);
		return convertNum(s, per);
	}

	/**
	 * New Instance of String Util
	 *
	 * @return
	 */
	public static StringUtil newInstance() {
		return helper.fetchPojo();
	}

	public String sNull(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public String sCsvNull(Object obj) {
		return obj == null ? "" : obj.toString().trim().replaceAll(",",
				"\uff0c");
	}

	public String sNullTrim(Object obj) {
		return sNull(obj).trim();
	}

	public String trim(Object obj) {
		return obj != null ? obj.toString().trim() : null;
	}

	/**
	 * Transfer date from DB to page
	 *
	 * @param dbDate
	 * @return
	 */
	public Date transferDateFromDToP(String dbDate) {
		if (sNullTrim(dbDate).equals(""))
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhss");
		Date da = null;
		try {
			da = format.parse(dbDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	public Boolean isDouble(String o,boolean checkNative) {
		// /^[-\+]?\d+(\.\d+)?$
		String str = o;
		if(!checkNative){
			if (o.indexOf("-") >= 0) {
				str = str.substring(1, str.length());
			}
		}
		return Pattern.matches("^\\d+(\\.\\d+)?$", str);
	}

	public Boolean isDoubleByTwoDigit(String o) {
		return Pattern.matches("^\\d+(\\.\\d{0,2})?$", o);
	}

	public Boolean isInteger(String o) {
		if (o == null)
			return false;
		return Pattern.matches("^\\d+?$", o);
	}

	public Boolean isInteger(char o) {
		return isInteger(String.valueOf(o));
	}

	public Boolean isAgreeRuleChar(String o) {

		return Pattern.matches("[a-zA-Z]", o);
	}

	public Boolean isAgreeRuleChar(char o) {

		return isAgreeRuleChar(String.valueOf(o));
	}

	/**
	 * Transfer date from page to DB
	 *
	 * @param date
	 * @return
	 */
	public String transferDateFromPToD(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		return format.format(date);
	}

	/**
	 * Fetch current time
	 *
	 * @return format yyyyMMddHHmm
	 */
	public String fetchNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		return format.format(new Date());
	}

	public String fetchNow(String prex) {
		SimpleDateFormat format = new SimpleDateFormat(prex);
		return format.format(new Date());
	}

	public String convertDate(String prex, String date) {
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat format = new SimpleDateFormat(prex);
			Date da = format1.parse(date);
			return format.format(da);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Fetch date by day
	 *
	 * @param date
	 * @return format yyyyMMdd
	 */
	public String fetchDateByDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	public String convertDate(String fromFormat, String toFormat, String date) {
		try {
			if (sNull(date).equals(""))
				return "";
			SimpleDateFormat format1 = new SimpleDateFormat(fromFormat);
			SimpleDateFormat format = new SimpleDateFormat(toFormat);
			Date da = format1.parse(date);
			return format.format(da);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Fetch date by month
	 *
	 * @param date
	 * @return format yyyyMM
	 */
	public String fetchDateByMonth(Date date) {
		if (date == null)
			return "209913";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(date);
	}

	/**
	 * Create 20 bit id
	 *
	 * @return
	 */
	public String createIdTwenty() {
		String mills = String.valueOf(System.currentTimeMillis());
		String times = fetchNow().substring(5, 12);
		return mills + times;
	}

	public String createRandomPassword(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public String createIdTwenty(int x, String id) {
		return id.substring(0, id.length() - 1) + x;
	}

	/**
	 * Create id
	 *
	 * @param prex
	 * @param i
	 * @param total
	 * @param id
	 * @return
	 */
	public String createIdTwenty(String prex, int i, int total, String id) {
		// public String getStringByDigit(int i,String prex,int total){
		String x = String.valueOf(i);
		String result = "";
		for (int j = 0; j < total - x.length(); j++) {
			result += prex + "";
		}
		return id.substring(0, id.length() - total) + result + x;
		// return result+x;
		// }
	}

	// public static void main(String[] args) {
	// StringUtil util = StringUtil.newInstance();
	// // // System.out.println(util.checkDic("/s"));
	// // String str = util.fetchStr("backup 11241125", 7);
	// // System.out.println(str);
	// //String da = "201012022207";
	// //util.convertDate("yyyy/MM/dd HH:mm", da);
	// //System.out.println(util.getStringByDigit(10115, "0", 4));
	// //System.out.println(util.fetchNow("yyMMdd"));
	// }

	/**
	 * Convert String character into double type.
	 *
	 * @param num
	 * @return
	 */
	public double convertSToD(String num) {
		if (sNull(num).equals(""))
			num = "0";
		return Double.valueOf(num);
	}

	public boolean checkDic(String co) {
		return Pattern.matches("/*", co);
	}

	/**
	 * Fetch the first day date of the mouth which has been inputed.
	 *
	 * @param date
	 * @return
	 */
	public Date fetchFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * Fetch date by year + month
	 *
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public Date fetchDateOfMonth(String year, String month) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.parse(year + "" + month);
	}

	/**
	 * Fetch first date of year you input yyyy
	 *
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public Date fetchFirstDayOfYear(String year) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(year + "0101");

	}

	/**
	 * Fetch last date of year you input yyyy
	 *
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public Date fetchLastDayOfYear(String year) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.parse(year + "1231");

	}

	/**
	 * Fetch the last day date of the mouth which has been inputed.
	 *
	 * @param date
	 * @return
	 */
	public Date fetchLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * The code can only contains English character or digit or "-"
	 *
	 * @param co
	 * @return
	 */
	public boolean checkDDT(String co) {
		return Pattern.matches("^[0-9a-zA-Z-]+?$", co);
	}

	/**
	 * Pattern of component code, The char is in front of two position of code
	 * must be \\s and + character or digit
	 *
	 * @param co
	 * @return
	 */
	public boolean checkCptCode(String co) {
		return Pattern.matches("^[a-zA-Z]{2}+[0-9a-zA-Z]+?$", co);
	}

	/**
	 * Pattern of service code, The char is in front of two position of code
	 * must be character and four digit is after it.
	 *
	 * @param co
	 * @return
	 */
	public boolean checkSrvCode(String co) {
		return Pattern.matches("^[a-zA-Z]{2}+[0-9]{4}+", co);
	}

	/**
	 * Pattern of format code, The char is in front of two position of code must
	 * be character and digit or character is after it.
	 *
	 * @param co
	 * @return
	 */
	public boolean checkFmtCode(String co) {
		return Pattern.matches("^[a-zA-Z]{2}+[0-9a-zA-Z]+?$", co);
	}

	/**
	 * Fetch max number
	 *
	 * @param db
	 * @return
	 */
	public Double fetchMax(List<Double[]> dbs) {
		if (dbs == null)
			return 0.0;
		if (dbs.isEmpty())
			return 0.0;
		double max = 0.0;
		for (Double db[] : dbs) {
			for (double d : db) {
				if (d > max) {
					max = d;
				}
			}
		}
		return max;
	}

	/**
	 * fetch previous string by digit
	 *
	 * @param str
	 * @param start
	 * @param end
	 * @return string
	 */
	public String fetchStr(String str, int start, int end) {
		if (str == null)
			return "";
		if (str.length() < end)
			return "";
		return str.substring(start, end);
	}

	/**
	 * fetch previous string by digit
	 *
	 * @param str
	 * @param start
	 * @return string
	 */
	public String fetchStr(String str, int start) {
		if (str == null)
			return "";
		if (str.length() < start)
			return "";
		return str.substring(start);
	}

	/**
	 * unpack string array
	 *
	 * @param str
	 * @param separator
	 * @return string array
	 */
	public String[] split(String str, String separator) {
		if (str == null)
			return null;
		return str.split(separator);
	}

	/**
	 * Transfer String collection to Characters
	 *
	 * @param collection
	 * @return
	 */
	public String convertCollectionToStr(Collection<String> collection) {
		if (collection == null)
			return "";
		String sReturn = "";
		for (Object o : collection) {
			sReturn = sReturn + sNull(o).toString() + ",";
		}
		int lastIndex = sReturn.lastIndexOf(",");
		if (lastIndex > -1) {
			sReturn = sReturn.substring(0, lastIndex);
		}
		return "[" + sReturn + "]";
	}

	/**
	 * Fetch number 00001,0010
	 *
	 * @param i
	 * @param prex
	 * @param total
	 * @return
	 */
	public String getStringByDigit(int i, String prex, int total)
			throws Exception {
		String x = String.valueOf(i);
		String result = "";
		if (x.length() > total)
			throw new Exception("largest limit!");
		for (int j = 0; j < total - x.length(); j++) {
			result += prex + "";
		}
		return result + x;
	}

	public String getGbkStr(String str) {
		try {
			String str1 = new String(str.getBytes("GBK"), "iso-8859-1");
			return str1;
		} catch (Exception e) {
			return "error" + e.getMessage();
		}

	}

	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String ISO8859_1 = "ISO8859_1";

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	public static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/**
	 * 将字符编码转换成US-ASCII码
	 */
	public String toASCII(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, US_ASCII);
	}

	/**
	 * 将字符编码转换成ISO-8859-1码
	 */
	public String toISO_8859_1(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, ISO_8859_1);
	}

	/**
	 * 将字符编码转换成UTF-8码
	 */
	public String toUTF_8(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_8);
	}

	/**
	 * 将字符编码转换成UTF-16BE码
	 */
	public String toUTF_16BE(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16BE);
	}

	/**
	 * 将字符编码转换成UTF-16LE码
	 */
	public String toUTF_16LE(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16LE);
	}

	/**
	 * 将字符编码转换成UTF-16码
	 */
	public String toUTF_16(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, UTF_16);
	}

	/**
	 * 将字符编码转换成GBK码
	 */
	public String toGBK(String str) throws UnsupportedEncodingException {
		return this.changeCharset(str, GBK);
	}

	/**
	 * 字符串编码转换的实现方法
	 *
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String changeCharset(String str, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * 字符串编码转换的实现方法
	 *
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	public String GBKToISO(String str) throws Exception {
		// new String(fileName.getBytes("GBK"),"ISO8859_1")
		return changeCharset(str, GBK, ISO_8859_1);
	}

	public String UTF8ToGBK(String str) throws Exception {
		// new String(fileName.getBytes("GBK"),"ISO8859_1")
		return changeCharset(str, UTF_8, GBK);
	}

	public int returnChineseCharTotal(String str) {
		char[] cs = str.toCharArray();
		int k = 0;
		for (int i = 0; i < cs.length; i++) {
			if (Pattern.matches("[^\\x00-\\xff]", String.valueOf(cs[i]))) {
				k++;
			}
		}
		return k;
	}

	public int returnCharLen(String str) {
		return str.replaceAll("[^\\x00-\\xff]", "**").length();
	}

	public int returnByteLen(String str) {
		return str.getBytes().length;
	}

	public static String returnRealCharByLen(String str, int len) {
		byte[] bys = str.getBytes();
		String s = "";
		byte tmp[] = new byte[3];// 存储汉字
		int j = 0;
		for (int i = 0; i < bys.length; i++) {
			if (i < len) {
				if (bys[i] < 0) {
					tmp[j] = bys[i];
					j++;
					if (j == 3) {
						s += new String(tmp);
						// 转汉字
						j = 0;
					}
				} else {
					s += String.valueOf((char) bys[i]);
				}
			}
		}
		return s;
	}

	public final boolean isImage(File file) {
		boolean flag = false;
		try {
			BufferedImage bufreader = ImageIO.read(file);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]
	 * </p>
	 *
	 * @param file
	 * @return fileType
	 * @author:[shixing_11@sina.com]
	 */
	public final String getFileType(File file) {
		String filetype = null;
		byte[] b = new byte[50];
		try {
			InputStream is = new FileInputStream(file);
			is.read(b);
			filetype = getFileTypeByStream(b);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filetype;
	}


	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileTypeByStream]
	 * </p>
	 *
	 * @param b
	 * @return fileType
	 * @author:[shixing_11@sina.com]
	 */
	public final String getFileTypeByStream(byte[] b) {
		String filetypeHex = String.valueOf(getFileHexString(b));
		Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP
				.entrySet().iterator();
		while (entryiterator.hasNext()) {
			Entry<String, String> entry = entryiterator.next();
			String fileTypeHexValue = entry.getValue();
			if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getFileHexString]
	 * </p>
	 *
	 * @param b
	 * @return fileTypeHex
	 * @author:[shixing_11@sina.com]
	 */
	public final static String getFileHexString(byte[] b) {
		StringBuilder stringBuilder = new StringBuilder();
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	private static Map<String,String> FILE_TYPE_IMG_MAP = new HashMap<String,String>();
	static {
		getAllFileType(); // 初始化文件类型信息
		FILE_TYPE_IMG_MAP.put("jpg", "");
		FILE_TYPE_IMG_MAP.put("png", "");
		FILE_TYPE_IMG_MAP.put("gif", "");
		FILE_TYPE_IMG_MAP.put("bmp", "");
		FILE_TYPE_IMG_MAP.put("tif", "");
	}

	public static Map<String,String> getImgType(){
		return FILE_TYPE_IMG_MAP;
	}
	/**
	 * Created on 2010-7-1
	 * <p>
	 * Discription:[getAllFileType,常见文件头信息]
	 * </p>
	 *
	 * @author:[shixing_11@sina.com]
	 */
	public static void getAllFileType() {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email
																	// [thorough
																	// only]
																	// (eml)
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
		FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意：word 和 excel的文件头一样
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA"); //
		FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
	}
	/**
	 * 积分计算
	 * @param oldPoint 原有积分
	 * @param increasePoint 增加的积分
	 * @return
	 */
	public Long getAddPoint(Long oldPoint,Long increasePoint){
		Long respoint;
		if((oldPoint + increasePoint) > Long.valueOf("9999999999")){
			respoint = Long.valueOf("9999999999") - oldPoint;
		}else if((oldPoint + increasePoint) < Long.valueOf("-9999999999")){
			respoint = Long.valueOf("-9999999999") - oldPoint;
		}else{
			respoint = increasePoint;
		}
		return respoint;
	}
}