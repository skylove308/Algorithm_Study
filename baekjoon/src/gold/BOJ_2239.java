package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2239 {
	static int[][] puzzle;
	static int[][] ans;
	static boolean end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		puzzle = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				puzzle[i][j] = s.charAt(j) - '0';
			}
		}
		ans = new int[9][9];
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] != 0) {
					cnt++;
				}
			}
		}
		sudoku(cnt);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(ans[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void sudoku(int cnt) {
		if (end)
			return;
		// print();
		if (cnt == 81) {
			end = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					ans[i][j] = puzzle[i][j];
				}
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (isGood(i, j, k)) {
							puzzle[i][j] = k;
							sudoku(cnt + 1);
						}
					}
					puzzle[i][j] = 0;
					return;
				}
			}
		}
	}

	static boolean isGood(int x, int y, int z) {
		for (int i = 0; i < 9; i++) {
			if (puzzle[x][i] == z)
				return false;
		}
		for (int i = 0; i < 9; i++) {
			if (puzzle[i][y] == z)
				return false;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle[i + 3 * (x/3)][j + 3 * (y/3)] == z)
					return false;
			}
		}

		return true;
	}

	static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(puzzle[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
