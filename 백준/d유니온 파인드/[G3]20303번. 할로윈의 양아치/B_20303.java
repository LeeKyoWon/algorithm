package again;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 유니온 파인드 + DP
 */
public class B_20303 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] children = new int[N+1];

        parent = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            children[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        Group[] candies = new Group[N+1];
        for (int i = 1; i <= N; i++) {
            candies[i] = new Group(0,0);
            int x = find(i);
            candies[x].num += 1;
            candies[x].candy += children[i];
        }

        int[] dp = new int[K];

        for (int i = 1; i <= N; i++) {
            if(candies[i].num == 0)
                continue;

            int cur_num = candies[i].num;
            int cur_candy = candies[i].candy;

            for(int j=K-1; j>=0; j--) {
                if(j >= cur_num) {
                    dp[j] = Math.max(dp[j], dp[j - cur_num] + cur_candy);
                }
            }
        }

        System.out.println(dp[K-1]);
    }

    public static class Group {
        int num;
        int candy;

        public Group(int num, int candy) {
            this.num = num;
            this.candy = candy;
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x < y)
            parent[y] = x;
        else if (y < x)
            parent[x] = y;
    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
