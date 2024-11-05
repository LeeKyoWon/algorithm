import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 그리디 + 우선순위 큐
 */
public class B_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        PriorityQueue<Long> min_q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            min_q.add(arr[i]);
        }

        long ans = 0;
        while(min_q.size() > 1) {
            long x = min_q.poll();
            long y = min_q.poll();

            min_q.add(x+y);
            ans += x+y;
        }

        System.out.println(ans);
    }
}
