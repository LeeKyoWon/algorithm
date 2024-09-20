import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N+1];
        ArrayList<Integer>[] singer_order = new ArrayList[N+1];

        for (int i = 0; i <= N; i++) {
            singer_order[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            int cur = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num-1; j++) {
                int next = Integer.parseInt(st.nextToken());
                in[next]++;
                singer_order[cur].add(next);
                cur = next;
            }
        }

        Queue<Integer> singer_q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            if(in[i] == 0) {
                singer_q.add(i);
                visited[i] = true;
                sb.append(i).append("\n");
            }
        }

        while(!singer_q.isEmpty()) {
            int cur = singer_q.poll();

            for(int singer : singer_order[cur]) {
                in[singer]--;

                if(in[singer] == 0) {
                    singer_q.add(singer);
                    visited[singer] = true;
                    sb.append(singer).append("\n");
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(sb);
    }
}
