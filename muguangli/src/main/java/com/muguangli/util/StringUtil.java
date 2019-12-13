package com.muguangli.util;

//import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.CharUtils;
import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc

/**
 * The Class StringUtil.
 *
 * @version 1.1
 * @module 系统模块
 * @func 系统基础类
 */

public class StringUtil {
    
    private static final Logger logger = Logger.getLogger(StringUtil.class);
    
    /**
     * <li>判断字符串是否为空值</li> <li>NULL、空格均认为空值</li>.
     *
     * @param value
     *            the value
     * @return true, if checks if is empty
     */
    public static boolean isEmpty(String value) {
        return null == value || "".equals(value.trim());
    }

    public static boolean isNotEmpty(String value) {
        return null != value && !"".equals(value.trim());
    }

    /**
     * 重复字符串 如 repeatString("a",3) ==> "aaa".
     *
     * @param src
     *            the src
     * @param repeats
     *            the repeats
     * @return the string
     * @author uke
     */
    public static String repeatString(String src, int repeats) {
        if (null == src || repeats <= 0) {
            return src;
        } else {
            StringBuffer bf = new StringBuffer();
            for (int i = 0; i < repeats; i++) {
                bf.append(src);
            }
            return bf.toString();
        }
    }

    /**
     * 左对齐字符串 * lpadString("X",3); ==>" X" *.
     *
     * @param src
     *            the src
     * @param length
     *            the length
     * @return the string
     */
    public static String lpadString(String src, int length) {
        return lpadString(src, length, " ");
    }

    /**
     * 以指定字符串填补空位，左对齐字符串 * lpadString("X",3,"0"); ==>"00X".
     *
     * @param src
     *            the src
     * @param length
     *            the length
     * @param single
     *            the single
     * @return the string
     */
    public static String lpadString(String src, int length, String single) {
        if (src == null || length <= src.getBytes().length) {
            return src;
        } else {
            return repeatString(single, length - src.getBytes().length) + src;
        }
    }

    /**
     * 右对齐字符串 * rpadString("9",3)==>"9 ".
     *
     * @param src
     *            the src
     * @param byteLength
     *            the byte length
     * @return the string
     */
    public static String rpadString(String src, int byteLength) {
        return rpadString(src, byteLength, " ");
    }

    /**
     * 以指定字符串填补空位，右对齐字符串 rpadString("9",3,"0")==>"900".
     *
     * @param src
     *            the src
     * @param single
     *            the single
     * @param length
     *            the length
     * @return the string
     */
    public static String rpadString(String src, int length, String single) {
        if (src == null || length <= src.getBytes().length) {
            return src;
        } else {
            return src + repeatString(single, length - src.getBytes().length);
        }
    }

    /**
     * 去除,分隔符，用于金额数值去格式化.
     *
     * @param value
     *            the value
     * @return the string
     */
    public static String decimal(String value) {
        if (null == value || "".equals(value.trim())) {
            return "0";
        } else {
            return value.replaceAll(",", "");
        }
    }

