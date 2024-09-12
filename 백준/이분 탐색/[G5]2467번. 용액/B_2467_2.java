import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2467_2 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            arr[i] = x;
        }

        for(int i=0; i<n-1; i++) {
            int[] tmp = binarySearch(i+1, n-1, arr[i]);

            if(tmp[0] == 0) {
                ans[0] = arr[i];
                ans[1] = tmp[1];
                break;
            }

            if(min > tmp[0]) {
                min = tmp[0];
                ans[0] = arr[i];
                ans[1] = tmp[1];
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }

    public static int[] binarySearch(int l, int r, int x){
        int[] ret = new int[2];
        int min = Integer.MAX_VALUE;

        while(l<=r) {
            int mid = (l+r)/2;

            int sum = x + arr[mid];

            if(min > Math.abs(sum)) {
                min = Math.abs(sum);
                ret[0] = min;
                ret[1] = arr[mid];
            }

            if(sum < 0) {
                l = mid+1;
            } else if (sum > 0) {
                r = mid-1;
            } else {
                return new int[]{0, arr[mid]};
            }
        }

        return new int[]{ret[0], ret[1]};
    }
}
