package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20056 {
	static class Fireball {
		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Fireball>[][] map = new LinkedList[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				map[i][j] = new LinkedList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Fireball(r, c, m, s, d));
		}

		for (int h = 0; h < K; h++) {
			LinkedList<Fireball>[][] map2 = new LinkedList[N + 1][N + 1];
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= N; j++) {
					map2[i][j] = new LinkedList<>();
				}
			}
			// 이동하는 중
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j].isEmpty())
						continue;

					int size = map[i][j].size();
					for (int k = 0; k < size; k++) {
						Fireball fb = map[i][j].poll();
						int nx = fb.r;
						int ny = fb.c;
						for (int l = 0; l < fb.s; l++) {
							nx += dx[fb.d];
							ny += dy[fb.d];
							if (nx == 0)
								nx = N;
							if (ny == 0)
								ny = N;
							if (nx == N + 1)
								nx = 1;
							if (ny == N + 1)
								ny = 1;
						}

						map2[nx][ny].add(new Fireball(nx, ny, fb.m, fb.s, fb.d));
					}
				}
			}
			
			// 합쳐지는 중
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map2[i][j].size() <= 1)
						continue;

					int m2 = 0;
					int s2 = 0;
					int d2 = -1; // 방향 계속 일치하면 1 or 2 다르면 3
					int size = map2[i][j].size();
					for (int k = 0; k < size; k++) {
						m2 += map2[i][j].get(k).m;
						s2 += map2[i][j].get(k).s;
						if (d2 == -1)
							d2 = map2[i][j].get(k).d % 2;
						else {
							if (d2 != map2[i][j].get(k).d % 2)
								d2 = 3;
						}
					}

					map2[i][j].clear();
					if (m2 / 5 != 0) {
						if (d2 == 3) {
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 1));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 3));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 5));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 7));

						} else {
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 0));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 2));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 4));
							map2[i][j].add(new Fireball(i, j, m2 / 5, s2 / size, 6));
						}
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					for (int k = 0; k < map2[i][j].size(); k++) {
						map[i][j].add(map2[i][j].get(k));
					}
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int size = map[i][j].size();
				for (int k = 0; k < size; k++) {
					ans += map[i][j].poll().m;
				}
			}
		}

		System.out.println(ans);

	}
}
