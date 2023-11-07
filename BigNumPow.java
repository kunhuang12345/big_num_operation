import parser.MultiplicationExpressionEvaluator;
import parser.PowExpressionEvaluator;
import parser.SignedNumericChecker;

import java.util.Scanner;

public class BigNumPow {

    public static String pow() {
        Scanner scanner = new Scanner(System.in);

        String num1 = scanner.nextLine();// 第一个大数，或者一个加式

        String num1Symbol = ""; // 符号
        String num2Symbol = "";

        // 如果输入的是一个加式
        String num2; // 第二个大数
        num1 = num1.replace(" ", "");
        // 判断是否为运算式
        if (num1.contains("^")) {
            boolean evaluated = PowExpressionEvaluator.evaluateExpression(num1);
            if (!evaluated) {
                return "运算式错误";
            }
            num1 = PowExpressionEvaluator.num1;
            num2 = PowExpressionEvaluator.num2;
            num1Symbol = PowExpressionEvaluator.num1Symbol;
            num2Symbol = PowExpressionEvaluator.num2Symbol;
        } else if (!SignedNumericChecker.isSignedNumeric(num1)) { // 判断数字是否合法
            return "运算式错误！";
        }  else {
            num2 = scanner.nextLine();
            num2 = num2.replace(" ", "");
            if (!SignedNumericChecker.isSignedNumeric(num2) || num2.startsWith("-")) {
                return "运算式错误！";
            }
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
            PowExpressionEvaluator.num1 = num1;
            PowExpressionEvaluator.num2 = num2;
            PowExpressionEvaluator.num1Symbol = num1Symbol;
            PowExpressionEvaluator.num2Symbol = num2Symbol;
        }

        if (num1Symbol.equals("-")) {
            return "-" + powHandle(num1, num2);
        }

        if (num1Symbol.equals("+")) {
            return powHandle(num1, num2);
        }
        return "运算式错误";
    }

    public static String powHandle(String num1, String num2) {
        String result = "1";
        while (Integer.parseInt(num2) > 0) {
            result = BigNumMultiply.multiplyHandle(result, num1);
            num2 = BigNumSub.subHandle(num2, "1", true);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(pow());
    }
}
