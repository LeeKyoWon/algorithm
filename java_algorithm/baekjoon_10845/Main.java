package baekjoon_10845;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new LinkedList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int backNum = 0;

        for (int i = 0; i < num; i++) {
            StringTokenizer commandToken = new StringTokenizer(br.readLine(), " ");
            String command = commandToken.nextToken();

            switch(command){
                case "push":
                    int pushNum = Integer.parseInt(commandToken.nextToken());
                    q.add(pushNum);
                    backNum = pushNum;
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        System.out.println(-1);
                    } else
                        System.out.println(q.poll());
                    break;
                case "size":
                    System.out.println(q.size());
                    break;
                case "empty":
                    if (q.isEmpty()) {
                        System.out.println(1);
                    }
                    else
                        System.out.println(0);
                    break;
                case "front":
                    if (q.isEmpty()) {
                        System.out.println(-1);
                    } else
                        System.out.println(q.peek());
                    break;
                case"back":
                    if (q.isEmpty()) {
                        System.out.println(-1);
                    } else
                        System.out.println(backNum);
                    break;
            }
        }
    }
}
