package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1949 {
	static class Point {
		int x, y, h;
		boolean isCut;
		boolean[][] visited;

		public Point(int x, int y, int h, boolean isCut, boolean[][] visited) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.isCut = isCut;
			this.visited = visited;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			ArrayList<Point> top = new ArrayList<>();
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] > max) {
						max = map[i][j];
						top.clear();
						top.add(new Point(i, j, map[i][j], false, new boolean[N][N]));
					} else if (map[i][j] == max) {
						top.add(new Point(i, j, map[i][j], false, new boolean[N][N]));
					}
				}
			}

			Queue<Point> q = new LinkedList<>();
			for (int t = 0; t < top.size(); t++) {
				q.add(top.get(t));
			}

			int len = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				for (int s = 0; s < size; s++) {
					Point p = q.poll();
					p.visited[p.x][p.y] = true;
					for (int d = 0; d < 4; d++) {
						int nx = p.x + dx[d];
						int ny = p.y + dy[d];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;

						if (p.visited[nx][ny])
							continue;

						if (p.h > map[nx][ny]) {
							p.visited[nx][ny] = true;
							boolean[][] temp = new boolean[N][N];
							for(int i=0; i<N; i++) {
								for(int j=0; j<N; j++) {
									temp[i][j] = p.visited[i][j];
								}
							}
							q.add(new Point(nx, ny, map[nx][ny], p.isCut, temp));
							p.visited[nx][ny] = false;
							continue;
						}

						if (p.h <= map[nx][ny] && !p.isCut) {
							for (int k = 1; k <= K; k++) {
								if (p.h > map[nx][ny] - k) {
									p.visited[nx][ny] = true;
									boolean[][] temp = new boolean[N][N];
									for(int i=0; i<N; i++) {
										for(int j=0; j<N; j++) {
											temp[i][j] = p.visited[i][j];
										}
									}
									q.add(new Point(nx, ny, map[nx][ny] - k, true, temp));
									p.visited[nx][ny] = false;
								}
							}
						}
					}
				}
				len++;
			}

			sb.append("#" + tc + " " + len + "\n");
		}

		System.out.println(sb);
	}
}
