import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백트래킹
 */
public class B_1759 {

    static StringBuilder sb = new StringBuilder();

    static int L;
    static int C;
    static char[] arr;
    static char[] out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        out = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            char c = st.nextToken().charAt(0);
            arr[i] = c;

        }

        Arrays.sort(arr);

        backtracking(0, 0);
        System.out.println(sb);
    }

    static void backtracking(int cur, int before) {
        if(cur==L) {

            // 모음 개수
            int cnt1 = 0;
            // 자음 개수
            int cnt2 = 0;

            for(int i=0; i<L; i++) {
                char c = out[i];
                if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }

            // 모음 1개 이상, 자음 2개 이상 이어야 출력
            if(cnt1 > 0 && cnt2 > 1) {
                for (int i = 0; i < L; i++) {
                    sb.append(out[i]);
                }
                sb.append("\n");
            }
            return;
        }

         for (int i = before; i < C; i++) {
            out[cur] = arr[i];
            backtracking(cur+1, i+1);
        }
    }
}
