import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_30805 {

    static int[] A;
    static int[] B;
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A_num = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[A_num];
        for (int i = 0; i < A_num; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int B_num = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        B = new int[B_num];
        for (int i = 0; i < B_num; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int[] idx = new int[]{-1,-1};

        while(idx != null) {
            if(idx[0] == A.length-1 || idx[1] == B.length-1) {
                break;
            }
            idx = getMaxValue(idx[0]+1, idx[1]+1);
        }

        System.out.println(cnt);
        System.out.println(sb);
    }

    // 배열 A와 배열 B의 특정 인덱스 범위의 원소들 중 공통된 최대의 원소를 찾아내는 함수
    // 검색할 인덱스 범위
    // 1. A -> start_idx1 ~ A.length-1
    // 2. B -> start_idx2 ~ B.length-1
    public static int[] getMaxValue(int start_idx1, int start_idx2) {
        int[] temp = new int[A.length - start_idx1];
        int[] temp2 = new int[B.length - start_idx2];

        int j=0;
        for(int i=start_idx1; i<A.length; i++) {
            temp[j++] = A[i];
        }

        j=0;
        for(int i=start_idx2; i<B.length; i++) {
            temp2[j++] = B[i];
        }

        Arrays.sort(temp);
        Arrays.sort(temp2);

        int idx1 = temp.length-1;
        int idx2 = temp2.length-1;

        int max = 0;

        while(idx1 >= 0 && idx2 >= 0) {
            if(temp[idx1] == temp2[idx2]) {
                max = temp[idx1];
                break;
            } else if(temp[idx1] > temp2[idx2] && idx1 > 0) {
                idx1--;
            } else if(temp2[idx2] > temp[idx1] && idx2 > 0){
                idx2--;
            } else {
                break;
            }
        }

        int max_idx1 = 0;
        int max_idx2 = 0;
        if(max > 0) {
            cnt++;
            sb.append(max).append(" ");

            for (int i = start_idx1; i < A.length; i++) {
                if(max == A[i]){
                    max_idx1 = i;
                    break;
                }
            }

            for (int i = start_idx2; i < B.length; i++) {
                if(max == B[i]){
                    max_idx2 = i;
                    break;
                }
            }

            return new int[]{max_idx1, max_idx2};
        } else {
            return null;
        }
    }

}
