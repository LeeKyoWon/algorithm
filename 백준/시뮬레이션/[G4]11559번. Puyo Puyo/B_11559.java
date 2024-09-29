package again;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS + 시뮬레이션
 */
public class B_11559 {

    static char[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,-1,0,1};
    static PriorityQueue<Point> remove_q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        Queue<Point> puyo_q = new ArrayDeque<>();
        remove_q = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.y != o2.y) return o1.y - o2.y;
                else return o1.x - o2.x;
            }
        });

        StringTokenizer st;
        for(int i=0; i<12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        boolean chain_flag = true;
        int ans = 0;
        while(chain_flag) {

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if(map[i][j] != '.') {
                        puyo_q.add(new Point(i,j));
                    }
                }
            }

            chain_flag = false;
            visited = new boolean[12][6];
            while (!puyo_q.isEmpty()) {
                Point cur = puyo_q.poll();

                if (!visited[cur.x][cur.y]) {
                    if(bfs(cur)) {
                        chain_flag = true;
                    }
                }
            }

            bomb();

            if(chain_flag)
                ans++;
        }

        System.out.println(ans);
    }

    public static void bomb() {
        while(!remove_q. isEmpty()) {
            Point cur = remove_q.poll();
            for (int i = cur.x; i > 0; i--) {
                map[i][cur.y] = map[i-1][cur.y];
            }
            map[0][cur.y] = '.';
        }
    }

    public static boolean bfs (Point start) {
        Queue<Point> q = new ArrayDeque<>();
        Queue<Point> temp = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            temp.add(cur);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < 12 && 0 <= ny && ny < 6) {
                    if(map[nx][ny] == map[start.x][start.y] && !visited[nx][ny]) {
                        q.add(new Point(nx,ny));
                        visited[nx][ny] = true;
                        cnt++;
                    }
                }
            }
        }

        if(cnt >= 4) {
            while(!temp.isEmpty()) {
                Point x = temp.poll();
                remove_q.add(x);
            }
            return true;
        }

        return false;
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
