import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static boolean[][] rows;
    public static boolean[][] cols;
    public static boolean[][] chk;
    public static int[][] map;

    public static boolean check = false;    // dfs 종료 조건 체크 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map =  new int[9][9];       // 스도쿠의 맵

        rows = new boolean[9][10];  // 행에 대한 체크
        cols = new boolean[9][10];  // 열에 대한 체크
        chk = new boolean[9][10];   // 스도쿠의 3X3 에 대한 체크

        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for(int j = 0; j < 9; j++) {
                int x = s.charAt(j) - '0';
                map[i][j] = x;

                int num = 3 * (i/3) + j / 3;    // 3x3 의 작은 네모 번호 (0 ~ 8 : 9개)
                chk[num][x] = true;
                rows[i][x] = true;
                cols[j][x] = true;
            }
        }

        dfs(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static void dfs(int cnt) {
        if(cnt == 81){
            check = true;
            return;
        }

        int x = cnt / 9;
        int y = cnt % 9;
        boolean flag = false;

        if(map[x][y] > 0) {
            dfs(cnt+1);
        } else {
            for (int i = 1; i <= 9; i++) {
                int num = 3 * (x / 3) + y / 3;
                if (!rows[x][i] && !cols[y][i] && !chk[num][i]) {
                    rows[x][i] = true;
                    cols[y][i] = true;
                    chk[num][i] = true;
                    map[x][y] = i;

                    dfs(cnt+1);

                    if(check)
                        return;

                    rows[x][i] = false;
                    cols[y][i] = false;
                    chk[num][i] = false;
                    map[x][y] = 0;
                }
            }
        }
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
