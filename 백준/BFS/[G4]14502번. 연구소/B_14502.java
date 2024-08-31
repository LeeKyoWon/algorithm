import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B_14502 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int ans;
    static ArrayList<Point> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        viruses = new ArrayList<>();
        ArrayList<Point> blanks = new ArrayList<>();
        ArrayList<Point> walls = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if(x == 0) {
                    blanks.add(new Point(i,j));
                }

                if(x == 1) {
                    walls.add(new Point(i,j));
                }

                if(x == 2) {
                    viruses.add(new Point(i,j));
                }


            }
        }

        int blank_size = blanks.size();
        Point[] wall = new Point[3];
        for(int i=0; i<blank_size; i++) {
            wall[0] = blanks.get(i);
            for (int j=i+1; j<blank_size; j++) {
                wall[1] = blanks.get(j);
                for (int k=j+1; k<blank_size; k++) {
                    wall[2] = blanks.get(k);
                    for(int l=0; l<3; l++) {
                        map[wall[l].x][wall[l].y] = 1;

                    }
                    bfs();

                    map = new int[N][M];
                    for(Point p : walls) {
                        map[p.x][p.y] = 1;
                    }

                    for(Point p : viruses) {
                        map[p.x][p.y] = 2;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static void bfs() {
        visited = new boolean[N][M];
        LinkedList<Point> queue = new LinkedList<>();
        for(Point p : viruses) {
            queue.add(p);
        }

        while(!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(map[nx][ny] == 0 && !visited[nx][ny]) {
                        map[nx][ny] = 2;
                        visited[nx][ny] = true;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        if(cnt > ans) {
            ans = cnt;
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
