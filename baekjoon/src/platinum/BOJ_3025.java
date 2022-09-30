package platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 좌표 (x, y)
class Points {
	int x, y;

	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_3025 {
	static int r, c; // 세로 r, 가로 c 크기
	static Stack<Points>[] route; // x열에서 돌을 떨구었을 때 그 경로를 저장해주는 스택
	static char[][] map; // 전체 맵

	public static void main(String[] args) throws IOException {
		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		route = new Stack[c + 1];
		for (int i = 1; i <= c; i++) {
			route[i] = new Stack<>();
		}
		map = new char[r][c + 1];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 1; j <= c; j++) {
				map[i][j] = s.charAt(j - 1);
			}
		}

		int n = Integer.parseInt(br.readLine()); // 돌 갯수
		for (int t = 0; t < n; t++) {
			int pj = Integer.parseInt(br.readLine()); // 돌을 떨군 시작 열 pj
			int i = 0; // 현재 행
			int j = pj; // 현재 열

			if (!route[pj].isEmpty()) { // 이미 경로가 존재한다면 마지막 지점을 불러온다.
				i = route[pj].peek().x;
				j = route[pj].peek().y;
				while (map[i][j] == 'O') { // 불러온 좌표에 이미 돌덩이가 있다면 그 전 경로를 불러온다.
					route[pj].pop();
					i = route[pj].peek().x;
					j = route[pj].peek().y;
				}
			}

			while (true) {
				i++;

				if (i == r || map[i][j] == 'X') { // 벽이나 바닥에 도달하면 돌이 굳음
					map[i - 1][j] = 'O';
					route[pj].pop();
					break;
				}

				if (map[i][j] == '.') { // 뚫려 있으면 진행 및 경로 저장
					route[pj].add(new Points(i, j));
					continue;
				}

				if (map[i][j] == 'O') { // 굳은 돌을 만나면 왼쪽 아니면 오른쪽으로 가거나 그 위에 굳는다
					if (j > 1 && map[i - 1][j - 1] == '.' && map[i][j - 1] == '.') { // 왼쪽이 뚫려 있을 때
						j--;
						i--;
					} else if (j < c && map[i - 1][j + 1] == '.' && map[i][j + 1] == '.') { // 오른쪽이 뚫려 있을 때
						j++;
						i--;
					} else { // 왼쪽 혹은 오른쪽으로 이동이 불가능하면 그 위에 그대로 돌이 굳는다.
						map[i - 1][j] = 'O';
						route[pj].pop();
						break;
					}
				}
			}
		}
		print();
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < r; i++) {
			for (int j = 1; j <= c; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
