package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5656 {
	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static int N, W, H, min;
	static int[][] map, temp_map, print;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			temp_map = new int[H][W];
			print = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					temp_map[i][j] = map[i][j];
				}
			}
			min = Integer.MAX_VALUE;
			shoot(0);
			sb.append("#" + tc + " " + min + "\n");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					temp_map[i][j] = map[i][j];
				}
			}
		}
		System.out.println(sb);
	}

	static void shoot(int n) {
		if (n == N) {
			int remain_block = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (temp_map[i][j] != 0) {
						remain_block++;
					}
				}
			}
			min = Math.min(min, remain_block);
			return;
		}

		int[][] m = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				m[i][j] = temp_map[i][j];
			}
		}

		boolean chk = false;
		for (int i = 0; i < W; i++) {
			boolean check = false;
			for (int j = 0; j < H; j++) {
				if (temp_map[j][i] != 0) {
					explode(new Point(j, i, temp_map[j][i]));
					int[][] temp = new int[H][W];
					for (int a = 0; a < W; a++) {
						int cnt = H - 1;
						for (int b = H - 1; b >= 0; b--) {
							if (temp_map[b][a] != 0) {
								temp[cnt][a] = temp_map[b][a];
								cnt--;
							}
						}
					}

					for (int a = 0; a < H; a++) {
						for (int b = 0; b < W; b++) {
							temp_map[a][b] = temp[a][b];
						}
					}
					check = true;
					chk = true;
					break;
				}
			}
			if (check) {
				shoot(n + 1);
				for (int a = 0; a < H; a++) {
					for (int b = 0; b < W; b++) {
						temp_map[a][b] = m[a][b];
					}
				}
			}
		}
		if (!chk) {
			min = 0;
			return;
		}
	}

	static void explode(Point p) {
		temp_map[p.x][p.y] = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j <= p.z; j++) {
				int nx = p.x + dx[i] * (j - 1);
				int ny = p.y + dy[i] * (j - 1);
				if (nx < 0 || ny < 0 || nx >= H || ny >= W)
					continue;
				if (temp_map[nx][ny] == 0)
					continue;
				explode(new Point(nx, ny, temp_map[nx][ny]));
			}
		}
	}

}
