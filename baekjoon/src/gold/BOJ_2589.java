package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='L') {
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		
		System.out.println(max);
	}
	
	static int bfs(int x, int y) {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point p = q.poll();
				visited[p.x][p.y] = true;
				for(int i=0; i<4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					
					if(nx<0||ny<0||nx>=N||ny>=M)
						continue;
					
					if(visited[nx][ny])
						continue;
					
					if(map[nx][ny]=='W')
						continue;
					
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
			cnt++;
		}
		
		return cnt-1;
	}
}
