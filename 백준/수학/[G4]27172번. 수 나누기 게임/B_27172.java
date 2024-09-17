import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 에라토스테네스의 체 응용 문제
 * 에라토스테네스의 체의 아이디어를 적용하는 것이 시간복잡도 측면에서 월등하다.
 */
public class B_27172 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        boolean[] arr = new boolean[1_000_001];
        int[] scores = new int[1_000_001];
        int[] numbers = new int[N];


        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0; i<N; i++){
            int x = Integer.parseInt(st.nextToken());
            arr[x] = true;
            numbers[i] = x;

            if(max < x)
                max = x;
        }

        for (int i = 0; i < N; i++) {
            int x = numbers[i];
            for(int j=x*2; j<=max; j+=x) {
                if(arr[j]) {
                    scores[x]++;
                    scores[j]--;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(scores[numbers[i]]).append(" ");
        }

        System.out.println(sb);

    }
}
