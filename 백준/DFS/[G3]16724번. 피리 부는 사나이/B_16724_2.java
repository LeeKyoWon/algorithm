import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DFS 활용
 */
public class B_16724_2 {

    static int N;
    static int M;
    static char[][] map;
    static int ans;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(N==1 && M==1) {
            br.readLine();
            System.out.println(1);
            return;
        }

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        visited = new int[N][M];

        for (int i = 0; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            dfs(x, y);
        }

        System.out.println(ans);
    }

    public static void dfs(int x, int y) {
        if(visited[x][y] == 2) {
            return;
        }

        if(visited[x][y] == 1){
            visited[x][y] = 2;
            ans++;
            return;
        }

        char command = map[x][y];

        int nx = x;
        int ny = y;

        switch(command) {
            case 'U':
                nx--;
                break;
            case 'D':
                nx++;
                break;
            case 'R':
                ny++;
                break;
            case 'L':
                ny--;
        }

        visited[x][y] = 1;
        dfs(nx,ny);
        if(visited[nx][ny] == 2)
            visited[x][y] = 2;
    }
}
