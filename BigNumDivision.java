import parser.AdditionExpressionEvaluator;
import parser.DivisionExpressionEvaluator;
import parser.SignedNumericChecker;
import parser.SubtractionExpressionEvaluator;
import util.CompareUtil;

import java.util.Scanner;

public class BigNumDivision {

    public static String division() {
        Scanner scanner = new Scanner(System.in);

        String num1 = scanner.nextLine();// 第一个大数，或者一个除式

        String num1Symbol = ""; // 符号
        String num2Symbol = "";

        // 如果输入的是一个加式
        String num2; // 第二个大数
        num1 = num1.replace(" ", "");

        // 提取num和numSymbol
        if (num1.contains("/")) {
            boolean evaluate = DivisionExpressionEvaluator.evaluateExpression(num1); // 判断表达式

            if (!evaluate) {
                return "运算式错误！";
            }
            num1 = DivisionExpressionEvaluator.num1;
            num2 = DivisionExpressionEvaluator.num2;
            num1Symbol = DivisionExpressionEvaluator.num1Symbol;
            num2Symbol = DivisionExpressionEvaluator.num2Symbol;
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
            DivisionExpressionEvaluator.num1 = num1;
            DivisionExpressionEvaluator.num2 = num2;
            DivisionExpressionEvaluator.num1Symbol = num1Symbol;
            DivisionExpressionEvaluator.num2Symbol = num2Symbol;
        }

        // 运算各种除法情况
        if (num1Symbol.equals("+") && num2Symbol.equals("+")) {
            return divisionHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("-")) {
            return divisionHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("+")) {
            return "-" + divisionHandle(num1, num2);
        }
        if (num1Symbol.equals("+") && num2Symbol.equals("-")) {
            return "-" + divisionHandle(num1, num2);
        }
        return "数字输入格式错误";

    }

    public static String divisionHandle(String num1, String num2) {
        StringBuilder remainder = new StringBuilder(); // 商
        String quotient = ""; // 余数

        if (num1.length() < num2.length()) { // TODO
            remainder = new StringBuilder("0");
            quotient = num2;
        } else {
            String remainderEvery = ""; // 商的每一位暂存处
            String num2LengthToNum1 = ""; // 从num1中取出长度为num2的String

            int start = 0;
            int end = num2.length();
            int i = 0; // 求remainderEvery
            num2LengthToNum1 = num1.substring(start, end);

            // 具体除法运算
            while(true) {
                String tempData = ""; // 临时存储属性
                // 获取remainderEvery
                i = 0;
                while (i <= 10) {
                    tempData = BigNumMultiply.multiplyHandle(num2, String.valueOf(i));
                    if (CompareUtil.compare(num2LengthToNum1, tempData) != -1) {
                        i++;
                    } else {
                        remainderEvery = String.valueOf(i - 1);
                        remainder.append(remainderEvery);
                        break;
                    }
                }
                tempData = BigNumMultiply.multiplyHandle(num2, remainderEvery);
                num2LengthToNum1 = BigNumSub.subHandle(num2LengthToNum1, tempData, true);

                int length = num2LengthToNum1.length();
                for (int j = 0; j < length; j++) {
                    if (length == 1 && num2LengthToNum1.charAt(0) == '0'){
                        num2LengthToNum1 = "";
                        break;
                    }
                    if (num2LengthToNum1.charAt(j) > '0') {
                        num2LengthToNum1 = num2LengthToNum1.substring(j);
                        break;
                    }
                    if (j == length - 1) {
                        num2LengthToNum1 = "0";
                    }
                }
                if (end == num1.length()) {
                    quotient = num2LengthToNum1;
                    break;
                }

                // 补低位
                if (num2LengthToNum1.length() < num2.length()) {
                    start = end;
                    end = num2.length() - num2LengthToNum1.length() + end;
                    if (end > num1.length()) {
                        quotient = num2LengthToNum1 + num1.substring(start);
                        break;
                    } else {
                        num2LengthToNum1 = num2LengthToNum1 + num1.substring(start, end);
                    }
                } else if (num2LengthToNum1.length() == num2.length()) {
                    start = end;
                    end = end + 1;
                    num2LengthToNum1 = num2LengthToNum1 + num1.substring(start, end);
                }
            }
        }

        String result = remainder.toString();
        // 去最高位0
        if (remainder.charAt(0) == '0' && remainder.length() > 1) {
            result = result.substring(1);
        }

        if (result.equals("")) result = "0";
        return (result + "······" + quotient);
    }

    public static void main(String[] args) {
        System.out.println(division());
    }


}
