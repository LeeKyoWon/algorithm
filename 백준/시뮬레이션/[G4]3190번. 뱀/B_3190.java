import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_3190 {

    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};

    static int N;
    static int[][] map;
    static Queue<Rotation> rotations;
    static Queue<int[]> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = -1;
        }

        map[1][1] = 1;
        snake = new ArrayDeque<>();
        snake.add(new int[]{1,1});

        int L = Integer.parseInt(br.readLine());
        rotations = new ArrayDeque<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            rotations.add(new Rotation(time, dir));
        }

        Rotation next = rotations.poll();
        dfs(0, 0, 1, 1, next);
    }

    static void dfs(int cur_time, int cur_dir, int x, int y, Rotation next) {
        // 방향 전환 할 시간이 되었다면 방향을 전환
        if(cur_time == next.time) {
            cur_dir = rotate(cur_dir, next.dir);
            if(!rotations.isEmpty()) {
                next = rotations.poll();
            }
        }

        // 결정된 방향대로 1칸 진행
        int nx = x + dx[cur_dir];
        int ny = y + dy[cur_dir];

        // 진행 후 종료 조건에 해당하는 지 체크
        if(isWall(nx, ny) || map[nx][ny] == 1) {
            System.out.println(cur_time + 1);
            return;
        }

        // 다음 자리가 사과가 아니면 뱀의 꼬리 1칸 제거
        if(map[nx][ny] != -1) {
            if(!snake.isEmpty()) {
                int[] tail = snake.poll();
                map[tail[0]][tail[1]] = 0;
            }
        }

        // 뱀의 머리를 앞으로 1칸 진행
        snake.add(new int[]{nx, ny});
        map[nx][ny] = 1;

        // 다음 시간 진행
        dfs(cur_time+1, cur_dir, nx, ny, next);
    }

    static class Rotation {
        int time;
        char dir;

        Rotation(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static boolean isWall(int x, int y){
        if(0 < x && x < N+1 && 0 < y && y < N+1) {
            return false;
        }

        return true;
    }

    static int rotate(int cur, char next) {
        if(next == 'D') {
            switch (cur) {
                case 0: return 2;
                case 1: return 3;
                case 2: return 1;
                default: return 0;
            }
        } else {
            switch (cur) {
                case 0: return 3;
                case 1: return 2;
                case 2: return 0;
                default: return 1;
            }
        }
    }
}
