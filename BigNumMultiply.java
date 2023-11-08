import parser.DivisionExpressionEvaluator;
import parser.MultiplicationExpressionEvaluator;
import parser.SignedNumericChecker;
import java.util.Scanner;

public class BigNumMultiply {
    public static String multiply() {
        Scanner scanner = new Scanner(System.in);

        String num1 = scanner.nextLine();// 第一个大数，或者一个加式

        String num1Symbol = ""; // 符号
        String num2Symbol = "";

        // 如果输入的是一个加式
        String num2; // 第二个大数
        num1 = num1.replace(" ", "");

        // 提取num和numSymbol
        if (num1.contains("*")) {
            boolean evaluate = MultiplicationExpressionEvaluator.evaluateExpression(num1); // 判断表达式

            if (!evaluate) {
                return "运算式错误！";
            }
            num1 = MultiplicationExpressionEvaluator.num1;
            num2 = MultiplicationExpressionEvaluator.num2;
            num1Symbol = MultiplicationExpressionEvaluator.num1Symbol;
            num2Symbol = MultiplicationExpressionEvaluator.num2Symbol;
        } else if (!SignedNumericChecker.isSignedNumeric(num1)) { // 判断数字是否合法
            return "运算式错误！";
        } else {
            num2 = scanner.nextLine();
            if (!SignedNumericChecker.isSignedNumeric(num2)) {
                return "运算式错误！";
            }
            num2 = num2.replace(" ", "");
            if (num1.startsWith("-")) {
                num1Symbol = "-";
                num1 = num1.substring(1);
            } else if (num1.startsWith("+")){
                num1Symbol = "+";
                num1 = num1.substring(1);
            } else {
                num1Symbol = "+";
            }
            if (num2.startsWith("-")) {
                num2Symbol = "-";
                num2 = num2.substring(1);
            } else if (num2.startsWith("+")){
                num2Symbol = "+";
                num2 = num2.substring(1);
            } else {
                num2Symbol = "+";
            }

            // 存储，方便之后的格式
            MultiplicationExpressionEvaluator.num1 = num1;
            MultiplicationExpressionEvaluator.num2 = num2;
            MultiplicationExpressionEvaluator.num1Symbol = num1Symbol;
            MultiplicationExpressionEvaluator.num2Symbol = num2Symbol;
        }

        // 运算各种除法情况
        if (num1Symbol.equals("+") && num2Symbol.equals("+")) {
            return multiplyHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("-")) {
            return multiplyHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("+")) {
            return "-" + multiplyHandle(num1, num2);
        }
        if (num1Symbol.equals("+") && num2Symbol.equals("-")) {
            return "-" + multiplyHandle(num1, num2);
        }
        return "数字输入格式错误";
    }

    public static String multiplyHandle(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String swap = num2;
            num2 = num1;
            num1 = swap;
        }

        String result = "0"; // 结果

        char[] num2CharArray = num2.toCharArray(); // 存储num2每一位
        String[] num2StringArray = new String[num2CharArray.length]; // 用来存储与每一位相乘

        // 初始化num2StringArray
        for (int i = 0; i < num2CharArray.length; i++) {
            num2StringArray[i] = num1;
        }


        for (int i = num2StringArray.length - 1; i >= 0; i--) {
            int j = i; // 补充0的个数
            StringBuilder zero = new StringBuilder(); // 0
            int count = num2CharArray[num2StringArray.length - 1 - i] - '0'; // num1相加次数
            int isZero = count;
            String add = num2StringArray[i]; // 加数
            num2StringArray[i] = "";
            while (count > 0) {
                count = count - 1;
                num2StringArray[i] = BigNumAdd.addHandle(num2StringArray[i], add);
            }

            if (isZero > 0) {
                while (j > 0) {
                    zero.append("0");
                    j--;
                }
                num2StringArray[i] = num2StringArray[i] + zero.toString();
            }
        }



        for (int i = num2StringArray.length; i > 0; i--) {
            result = BigNumAdd.addHandle(result, num2StringArray[i-1]);
        }
        return result;
    }
}
