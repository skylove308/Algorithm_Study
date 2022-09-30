package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
	static class Shark {
		int x, y, size;

		public Shark(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	static int n;
	static int[][] map;
	static Queue<Shark> shark;
	static boolean[][] visited;
	static Shark cur_shark;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		shark = new LinkedList<>();
		visited = new boolean[n][n];
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark.add(new Shark(i, j, 2)); // 아기 상어 초기 위치
					map[i][j] = 0; // 지나다녀야 하므로 0으로 바꿔서 표기
					visited[i][j] = true; // 시작 지점 visit체크
				}
			}
		}
		int ans = 0; // 정답 변수
		int cnt = 0; // 아기상어가 자기 몸 크기만큼 물고기를 먹으면 진화할 수 있도록 세어주는 변수
		while (true) {
			int time = bfs();
			if (time == -1) // 더 이상 먹을 물고기가 없으면 break
				break;
			else {
				shark.clear(); // shark queue 비워주고 다시 시작
				ans += time; // 정답에 아기상어가 물고기 먹는데 소요한 시간 더해줌
				cnt++;
				if (cnt == cur_shark.size) { // 아기 상어가 먹은 물고기 수가 자기 사이즈랑 같다면 아기 상어 진화
					shark.add(new Shark(cur_shark.x, cur_shark.y, cur_shark.size + 1));
					cnt = 0;
				} else // 보다 작으면 진화 안함
					shark.add(new Shark(cur_shark.x, cur_shark.y, cur_shark.size));
			}
			visited = new boolean[n][n]; // 다시 bfs시작하므로 방문 배열 초기화
			//print();
		}
		System.out.println(ans);
	}

	// map 프린트 함수
	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int bfs() {
		int cnt = 0; // 아기 상어가 이동한 횟수
		while (!shark.isEmpty()) {
			List<Shark> temp = new LinkedList<>(); // for문을 도는 동안 먹을 수 있는 물고기가 있다면 temp에 저장
			int size = shark.size();
			for (int t = 0; t < size; t++) {
				Shark s = shark.poll();
				int px = s.x;
				int py = s.y;
				if (map[px][py] != 0 && s.size > map[px][py]) { // 아기 상어가 맵의 크기보다 크다면 먹을 수 있으므로 temp리스트에 추가
					temp.add(s);
				}

				for (int i = 0; i < 4; i++) {
					int nx = px + dx[i];
					int ny = py + dy[i];
					if (nx < 0 || ny < 0 || nx >= n || ny >= n) // 배열 범위 바깥 패스
						continue;
					if (visited[nx][ny]) // 방문한 지역이면 패스
						continue;
					if (map[nx][ny] > s.size) // 아기 상어보다 사이즈가 큰 물고기가 있어서 못 지나가면 패스
						continue;
					shark.add(new Shark(nx, ny, s.size));
					visited[nx][ny] = true;
				}
			}
			if (!temp.isEmpty()) { // 아기상어가 먹을 수 있는 물고기가 있다면 
				Collections.sort(temp, (o1, o2) -> { // 위, 왼쪽 순으로 먼저 먹을 수 있도록 정렬
					if (o1.x == o2.x)
						return o1.y - o2.y;
					return o1.x - o2.x;
				});
				
				cur_shark = temp.get(0); // 첫번째 상어가 조건에 가장 만족하는 물고기이므로 이걸 먹는다.
				map[cur_shark.x][cur_shark.y] = 0; // 물고기 먹었으므로 map에서 0으로 표기
				return cnt;
			}
			cnt++;
		}
		return -1; // 먹을 수 있는 물고기가 없었다.
	}

}
