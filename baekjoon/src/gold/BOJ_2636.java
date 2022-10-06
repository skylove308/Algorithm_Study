package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, cheese;
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
		cheese = 0;
		ArrayList<Point> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1)
					cheese++;
				if(i==0||j==0||i==N-1||j==M-1) {
					if(map[i][j]==0)
						arr.add(new Point(i, j));
				}
			}
		}
		int time = 0;
		int remain_cheese = 0;
		while(cheese>0) {
			remain_cheese = cheese;
			visited = new boolean[N][M];
			for(int i=0; i<arr.size(); i++) {
				int x = arr.get(i).x;
				int y = arr.get(i).y;
				if(!visited[x][y]) {
					bfs(x, y);
				}
			}
			//print();
			time++;
		}
		System.out.println(time);
		System.out.println(remain_cheese);
	}
	
	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx<0||ny<0||nx>=N||ny>=M) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==1) {
					map[nx][ny] = 0;
					visited[nx][ny] = true;
					cheese--;
					continue;
				}
				q.add(new Point(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
