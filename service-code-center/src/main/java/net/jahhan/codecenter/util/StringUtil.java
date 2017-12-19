package net.jahhan.codecenter.util;

public class StringUtil {

	/**
	 * 分割字串，一般情况下客户端要考虑把返回字符数组中每个字符串进行trim()<br>
	 * 本方法本身不会添加空格，不过也不会把前后空格删除。
	 * 
	 * @date 2003年7月28日,2003年8月3日
	 * @param toSplit
	 *            原始字符串
	 * @param delimiter
	 *            分割字符串
	 * @return 字符串数组
	 */
	public static String[] splitString(String toSplit, String delimiter) {
		if (toSplit == null)
			return new String[0];

		int arynum = 0, intIdx = 0, intIdex = 0, div_length = delimiter.length();
		if (toSplit.compareTo("") != 0) {
			if (toSplit.indexOf(delimiter) != -1) {
				intIdx = toSplit.indexOf(delimiter);
				for (int intCount = 1;; intCount++) {
					if (toSplit.indexOf(delimiter, intIdx + div_length) != -1) {
						intIdx = toSplit.indexOf(delimiter, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
				}
			} else
				arynum = 1;
		} else
			arynum = 0;

		intIdx = 0;
		intIdex = 0;
		String[] returnStr = new String[arynum];

		if (toSplit.compareTo("") != 0) {
			if (toSplit.indexOf(delimiter) != -1) {
				intIdx = (int) toSplit.indexOf(delimiter);
				returnStr[0] = (String) toSplit.substring(0, intIdx);
				for (int intCount = 1;; intCount++) {
					if (toSplit.indexOf(delimiter, intIdx + div_length) != -1) {
						intIdex = (int) toSplit.indexOf(delimiter, intIdx + div_length);
						returnStr[intCount] = (String) toSplit.substring(intIdx + div_length-1, intIdex);
						intIdx = (int) toSplit.indexOf(delimiter, intIdx + div_length);
					} else {
						returnStr[intCount] = (String) toSplit.substring(intIdx + div_length-1, toSplit.length());
						break;
					}
				}
			} else {
				returnStr[0] = (String) toSplit.substring(0, toSplit.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}
	
	/**
	 * 表字段转为java属性: ABC_DE_F->abcBeF
	 * 
	 * @param str
	 * @return
	 */
	public static String makeJavaNameStr(String str) {
		String[] strs = str.split("_");
		String namestr = "";
		if (strs.length == 1) {
			namestr = strs[0].toLowerCase();
		} else {
			namestr = strs[0].toLowerCase();
			for (int j = 1; j < strs.length; j++) {
				namestr += makeClassNameStr(strs[j].toLowerCase());
			}
		}
		return namestr;
	}

	/**
	 * 表名转化为类名:规则a_b->AB,abc_dg_ef->AbcDgEf
	 * 
	 * @param str
	 * @return
	 */
	public static String makeClassNameStr(String str) {
		String[] strs = str.split("_");
		String namestr = "";
		for (int j = 0; j < strs.length; j++) {
			namestr += toUpperCaseFirstOne(strs[j].toLowerCase());
		}
		return namestr;
	}

	/**
	 * 首字母转大小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	public static void main(String[] args) {
		int i=1;
		String aa = "用户表@Inject@Email@Service@Control";
		String[] splitString = splitString(aa,"@");
		for(String a:splitString){
			System.out.println("++++"+i+"   :  "+a);
			i++;
		}
		
	}
	
}
