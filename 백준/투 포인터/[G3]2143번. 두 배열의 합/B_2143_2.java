import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 투 포인터 + 누적 합
 */
public class B_2143_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
        }

        int[] A_sum = new int[n*(n+1)/2];
        int[] B_sum = new int[m*(m+1)/2];

        int idx = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                A_sum[idx++] = A[j] - A[j-i];
            }
        }

        idx = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                B_sum[idx++] = B[j] - B[j-i];
            }
        }

        Arrays.sort(A_sum);
        Arrays.sort(B_sum);

        int l = 0;
        int r = B_sum.length - 1;

        long ans = 0;
        while(l < A_sum.length && r >= 0) {

            if(A_sum[l] + B_sum[r] < T) {
                l++;
                continue;
            }

            if(A_sum[l] + B_sum[r] > T) {
                r--;
                continue;
            }

            if(A_sum[l] + B_sum[r] == T) {
                int a_cnt = 0;
                int b_cnt = 0;

                int a_tmp = A_sum[l];
                while(l<A_sum.length && A_sum[l] == a_tmp){
                    a_cnt++;
                    l++;
                }

                int b_tmp = B_sum[r];
                while(r >= 0 && B_sum[r] == b_tmp) {
                    b_cnt++;
                    r--;
                }

                ans += (long)a_cnt * b_cnt;
            }
        }

        System.out.println(ans);
    }
}
