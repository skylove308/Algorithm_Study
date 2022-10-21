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
			String str = "";
			for (int i = 0; i < 2; i++) {
				if (diff[i])
					str += 1;
				else
					str += 0;
			}

			switch (str) {
			case "11":
				ans = 1;
				break;
			default:
				break;
			}
		} else if (N == 3) {
			String str = "";
			for (int i = 0; i < 3; i++) {
				if (diff[i])
					str += 1;
				else
					str += 0;
			}

			switch (str) {
			case "000":
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
			case "010":
				ans = 3;
			default:
				break;
			}
		} else {
			for (int i = 0; i <= N - 3; i++) {
				compare(i);
			}

			boolean check1 = false;
			for (int i = 0; i < N; i++) {
				if (diff[i]) {
					check1 = true;
					break;
				}
			}

			int cnt1 = Integer.MAX_VALUE;
			if(!check1)
				cnt1 = ans;
			
			ans = 0;
			
			for(int i=0; i<N; i++) {
				diff[i] = origin_diff[i];
			}
			
			diff[0] = !diff[0];
			diff[1] = !diff[1];
			ans+=1;
			for (int i = 0; i <= N - 3; i++) {
				compare(i);			
			}

			boolean check2 = false;
			for (int i = 0; i < N; i++) {
				if (diff[i]) {
					check2 = true;
					break;
				}
			}

			int cnt2 = Integer.MAX_VALUE;
			if(!check2)
				cnt2 = ans;
			
			ans = 0;
			
			for(int i=0; i<N; i++) {
				diff[i] = origin_diff[i];
			}
			
			diff[0] = !diff[0];
			diff[1] = !diff[1];
			diff[2] = !diff[2];
			ans+=1;
			
			for (int i = 0; i <= N - 3; i++) {
				compare(i);			
			}

			boolean check3 = false;
			for (int i = 0; i < N; i++) {
				if (diff[i]) {
					check3 = true;
					break;
				}
			}

			int cnt3 = Integer.MAX_VALUE;
			if(!check3)
				cnt3 = ans;

			
			ans = Math.min(Math.min(cnt1, cnt2), cnt3);
			
			if(ans==Integer.MAX_VALUE)
				ans = -1;

		}

		System.out.println(ans);

	}

	static void compare(int idx) {
		String str = "";
		for (int i = 0; i < 3; i++) {
			if (diff[idx + i])
				str += 1;
			else
				str += 0;
		}

		switch (str) {
		case "000":
			break;
		case "011":
			if (idx < N - 3) {
				diff[idx + 1] = !diff[idx + 1];
				diff[idx + 2] = !diff[idx + 2];
				diff[idx + 3] = !diff[idx + 3];
			}
			ans += 1;
			break;
		case "111":
			diff[idx] = !diff[idx];
			diff[idx + 1] = !diff[idx + 1];
			diff[idx + 2] = !diff[idx + 2];
			ans += 1;
			break;
		case "100":
			if (idx < N - 3) {
				diff[idx] = !diff[idx];
				diff[idx + 3] = !diff[idx + 3];
			}
			ans += 2;
			break;
		default:
			break;
		}
	}
}
