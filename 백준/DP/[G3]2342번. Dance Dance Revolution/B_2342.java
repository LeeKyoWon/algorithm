import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2342 {

    public static int INF = 500_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> input = new ArrayDeque<>();
        int x = Integer.parseInt(st.nextToken());
        while (x != 0) {
            input.add(x);
            x = Integer.parseInt(st.nextToken());
        }

        int size = input.size();
        int[][][] dp = new int[size + 1][5][5];

        for (int i = 0; i <= size; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[0][0][0] = 0;
        for (int i = 1; i <= size; i++) {
            int next = input.poll();
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    dp[i][j][next] = Math.min(dp[i][j][next], dp[i-1][j][k] + getPower(k, next));
                    dp[i][next][k] = Math.min(dp[i][next][k], dp[i-1][j][k] + getPower(j, next));
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ans = Math.min(ans, dp[size][i][j]);
            }
        }

        System.out.println(ans);
    }

    public static int getPower(int x, int y){
        if(x==0) {
            return 2;
        } else if (x==y) {
            return 1;
        } else if ((x+y)%2 == 0) {
            return 4;
        } else {
            return 3;
        }
    }
}
