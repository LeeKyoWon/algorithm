import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 증가 dp
        int[] inc_dp = new int[n];
        // 감소 dp
        int[] dec_dp = new int[n];

        Arrays.fill(inc_dp, 1);
        Arrays.fill(dec_dp, 1);

        for(int i=1; i<n; i++) {
            for(int j=i-1; j>=0; j--){
                if(arr[j] < arr[i]) {
                    inc_dp[i] = Math.max(inc_dp[i], inc_dp[j]+1);
                }
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for(int j=i+1; j<n; j++) {
                if (arr[j] < arr[i]){
                    dec_dp[i] = Math.max(dec_dp[i], dec_dp[j]+1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, inc_dp[i] + dec_dp[i] - 1);
        }

        System.out.println(ans);
    }
}
