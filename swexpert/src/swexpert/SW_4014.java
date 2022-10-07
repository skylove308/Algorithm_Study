package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				int[] road = new int[N]; // 0은 평지 1은 경사로
				int h = map[i][0];
				boolean check = true; // false시 전체 도로 지을 수 없음
				int j = 1;
				while (j < N) {
					if (h == map[i][j]) { // 평탄한 도로 건설시 pass
						j++;
						continue;
					}
					if (h == map[i][j] + 1) { // 내리막 경사로
						boolean isPossible = true; // 경사로 지을 수 있는가?
						for (int k = 0; k < X; k++) {
							if (j >= N || map[i][j] != h - 1) {
								isPossible = false;
								break;
							} 
							road[j] = 1;
							j++;
						}
						if (!isPossible) {
							check = false;
							break;
						}
						h = map[i][j - 1];
						continue;
					}
					if (h == map[i][j] - 1) { // 오르막 경사로
						boolean isPossible = true; // 경사로 지을 수 있는가?
						for (int k = 0; k < X; k++) {
							j--;
							if (j < 0 || map[i][j] != h || road[j] == 1) {
								isPossible = false;
								break;
							}
							road[j] = 1;
						}
						if (!isPossible) {
							check = false;
							break;
						}
						j += X; // 다시 돌아옴
						h = map[i][j];
						j++;
						continue;
					}
					check = false; // 이외의 경우 도로 지을 수 없음
					break;
				}
				if (check) // 도로 지을 수 있으면 답 +1
					ans++;
			}
			for (int i = 0; i < N; i++) {
				int[] road = new int[N]; // 0은 평지 1은 경사로
				int h = map[0][i];
				boolean check = true; // false시 전체 도로 지을 수 없음
				int j = 1;
				while (j < N) {
					if (h == map[j][i]) { // 평탄한 도로 건설시 pass
						j++;
						continue;
					}
					if (h == map[j][i] + 1) { // 내리막 경사로
						boolean isPossible = true; // 경사로 지을 수 있는가?
						for (int k = 0; k < X; k++) {
							if (j >= N || map[j][i] != h - 1) {
								isPossible = false;
								break;
							}
							road[j] = 1;
							j++;
						}
						if (!isPossible) {
							check = false;
							break;
						}
						h = map[j - 1][i];
						continue;
					}
					if (h == map[j][i] - 1) { // 오르막 경사로
						boolean isPossible = true; // 경사로 지을 수 있는가?
						for (int k = 0; k < X; k++) {
							j--;
							if (j < 0 || map[j][i] != h || road[j] == 1) {
								isPossible = false;
								break;
							}
							road[j] = 1;
						}
						if (!isPossible) {
							check = false;
							break;
						}
						j += X; // 다시 돌아옴
						h = map[j][i];
						j++;
						continue;
					}
					check = false; // 이외의 경우 도로 지을 수 없음
					break;
				}
				if (check) // 도로 지을 수 있으면 답 +1
					ans++;
			}
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);

	}
}
