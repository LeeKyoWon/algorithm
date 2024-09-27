import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS + 벽을 K개 만큼 무시하는 법
 *
 * 1. K개를 백트래킹으로 임시로 0으로 바꾸는 방법을 시도
 *    -> "시간 초과" 발생
 *
 * 2. 얼마만큼의 벽을 뚫고 왔는지를 visited 를 통해 체크하며 진행
 *    -> boolean[][][] visited = new boolean[N+1][M+1][K+1];
 *
 */
public class B_14442 {

    static int N;
    static int M;
    static int K;
    static boolean[][][] visited;
    static int[][] map;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};
    static ArrayList<int[]> walls;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        walls = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                int x = s.charAt(j - 1) - '0';
                map[i][j] = x;
                if (x == 1) {
                    walls.add(new int[]{i, j});
                }
            }
        }

        ans = bfs();

        System.out.println(ans);
    }

    public static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        int[][] ans = new int[N+1][M+1];
        visited = new boolean[N+1][M+1][K+1];

        q.add(new Node(1,1,1,0));
        visited[1][1][0] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.x == N && cur.y == M) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int cur_dist = cur.dist;
                int cur_k = cur.k;

                if(1<=nx && nx <= N && 1<=ny && ny<= M) {
                    if(map[nx][ny] == 0 && !visited[nx][ny][cur_k]) {
                        visited[nx][ny][cur_k] = true;
                        ans[nx][ny] = ans[cur.x][cur.y] + 1;
                        q.add(new Node(nx,ny, cur_dist+1, cur_k));
                    } else if (cur_k + 1 <= K && map[nx][ny] == 1 && !visited[nx][ny][cur_k+1]) {
                        visited[nx][ny][cur_k+1] = true;
                        q.add(new Node(nx, ny, cur_dist+1, cur_k+1));
                    }
                }
            }
        }

        return -1;
    }

    public static class Node{
        int x;
        int y;
        int dist;
        int k;

        public Node(int x, int y, int dist, int k) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.k = k;
        }
    }
}
