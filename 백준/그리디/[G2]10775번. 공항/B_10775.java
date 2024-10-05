import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_10775 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G+1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 0; i < P; i++) {
            int x = Integer.parseInt(br.readLine());

            int next = find(x);
            if(next == 0){
                break;
            } else {
                ans++;
                union(x, next-1);
            }
        }
        System.out.println(ans);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x > y)
            parent[x] = y;
        else if (x < y)
            parent[y] = x;
    }

    public static int find(int x) {
        if (x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
