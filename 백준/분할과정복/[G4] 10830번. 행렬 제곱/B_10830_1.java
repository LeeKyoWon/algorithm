import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10830_1 {

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int exp= 0;
        long temp = B;
        while(temp > 0) {
            temp /= 2;
            exp++;
        }

        int[][][] mat = new int[exp][N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mat[0][i][j] = (Integer.parseInt(st.nextToken())) % 1000;
            }
        }

        for(int i=1; i<exp; i++) {
            mat[i] = matrix_mul(mat[i-1], mat[i-1]);
        }

        int[][] ans = mat[exp-1];

        // (int) 로 넣으니까 intellij 에서는 정상 동작하지만 백준에서는 틀렸다고 나온다!!
        B -= (long)Math.pow(2, exp-1);
        while(B > 0) {
            temp = B;
            exp= 0;
            while(temp > 0) {
                temp /= 2;
                exp++;
            }
            ans = matrix_mul(ans, mat[exp-1]);
            B -= (long)Math.pow(2, exp-1);
        }

        for (int i = 0; i < N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static int[][] matrix_mul (int[][] mat1, int[][] mat2) {
        int[][] result_mat = new int[N][N];

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int value = 0;
                for(int k = 0; k<N; k++) {
                    value += (mat1[i][k] * mat2[k][j]) % 1000;
                }
                result_mat[i][j] = value % 1000;
            }
        }

        return result_mat;
    }
}
