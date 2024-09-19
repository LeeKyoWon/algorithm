import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * - 해시맵 + 누적합 응용
 */
public class B_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> A_map = new HashMap<>();
        HashMap<Integer, Integer> B_map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = A[i-1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for(int j=i; j<=n; j++) {
                int tmp = A[j]-A[j-i];
                A_map.put(tmp, A_map.getOrDefault(tmp, 0) + 1);
            }
        }


        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            B[i] = B[i-1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= m; i++) {
            for(int j=i; j<=m; j++) {
                int tmp = B[j]-B[j-i];
                B_map.put(tmp, B_map.getOrDefault(tmp, 0) + 1);
            }
        }

        long ans = 0;
        for(int a : A_map.keySet()) {
            if(B_map.containsKey(T-a)) {
                int a_cnt = A_map.get(a);
                int b_cnt = B_map.get(T-a);
                ans += (long) a_cnt * b_cnt;
            }
        }

        System.out.println(ans);
    }
}
