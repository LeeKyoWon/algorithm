import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유니온 파인드
 * -> 사이클 검사
 */
public class B_20040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(!union(parent, x, y)) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    public static boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x==y)
            return false;
        else if (x>y)
            parent[x] = y;
        else
            parent[y] = x;

        return true;
    }

    public static int find(int[] parent, int x) {
        if(x==parent[x]) return x;
        return parent[x] = find(parent, parent[x]);
    }

    public static class Edge {
        int x;
        int y;
        int num;

        public Edge(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
