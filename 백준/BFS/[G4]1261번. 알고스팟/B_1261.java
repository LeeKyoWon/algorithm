import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BFS 를 진행하면서 최단 거리가 아닌 최소 벽 깨기를 기준으로 진행해나가려면 ?
 *  -> BFS 에서 벽 깬 횟수를 기준으로 우선순위로 저장하는 "PriorityQueue" 를 활용하자
 *  -> PriorityQueue 관련한 아이디어는 "다익스트라의 아이디어"
 */
public class B_1261 {

    static int N;
    static int M;
    static int[][] map;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,-1,0,1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = s.charAt(j-1) - '0';
            }
        }

        bfs();

        System.out.println(ans);;
    }

    public static void bfs (){
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][M+1];
        q.add(new Node(1,1,0));
        visited[1][1] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();


            if(cur.x == N && cur.y == M) {
                ans = cur.boom_cnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0<nx && nx<=N && 0<ny && ny<=M && !visited[nx][ny]) {
                    if(map[nx][ny] == 0){
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny, cur.boom_cnt));
                    } else {
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny, cur.boom_cnt+1));
                    }
                }
            }
        }
    }

    public static class Node implements Comparable<Node>{
        int x;
        int y;
        int boom_cnt;

        public Node(int x, int y, int boom_cnt) {
            this.x = x;
            this.y = y;
            this.boom_cnt = boom_cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.boom_cnt - o.boom_cnt;
        }
    }
}
