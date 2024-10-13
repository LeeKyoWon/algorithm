package again;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2146 {

    static int N;
    static int island_idx;

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,-1,0,1};

    static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        int min = Integer.MAX_VALUE;

        island_idx = 0;

        // map 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
            }
        }

        // 섬 별로 인덱스 붙이기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    island_idx++;

                    // 큐 재활용
                    q.clear();
                    findIslands(new Point(i,j));
                }
            }
        }

        // 하나의 섬에서 다른 섬으로 가는 최단 경로 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] > 0) {

                    // 큐와 visited 재활용
                    q.clear();
                    resetVisited();

                    int cur = findNearestIsland(new Point(i, j, 0));
                    if (min > cur) {
                        min = cur;
                    }
                }
            }
        }

        System.out.println(min);
    }

    // 섬에 인덱스를 붙이기 위한 BFS
    static void findIslands(Point start) {
        map[start.x][start.y] = island_idx;
        visited[start.x][start.y] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny));
                        map[nx][ny] = island_idx;
                    }
                }
            }
        }
    }

    // 다른 섬까지의 최소 거리를 구하기 위한 BFS
    static int findNearestIsland(Point start) {
        visited[start.x][start.y] = true;
        q.add(start);

        while(!q.isEmpty()){
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny]) {
                        if (map[nx][ny] == 0){
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny, cur.dist + 1));
                        } else if (map[nx][ny] != map[start.x][start.y]) {
                            return cur.dist;
                        }
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    static void resetVisited(){
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    static class Point {
        int x;
        int y;
        int dist = 0;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
