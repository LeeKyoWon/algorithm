import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이분 탐색
 */
public class B_1011 {

    static long[] arr = new long[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i=1; i<arr.length; i++) {
            arr[i] = arr[i-1] + (i-1)/2 + 1;
        }

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int ans = getCount(0, arr.length-1, y-x);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static int getCount(int l, int r, int x) {

        while(l <= r) {
            int mid = (l+r)/2;
            if (arr[mid] < x)
                l = mid+1;
            else if (arr[mid] > x)
                r = mid-1;
            else {
                return mid;
            }
        }

        return l;
    }
}
