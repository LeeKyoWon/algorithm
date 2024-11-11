import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS
 * - bfS() 에서 큐와 리스트 모두 활용
 *
 * 1. 큐     : 일반적인 bfs 탐색용
 * 2. 리스트  : 큐에 담았던 좌표들 보관용
 *
 * 큐 탐색이 끝나면 연결된 모든 값의 평균값을 구한 후
 * 리스트로 보관했던 각각의 좌표에 평균값을 넣어준다.
 */
public class B_16234 {

    static int N;
    static int L;
    static int R;

    static int[][] map;
    static boolean[][] visited;

    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while(true) {
            boolean flag = true;

            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if(bfs(new int[]{i, j}) && flag) {
                            ans++;
                            flag = false;
                        }
                    }
                }
            }

            if(!flag)
                break;
        }

        System.out.println(ans);
    }

    static boolean bfs(int[] start) {
        Queue<int[]> check_q = new ArrayDeque<>();
        ArrayList<int[]> connect_array = new ArrayList<>();
        check_q.add(start);
        visited[start[0]][start[1]] = true;

        int sum = 0;
        int cnt = 0;

        while(!check_q.isEmpty()) {
            int[] cur = check_q.poll();

            connect_array.add(cur);
            sum += map[cur[0]][cur[1]];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    int gap = Math.abs(map[cur[0]][cur[1]] - map[nx][ny]);
                    if(!visited[nx][ny] && L <= gap && gap <= R) {
                        visited[nx][ny] = true;
                        check_q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if(cnt > 1) {

            int value = sum / cnt;

            for(int i=0; i<connect_array.size(); i++) {
                int[] cur = connect_array.get(i);
                map[cur[0]][cur[1]] = value;
            }

            return true;
        }

        return false;
    }
}
