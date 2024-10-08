# [Gold 4] 수 나누기 게임 - 27172 

[문제 링크](https://www.acmicpc.net/problem/27172) 

### 주요한 생각의 흐름

- 에라토스테네스의 체가 소수 판별하는 아이디어를 문제에 적용

	-> 주어진 각각의 수에 대해서 하나씩 최댓값 이내의 모든 배수에 대해 비교하는 방식

	-> 최댓값 이내의 모든 배수에 대해서 해당 배수가 주어진 수의 배열에 속하면 대결 수행하는 방식 

- 사용되는 3가지 배열

	1. 크기 1_000_001 짜리 boolean 배열을 만들어서 입력 받은 수는 true로 변경

	2. 크기 1_000_001 짜리 int 배열을 만들어서 대결 수행할 때마다 해당 수들 더하거나, 빼준다

	3. 입력 받은 수들은 순서대로 크기 N짜리 int 배열에 저장해서 하나씩 빼가며 해당 수의 배수 중 boolean이 true인 것에 대하여 대결 수행
 
---

### 성능 요약

메모리: 33240 KB, 시간: 360 ms

### 알고리즘 종류

- 수학 : 에라토스테네스의 체

### 제출 일자

2024년 9월 17일

### 문제 설명

《보드게임컵》을 준비하다 지친 은하는 보드게임컵 참가자들을 경기장에 몰아넣고 결투를 시키는 게임 《수 나누기 게임》을 만들었습니다.

《수 나누기 게임》의 규칙은 다음과 같습니다.

- 게임을 시작하기 전 각 플레이어는 1부터 1,000,000 사이의 수가 적힌 서로 다른 카드를 잘 섞은 뒤 한 장씩 나눠 가집니다.

- 매 턴마다 플레이어는 다른 플레이어와 한 번씩 결투를 합니다.

- 결투는 서로의 카드를 보여주는 방식으로 진행되며, 플레이어의 카드에 적힌 수로 다른 플레이어의 카드에 적힌 수를 나눴을 때, 나머지가 0이면 승리합니다. 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나누어 떨어지면 패배합니다. 둘 다 아니라면 무승부입니다.

- 승리한 플레이어는 1점을 획득하고, 패배한 플레이어는 1점을 잃습니다. 무승부인 경우 점수의 변화가 없습니다.

- 본인을 제외한 다른 모든 플레이어와 정확히 한 번씩 결투를 하고 나면 게임이 종료됩니다.


《수 나누기 게임》의 결과를 가지고 한별이와 내기를 하던 은하는 게임이 종료되기 전에 모든 플레이어의 점수를 미리 알 수 있을지 궁금해졌습니다. 은하를 위해 각 플레이어가 가지고 있는 카드에 적힌 수가 주어졌을 때, 게임이 종료된 후의 모든 플레이어의 점수를 구해주세요.

### 입력 

- 첫 번째 줄에 플레이어의 수 N이 주어집니다.

- 두 번째 줄에 첫 번째 플레이어부터 N번째 플레이어까지 각 플레이어가 가지고 있는 카드에 적힌 정수 x1, . . ., xN 이 공백으로 구분되어 주어집니다.


### 출력 

- 첫 번째 플레이어부터 N번째 플레이어까지 게임이 종료됐을 때의 각 플레이어의 점수를 공백으로 구분하여 출력해주세요.
