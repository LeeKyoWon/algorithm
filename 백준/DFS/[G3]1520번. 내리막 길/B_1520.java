import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP + DFS
 */
public class B_1520 {

    static int N;
    static int M;
    static int[][] map;
    static int[][] dp;

    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(0,0);

        System.out.println(dp[0][0]);
    }

    // (0,0) ~ (M-1,N-1)
    static int dfs(int x, int y) {

        // 목적지에 도달
        if(x == M-1 && y == N-1){
            return 1;
        }

        // 기존에 방문한 적 있음
        if(dp[x][y] > -1) {
            return dp[x][y];
        }

        // 최초 방문
        dp[x][y] = 0;
        int cur = map[x][y];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                int next = map[nx][ny];

                if(cur > next) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }
        return dp[x][y];
    }
}
