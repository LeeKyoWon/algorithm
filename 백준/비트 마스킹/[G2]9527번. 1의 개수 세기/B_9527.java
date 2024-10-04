import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 누적합 + 비트마스킹 [G2]
 *
 * [핵심 아이디어 ]
 *  1. 누적합 -> 답 : getOne[B] - getOne[A-1]
 *
 *  2. power2[i] -> 0 ~ 1...1 ( i+1 개의 1 )
 *    : power2[i-1] * 2 + ( 1 << i )
 *      // - 000 ~ 111 까지의 1의 개수의 합
 *           : 00 ~ 11 까지으 1의 개수의 합 * 2 + 00~11까지의 수의 갯수
 *
 *  3. 1000 1101 에서 맨 앞의 1의 의미
 *    : 0 ~ 0111 1111    +    "1"000 0000 ~ "1"000 1101
 *   -> power2[i-1]      +    (x - (1L<<i) + 1);       ( 여기서의 i는 7 )
 *
 */
public class B_9527 {

    static long[] power2;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long temp = B;
        max = 0;
        while (temp > 0){
            temp /= 2;
            max++;
        }

        // 2^i - 1 까지의 모든 1의 개수를 구해놓는 배열
        power2 = new long[max];
        power2[0] = 1;
        for(int i=1; i<max; i++) {
            power2[i] = power2[i-1] * 2 + (1L << i);
        }

        long ans = getOne(B) - getOne(A-1);

        System.out.println(ans);
    }

    public static long getOne(long x) {
        long ret = x & 1;

        for(int i=max; i>0; i--) {
            if((x & (1L << i)) != 0) {
                ret += power2[i-1] + (x - (1L<<i) + 1);
                x -= (1L << i);
            }
        }

        return ret;
    }
}
