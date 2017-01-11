package cn.obwq.util;

/**
 * Created by guoxing.zgx on 2017/1/11.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }



    public static boolean isBlank(String str) {
        int length;
        if(str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        int length;
        if(str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }



    public static boolean isNumeric(String str) {
        if(str == null) {
            return false;
        } else {
            int length = str.length();

            for(int i = 0; i < length; ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

}
