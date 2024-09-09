import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 우선순위 큐 (응용 문제 -> 다양한 우선순위 활용)
 */
public class B_1202 {

    static long ans ;
    static PriorityQueue<Jewelry> min_heap;
    static PriorityQueue<Jewelry> max_heap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] C = new int[K];

        // 무게가 낮은 보석이 먼저 오게하는 큐
       min_heap = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o1.m - o2.m;
            }
        });

       // 가격이 높은 보석이 먼저 오게하는 큐
        max_heap = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.v - o1.v;
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(m==0) {
                max_heap.add(new Jewelry(m,v));
                continue;
            }
            min_heap.add(new Jewelry(m, v));
        }

        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(C);

        for(int i=0; i<C.length; i++) {
            int temp = getMaxJewelry(C[i]);
            if(temp == -1) {
                continue;
            }
            ans += temp;
        }

        System.out.println(ans);
    }

    public static int getMaxJewelry(int capacity) {
        while(!min_heap.isEmpty()) {
            Jewelry cur = min_heap.poll();

            if(cur.m <= capacity) {
                max_heap.add(cur);
            } else {
                min_heap.add(cur);
                break;
            }
        }

        if(!max_heap.isEmpty()) {
            return max_heap.poll().v;
        }

        return -1;
    }

    public static class Jewelry {
        int m;
        int v;

        public Jewelry(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
