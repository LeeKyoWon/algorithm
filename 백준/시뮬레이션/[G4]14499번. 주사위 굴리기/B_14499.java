import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14499 {

    static int N;
    static int M;
    static int x;
    static int y;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice();
        // 바닥은 6, 위는 1

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int command = Integer.parseInt(st.nextToken());
            if(rollDice(command, dice)) {
                sb.append(dice.one).append("\n");
            }
        }

        System.out.println(sb);
    }

    static boolean rollDice(int command, Dice dice) {
        boolean flag = false;

        switch(command) {
            case 1:
                if (y < M - 1) {
                    flag = true;
                    dice.rollEast();
                    y++;
                    if (map[x][y] > 0) {
                        dice.six = map[x][y];
                        map[x][y] = 0;
                    } else {
                        map[x][y] = dice.six;
                    }
                }
                break;
            case 2:
                if (y > 0) {
                    flag = true;
                    y--;
                    dice.rollWest();
                    if (map[x][y] > 0) {
                        dice.six = map[x][y];
                        map[x][y] = 0;
                    } else {
                        map[x][y] = dice.six;
                    }
                }
                break;
            case 3:
                if (x > 0) {
                    flag = true;
                    dice.rollNorth();
                    x--;
                    if (map[x][y] > 0) {
                        dice.six = map[x][y];
                        map[x][y] = 0;
                    } else {
                        map[x][y] = dice.six;
                    }
                }
                break;
            case 4:
                if (x < N - 1) {
                    flag = true;
                    dice.rollSouth();
                    x++;
                    if (map[x][y] > 0) {
                        dice.six = map[x][y];
                        map[x][y] = 0;
                    } else {
                        map[x][y] = dice.six;
                    }
                }
        }
        return flag;
    }

    static class Dice{
        int one;
        int two;
        int three;
        int four;
        int five;
        int six;

        void rollEast() {
            int temp = one;
            one = four;
            four = six;
            six = three;
            three = temp;
        }

        void rollWest() {
            int temp = one;
            one = three;
            three = six;
            six = four;
            four = temp;
        }

        void rollNorth() {
            int temp = one;
            one = five;
            five = six;
            six = two;
            two = temp;
        }

        void rollSouth(){
            int temp = one;
            one = two;
            two = six;
            six = five;
            five = temp;
        }
    }
}
