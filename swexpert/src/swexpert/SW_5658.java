package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW_5658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			LinkedList<String> q = new LinkedList<>();
			String s = br.readLine();
			for (int i = 0; i < N; i++) {
				q.add(Character.toString(s.charAt(i)));
			}
			int num_len = N / 4;
			TreeSet<Integer> password = new TreeSet<>((o1, o2) -> {
				return o2 - o1;
			});

			for (int i = 0; i <= num_len; i++) {
				q.add(q.removeFirst());
				for (int j = 0; j < 4; j++) {
					String s2 = "";
					for (int k = 0; k < num_len; k++) {
						s2 += q.get(k + j * num_len);
					}
					password.add(Integer.parseInt(s2, 16));
				}
			}
			sb.append("#" + tc + " " + password.toArray()[K - 1] + "\n");
		}
		
		System.out.println(sb);
	}

}
