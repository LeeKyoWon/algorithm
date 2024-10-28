import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시뮬레이션
 */
public class B_14503 {

    static int N;
    static int M;

    static int[][] map;
    static boolean[][] visited;


    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if(x == 1) {
                    visited[i][j] = true;
                }
            }
        }

        cleaning(sx, sy, direction);

        System.out.println(ans);
    }

    static void cleaning(int x, int y, int d) {
        // 현재 칸이 청소되지 않았다면 청소 진행
        if(!visited[x][y]) {
            visited[x][y] = true;
            ans++;
        }

        // 주변에 청소되지 않은 칸이 있는지 체크
        boolean flag = false;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!visited[nx][ny]) {
                flag = true;
            }
        }

        // 주변에 청소되지 않은 빈칸이 없고, 뒤가 벽이 아니라면 후진한다.
        if(!flag) {
            if(map[x-dx[d]][y-dy[d]] == 0) {
                cleaning(x - dx[d], y - dy[d], d);
                return;
            } else {
                return;
            }
        }

        // 주변에 청소되지 않은 칸이 존재한다면
        for(int i=0; i<4; i++) {
            d = rotate(d);
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!visited[nx][ny]) {
                cleaning(nx, ny, d);
                return;
            }
        }
    }

    static int rotate(int d){
        if(d == 0){
            return 3;
        } else {
            return d-1;
        }
    }

}
