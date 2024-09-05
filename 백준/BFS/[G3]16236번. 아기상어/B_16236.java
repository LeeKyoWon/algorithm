import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_16236 {

    static int N;
    static int[][] map;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        Point shark = new Point(0,0,0);
        int shark_exp = 0;  // 현재 크기 기준 먹은 물고기 수
        int shark_size = 2; // 현재 크기
        int ans = 0;        // 현재까지 이동한 모든 거리의 합

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                // 빈 칸
                if (x == 9) {
                    shark.x = i;
                    shark.y = j;
                    continue;
                }

                // 물고기
                map[i][j] = x;
            }
        }

        while(true) {

            PriorityQueue<Point> fish_q = new PriorityQueue<>(new Comparator<>(){
                @Override
                public int compare(Point o1, Point o2) {
                    if(o1.dist != o2.dist){
                        return o1.dist - o2.dist;
                    }

                    if(o1.x != o2.x) {
                        return o1.x - o2.x;
                    }

                    return o1.y - o2.y;
                }
            });

            boolean[][] visited = new boolean[N][N];
            boolean is_ate_fish = false;

            fish_q.add(new Point(shark.x, shark.y, 0));

            while(!fish_q.isEmpty()) {
                Point cur = fish_q.poll();

                if(0 < map[cur.x][cur.y] && map[cur.x][cur.y] < shark_size) {
                    map[cur.x][cur.y] = 0;
                    is_ate_fish = true;
                    ans += cur.dist;
                    shark_exp++;
                    shark.x = cur.x;
                    shark.y = cur.y;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                        if (!visited[nx][ny] && map[nx][ny] <= shark_size) {
                            visited[nx][ny] = true;
                            fish_q.add(new Point(nx, ny, cur.dist+1));
                        }
                    }
                }
            }

            if(!is_ate_fish){
                break;
            }

            if(shark_size == shark_exp) {
                shark_size++;
                shark_exp = 0;
            }
        }

        System.out.println(ans);
    }

    public static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
