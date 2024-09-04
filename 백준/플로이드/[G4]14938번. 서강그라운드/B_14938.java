import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14938 {

    static final int INF = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] area_items = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            area_items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] map = new int[n+1][n+1];

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i==j) {
                    continue;
                }
                map[i][j] = INF;
            }
        }

        for(int i=0; i<r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map[a][b] = l;
            map[b][a] = l;
        }

        for(int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (j == k)
                        continue;
                    else {
                        if (map[j][k] > map[j][i] + map[i][k])
                            map[j][k] = map[j][i] + map[i][k];
                    }
                }
            }
        }

        int ans = 0;

        for(int i=1; i<=n; i++) {
            int tmp = area_items[i];
            for(int j=1; j<=n; j++) {
                if(i==j || map[i][j] == INF)
                    continue;

                if(map[i][j] <= m) {
                    tmp += area_items[j];
                }
            }
            if(tmp > ans)
                ans = tmp;
        }

        System.out.println(ans);
    }
}
