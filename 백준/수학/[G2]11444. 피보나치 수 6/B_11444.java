import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 피보나치 수열을 행렬로 생각한다는 충격적인 사고의 전환
 */
public class B_11444 {

    public static long[] dp;
    public static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        // 예외처리 하지 않으면 arrMatrixNSquare()의 인자로 0이 들어가 에러 발생
        if(n==1) {
            System.out.println(1);
            return;
        }

        arr = new long[][]{{1,1},{1,0}};

        long[][] ans = getMatrixNSquare(n-1);

        ans = multiplyMatrix(ans, new long[][]{{1},{0}});

        System.out.println(ans[0][0]);
    }

    public static long[][] getMatrixNSquare(long n) {

        if(n==1) {
            return arr;
        }

        long[][] ans = getMatrixNSquare(n / 2);

        ans = multiplyMatrix(ans, ans);

        if(n % 2 == 1) {
            ans = multiplyMatrix(ans, arr);
        }
        return ans;
    }

    public static long[][] multiplyMatrix(long[][] arr1, long[][] arr2) {

        long[][] temp = new long[arr1.length][arr2[0].length];

        for(int i=0; i<arr1.length; i++) {
            for(int j=0; j<arr2[0].length; j++) {
                for(int k=0; k<arr1[0].length; k++) {
                    temp[i][j] += (arr1[i][k] * arr2[k][j]) % 1_000_000_007;
                    temp[i][j] %= 1_000_000_007;
                }
            }
        }

        return temp;
    }

}
