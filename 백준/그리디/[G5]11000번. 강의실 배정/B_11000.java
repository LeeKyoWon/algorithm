import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
* 그리디 
*/
public class B_11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Lesson> lessons = new PriorityQueue<>();
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lessons.add(new Lesson(start, end));
        }

        while(!lessons.isEmpty()) {
            Lesson cur = lessons.poll();

            if(!rooms.isEmpty()) {
                if (rooms.peek() <= cur.start) {
                    rooms.poll();
                }
            }
            rooms.add(cur.end);
        }

        System.out.println(rooms.size());

    }

    static class Lesson implements Comparable<Lesson>{
        int start;
        int end;

        Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lesson o) {
            if(this.start == o.start) {
                return this.end - o.end;
            } else {
                return this.start - o.start;
            }
        }
    }
}
