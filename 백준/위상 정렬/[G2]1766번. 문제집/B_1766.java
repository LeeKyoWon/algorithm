import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] problem_order = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            problem_order[i] = new ArrayList<>();
        }

        int[] d = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            problem_order[pre].add(post);
            d[post]++;
        }

        PriorityQueue<Integer> problem_q = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(d[i] == 0) {
                problem_q.add(i);
            }
        }

        while(!problem_q.isEmpty()) {
            int cur = problem_q.poll();
            sb.append(cur).append(" ");

            for(int next : problem_order[cur]) {
                d[next]--;

                if(d[next] == 0) {
                    problem_q.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
