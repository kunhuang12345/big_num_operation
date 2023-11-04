package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdditionExpressionEvaluator {

    public static String num1;
    public static String num2;
    public static String num1Symbol = "+"; // 用于存储第一个数字的符号，默认为正号
    public static String num2Symbol = "+"; // 用于存储第二个数字的符号，默认为正号

    public static boolean evaluateExpression(String operation) {
        // 去除所有空格
        String normalizedOperation = operation.replaceAll("\\s+", "");
        // 正则表达式匹配两个数字，不允许加号后直接跟负号
        Pattern pattern = Pattern.compile("^(-?\\d+|\\(-?\\d+\\))(\\+)(?!\\+|-)(\\d+|\\(-?\\d+\\))$");
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

            return true; // 表达式符合加法格式
        }

        return false; // 表达式不符合加法格式
    }




    public static String getNum1() {
        return num1;
    }

    public static String getNum2() {
        return num2;
    }

    // 以下是添加的getter方法，以获取符号信息
    public static String getNum1Symbol() {
        return num1Symbol;
    }

    public static String getNum2Symbol() {
        return num2Symbol;
    }
//    public static void main(String[] args) {
//        System.out.println(evaluateExpression("9845616+(-545)")); // 应该为 true
//        System.out.println(evaluateExpression("9845616++545"));  // 应该为 false
//        System.out.println(evaluateExpression("-9845616+545"));
//        System.out.println(evaluateExpression("-9845616+(545)"));
//        System.out.println(evaluateExpression("-9845616+(-545)"));
//        System.out.println(evaluateExpression("(-9845616)+(-545)"));
//    }
}

