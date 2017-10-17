import java.util.*;

/**
* @problem Spreadsheets
* @url http://codeforces.com/contest/1/problem/B
* @description http://www.coltonstack.com/stack.do?pclass_id=3&stackpost_id=34
* @author coltonoppa
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            String str = sc.next();
            int idx = 0;
            if (str.matches("[R]\\d+[C]\\d+")) {
                idx = str.indexOf("C");
                int row = Integer.parseInt(str.substring(1, idx));
                int col = Integer.parseInt(str.substring(idx + 1));
                System.out.println(nToS(col) + row);
            } else {
                while (str.charAt(idx) >= 'A' && str.charAt(idx) <= 'Z') idx++;
                int row = Integer.parseInt(str.substring(idx));
                int col = sToN(str.substring(0, idx));
                System.out.println("R" + row + "C" + col);
            }
        }
    }
    
    static String nToS(int n) {
        StringBuilder sb = new StringBuilder("");
        while (n > 0) {
            int tmp = n % 26;
            if (tmp == 0) {
                sb.append((char)('Z'));
                n = n / 26 - 1;
            } else {
                sb.append((char)(n % 26 + (int)'A' - 1));
                n /= 26;
            }
        }
        sb.reverse();
        return sb.toString();
    }
    
    static int sToN(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++)
            sum += (int)(s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - i - 1);
        return sum;
    }
}