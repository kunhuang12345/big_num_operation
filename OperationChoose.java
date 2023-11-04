import parser.AdditionExpressionEvaluator;
import parser.DivisionExpressionEvaluator;
import parser.MultiplicationExpressionEvaluator;
import parser.SubtractionExpressionEvaluator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class OperationChoose {

    public static int chooseNum = 0;
    public static void operation() {
        System.out.println("请选择运算类型:\n\t\t1.大整数加法运算\n\t\t2.大整数减法运算\n\t\t3.大整数除法运算\n\t\t4.大整数乘法运算\n\t\t5.退出运算");
        Scanner scanner = new Scanner(System.in);
        chooseNum = scanner.nextInt(); // 操作数
        System.out.print("请输入运算式或者两个运算数：");
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
            return;
        } else {
            System.out.println("错误的操作类型！");
        }

        if ("".equals(sign)) return;

        if (num1Symbol.equals("-")) {
            num1 = num1Symbol + num1;
        }
        if (num2Symbol.equals("-")) {
            num2 = "(" + num2Symbol + num2 + ")";
        }
        System.out.println(num1 + " " + sign + " " + num2 + " = " + result);

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
