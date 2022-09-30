package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	static class Edge {
		int v, weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
	static int n, max_idx;
	static ArrayList<Edge>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree[a].add(new Edge(b, cost));
			tree[b].add(new Edge(a, cost));
		}
		
		if(n == 1) {
			System.out.println(0);
		}
		else {
			bfs(1);
			System.out.println(bfs(max_idx));
		}
		
		
	}
	
	static int bfs(int x) {
		boolean[] visited = new boolean[n+1];
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(x, 0));
		visited[x] = true;
		
		int max_dist = 0;
		
		while(!q.isEmpty()) {
			Edge E = q.poll();
			
			if(E.weight > max_dist) {
				max_dist = E.weight;
				max_idx = E.v;
			}
			
			for(Edge e: tree[E.v]) {
				if(visited[e.v]) continue;
				visited[e.v] = true;
				q.add(new Edge(e.v, E.weight+e.weight));
			}
		}
		
		return max_dist;
	}
}
