import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 그리디 알고리즘
 */
public class B_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int ans = 0;
        for(int i=arr.length-1; i>0; i-=2) {
            if(arr[i] > 1 && arr[i-1] > 1) {
                visited[i] = true;
                visited[i-1] = true;

                ans += arr[i] * arr[i-1];
            }
        }

        for (int i = 0; i < arr.length-1; i+=2) {
            if (arr[i] < 0 && arr[i+1] < 0) {
                visited[i] = true;
                visited[i+1] = true;

                ans += arr[i] * arr[i+1];
            }

            if (arr[i] < 0 && arr[i+1] == 0) {
                visited[i] = true;
                visited[i+1] = true;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                ans += arr[i];
            }
        }

        System.out.println(ans);
    }
}
