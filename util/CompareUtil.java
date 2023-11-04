package util;

public class CompareUtil {

    /**
     * 比较两数大小
     * @param num1
     * @param num2
     * @return
     */
    public static int compare(String num1, String num2) {
        if (num1.length() > num2.length()) return 1;
        if (num1.length() == num2.length()) {
            int i = 0;
            for (i = 0; i < num1.length(); i++){
                if (num1.charAt(i) > num2.charAt(i)) {
                    return 1;
                } else if (num1.charAt(i) < num2.charAt(i)) {
                    return -1;
                }
            }
            if (i == num2.length() && num1.charAt(i-1) == num2.charAt(i-1)) {
                return 0;
            }
        }
        return -1;
    }
}
