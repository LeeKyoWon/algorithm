# [Gold 5] 로봇 청소기 - 14503

[문제 링크](https://www.acmicpc.net/problem/14503) 

### 주요한 생각의 흐름
 
- 경우의 수를 따져서 상황에 맞게 구현해야하는 시뮬레이션 문제 

---

### 성능 요약

- 메모리: 14420 KB, 시간: 112 ms

### 알고리즘 종류

- 구현, 시뮬레이션

### 제출 일자

2024년 10월 28일

### 문제 설명

로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

로봇 청소기가 있는 방은 N X M 크기의 직사각형으로 나타낼 수 있으며, 1 X 1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다. 방의 각 칸은 좌표 (r, c)로 나타낼 수 있고, 가장 북쪽 줄의 가장 서쪽 칸의 좌표가 (0, 0), 가장 남쪽 줄의 가장 동쪽 칸의 좌표가 (N-1, M-1)이다. 즉, 좌표 (r, c)는 북쪽에서 (r+1)번째에 있는 줄의 서쪽에서 (c+1)번째 칸을 가리킨다. 처음에 빈 칸은 전부 청소되지 않은 상태이다.

로봇 청소기는 다음과 같이 작동한다.

1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.

2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우, 

	  2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.

	  2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.

3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우, 

	  3-1. 반시계 방향으로 90도 회전한다.

	  3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.

	  3-3. 1번으로 돌아간다.

### 입력 

- 첫째 줄에 방의 크기 N과 M이 입력된다. (3 <= N, M <= 50)  

- 둘째 줄에 처음에 로봇 청소기가 있는 칸의 좌표 (r, c)와 처음에 로봇 청소기가 바라보는 방향 d가 입력된다. d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽을 바라보고 있는 것이다.

- 셋째 줄부터 N개의 줄에 각 장소의 상태를 나타내는 N X M개의 값이 한 줄에 M개씩 입력된다. i번째 줄의 j번째 값은 칸 (i, j)의 상태를 나타내며, 이 값이 0인 경우 (i, j)가 청소되지 않은 빈 칸이고, 1인 경우 (i, j)에 벽이 있는 것이다. 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다. 

- 로봇 청소기가 있는 칸은 항상 빈 칸이다.

### 출력 

- 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
