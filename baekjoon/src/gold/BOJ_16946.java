package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946 {
	static class Point {
		int x, y, idx, cnt;
		public Point(int x, int y, int idx, int cnt) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		Point[][] map2 = new Point[N][M];
		int idx = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='0' && !visited[i][j]) {
					Queue<Point> q = new LinkedList<>();
					Queue<Point> q2 = new LinkedList<>();
					q.add(new Point(i, j, 0, 0));
					q2.add(new Point(i, j, 0, 0));
					visited[i][j] = true;
					while(!q2.isEmpty()) {
						Point p = q2.poll();
						for(int k=0; k<4; k++) {
							int nx = p.x + dx[k];
							int ny = p.y + dy[k];
							if(nx<0||ny<0||nx>=N||ny>=M)
								continue;
							if(visited[nx][ny])
								continue;
							if(map[nx][ny]=='1')
								continue;
							q2.add(new Point(nx, ny, 0, 0));
							visited[nx][ny] = true;
							q.add(new Point(nx, ny, 0, 0));
						}
					}
					int size = q.size();
					while(!q.isEmpty()) {
						Point p = q.poll();
						map2[p.x][p.y] = new Point(p.x, p.y, idx, size);
					}
					idx++;
				}
			}
		}

		int[][] map3 = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]=='1') {
					ArrayList<Integer> index = new ArrayList<>();
					int cnt = 0;
					for(int k=0; k<4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(nx<0||ny<0||nx>=N||ny>=M)
							continue;
						if(map[nx][ny]=='1')
							continue;
						if(map2[nx][ny] == null)
							continue;
						if(index.contains(map2[nx][ny].idx))
							continue;
						cnt += map2[nx][ny].cnt;
						index.add(map2[nx][ny].idx);
					}
					map3[i][j] = (cnt+1) % 10;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map3[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
