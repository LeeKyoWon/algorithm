import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 유니온 파인드
 */
public class B_16724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N==1 && M==1) {
            br.readLine();
            System.out.println(1);
            return;
        }

        int[] parent = new int[N*M];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char command = s.charAt(j);
                int cur = M*i + j;

                switch(command) {
                    case 'U':
                        union(parent, cur, cur-M);
                        break;
                    case 'D':
                        union(parent, cur, cur+M);
                        break;
                    case 'L':
                        union(parent, cur, cur-1);
                        break;
                    case 'R':
                        union(parent, cur, cur+1);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<parent.length; i++) {
            set.add(find(parent, i));
        }

        System.out.println(set.size());
    }

    public static boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if(x == y) {
            return false;
        } else if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }

        return true;
    }

    public static int find(int[] parent, int x){
        if(x==parent[x]) return x;
        return parent[x] = find(parent, parent[x]);
    }
}
