import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 그리디 알고리즘
 *  : 기준 날짜보다 같거나 빨리 피면서 + 가장 늦게 지는 꽃을 고르는 것을
 *    기준 날짜가 12월이 될 때까지 반복한다.
 */
public class B_2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Flower> earliest_heap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start_m = Integer.parseInt(st.nextToken());
            int start_d = Integer.parseInt(st.nextToken());
            int end_m = Integer.parseInt(st.nextToken());
            int end_d = Integer.parseInt(st.nextToken());

            earliest_heap.add(new Flower(new Date(start_m, start_d), new Date(end_m, end_d)));
        }

        Date thresh = new Date(3, 1);
        Date next_thresh = new Date(0, 0);

        int ans = 0;
        while(!earliest_heap.isEmpty()) {
            Flower cur = earliest_heap.poll();

            // 기준 날짜보다 같거나 이르다면
            if (cur.start.compareTo(thresh) <= 0) {

                if(cur.end.compareTo(next_thresh) > 0) {
                    next_thresh = new Date(cur.end.m, cur.end.d);
                }

            } else {
                earliest_heap.add(cur);

                // 이전 기준 날짜보다 새로운 기준날짜가 이후라면 기준 날짜를 갱신한다
                if(next_thresh.compareTo(thresh) > 0) {
                    ans++;
                    thresh.m = next_thresh.m;
                    thresh.d = next_thresh.d;
                }
                // 그렇지 않으면 3월 1일 ~ 11월 30일 사이에 꽃이 피지 않는 빈공간이 발생한 것
                else {
                    System.out.println(0);
                    return;
                }

                // 갱신 결과 변경된 기준 날짜가 12월이라면 종료
                if(next_thresh.m > 11) {
                    System.out.println(ans);
                    return;
                }
            }
        }

        if(next_thresh.m > 11) {
            ans++;
            System.out.println(ans);
        } else {
            System.out.println(0);
        }
    }

    static class Date implements Comparable<Date> {
        int m;
        int d;

        public Date(int m, int d){
            this.m = m;
            this.d = d;
        }

        @Override
        public int compareTo(Date o) {
            if(this.m != o.m) return this.m - o.m;
            return this.d - o.d;
        }
    }

    static class Flower implements Comparable<Flower>{
        Date start;
        Date end;

        public Flower(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(this.start.m == o.start.m) return this.start.d - o.start.d;
            return this.start.m - o.start.m;
        }
    }
}
