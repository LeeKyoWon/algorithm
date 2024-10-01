import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[] invited = new boolean[n+1];
        invited[1] = true;

        ArrayList<Integer>[] friends = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        int ans = 0;

        for(int i : friends[1]) {

            if(!invited[i]) {
                invited[i] = true;
                ans++;
            }

            for(int j : friends[i]) {

                if(!invited[j]) {
                    invited[j] = true;
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
