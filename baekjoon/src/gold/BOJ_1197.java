package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {
	static class Edge implements Comparable<Edge>{
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
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		ArrayList<Edge>[] adjList = new ArrayList[V+1];
		boolean[] visited = new boolean[V+1];
		for(int i=1; i<=V; i++) {
			adjList[i] = new ArrayList<>();
		}
		int a=0, b, c;
		for(int i=0; i<E; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
			adjList[b].add(new Edge(a, c));
		}
		
		pq.add(new Edge(a, 0));
		
		int vertexCnt = 0;
		int cost = 0;
		while(!pq.isEmpty()) {
			Edge minE = pq.poll();
			
			if(visited[minE.V]) continue;
			
			visited[minE.V] = true;
			cost += minE.cost;
			vertexCnt++;
			if(vertexCnt==V) break;
			
			for(Edge e : adjList[minE.V]) {
				if(!visited[e.V])
					pq.add(e);
			}
		}
		
		System.out.println(cost);
	}
}
