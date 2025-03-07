import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for(int i=0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];
        Arrays.fill(dp,100000);
        dp[0] = 0;

        for(int i=0; i<n; i++) {
            for(int j=1; j<=k; j++) {
                if(j >= arr[i]) {
                    dp[j] = Math.min(dp[j], dp[j-arr[i]] + 1);
                }
            }
        }

        if (dp[k] == 100000) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
