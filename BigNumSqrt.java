import parser.SignedNumericChecker;
import util.CompareUtil;

import java.util.Scanner;

public class BigNumSqrt {
    public static String num1;
    public static String sqrt() {
        Scanner scanner = new Scanner(System.in);

        num1 = scanner.nextLine();// 第一个大数

        if (!SignedNumericChecker.isSignedNumeric(num1)) {
            return "错误的数字！";
        }
        num1 = num1.replace(" ", "");
        if (num1.startsWith("-")) {
            return "错误的数字！";
        }
        String result = sqrtHandle(num1);
        return "-" + result + ", " + "+" + result;
    }


    public static String sqrtHandle(String N) {
        String low = "1";
        String high = N;
        String mid, sqr, sqrPlusOne;

        while (CompareUtil.compare(low, high) <= 0) {
            mid = BigNumAdd.addHandle(low, high);
            mid = BigNumDivision.divisionHandle(mid, "2").split("······")[0]; // 取商作为新的中间值

            // 计算mid的平方和(mid+1)的平方
            sqr = BigNumMultiply.multiplyHandle(mid, mid);
            sqrPlusOne = BigNumAdd.addHandle(mid, "1");
            sqrPlusOne = BigNumMultiply.multiplyHandle(sqrPlusOne, sqrPlusOne);

            int cmpSqr = CompareUtil.compare(sqr, N);
            int cmpSqrPlusOne = CompareUtil.compare(sqrPlusOne, N);

            if (cmpSqr <= 0 && cmpSqrPlusOne > 0) {
                // 如果mid的平方小于等于N，但是(mid+1)的平方大于N，则找到结果
                return mid;
            } else if (cmpSqr > 0) {
                // 如果mid的平方大于N，说明真实平方根在low和mid之间
                high = BigNumSub.subHandle(mid, "1", true);
            } else {
                // 如果mid的平方小于N，说明真实平方根在mid和high之间
                low = BigNumAdd.addHandle(mid, "1");
            }
        }

        return low; // 这种情况不应该发生，因为上面的循环已经处理了所有情况
    }
}