    /**
     * 在数组中查找字符串.
     *
     * @param params
     *            the params
     * @param name
     *            the name
     * @param ignoreCase
     *            the ignore case
     * @return the int
     */
    public static int indexOf(String[] params, String name, boolean ignoreCase) {
        if (params == null) {
            return -1;
        }
        for (int i = 0, j = params.length; i < j; i++) {
            if (ignoreCase && params[i].equalsIgnoreCase(name)) {
                return i;
            } else if (params[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将字符转数组.
     *
     * @param str
     *            the str
     * @return the string[]
     */
    public static String[] toArr(String str) {
        String inStr = str;
        String a[] = null;
        try {
            if (null != inStr) {
                StringTokenizer st = new StringTokenizer(inStr, ",");
                if (st.countTokens() > 0) {
                    a = new String[st.countTokens()];
                    int i = 0;
                    while (st.hasMoreTokens()) {
                        a[i++] = st.nextToken();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 将字符转数组.
     *
     * @param str
     *            the str
     * @param splitChar
     *            the split char
     * @return the string[]
     */
    public static String[] toArr(String str, String splitChar) {
        String inStr = str;
        String a[] = null;
        try {
            if (null != inStr) {
                StringTokenizer st = new StringTokenizer(inStr, splitChar);
                if (st.countTokens() > 0) {
                    a = new String[st.countTokens()];
                    int i = 0;
                    while (st.hasMoreTokens()) {
                        a[i++] = st.nextToken();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 产生编号 默认是4位
     *
     * @param str
     * @return
     */
    public static String toFourNumber(Integer arg0) {
        StringBuffer buffer = new StringBuffer();
        int num = 3;
        if (null != arg0) {
            String str = String.valueOf(arg0);
            if (num > str.length()) {
                buffer.append("000");
                buffer.replace(num - str.length(), num, str);
            } else {
                buffer.append(str);
            }
        } else {
            buffer.append("001");
        }
        return buffer.toString();
    }

    /**
     * 字符串数组包装成字符串.
     *
     * @param ary
     *            the ary
     * @param s
     *            包装符号如 ' 或 "
     * @return the string
     */
    public static String toStr(String[] ary, String s) {
        if (ary == null || ary.length < 1) {
            return "";
        }
        StringBuffer bf = new StringBuffer();
        bf.append(s);
        bf.append(ary[0]);
        for (int i = 1; i < ary.length; i++) {
            bf.append(s).append(",").append(s);
            bf.append(ary[i]);
        }
        bf.append(s);
        return bf.toString();
    }

    /**
     * 設置MESSAGE中的變量{0}...{N}
     *
     * @param msg
     *            the msg
     * @param vars
     *            the vars
     * @return the message
     */
    public static String getMessage(String msg, String[] vars) {
        for (int i = 0; i < vars.length; i++) {
            msg = msg.replaceAll("\\{" + i + "\\}", vars[i]);
        }
        return msg;
    }

    /**
     * Gets the message.
     *
     * @param msg
     *            the msg
     * @param var
     *            the var
     * @return the message
     */
    public static String getMessage(String msg, String var) {
        return getMessage(msg, new String[] { var });
    }

    /**
     * Gets the message.
     *
     * @param msg
     *            the msg
     * @param var
     *            the var
     * @param var2
     *            the var2
     * @return the message
     */
    public static String getMessage(String msg, String var, String var2) {
        return getMessage(msg, new String[] { var, var2 });
    }

    /**
     * 从Map中取String类型值.
     *
     * @param map
     *            the map
     * @param key
     *            the key
     * @return the map value
     */
    public static Object getMapValue(Map<Object, Object> map, Object key) {
        if (null == map || null == key) {
            return "";
        }
        Object value = map.get(key);
        return null == value ? "" : value;
    }

    /**
     * 从Map中取Integer类型值.
     *
     * @param map
     *            the map
     * @param key
     *            the key
     * @return the map int value
     */
    public static Object getMapIntValue(Map<Object, Object> map, Object key) {
        if (null == map || null == key) {
            return new Integer(0);
        }
        Object value = map.get(key);
        return null == value ? new Integer(0) : value;
    }

    /**
     * 將key=value;key2=value2……轉換成Map.
     *
     * @param params
     *            the params
     * @return the map
     */
    public static Map<Object, Object> gerneryParams(String params) {
        Map<Object, Object> args = new HashMap<Object, Object>();
        if (!isEmpty(params)) {
            try {
                String map, key, value;
                StringTokenizer st = new StringTokenizer(params, ";");
                StringTokenizer stMap;
                while (st.hasMoreTokens()) {
                    map = st.nextToken();
                    if (isEmpty(map.trim())) {
                        break;
                    }
                    stMap = new StringTokenizer(map, "=");
                    key = stMap.hasMoreTokens() ? stMap.nextToken() : "";
                    if (isEmpty(key.trim())) {
                        continue;
                    }
                    value = stMap.hasMoreTokens() ? stMap.nextToken() : "";
                    args.put(key, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return args;
    }

    /**
     * 获取主键.
     *
     * @return the string
     */
    public static String uuid32len() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将字符数值取scale为小数.
     *
     * @param v
     *            the v
     * @param scale
     *            the scale
     * @return the string
     */
    public static String round(String v, int scale) {
        if ((v == null) || (v.equals(""))) {
            return "";
        }
        try {
            BigDecimal b = new BigDecimal(v);
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将字符数值取scale为小数.
     *
     * @param value
     *            the value
     * @param XT
     *            the xT
     * @param SJLX
     *            the sJLX
     * @return the value
     */
    public static String getValue(String value, String XT, String SJLX) {
        try {
            if (value == null) {
                return "";
            }
            return StringUtil.round(value, 2);
        } catch (Exception e) {
            logger.error("getValue error:", e);
        }
        return value;
    }

    /**
     * Tran code p.
     *
     * @param value
     *            the value
     * @return the string
     */
    public static String tranCodeP(String value) {
        String tempStr = "";
        if (value != null) {
            if (System.getProperties().toString().indexOf("tomcat") != -1) {
                try {
                    // tempStr = new String(value.getBytes("iso-8859-1"),
                    // "GBK");
                    tempStr = (null == value ? "" : value);
                } catch (Exception ex){// UnsupportedEncodingException ex
                    logger.error("tranCodeP error:", ex);
                }
            } else {
                tempStr = value;
            }
        }
        return tempStr.trim();
    }

    /**
     * 字符串替换.
     *
     * @param strSource
     *            the str source
     * @param oldStr
     *            the old str
     * @param newStr
     *            the new str
     * @return the string
     */
    public static String replace(String strSource, String oldStr, String newStr) {
        // String strDest = "";
        int intFromLen = oldStr.length();
        int intPos;
        StringBuffer strDest = new StringBuffer();
        while ((intPos = strSource.indexOf(oldStr)) != -1) {
            strDest.append(strSource.substring(0, intPos));
            strDest.append(newStr);
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest.append(strSource);
        return strDest.toString();
    }

    /**
     * utf8转码 Description :.
     *
     * @param str
     *            the str
     * @return the string
     */
    public static String utf8Decoder(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * utf8转码 Description :
     *
     * @param str
     *            String
     * @return String
     */
    public static String utf8Decoder2(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");

        } catch (IllegalArgumentException e) {
            str = str.replaceAll("%", "%25");
            String r = str;
            try {
                r = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            return r;
        } catch (UnsupportedEncodingException e2) {
            return str;
        }
    }

    /**
     * 将空串转换成空字符串.
     *
     * @param obj
     *            the obj
     * @return the string
     */
    public static String nullToSring(Object obj) {
        if (null == obj || "".equals(obj) || "null".equals(obj)) {
            return "";
        }
        return String.valueOf(obj).trim();
    }

    /**
     * 将空串转换成空字符串.
     *
     * @param obj
     *            the obj
     * @return the string
     */
    public static String charToSring(Object obj) {
        if (null == obj || "".equals(obj) || "null".equals(obj)) {
            return "";
        }
        obj = obj.toString().replaceAll("\t", "");
        obj = obj.toString().replaceAll("\r", "");
        obj = obj.toString().replaceAll("\n", "");
        obj = obj.toString().replaceAll("'", "’");
        obj = obj.toString().replaceAll("\"", "“");
        obj = obj.toString().replaceAll(";", "；");
        return String.valueOf(obj).trim();
    }

    public static String filterString(String str) {
        // for example
        String[] fileStr = { "'", "$", "=", "select" };
        String[] replaceStr = { "", "", "", "" };
        int len = fileStr.length;
        for (int i = 0; i < len; i++) {
            str = str.replaceAll(fileStr[i], replaceStr[i]);
        }
        return str;
    }

    /**
     * MD5加密
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        /*
         * MessageDigest md5=MessageDigest.getInstance("MD5"); BASE64Encoder base64en = new BASE64Encoder(); //加密后的字符串
         * String newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); return newstr;
         */
        return "";
    }

    /**
     * SHA-1加密
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderBySha1(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        /*
         * MessageDigest md5=MessageDigest.getInstance("SHA-1"); BASE64Encoder base64en = new BASE64Encoder(); //加密后的字符串
         * String newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); return newstr;
         */
        return "";
    }

    public static String trimSpaces(String IP) {// 去掉IP字符串前后所有的空格
        while (IP.startsWith(" ")) {
            IP = IP.substring(1, IP.length()).trim();
        }
        while (IP.endsWith(" ")) {
            IP = IP.substring(0, IP.length() - 1).trim();
        }
        return IP;
    }

    // 校验是否是IP
    public static boolean isIp(String IP) {// 判断是否是一个IP
        boolean b = false;
        IP = trimSpaces(IP);
        if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String s[] = IP.split("\\.");
            if (Integer.parseInt(s[0]) < 255)
                if (Integer.parseInt(s[1]) < 255)
                    if (Integer.parseInt(s[2]) < 255)
                        if (Integer.parseInt(s[3]) < 255) {
                            b = true;
                        }
        }
        return b;
    }

    // 去除字符串最后一个","
    public static String removeLastStr(String item) {
        if (StringUtil.isEmpty(item)) {
            return "";
        } else {
            String str = item.substring(0, item.length() - 1);
            return str;
        }
    }

    // 将List转换为STRING
    public static String getIdListStr(List<String> idList) {
        StringBuffer sub = new StringBuffer();
        int i = 0;
        for (String str : idList) {
            if (i > 0) {
                sub.append(",");
            }
            sub.append(str);
            i++;
        }

        return sub.toString();
    }

    // 将字符串已逗号分隔，变成LIST
    public static List<Object> getIdList(String idsstr) {
        List<Object> list = new ArrayList<Object>();

        String[] idtemp = idsstr.split(",");
        for (String id : idtemp) {
            list.add(id);
        }

        return list;
    }
    
    // 对象属性转换为字段  例如：userName to user_name  
    public static String propertyToField(String property) {  
        if (null == property) {  
            return "";  
        }  
        char[] chars = property.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (char c : chars) {  
            if (CharUtils.isAsciiAlphaUpper(c)) {  
                sb.append("_" + String.valueOf(c).toLowerCase());  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }  
  
    // 字段转换成对象属性 例如：user_name to userName 
    public static String fieldToProperty(String field) {  
        if (null == field) {  
            return "";  
        }  
        char[] chars = field.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < chars.length; i++) {  
            char c = chars[i];  
            if (c == '_') {  
                int j = i + 1;  
                if (j < chars.length) {  
                    sb.append(CharUtils.toString(chars[j]).toUpperCase());  
                    i++;  
                }  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }  
}
