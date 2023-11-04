package parser;

public class SignedNumericChecker {

    /**
     * 检查去除所有空白字符后的字符串是否以可选的'+'或'-'符号开头，且符号后面是纯数字。
     *
     * @param str 要检查的字符串
     * @return 如果字符串匹配格式，返回true；否则返回false
     */
    public static boolean isSignedNumeric(String str) {
        if (str == null) {
            return false;
        }

        // 去除所有空白字符
        str = str.replaceAll("\\s+", "");

        // 空字符串或仅符号不是有效的数字
        if (str.isEmpty() || str.equals("+") || str.equals("-")) {
            return false;
        }

        // 检查是否以可选的'+'或'-'开头，且其后均为数字
        return str.matches("[-+]?\\d+");
    }
}
