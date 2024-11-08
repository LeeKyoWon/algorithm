import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시뮬레이션
 */
public class B_14890 {

    static int N;
    static int L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        // 가로
        for (int i = 0; i < N; i++) {
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = map[i][j];
            }
            if(isPath(arr))
                ans++;
        }

        for (int i = 0; i < N; i++) {
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = map[j][i];
            }
            if(isPath(arr))
                ans++;
        }

        System.out.println(ans);
    }

    static boolean isPath(int[] arr) {
        boolean[] check = new boolean[N];

        int threshold = arr[0];
        int idx = 0;
        while(idx < N-1) {
            idx++;

            // 이전과 동일한 높이
            if(arr[idx] == threshold)
                continue;

            // 높이 차이가 2 이상
            if(Math.abs(arr[idx] - threshold) > 1)
                return false;

            // 내리막
            if(threshold - arr[idx] == 1) {
                if(idx + L > N)
                    return false;

                // 경사로를 깔 길의 높이가 동일하지 않음
                for(int i=1; i<L; i++) {
                    if(arr[idx] != arr[idx+i]) {
                        return false;
                    }
                }

                // 경사로를 깔고 나서 체크
                for(int i=0; i<L; i++) {
                    check[idx+i] = true;
                }
                threshold = arr[idx];
                idx += L-1;

                // L만큼 더하고 난 뒤에 높이가 높아지면 안됨
                if(idx+L < N && arr[idx] < arr[idx+L])
                    return false;
            }

            // 오르막
            else {
                if(idx < L) {
                    return false;
                }
                // L의 길이만큼 경사로 설치 가능 여부, 이미 다른 경사로가 설치되었는지 여부 체크
                for(int i=0; i<L; i++) {
                    if(arr[idx-1] != arr[idx-1-i] || check[idx-1-i]) {
                        return false;
                    }
                }
                threshold = arr[idx];
            }
        }
        return true;
    }
}
