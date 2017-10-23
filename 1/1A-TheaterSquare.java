import java.util.*;

/**
* @problem Theatre Square
* @url http://codeforces.com/problemset/problem/1/A
* @description http://www.coltonstack.com/stack.do?pclass_id=3&stackpost_id=33
* @author coltonoppa
*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long m = sc.nextInt();
        long a = sc.nextInt();
        long result = (n/a + (n % a == 0 ? 0 : 1)) * (m/a + (m % a == 0 ? 0 : 1));
        System.out.println(result);
    }
}
