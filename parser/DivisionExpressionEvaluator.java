package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivisionExpressionEvaluator {

    public static String num1;
    public static String num2;
    public static String num1Symbol = "+"; // 用于存储第一个数字的符号，默认为正号
    public static String num2Symbol = "+"; // 用于存储第二个数字的符号，默认为正号

    public static boolean evaluateExpression(String operation) {
        // 去除所有空格
        String normalizedOperation = operation.replaceAll("\\s+", "");
        // 正则表达式匹配两个数字，第一个数字允许有可选的加号或负号（无论是否在括号内），
        // 第二个数字如果有符号，则必须在括号内，如果没有符号，则括号可选。
        Pattern pattern = Pattern.compile("^(-?\\+?\\d+|\\(-?\\+?\\d+\\))(\\/)(\\d+|\\(\\+?-?\\d+\\))$");

        Matcher matcher = pattern.matcher(normalizedOperation);

        if (matcher.matches()) {
            String matchedNum1 = matcher.group(1);
            String matchedNum2 = matcher.group(3);

            // 提取第一个数字并确定其符号
            if (matchedNum1.startsWith("(")) {
                matchedNum1 = matchedNum1.substring(1, matchedNum1.length() - 1); // 去除括号
            }
            num1Symbol = matchedNum1.startsWith("-") ? "-" : "+";
            num1 = matchedNum1.replaceFirst("^[+-]", ""); // 移除开头的加号或负号

            // 提取第二个数字并确定其符号
            if (matchedNum2.startsWith("(")) {
                matchedNum2 = matchedNum2.substring(1, matchedNum2.length() - 1); // 去除括号
            }
            num2Symbol = matchedNum2.startsWith("-") ? "-" : "+";
            num2 = matchedNum2.replaceFirst("^[+-]", ""); // 移除开头的加号或负号

            return true; // 表达式符合除法格式
        }

        return false; // 表达式不符合除法格式
    }
}
