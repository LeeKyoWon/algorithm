import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_9252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        int max = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(max < dp[i][j]) {
                        max = dp[i][j];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }


        char[] ans = new char[max];
        getAnswer(ans, dp, s1);

        for(int i=0; i<max; i++) {
            sb.append(ans[i]);
        }

        System.out.println(max);
        System.out.println(sb);
    }

    public static void getAnswer(char[] ans, int[][] dp, String s1) {

        int cr = dp.length - 1;
        int cc = dp[0].length - 1;

        int cur = dp[cr][cc];

        while(cur > 0) {
            if(dp[cr-1][cc] == cur) {
                cr--;
            } else if (dp[cr][cc-1] == cur) {
                cc--;
            } else {
                cur--;
                ans[cur] = s1.charAt(cr-1);
                cr--;
                cc--;
            }
        }
    }
}
