import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 구현, 시뮬레이션 문제
 *  : 연결 리스트 활용
 */
public class B_17144 {

    static int R;
    static int C;
    static int T;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int air_purifier_top;
    static int air_purifier_bottom;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        LinkedList<Dust> dusts = new LinkedList<>();
        boolean flag = false;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if(tmp > 0) {
                    dusts.add(new Dust(i,j,tmp));
                } else if (tmp == -1 && !flag) {
                    air_purifier_top = i;
                    air_purifier_bottom = i+1;
                    flag = true;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            while(!dusts.isEmpty()) {
                dust_spread(dusts.poll());
            }

            purify_air(map);

            if(i<T-1) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if (map[j][k] > 0) {
                            dusts.add(new Dust(j, k, map[j][k]));
                        }
                    }
                }
            } else {
                int ans = 0;
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        if (map[j][k] > 0) {
                            ans += map[j][k];
                        }
                    }
                }
                System.out.println(ans);
            }
        }

    }

    public static void dust_spread(Dust dust){
        int spread_cnt = dust.cnt / 5;

        for(int i=0; i<4; i++) {
            int nx = dust.x + dx[i];
            int ny = dust.y + dy[i];
            if(0<=nx && nx<R && 0<=ny && ny<C) {
                if(map[nx][ny] != -1) {
                    map[nx][ny] += spread_cnt;
                    map[dust.x][dust.y] -= spread_cnt;
                }
            }
        }
    }

    public static void purify_air(int[][] map) {

        // 상단 회전
        for(int i=air_purifier_top-1; i>0; i--) {
            map[i][0] = map[i-1][0];
        }

        for(int i=0; i<C-1; i++) {
            map[0][i] = map[0][i+1];
        }

        for(int i=0; i<air_purifier_top; i++) {
            map[i][C-1] = map[i+1][C-1];
        }

        for(int i=C-1; i>1; i--) {
            map[air_purifier_top][i] = map[air_purifier_top][i-1];
        }

        map[air_purifier_top][1] = 0;

        //하단 회전
        for (int i = air_purifier_bottom + 1; i < R-1; i++) {
            map[i][0] = map[i+1][0];
        }

        for(int i=0; i<C-1; i++) {
            map[R-1][i] = map[R-1][i+1];
        }

        for(int i=R-1; i>air_purifier_bottom; i--) {
            map[i][C-1] = map[i-1][C-1];
        }

        for(int i=C-1; i>1; i--) {
            map[air_purifier_bottom][i] = map[air_purifier_bottom][i-1];
        }

        map[air_purifier_bottom][1] = 0;
    }


    public static class Dust {
        int x;
        int y;
        int cnt;

        public Dust(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
