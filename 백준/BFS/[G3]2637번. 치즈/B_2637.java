import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_2638 {

    static int N;
    static int M;

    static int[][] map;
    static boolean[][] visited ;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        LinkedList<Point> cheese_q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x == 1){
                    cheese_q.add(new Point(i, j));
                }
            }
        }

        int ans = 0;

        bfs(new Point(0, 0));
        while(!cheese_q.isEmpty()) {
            ans++;

            LinkedList<Point> delete_q = new LinkedList<>();

            int cur_size = cheese_q.size();
            for (int i = 0; i < cur_size; i++) {
                int cnt = 0;
                Point cur = cheese_q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = cur.x + dx[j];
                    int ny = cur.y + dy[j];

                    if(visited[nx][ny] && map[nx][ny] == 0) {
                        cnt++;
                    }
                }

                if(cnt < 2) {
                    cheese_q.add(cur);
                } else {
                    map[cur.x][cur.y] = 0;
                    delete_q.add(new Point(cur.x, cur.y));
                }
            }

            while(!delete_q.isEmpty()) {
                Point cur = delete_q.poll();
                bfs(new Point(cur.x, cur.y));
            }
        }



        System.out.println(ans);
    }

    public static void bfs(Point start) {
        LinkedList<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<M) {
                    if(!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx,ny));
                    }
                }
            }
        }
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
