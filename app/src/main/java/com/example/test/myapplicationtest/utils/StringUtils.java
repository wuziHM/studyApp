package com.example.test.myapplicationtest.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import org.kobjects.base64.Base64;
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param value 待判断的字符串
     * @return 如果是空指针或空白字符串，返回true，否则返回false
     */
    public static boolean isBlank(CharSequence value) {
        return value == null || value.length() == 0;
    }

    /**
     * 判断字符串是否为空
     * @param value 待判断的字符串
     * @return 如果是空指针或空白字符串，返回true，否则返回false
     */
    public static boolean isBlank(String value) {
        return value == null || value.equals("");
    }

    /**
     * 判断是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(null == str) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return (object == null ? "" : object.toString());
    }

    /**
     * 获取Base64的编码
     * @param file
     * @return
     */
    public static String getEncodeString(File file) {
        String result = "";
        FileInputStream fin = null;
        ByteArrayOutputStream output = null;
        try {
            byte[] buffer = new byte[1024];
            fin = new FileInputStream(file);
            output = new ByteArrayOutputStream();

            int bytesread = fin.read(buffer, 0, buffer.length);
            while (bytesread > 0) {
                output.write(buffer);
                bytesread = fin.read(buffer, 0, buffer.length);
            }
            result = Base64.encode(output.toByteArray());
            fin.close();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
