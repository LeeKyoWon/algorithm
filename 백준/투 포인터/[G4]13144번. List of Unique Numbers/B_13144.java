import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] cnt = new int[100_001];
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = 0;

        long ans = 0;

        cnt[arr[0]]++;
        while(l < N) {

            // r 을 중복을 찾을떄까지 이동
            while(r+1 < N && cnt[arr[r+1]] == 0) {
                r++;
                cnt[arr[r]]++;
            }

            // l 을 움직이며 정답에 반영한다
            ans += r - l + 1;
            
            // 이미 처리된 l 이 가리키는 것에 대해서는 cnt를 다시 감소시킨다 
            cnt[arr[l++]]--;
        }

        System.out.println(ans);
    }
}
