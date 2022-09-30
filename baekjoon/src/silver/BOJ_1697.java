package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
	static int n, k;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		visited[n] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int t=0; t<size; t++) {
				int x = q.poll();
				if(x==k) return cnt;
				for(int i=0; i<3; i++) {
					if(i==0) {
						int nx = x - 1;
						if(nx<0||nx>100000) continue;
						if(visited[nx]) continue;
						q.add(nx);
						visited[nx] = true;
					}
					else if(i==1) {
						int nx = x + 1;
						if(nx<0||nx>100000) continue;
						if(visited[nx]) continue;
						q.add(nx);
						visited[nx] = true;
					}
					else {
						int nx = x * 2;
						if(nx<0||nx>100000) continue;
						if(visited[nx]) continue;
						q.add(nx);
						visited[nx] = true;
					}
				}
			}
			cnt++;
		}
		return -1;
		
	}
}
