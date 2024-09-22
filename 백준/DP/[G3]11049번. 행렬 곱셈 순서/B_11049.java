import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] matrices = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrices[i][0] = Integer.parseInt(st.nextToken());
            matrices[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];

        for(int i=0; i<N-1; i++) {
            dp[i][i+1] = matrices[i][0] * matrices[i][1] * matrices[i+1][1];
        }

        for(int i=2; i<N; i++) {
            for (int j = 0; j < N - i; j++) {
                int min = Integer.MAX_VALUE;
                for(int k=0; k < i; k++) {
                    int tmp = dp[j][j+k] + dp[j+k+1][j+i] + matrices[j][0] * matrices[j+k][1] * matrices[j+i][1];
                    if (tmp < min) {
                        min = tmp;
                    }
                }
                dp[j][j+i] = min;
            }
        }

        System.out.println(dp[0][N-1]);
    }



    public static class Matrix {
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
