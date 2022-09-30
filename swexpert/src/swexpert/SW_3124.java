package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW_3124 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점 갯수
			int E = Integer.parseInt(st.nextToken()); // 간선 갯수
			ArrayList<Edge>[] adjList = new ArrayList[V+1];
			for(int i=1; i<=V; i++) {
				adjList[i] = new ArrayList<>();
			}
			for(int i=1; i<=E; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjList[v1].add(new Edge(v2, weight));
				adjList[v2].add(new Edge(v1, weight));
			}
			
			boolean[] visited = new boolean[V+1];
			long result = 0;
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(1, 0));
			
			int vertexCnt = 0;
			while(!pq.isEmpty()) {
				Edge minEdge = pq.poll();
				
				if(visited[minEdge.v]) continue;
				
				result += minEdge.weight;
				visited[minEdge.v] = true;
				vertexCnt++;
				if(vertexCnt == V) break;
				
				pq.addAll(adjList[minEdge.v]);
				for(Edge edge: adjList[minEdge.v]) {
					if(!visited[edge.v])
						pq.add(edge);
				}
			}
			
			System.out.println("#"+test_case+" "+result);
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			if(this.weight == o.weight) {
				return this.v - o.v;
			}
			return this.weight - o.weight;
		}
		
	}
}
