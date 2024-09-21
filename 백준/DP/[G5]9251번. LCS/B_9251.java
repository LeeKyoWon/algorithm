import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DP
 */
public class B_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int s1_length = s1.length();
        int s2_length = s2.length();

        int[][] dp = new int[s1_length+1][s2_length+1];

        for (int i = 1; i <= s1_length; i++) {
            for (int j = 1; j <= s2_length; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }

                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[s1_length][s2_length]);
    }
}
