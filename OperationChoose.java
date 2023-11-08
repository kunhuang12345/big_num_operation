import parser.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class OperationChoose {

    public static int chooseNum = 0;
    public static void operation() {
        System.out.println("请选择运算类型:\n\t\t1.大整数加法运算\n\t\t2.大整数减法运算\n\t\t3.大整数除法运算\n\t\t4.大整数乘法运算\n\t\t5.大整数幂运算\n\t\t6.大整数平方根运算\n\t\t7.退出运算");
        Scanner scanner = new Scanner(System.in);
        chooseNum = scanner.nextInt(); // 操作数
        if (chooseNum != 6) {
            System.out.print("请输入运算式或者两个运算数：");
        }
        String num1 = "";
        String num2 = "";
        String num1Symbol = "";
        String num2Symbol = "";
        String result = "";
        String sign = "";
        if (chooseNum == 1) {
            result = BigNumAdd.add();
            sign = "+";
            num1 = AdditionExpressionEvaluator.num1;
            num2 = AdditionExpressionEvaluator.num2;
            num1Symbol = AdditionExpressionEvaluator.num1Symbol;
            num2Symbol = AdditionExpressionEvaluator.num2Symbol;


        } else if (chooseNum == 2) {
            result = BigNumSub.sub();
            sign = "-";
            num1 = SubtractionExpressionEvaluator.num1;
            num2 = SubtractionExpressionEvaluator.num2;
            num1Symbol = SubtractionExpressionEvaluator.num1Symbol;
            num2Symbol = SubtractionExpressionEvaluator.num2Symbol;


        } else if (chooseNum == 3) {
            result = BigNumDivision.division();
            sign = "/";
            num1 = DivisionExpressionEvaluator.num1;
            num2 = DivisionExpressionEvaluator.num2;
            num1Symbol = DivisionExpressionEvaluator.num1Symbol;
            num2Symbol = DivisionExpressionEvaluator.num2Symbol;


        } else if (chooseNum == 4) {
            result = BigNumMultiply.multiply();
            sign = "*";
            num1 = MultiplicationExpressionEvaluator.num1;
            num2 = MultiplicationExpressionEvaluator.num2;
            num1Symbol = MultiplicationExpressionEvaluator.num1Symbol;
            num2Symbol = MultiplicationExpressionEvaluator.num2Symbol;

        } else if (chooseNum == 5) {
            result = BigNumPow.pow();
            sign = "^";
            num1 = PowExpressionEvaluator.num1;
            num2 = PowExpressionEvaluator.num2;
            num1Symbol = PowExpressionEvaluator.num1Symbol;
            num2Symbol = PowExpressionEvaluator.num2Symbol;
        } else if (chooseNum == 6) {
            System.out.println("请输入需要开平方根的数字：");
            result = BigNumSqrt.sqrt();
            sign = "^";
            num1 = BigNumSqrt.num1;
        } else if (chooseNum == 7) {
            return;
        } else {
            System.out.println("错误的操作类型！");
        }

        if (num1 == null) {
            System.out.println(result);
            return;
        }

        if (chooseNum != 5 && chooseNum != 6) {
            if ("".equals(sign)) return;

            if (num1Symbol.equals("-")) {
                num1 = num1Symbol + num1;
            }
            if (num2Symbol.equals("-")) {
                num2 = "(" + num2Symbol + num2 + ")";
            }
            System.out.println(num1 + " " + sign + " " + num2 + " = " + result);
        } else if (chooseNum == 5) {
            if ("".equals(sign)) return;
            if (num1Symbol.equals("-")) {
                num1 = "(" + num1Symbol + num1 + ")";
            }
            System.out.println(num1 + "^" + num2 + "=" + result);
        } else {
            if ("".equals(sign)) return;
            num2 = "2";
            num2Symbol = "-";
            num2 = "(" + num2Symbol + num2 + ")";
            System.out.println(num1 + "^" + num2 + "=" + result);
        }

        // 保存原始的System.out
        PrintStream originalOut = System.out;
        // 将System.out重定向到文件
        try (PrintStream fileOut = new PrintStream(new FileOutputStream("C:\\Users\\kunhuang\\Desktop\\bigNum.txt", true))) {
            System.setOut(fileOut);
            System.out.println(num1 + " " + sign + " " + num2 + " = " + result);;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 将System.out重定向回控制台
        System.setOut(originalOut);
    }

    public static void main(String[] args) {
        while(chooseNum != 5) {
            operation();
        }
    }
}
