
package again;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현, 시뮬레이션 문제
 *  : 배열 활용
 */
public class B_17144_2 {

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

        boolean flag = false;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;

                if (tmp == -1 && !flag) {
                    air_purifier_top = i;
                    air_purifier_bottom = i+1;
                    flag = true;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            map = dust_spread(map);
            purify_air(map);
        }

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


    public static int[][] dust_spread(int[][] map){
        int[][] next_map = new int[R][C];
        next_map[air_purifier_top][0] = -1;
        next_map[air_purifier_bottom][0] = -1;

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++){
                if(map[i][j] > 0) {
                    int spread_cnt = map[i][j] / 5;
                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(0<=nx && nx<R && 0<=ny && ny<C) {
                            if(map[nx][ny] != -1) {
                                next_map[nx][ny] += spread_cnt;
                                map[i][j] -= spread_cnt;
                            }
                        }
                    }
                    next_map[i][j] += map[i][j];
                }
            }
        }

        return next_map;
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
