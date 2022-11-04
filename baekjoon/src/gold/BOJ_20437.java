package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_20437 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			int min = Integer.MAX_VALUE;
			int max = 0;
			ArrayList<Integer>[] arr = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				arr[i] = new ArrayList<>();
			}
			for (int i = 0; i < W.length(); i++) {
				arr[W.charAt(i) - 'a'].add(i);
			}

			for (int i = 0; i < 26; i++) {
				for (int j = 0; j + K - 1 < arr[i].size(); j++) {
					min = Math.min(min, arr[i].get(j + K - 1) - arr[i].get(j) + 1);
					max = Math.max(max, arr[i].get(j + K - 1) - arr[i].get(j) + 1);
				}
			}

			if (max == 0)
				sb.append(-1 + "\n");
			else
				sb.append(min + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
