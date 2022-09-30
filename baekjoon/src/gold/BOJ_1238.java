package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1238 {
	static class Edge {
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight =weight;
		}
	}
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N명의 학생 및 도시 번호
		int M = Integer.parseInt(st.nextToken()); // 간선의 갯수 M
		int X = Integer.parseInt(st.nextToken()); // 파티가 벌어질 도시 번호
		
		// 간선 정보를 저장할 인접리스트 생성
		ArrayList<Edge>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 간선 정보 인접리스트로 받음
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			adjList[A].add(new Edge(B, T));
		}
		// 문제 입력 끝
		
		int max = 0; // N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간
		// 각각의 학생에 대하여 다익스트라 알고리즘을 사용해 X까지의 최단 시간 구함
		for(int i=1; i<=N; i++) {
			// 문제를 해결하기 위한 다익스트라 알고리즘 구현
			int itoiTime = 0; // i에서 X로 가서 다시 i로 돌아오는 시간
			// i에서 X로 가는 최단 시간 구하기
			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)-> {
				return o1.weight - o2.weight;
			});
			
			boolean[] visited = new boolean[N+1]; // visited 배열 초기화
			int[] time = new int[N+1]; // i번에서 출발해서 각 마을에 도착할 때 까지의 최단 시간을 갱신하는 배열
			for(int j=1; j<=N; j++) {
				time[j] = Integer.MAX_VALUE; // 최대 시간으로 초기화
			}
		
			pq.add(new Edge(i, 0));
			time[i] = 0;
			while(!pq.isEmpty()) {
				Edge minE = pq.poll();
				
				if(minE.v == X) break;

				if(visited[minE.v]) continue;

				visited[minE.v] = true;
				
				for(Edge e: adjList[minE.v]) {
					if(!visited[e.v] && time[e.v] > time[minE.v] + e.weight) {
						time[e.v] = time[minE.v] + e.weight;
						pq.add(new Edge(e.v, time[e.v]));
					}
				}
			}
			
			itoiTime += time[X];
			
			// X에서 i로 가는 최단 시간 구하기
			pq = new PriorityQueue<>((o1, o2)-> {
				return o1.weight - o2.weight;
			});
			
			visited = new boolean[N+1]; // visited 배열 초기화
			time = new int[N+1]; // i번에서 출발해서 각 마을에 도착할 때 까지의 최단 시간을 갱신하는 배열
			for(int j=1; j<=N; j++) {
				time[j] = Integer.MAX_VALUE; // 최대 시간으로 초기화
			}
		
			pq.add(new Edge(X, 0));
			time[X] = 0;
			
			while(!pq.isEmpty()) {
				Edge minE = pq.poll();
				
				if(minE.v == i) break;
				
				if(visited[minE.v]) continue;

				visited[minE.v] = true;
				
				for(Edge e: adjList[minE.v]) {
					if(!visited[e.v] && time[e.v] > time[minE.v] + e.weight) {
						time[e.v] = time[minE.v] + e.weight;
						pq.add(new Edge(e.v, time[e.v]));
					}
				}
			}
			
			itoiTime += time[i];
			
			max = Math.max(max, itoiTime);
		}
		
		System.out.println(max);
		
	}
}
