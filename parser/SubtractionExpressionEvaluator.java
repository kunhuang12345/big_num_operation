package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtractionExpressionEvaluator {

    public static String num1;
    public static String num2;
    public static String num1Symbol = "+"; // 用于存储第一个数字的符号，默认为正号
    public static String num2Symbol = "+"; // 用于存储第二个数字的符号，默认为正号

    public static boolean evaluateExpression(String operation) {
        // 去除所有空格
        String normalizedOperation = operation.replaceAll("\\s+", "");
        // 正则表达式匹配两个数字，不允许减号后直接跟负号
        Pattern pattern = Pattern.compile("^(-?\\d+|\\(-?\\d+\\))(\\-)(\\d+|\\(-?\\d+\\))$");
        Matcher matcher = pattern.matcher(normalizedOperation);

        if (matcher.matches()) {
            String matchedNum1 = matcher.group(1);
            String matchedNum2 = matcher.group(3);

            // 检查并设置第一个数字的符号
            if (matchedNum1.startsWith("-") || (matchedNum1.startsWith("(") && matchedNum1.contains("-"))) {
                num1Symbol = "-";
            } else {
                num1Symbol = "+";
            }
            // 对于第二个数字，只考虑括号中的负号情况
            if (matchedNum2.startsWith("(") && matchedNum2.contains("-")) {
                num2Symbol = "-";
            } else {
                num2Symbol = "+";
            }

            // 去除数字周围的括号和符号
            num1 = matchedNum1.replaceAll("[()-]", "");
            num2 = matchedNum2.replaceAll("[()-]", "");

            return true; // 表达式符合减法格式
        }

        return false; // 表达式不符合减法格式
    }

}