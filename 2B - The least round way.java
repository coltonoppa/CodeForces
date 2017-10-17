import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int zy = 0;
        int zx = 0;
        int[][][] dp = new int[n+1][n+1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int num = sc.nextInt();
                
                if (num == 0) {
                    zy = i;
                    zx = j;
                }
                
                while (num % 2 == 0 && num != 0) {
                    num /= 2;
                    dp[i][j][0]++;
                }
                while (num % 5 == 0 && num != 0) {
                    num /= 5;
                    dp[i][j][1]++;
                }
                if (i == 1 || j == 1) {
                    dp[i][j][0] += dp[i-1][j][0] + dp[i][j-1][0];
                    dp[i][j][1] += dp[i-1][j][1] + dp[i][j-1][1];
                }
                if (i > 1 && j > 1) {
                    dp[i][j][0] += Integer.min(dp[i-1][j][0], dp[i][j-1][0]);
                    dp[i][j][1] += Integer.min(dp[i-1][j][1], dp[i][j-1][1]);
                }
            }
        }
        int flag = dp[n][n][0] < dp[n][n][1] ? 0 : 1;
        int ans = Integer.min(dp[n][n][0], dp[n][n][1]);
        
        StringBuilder sb = new StringBuilder("");
        if (zy != 0 && zx != 0 && ans > 1) {
            ans = 1;
            for (int i = 1; i < zy; i++)    sb.append("D");
            for (int i = 1; i < zx; i++)    sb.append("R");
            for (int i = zy; i < n; i++)    sb.append("D");
            for (int i = zx; i < n; i++)    sb.append("R");
        } else {
            int i = n, j = n;
            while (i != 1 || j != 1) {
                if (i > 1 && dp[i-1][j][flag] <= ans) {
                    i--;
                    sb.append("D");
                }
                if (j > 1 && dp[i][j-1][flag] <= ans) {
                    j--;
                    sb.append("R");
                }
            }
            sb.reverse();
        }
               
        System.out.println(ans + "\n" + sb.toString());
    }
}
