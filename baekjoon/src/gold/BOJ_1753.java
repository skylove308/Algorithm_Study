package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753 {
	static class Edge implements Comparable<Edge> {
		int V, cost;

		public Edge(int V, int cost) {
			this.V = V;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge>[] adjList = new ArrayList[V + 1];
		boolean[] visited = new boolean[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
		}

		int[] dist = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		pq.add(new Edge(K, 0));
		dist[K] = 0;

		int vertexCnt = 0;
		while (!pq.isEmpty()) {
			Edge minE = pq.poll();

			if (visited[minE.V])
				continue;
			if (vertexCnt == V)
				break;
			visited[minE.V] = true;
			vertexCnt++;
			for (Edge e : adjList[minE.V]) {
				if (!visited[e.V] && dist[minE.V] + e.cost < dist[e.V]) {
					dist[e.V] = e.cost + dist[minE.V];
					pq.add(new Edge(e.V, dist[e.V]));
				}

			}
		}

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}
}
