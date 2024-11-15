import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 유니온 파인드
 */
public class B_4803 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) {
                break;
            }

            int[] parent = new int[n+1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            boolean flag = false;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if(!union(parent, u, v)) {
                    flag = true;
                    union(parent, u, 0);
                }
            }

            HashSet<Integer> tree_cnt = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                tree_cnt.add(find(parent, i));
            }

            int size = tree_cnt.size();
            if(flag) {
                size--;
            }
            sb.append("Case ").append(cnt).append(": ");
            if(size == 0) {
                sb.append("No trees.");
            } else if (size == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(size).append(" trees.");
            }
            sb.append("\n");
            cnt++;
        }

        System.out.println(sb);
    }

    static boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y)
            return false;
        else if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
        return true;
    }

    static int find(int[] parent, int x) {
        if(x==parent[x]) return x;
        return parent[x] = find(parent, parent[x]);
    }
}
