package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138 {
	static boolean[] diff, origin_diff;
	static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] before = new char[N];
		char[] after = new char[N];
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			before[i] = s.charAt(i);
		}
		s = br.readLine();
		for (int i = 0; i < N; i++) {
			after[i] = s.charAt(i);
		}

		diff = new boolean[N];
		origin_diff = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (before[i] != after[i]) {
				diff[i] = true;
				origin_diff[i] = true;
			}
		}

		if (N == 2) {
			if (diff[0] && diff[1])
				ans = 1;
			else if (!diff[0] && !diff[1])
				ans = 0;
			else
				ans = -1;
		} else if (N == 3) {
			String str = "";
			for (int i = 0; i < 3; i++) {
				if (diff[i])
					str += "1";
				else
					str += "0";
			}

			switch (str) {
			case "000":
				ans = 0;
				break;
			case "110":
				ans = 1;
				break;
			case "011":
				ans = 1;
				break;
			case "111":
				ans = 1;
				break;
			case "001":
				ans = 2;
				break;
			case "100":
				ans = 2;
				break;
			case "101":
				ans = 2;
				break;
			case "010":
				ans = 3;
				break;
			default:
				ans = -1;
				break;
			}
		} else {
			for (int i = 0; i <= N - 3; i++) {
				if (diff[i]) {
					diff[i] = !diff[i];
					diff[i + 1] = !diff[i + 1];
					diff[i + 2] = !diff[i + 2];
					ans++;
				}
			}

			boolean check = false;
			for (int i = 0; i < N; i++) {
				if (diff[i]) {
					check = true;
					break;
				}
			}

			int cnt1 = ans;
			if (check)
				cnt1 = Integer.MAX_VALUE;

			ans = 0;
			int cnt2 = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				diff[i] = origin_diff[i];
			}

			diff[0] = !diff[0];
			diff[1] = !diff[1];
			ans++;

			for (int i = 0; i <= N - 3; i++) {
				if (diff[i]) {
					diff[i] = !diff[i];
					diff[i + 1] = !diff[i + 1];
					diff[i + 2] = !diff[i + 2];
					ans++;
				}
			}

			check = false;
			for (int i = 0; i < N; i++) {
				if (diff[i]) {
					check = true;
					break;
				}
			}

			cnt2 = ans;
			if (check)
				cnt2 = Integer.MAX_VALUE;

			ans = Math.min(cnt1, cnt2);

			if (ans == Integer.MAX_VALUE)
				ans = -1;

		}

		System.out.println(ans);
	}

}
