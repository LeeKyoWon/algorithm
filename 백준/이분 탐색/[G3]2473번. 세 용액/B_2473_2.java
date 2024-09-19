import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이분 탐색
 */
public class B_2473_2 {

    static int[] ans = new int[3];
    static int[] arr;
    static long min = 3_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i=0; i<N-2; i++) {
            for(int j=i+1; j<N-1; j++) {
                binarySearch(i,j);
                if (min == 0) {
                    System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
                    return;
                }
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }

    public static void binarySearch(int i, int j) {
        int l = j+1;
        int r = arr.length-1;
        int K = arr[i] + arr[j];

        while(l <= r) {

            int mid = (l+r)/2;
            long sum = (long)arr[i] + arr[j] + arr[mid];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                ans[0] = arr[i];
                ans[1] = arr[j];
                ans[2] = arr[mid];
            }

            if(arr[mid] < -K) {
                l = mid+1;
            } else if (arr[mid] > -K) {
                r = mid-1;
            } else {
                break;
            }
        }
    }
}
