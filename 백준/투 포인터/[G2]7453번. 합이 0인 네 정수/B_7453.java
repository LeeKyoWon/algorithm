import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 투 포인터
 */
public class B_7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB_sum = new int[N*N];
        int[] CD_sum = new int[N*N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB_sum[idx] = A[i] + B[j];
                CD_sum[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(AB_sum);
        Arrays.sort(CD_sum);

        long ans = 0;

        int l = 0;
        int r = CD_sum.length-1;

        while( l < AB_sum.length && r >= 0) {
            int l_cnt= 1;
            int r_cnt = 1;

            if(AB_sum[l] + CD_sum[r] < 0) {
                l++;
            } else if (AB_sum[l] + CD_sum[r] > 0) {
                r--;
            } else {

                // AB_sum 의 같은 수가 여러 개 인 경우
                while (l < AB_sum.length-1 && AB_sum[l] == AB_sum[l+1]){
                    l++;
                    l_cnt++;
                }
                // CD_sum 의 같은 수가 여러 개 인 경우
                while (r > 0 && CD_sum[r] == CD_sum[r-1]){
                    r--;
                    r_cnt++;
                }

                l++;
                ans += (long)r_cnt * l_cnt;
            }
        }

        System.out.println(ans);
    }
}
