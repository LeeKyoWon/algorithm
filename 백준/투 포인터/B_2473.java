import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 투 포인터
 */
public class B_2473 {

    static final long INF = 3_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long min = 3_000_000_000L;
        int[] ans = new int[3];

        for(int i=0; i<N-2; i++) {
            int K = arr[i];
            int l = i+1;
            int r = arr.length-1;

            while(l < r) {

                long tmp = Math.abs((long)K+arr[l]+arr[r]);
                if(tmp < min) {
                    min = tmp;
                    ans[0] = arr[i];
                    ans[1] = arr[l];
                    ans[2] = arr[r];
                }

                if(arr[l] + arr[r] < -K){
                    l++;
                } else if (arr[l] + arr[r] > -K) {
                    r--;
                } else {
                    System.out.println(arr[i] + " " + arr[l] + " " + arr[r]);
                    return;
                }
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
