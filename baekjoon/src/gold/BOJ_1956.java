package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1956 {
	static class Edge {
		int v, cost;

		public Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] adjList = new ArrayList[V + 1];
		for(int i=0; i<=V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
		}

		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
				return o1.cost - o2.cost;
			});
			boolean[] visited = new boolean[V+1];
			int[] dist = new int[V+1];
			for(int j=1; j<=V; j++) {
				dist[j] = Integer.MAX_VALUE;
			}
			
			boolean isStart = true;
			pq.add(new Edge(i, 0));
			while(!pq.isEmpty()) {
				Edge minE = pq.poll();
				if(isStart) 
					isStart = false;
				else {
					if(minE.v == i)
						break;
					visited[minE.v] = true;
				}
				for(Edge e: adjList[minE.v]) {
					if(visited[e.v])
						continue;
					if(dist[e.v] > minE.cost + e.cost) {
						dist[e.v] = minE.cost + e.cost;
						pq.add(new Edge(e.v, dist[e.v]));
					}
				}
			}
			
			min = Math.min(min, dist[i]);

		}
		
		if(min==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}
}
