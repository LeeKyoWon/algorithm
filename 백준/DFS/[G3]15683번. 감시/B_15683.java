import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 시뮬레이션 + 백트래킹
 */
public class B_15683 {

    static int N;
    static int M;
    static ArrayList<Cctv> cctv_list;
    static int cctv_num;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        cctv_list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;

                if(1 <= x && x <= 5) {
                    cctv_list.add(new Cctv(i, j, x));
                }
            }
        }

        cctv_num = cctv_list.size();

        dfs(0, map);

        System.out.println(ans);
    }

    static void dfs(int cur, int[][] map) {
        if(cur == cctv_num) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 0) {
                        cnt++;
                    }
                }
            }

            if(ans > cnt) {
                ans = cnt;
            }
            return;
        }

        int[][] tmp = copy_map(map);
        Cctv cctv = cctv_list.get(cur);
        int reps = getReps(cctv.type);

        for(int i=0; i < reps; i++) {
            monitor(map, cctv, i);
            dfs(cur+1, map);
            undo(map, tmp);
        }
    }

    static int[][] copy_map(int[][] map) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    static void undo(int[][] map, int[][] tmp) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    static void monitor(int[][] map, Cctv cctv, int num) {
        int x = cctv.x;
        int y = cctv.y;
        int type = cctv.type;

        switch(type) {
            case 1:
                monitor_one_direction(map, x, y, num);
                break;
            case 2:
                monitor_one_direction(map,x,y,num);
                monitor_one_direction(map,x,y,num+2);
                break;
            case 3:
                monitor_one_direction(map,x,y,num);
                monitor_one_direction(map,x,y,(num+1)%4);
                break;
            case 4:
                monitor_one_direction(map,x,y,num);
                monitor_one_direction(map,x,y,(num+1)%4);
                monitor_one_direction(map,x,y,(num+2)%4);
                break;
            case 5:
                monitor_one_direction(map,x,y,num);
                monitor_one_direction(map,x,y,num+1);
                monitor_one_direction(map,x,y,num+2);
                monitor_one_direction(map,x,y,num+3);
        }
    }

    static void monitor_one_direction(int[][] map, int x, int y, int direction) {
        int[] dx = new int[]{-1,0,1,0};
        int[] dy = new int[]{0,1,0,-1};

        while(true){
            x += dx[direction];
            y += dy[direction];

            if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6) {
                break;
            }

            if(map[x][y] == 0) {
                map[x][y] = -1;
            }
        }
    }

    static int getReps(int cctv_type) {
        switch(cctv_type) {
            case 2: return 2;
            case 5: return 1;
            default : return 4;
        }
    }

    static class Cctv {
        int x;
        int y;
        int type;

        Cctv(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
