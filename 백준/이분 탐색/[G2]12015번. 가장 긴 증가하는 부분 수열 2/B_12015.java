import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 이분 탐색
 *  -> 상황에 맞게 최적화를 하기 위해 필요한 경우 특정 인덱스의 값 교체
 *   Ex) 20 뒤에 15가 나오면 20의 자리를 15가 대신 차지하는 것이 최적화 관점에서 맞다
 *
 *   10 20 10 30 15 25 27 50
 *    -> 10 20 30
 *    -> 10 15 30 ( 20 자리를 15가 차지 )
 *    -> 10 15 25 27 50 ( 30 자리를 25가 차지 )
 */
public class B_12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> ans_list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        ans_list.add(Integer.parseInt(st.nextToken()));

        for (int i = 1; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            int idx = getIndex(ans_list, x);

            if(idx >= ans_list.size()) {
                ans_list.add(x);
            } else {
                ans_list.set(idx, x);
            }
        }


        System.out.println(ans_list.size());
    }

    public static int getIndex(ArrayList<Integer> ans_list, int x) {
        int l = 0;
        int r = ans_list.size()-1;

        while(l <= r) {
            int m = (l+r)/2;
            int mid_value = ans_list.get(m);

            if(x < mid_value) {
                r = m-1;
            } else if (x > mid_value) {
                l = m+1;
            } else {
                return m;
            }
        }

        return l;
    }
}
