package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136 {
	static int end;
	static int total_count;
	static int[] possible_size = { 0, 5, 25, 70, 150, 250 };
	static int[][] arr;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					total_count++;
			}
		}
		int[] paper = { 0, 5, 5, 5, 5, 5 };
		dfs(0, 5, paper, total_count);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static void dfs(int cnt, int size, int[] paper, int total_count) {
		if (min <= cnt)
			return;
		
		if (total_count == 0) {
			min = cnt;
			return;
		}
		
		if (possible_size[size] < total_count) {
			return;
		}
		
		if (size == 1) {
			int count = 0;
			for (int i = 0; i <= 10 - size; i++) {
				for (int j = 0; j <= 10 - size; j++) {
					if (arr[i][j] == 1) {
						count++;
					}
				}
			}

			min = Math.min(min, cnt + count);
			return;
		}

		for (int i = 0; i <= 10 - size; i++) {
			for (int j = 0; j <= 10 - size; j++) {
				if (isFilled(i, j, size)) {

					if (paper[size] == 0)
						continue;

					for (int k = i; k < i + size; k++) {
						for (int l = j; l < j + size; l++) {
							arr[k][l] = 0;
						}
					}
					paper[size] -= 1;
					dfs(cnt + 1, size, paper, total_count - size * size);

					for (int k = i; k < i + size; k++) {
						for (int l = j; l < j + size; l++) {
							arr[k][l] = 1;
						}
					}
					paper[size] += 1;
				}
			}
		}

		dfs(cnt, size - 1, paper, total_count);

	}

	static void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static boolean isFilled(int x, int y, int size) {
		boolean check = true;
		loop: for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (arr[i][j] == 0) {
					check = false;
					break loop;
				}
			}
		}
		return check;
	}
}
