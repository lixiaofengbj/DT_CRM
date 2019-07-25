package com.dt.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.common.bean.ResponseBean;
import com.dt.common.constant.ErrorEnum;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {
    private static char md5Chars[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        return false;
    }

    public static boolean isNotNull(Object object) {
        if (object == null)
            return false;
        return true;
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    public static String getRandowUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim()))
            return true;
        return false;
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str.trim()))
            return true;
        return false;
    }

    public static String formatDate(Date data, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(data);
    }

    /**
     * 解析命令字符串
     */
    public static Map<String, String> parseCommand(String command) {
        Map<String, String> cmdMap = new HashMap<String, String>();
        String[] items = command.split("&");
        for (String item : items) {
            int splitIndex = item.indexOf("=");
            String key = item.substring(0, splitIndex);
            String value = item.substring(splitIndex + 1);
            cmdMap.put(key, value);
        }
        return cmdMap;
    }

    /**
     * 四舍五入
     */
    public static float getRound(float num, int digit) {
        BigDecimal b = new BigDecimal(num);
        float f1 = b.setScale(digit, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }

    /**
     * 获取当前时间戳
     */
    public static long getCurrentMillisecond() {
        return new Date().getTime();
    }

    /**
     * 按指定格式解析日期
     */
    public static Timestamp parseDate(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return new Timestamp(dateFormat.parse(date).getTime());
    }

    /**
     * 获取本机ip
     */
    public static String getLocalIp() {
        try {
            if (isWindowsOS()) {
                return InetAddress.getLocalHost().getHostAddress();
            } else {
                return getLinuxLocalIp();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeBase64(String str) {
        Base64 base64 = new Base64();
        try {
            return base64.encodeAsString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeBase64(String str) {
        Base64 base64 = new Base64();
        try {
            return new String(base64.decode(str.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeBase32(String str) {
        Base32 base32 = new Base32();
        try {
            return new String(base32.decode(str.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encodeBase32(String str) {
        Base32 base32 = new Base32();
        try {
            return base32.encodeAsString(str.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String toHexString(byte[] b) {
        char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String Bit32(String SourceString) {
        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(SourceString.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String Bit16(String SourceString) {
        try {
            return Bit32(SourceString).substring(8, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getShortSide(String res) {
        String[] resArr = res.split("x");
        int width = Integer.parseInt(resArr[0]);
        int height = Integer.parseInt(resArr[1]);

        if (width > height)
            return height;

        return width;
    }

    /**
     * 获取短边长度
     */
    public static int getShortSide(int width, int height) {
        if (width > height)
            return height;

        return width;
    }

    /**
     * md5加密
     */
    public static String md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes("UTF-8"));
        byte digest[] = md5.digest();
        char chars[] = toHexChars(digest);
        return new String(chars);
    }

    private static char[] toHexChars(byte digest[]) {
        char chars[] = new char[digest.length * 2];
        int i = 0;
        byte abyte0[] = digest;
        int j = abyte0.length;
        for (int k = 0; k < j; k++) {
            byte b = abyte0[k];
            char c0 = md5Chars[(b & 0xf0) >> 4];
            chars[i++] = c0;
            char c1 = md5Chars[b & 0xf];
            chars[i++] = c1;
        }

        return chars;
    }


    /**
     * 判断操作系统是否是Windows
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取本地Host名称
     */
    public static String getLocalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    /**
     * 获取Linux下的IP地址
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            ip = ipaddress;
                        }
                    }
                }
            }
        }

        return ip;
    }


    /**
     * 文件大小格式化
     *
     * @param bSize b单位
     * @return
     */
    public static String sizeFormat(long bSize) {
        String size = "";
        float kbSize = bSize / 1024.0f;
        if (kbSize >= 1.0f) {
            float mbSize = kbSize / 1024.0f;
            if (mbSize >= 1.0f) {
                float gbSize = mbSize / 1024.0f;
                if (gbSize >= 1.0f) {
                    size = getRound(gbSize, 2) + "G";
                } else {
                    size = getRound(mbSize, 2) + "M";
                }
            } else {
                size = getRound(kbSize, 2) + "K";
            }
        } else {
            size = getRound(bSize, 2) + "B";
        }
        return size;
    }

    /**
     * 相比返回最大值
     *
     * @param width
     * @param height
     * @return
     */
    public static int max(int width, int height) {
        if (width > height)
            return width;
        return height;
    }

    /**
     * 将秒转换成固定格式时分秒输出
     */

    public static String secondTimeFormat(long second, String separator) {
        int secondCardinal = 1;
        int minuteCardinal = secondCardinal * 60;
        int hourCardinal = minuteCardinal * 60;

        String hourStr = null;
        String minuteStr = null;
        String secondStr = null;

        long hour = 0;
        long minute = 0;
        String timeStr = null;

        if ((hour = second / hourCardinal) > 0)
            second = second - hourCardinal * hour;
        if ((minute = second / minuteCardinal) > 0)
            second = second - minuteCardinal * minute;

        hourStr = (String.valueOf(hour).length() == 1) ? "0" + hour : hour + "";
        minuteStr = (String.valueOf(minute).length() == 1) ? "0" + minute : minute + "";
        secondStr = (String.valueOf(second).length() == 1) ? "0" + second : second + "";
        return hourStr + separator + minuteStr + separator + secondStr;
    }

    /**
     * 将固定格式时分秒输出转换成秒
     */
    public static long timeStrToSecond(String timeStr, String separator) {
        int secondCardinal = 1;
        int minuteCardinal = secondCardinal * 60;
        int hourCardinal = minuteCardinal * 60;

        String[] timeArr = timeStr.split(separator);
        long hour = Long.parseLong(timeArr[0]);
        long minute = Long.parseLong(timeArr[1]);
        long second = Long.parseLong(timeArr[2]);
        return hour * hourCardinal + minute * minuteCardinal + second;
    }

    /**
     * 获取异常日志
     */
    public static String getExceptioniInformation(Throwable ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception e) {
            return null;
        }
        return ret;
    }

    /**
     * 将对象转成map形式
     */
    public static Map objectToMap(Object o) {
        return (Map) JSONObject.toJSON(o);
    }


    /**
     * 判断当前json数组是否为空
     */
    public static boolean isEmpty(JSONArray jsonArray) {
        if (isNotNull(jsonArray) && !jsonArray.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 返回当前时间,格式HH:mm:ss new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
     *
     * @return String 当前时间
     */
    public static String getCurrentDate(String formatStr) {
        String time = null;
        try {
            DateFormat myformat = new SimpleDateFormat(formatStr);
            time = myformat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean isRequestSuccess(ResponseBean responseBean) {
        if (ErrorEnum.SUCCESS.getErrorCode() == responseBean.getErrorCode()) {
            return true;
        }
        return false;
    }

    public static boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestType)) {
            return true;
        }
        return false;
    }

    /**
     * 将字符串匹配替换成占位符形式
     *
     * @param str
     * @param separator
     * @return
     */
    public static String format(String str, String regex, String separator) {
        String temp = "";
        String[] pathArr = null;
        if (str.startsWith(separator)) {
            temp = separator;
            pathArr = str.replaceFirst(separator, "").split(separator);
        } else {
            pathArr = str.split(separator);
        }
        int perchIndex = 0;
        String path = null;
        for (int i = 0; i < pathArr.length; i++) {
            path = pathArr[i];
            if (path.matches("\\d+") || path.indexOf("%2C") >= 0 || path.indexOf(",") >= 0) {
                //path = path.replaceFirst("(\\d+)", String.format("%s%d%s", "{", perchIndex, "}"));
                path = "{" + perchIndex + "}";
                perchIndex++;
            }
            temp += path;
            if (i != pathArr.length - 1) {
                temp += separator;
            }
        }
        return temp;
    }

    public static <T> T toObject(Object o, Class<T> tClass) {
        return JSONObject.parseObject(JSONObject.toJSONString(o), tClass);
    }

    /**
     * 判断json中是否有该key的json对象，如果没有将该key添加到json中，并返回key代表的json對象
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        JSONObject obj = jsonObject.getJSONObject(key);
        if (CommonUtils.isNull(obj)) {
            obj = new JSONObject();
            jsonObject.put(key, obj);
        }
        return obj;
    }

    /**
     * 判断jsonArr中是否有该key的json对象，如果没有将该key添加到json中，并返回key代表的jsonArr對象
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        JSONArray obj = jsonObject.getJSONArray(key);
        if (CommonUtils.isNull(obj)) {
            obj = new JSONArray();
            jsonObject.put(key, obj);
        }
        return obj;
    }

    public static void returnError(HttpServletResponse response, ErrorEnum accessDenied) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        ErrorEnum errorEnum = ErrorEnum.ERROR;
        ResponseBean responseBean = new ResponseBean(errorEnum.getErrorCode(), errorEnum.getErrorMessage());
        writer.print(JSONObject.toJSONString(responseBean));
    }

    /**
     * 判断是否是网络地址
     */
    public static boolean isHttpUrl(String url) {
        if (isNotEmpty(url)) {
            if (url.startsWith("http://") || url.startsWith("https://")) {
                return true;
            }
        }
        return false;
    }

    public static Timestamp getTimestamp(String timeStr, String fromPattern) {
        Timestamp dateTime = null;
        try {
            SimpleDateFormat formatter = null;
            if (isEmpty(fromPattern)) {
                formatter = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                formatter = new SimpleDateFormat(fromPattern);
            }
            Date day = formatter.parse(timeStr);
            dateTime = new Timestamp(day.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dateTime;
    }
}
