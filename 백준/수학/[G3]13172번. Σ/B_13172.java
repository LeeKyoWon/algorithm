import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주의사항 : 부동소수점 연산을 활용하는 Math.pow() 는 Long 값 계산에 오차가 발생한다
 */
public class B_13172 {

    public static final int P = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        int[] N = new int[M];
        int[] S = new int[M];

        long sum = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N[i] = Integer.parseInt(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken());

            // 기약분수로 만들기 위해 최대 공약수 활용
            int gcd_value = 0;
            if(N[i] > S[i])
                gcd_value = gcd(N[i], S[i]);
            else
                gcd_value = gcd(S[i], N[i]);

            if (gcd_value > 1) {
                N[i] /= gcd_value;
                S[i] /= gcd_value;
            }

            // N[i] 의 역원 구하기
            long tmp = getPow(N[i], P-2);
            sum = (sum + (S[i] * tmp) % P) % P;
        }

        System.out.println(sum);
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    public static long getPow(int x, int exp) {
        if(exp == 1) {
            return x;
        }

        long result = getPow(x, exp/2);
        result = result * result % P ;

        if(exp % 2 == 1) {
            result = (result * x) % P;
        }

        return result;
    }
}
