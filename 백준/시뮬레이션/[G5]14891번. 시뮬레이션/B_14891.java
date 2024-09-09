import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시뮬레이션
 *
 * - 문제를 제대로 파악하지 못했음 ( 마주한 두 극이 다르면 같아질 때까지 계속 회전시켜야 되는 것으로 착각 )
 * - 재귀의 아이디어를 떠올리지 못해서 헤맸음
 */
public class B_14891 {

    static final int N = 0;
    static final int S = 1;

    static int[][] wheel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        wheel = new int[5][9];
        for (int i = 1; i <= 4; i++) {
            String s = br.readLine();
            for (int j = 1; j <= 8; j++) {
                wheel[i][j] = s.charAt(j-1) - 48;
            }
        }

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheel_num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            operation(wheel_num, direction);
        }

        int ans = 0;
        for(int i=1; i<=4; i++) {
            if(wheel[i][1] == S) {
                switch(i) {
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 2;
                        break;
                    case 3:
                        ans += 4;
                        break;
                    case 4:
                        ans += 8;
                }
            }

        }

        System.out.println(ans);
    }

    public static void operation(int num, int direction) {
        int reverse_direction = direction == 1 ? -1 : 1;

        left_op(num-1, reverse_direction);
        right_op(num+1, reverse_direction);
        rotate(num, direction);
    }

    public static void left_op (int num, int direction) {
        int reverse_direction = direction == 1 ? -1 : 1;

        if(num < 1)
            return;

        if(wheel[num][3] != wheel[num+1][7]) {
            left_op(num - 1, reverse_direction);
        } else {
            return;
        }

        rotate(num, direction);
    }

    public static void right_op (int num, int direction) {
        int reverse_direction = direction == 1 ? -1 : 1;

        if (num > 4)
            return;

        if(wheel[num][7] != wheel[num-1][3]) {
            right_op(num+1, reverse_direction);
        } else{
            return;
        }

        rotate(num, direction);
    }

    public static void rotate(int num, int direction) {

        // 시계 방향
        if(direction == 1) {
            wheel[num][0] = wheel[num][8];

            for(int i=7; i>=0; i--) {
                wheel[num][i+1] = wheel[num][i];
            }
        }

        // 반시계 방향
        else {
            wheel[num][0] = wheel[num][1];
            for(int i=2; i<=8; i++) {
                wheel[num][i-1] = wheel[num][i];
            }
            wheel[num][8] = wheel[num][0];
        }


    }
}
