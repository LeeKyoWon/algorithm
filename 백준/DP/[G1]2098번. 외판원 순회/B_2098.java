import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP + 비트마스킹 (어려운 문제 ...)
 *
 *  - 생각해야 할 논리적 흐름들
 *
 *  1. DP 의 Top-Down 방식을 활용해 문제를 풀었다
 *
 *  2. no_cycle 과 not_visit 을 구분해줘야 한다.
 *    : 이유 -> 이전 과정의 기록을 남기기 위해 not_visit 을 활용하는 데,
 *              no_cycle은 visit 했지만 결과적으로 경로가 없었던 것이므로
 *              no_cycle 을 not_visit 과 동일한 값을 사용하면 이미 방문 했음에도 방문하지 않은 기록으로 남아 계속 연산하게 됨
 *
 *  3. 비트마스킹으로 방문했던 점들 표현
 *    : Ex) N : 6일 때, 000101 -> 6개의 점 중 1, 3번 점을 방문했다.
 *
 *  4. 비트연산으로 아직 방문하지 않은 점 구하기
 *    : 방문했던 점에 대한 비트와 아직 방문하지 않은 비트 간의 & 연산을 하면 0이 된다
 *    : Ex) N : 6, visited = 000101 일 때, 이번에 찾아볼 점이 4 (001000) 이라면
 *          000101 & 001000 -> 000000 이 된다.
 */
public class B_2098 {

    static int N;
    static final int no_cycle = 20_000_000;
    static final int not_visit = 40_000_000;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        dp = new int[N+1][1<<N];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j <= N; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
            }
            Arrays.fill(dp[i], not_visit);
        }

        System.out.println(tsp(1, 0));
    }

    public static int tsp(int now, int visited) {

        visited = visited | 1 << (now-1);

        // 이미 다른 경로를 탐색하던 과정에서 최적화 완료된 경로
        if(dp[now][visited] != not_visit) {
            return dp[now][visited];
        }

        // 순회 과정의 마지막 경유지
        if(visited == (1 << N) - 1) {

            // 마지막에 다시 1로 돌아가는 경로가 없어서 순회가 결국 불가능하다고 판정된 경우
            if(map[now][1] == 0){
                return no_cycle;
            }

            return map[now][1];
        }

        for (int next = 1; next <= N; next++) {
            if( (visited & (1 << next-1)) == 0) {
                if (map[now][next] != 0) {
                    int temp = tsp(next, visited) + map[now][next];

                    if(dp[now][visited] > temp)
                        dp[now][visited] = temp;
                }
            }
        }

        return dp[now][visited];
    }
}
