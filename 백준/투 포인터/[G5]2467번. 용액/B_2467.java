import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 투 포인터
 */
public class B_2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0;
        int r = n-1;

        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];

        while(l < r) {
            int sum = arr[l] + arr[r];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                ans[0] = arr[l];
                ans[1] = arr[r];
            }

            if(sum > 0) {
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                System.out.println(arr[l] + " " + arr[r]);
                return;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
