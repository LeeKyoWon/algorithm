# [Gold 4] List Of Unique Numbers - 13144

[문제 링크](https://www.acmicpc.net/problem/13144) 

### 주요한 생각의 흐름
 
- 1 ~ 10만 사이의 수가 입력으로 오고, 중복을 체크해야 하기 때문에

	- 크기 100_001짜리 int[]  cnt 를 활용해서 중복된 수 여부를 체크한다

- 투 포인터의 l, r 중 r 을 움직여가며 중복된 수를 발견하면 ( 중복 idx1, 중복 idx2 )
  
  1. l 을 현재 위치부터 중복 idx1 까지 움직이며 r - l + 1 을 "정답 경우의 수"에 더한다

  2. l 을 움직인다면 l 이 가리키고 있던 것은 정답에 반영되어 처리가 완료된 것이기 때문에 cnt를 감소시켜준다 

	3. l 이 중복 idx1 까지 처리하여 cnt에 반영하면 기존 중복이던 수는 더 이상 중복이 아니게 된다.

	4. 따라서 r 를 다시 중복을 찾을 때까지 움직인다.

- 위 과정을 l 이 입력된 배열의 끝에 다다를 때까지 반복한다.

---

### 성능 요약

- 메모리: 25316 KB, 시간: 260 ms

### 알고리즘 종류

- 투 포인터

### 제출 일자

2024년 10월 18일

### 문제 설명

길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수를 구하는 프로그램을 작성하여라.

### 입력 

- 첫 번째 줄에는 수열의 길이 N이 주어진다. (1 ≤ N ≤ 100,000)

- 두 번째 줄에는 수열을 나타내는 N개의 정수가 주어진다. 

- 수열에 나타나는 수는 모두 1 이상 100,000 이하이다.

### 출력 

- 조건을 만족하는 경우의 수를 출력한다.
