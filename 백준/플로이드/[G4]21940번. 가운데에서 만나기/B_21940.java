import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 플로이드 알고리즘 -> k i j
 */
public class B_21940 {

    static final int INF = 300_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][N+1];

        for (int[] m : map) {
            Arrays.fill(m, INF);
        }
        for (int i = 1; i <= N; i++) {
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[from][to] = cost;
        }

        int K = Integer.parseInt(br.readLine());
        int[] cities = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int[] tmp_max = new int[N+1];
        for(int i=1; i<=N; i++) {
            for (int j = 0; j < K; j++) {
                if (tmp_max[i] < map[cities[j]][i] + map[i][cities[j]])
                    tmp_max[i] = map[cities[j]][i] + map[i][cities[j]];
            }

            if (min > tmp_max[i]) {
                min = tmp_max[i];
            }
        }

        for (int i = 1; i <= N; i++) {
            if(min == tmp_max[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
