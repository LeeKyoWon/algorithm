import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class B_10830_2 {
    public static int N;
    public static int[][] origin;	// A^1 일 때의 배열(초기 배열)

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        origin = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] ans = pow(origin, B);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(ans[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }


    // 행렬 제곱 분할정복 메소드
    public static int[][] pow(int[][] A, long exp) {

        // 지수가 1일 땐 A를 return한다.
        if(exp == 1L) {
            return A;
        }

        // 지수를 절반으로 분할하여 재귀호출
        int[][] result_mat = pow(A, exp / 2);

        result_mat = multiply(result_mat, result_mat);

        if(exp % 2 == 1L) {
            result_mat = multiply(result_mat, origin);
        }

        return result_mat;
    }


    // o1과 o2 행렬을 곱해주는 메소드
    public static int[][] multiply(int[][] mat1, int[][] mat2) {

        int[][] result_mat = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {

                    result_mat[i][j] += mat1[i][k] * mat2[k][j];
                    result_mat[i][j] %= 1000;
                }
            }
        }
        return result_mat;
    }
}
