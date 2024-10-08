# [Gold 2] 보석 도둑 - 1202 

[문제 링크](https://www.acmicpc.net/problem/1202) 

### 주요한 생각의 흐름

- 우선순위 큐와 그리디의 적절한 조화

	- 무게 오름차순 우선순위 큐

	- 가격 내림차순 우선순위 큐

	- 용량 기준으로 오름차순 정렬된 가방들에 대하여 현재 무게보다 작거나 같으면서 가장 가치가 높은 보석을 꺼내도록 설계 

---

### 성능 요약

메모리: 120624 KB, 시간: 1396 ms

### 알고리즘 종류

그리디, 우선순위 큐

### 제출 일자

2024년 9월 9일

### 문제 설명

<p>세계적인 도둑 상덕이는 보석점을 털기로 결심했다.

상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 가방에는 최대 한 개의 보석만 넣을 수 있다.

상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.</p>

### 입력 

- 첫째 줄에 N과 K가 주어진다. (1 ≤ N, K ≤ 300,000)

- 다음 N개 줄에는 각 보석의 정보 Mi와 Vi가 주어진다. (0 ≤ Mi, Vi ≤ 1,000,000)

- 다음 K개 줄에는 가방에 담을 수 있는 최대 무게 Ci가 주어진다. (1 ≤ Ci ≤ 100,000,000)

- 모든 숫자는 양의 정수이다.

### 출력 

- 첫째 줄에 상덕이가 훔칠 수 있는 보석 가격의 합의 최댓값을 출력한다.

