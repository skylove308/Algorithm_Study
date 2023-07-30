package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16988 {
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					
				}
				if(map[i][j] == 2) {
					bfs(i, j);
				}
			}
		}
	}
	
	static int bfs(int x, int y) {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0||ny<0||nx>=N||ny>=M||visited[nx][ny]||map[nx][ny]==1)
					continue;
				if(map[nx][ny]==0) {
					cnt++;
					visited[nx][ny] = true;
				}
				if(map[nx][ny]==2) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
					
			}
		}
		
		return cnt;
	}
	
	static void dfs(int cnt) {
		if(cnt == 2)
			return;
		
		
		
	}
	
}
