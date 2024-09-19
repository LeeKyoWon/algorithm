import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이분 탐색 + 누적 합
 */
public class B_2143_3 {
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
        for(int i=1; i<=n; i++) {
            for(int j=i; j<=n; j++) {
                A_sum[idx++] = A[j] - A[j-i];
            }
        }

        idx = 0;
        for(int i=1; i<=m; i++) {
            for (int j = i; j <= m; j++) {
                B_sum[idx++] = B[j] - B[j-i];
            }
        }

        Arrays.sort(A_sum);
        Arrays.sort(B_sum);

        long ans = 0;

        for (int i = 0; i < A_sum.length; i++) {
            int cur = A_sum[i];

            int al = left_bs(A_sum, cur);
            int ar = right_bs(A_sum, cur);
            int a_cnt = ar-al;
            i += a_cnt-1;

            int bl = left_bs(B_sum, T-cur);
            int rb = right_bs(B_sum, T-cur);
            int b_cnt = rb - bl;

            ans += (long)a_cnt * b_cnt;
        }

        System.out.println(ans);
    }

    public static int left_bs(int[] arr, int x) {
        int l = 0;
        int r = arr.length-1;

        while(l<=r) {
            int mid = (l+r)/2;

            if(x <= arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public static int right_bs(int[] arr, int x) {
        int l = 0;
        int r = arr.length-1;

        while(l<=r) {
            int mid = (l+r)/2;

            if(x < arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
