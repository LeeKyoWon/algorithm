import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그리디
 *  : 거꾸로 보는 아이디어 ( N-1 -> 0 )
 */
public class B_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int l = 0; l < T; l++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];

            long ans = 0;

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            for(int i = N-1; i>=0; i--) {
                max = Math.max(arr[i], max);
                if(max > arr[i]) {
                    ans += max - arr[i];
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

}
