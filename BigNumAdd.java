import parser.AdditionExpressionEvaluator;
import parser.SignedNumericChecker;
import util.CompareUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BigNumAdd {


    public static String add() {
        Scanner scanner = new Scanner(System.in);

        String num1 = scanner.nextLine();// 第一个大数，或者一个加式

        String num1Symbol = ""; // 符号
        String num2Symbol = "";

        // 如果输入的是一个加式
        String num2; // 第二个大数
        num1 = num1.replace(" ", "");

        // 提取num和numSymbol
        if ((num1.contains("+") && !num1.startsWith("+")) || (num1.contains("+")) && (num1.length() - num1.replace("+", "").length()) > 1) {
            boolean evaluate = AdditionExpressionEvaluator.evaluateExpression(num1); // 判断表达式

            if (!evaluate) {
                return "运算式错误！";
            }
            num1 = AdditionExpressionEvaluator.num1;
            num2 = AdditionExpressionEvaluator.num2;
            num1Symbol = AdditionExpressionEvaluator.num1Symbol;
            num2Symbol = AdditionExpressionEvaluator.num2Symbol;
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
            AdditionExpressionEvaluator.num1 = num1;
            AdditionExpressionEvaluator.num2 = num2;
            AdditionExpressionEvaluator.num1Symbol = num1Symbol;
            AdditionExpressionEvaluator.num2Symbol = num2Symbol;
        }

        // 运算各种加法情况
        if (num1Symbol.equals("+") && num2Symbol.equals("+")) {
            return addHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("-")) {
            return "-" + addHandle(num1, num2);
        }
        if (num1Symbol.equals("-") && num2Symbol.equals("+")) {
            String swap = num1;
            num1 = num2;
            num2 = swap;

            boolean tag = (CompareUtil.compare(num1, num2) == -1); // 正负标记
            if (tag) {
                swap = num1;
                num1 = num2;
                num2 = swap;
            }
            return BigNumSub.subHandle(num1, num2, !tag);
        }
        if (num1Symbol.equals("+") && num2Symbol.equals("-")) {
            boolean tag = (CompareUtil.compare(num1, num2) == -1); // 正负标记
            if (tag) {
                String swap = num1;
                num1 = num2;
                num2 = swap;
            }
            return BigNumSub.subHandle(num1, num2, !tag);
        }
        return "输入数字格式出错";
    }

    // 处理大整数加法运算
    public static String addHandle(String num1, String num2) {
        int num1Length = num1.length(); // num1数字长度
        int num2Length = num2.length(); // num2数字长度
        Map<Integer, String> resultMap = new HashMap<>(); // 结果集


        int num1LengthQuotient = num1Length / 18; // num1中有几个长度18字符串
        int num2LengthQuotient = num2Length / 18; // num2中有几个长度18字符串

        int num1LengthRemainder = num1Length % 18; // 余数
        int num2LengthRemainder = num2Length % 18;

        int num1Tag = num1LengthRemainder;
        int num2Tag = num2LengthRemainder;

        Map<Integer, Long> num1Map = new HashMap<>();
        Map<Integer, Long> num2Map = new HashMap<>();


        if (num1LengthRemainder > 0) {
            num1Map.put(num1LengthQuotient, Long.valueOf(num1.substring(0, num1Tag)));
        }

        if (num2LengthRemainder > 0) {
            num2Map.put(num2LengthQuotient, Long.valueOf(num2.substring(0, num2Tag)));
        }

        while (num1LengthQuotient > 0) {
            num1LengthQuotient = num1LengthQuotient - 1;
            num1Map.put(num1LengthQuotient, Long.valueOf(num1.substring(num1Tag, num1Tag + 18)));
            num1Tag = num1Tag + 18;
        }

        while (num2LengthQuotient > 0) {
            num2LengthQuotient = num2LengthQuotient - 1;
            num2Map.put(num2LengthQuotient, Long.valueOf(num2.substring(num2Tag, num2Tag + 18)));
            num2Tag = num2Tag + 18;
        }


        // map中元素进行加法运算
        int count = Math.min(num1Map.size(), num2Map.size()); // 加法次数
        int carry = 0; // 进位
        int position = 0;
        while (count > 0) {
            count = count - 1;
            Long mapNum1 = num1Map.get(position); // map中num1的每个position的数字
            Long mapNum2 = num2Map.get(position);

            String result = String.valueOf(mapNum1 + mapNum2 + carry);
            carry = 0;
            if (result.length() == 19) {
                carry = 1;
                result = result.substring(1);
            }
            resultMap.put(position, result);
            position = position + 1;
        }

        // 将未运算的高未放入Map结果集
        if (num1Map.size() > num2Map.size()) {
            int sub = num1Map.size() - num2Map.size(); // 差几个size
            while (sub > 0) {
                sub = sub - 1;
                String value = String.valueOf(num1Map.get(position));
                resultMap.put(position, value);
                position = position + 1;
            }
        } else if (num1Map.size() < num2Map.size()) {
            int sub = num2Map.size() - num1Map.size(); // 差几个size
            while (sub > 0) {
                sub = sub - 1;
                String value = String.valueOf(num2Map.get(position));
                resultMap.put(position, value);
                position = position + 1;
            }
        }

        StringBuilder result = new StringBuilder();
        int tag = position - 1;
        while (position > 0) {
            String value = resultMap.get(position - 1);
            StringBuilder zero = new StringBuilder(); // 补充0
            if (value.length() < 18 && tag != position - 1) {
                int i = 18 - value.length(); // 补充0的个数
                while (i > 0) {
                    zero.append("0");
                    i--;
                }
                value = zero + value;
            }
            result.append(value);
            position = position - 1;
        }
        String resultNum = result.toString(); // 结果
        return resultNum;
    }
}
