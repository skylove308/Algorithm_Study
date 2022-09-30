package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3985 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int l = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int[] cake = new int[l];
		int max_pieces = 0;
		int expected_max_pieces = 0;
		int max_pnum = 0;
		int expected_max_pnum = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pieces = 0;
			int expected_pieces = 0;
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int j = a - 1; j < b; j++) {
				expected_pieces++;
				if(cake[j] == 0) {
					cake[j] = i + 1;
					pieces++;
				}
			}
			if(pieces > max_pieces) {
				max_pieces = pieces;
				max_pnum = i + 1;
			}
			if(expected_pieces > expected_max_pieces) {
				expected_max_pieces = expected_pieces;
				expected_max_pnum = i + 1;
			}
		}
		System.out.println(expected_max_pnum);
		System.out.println(max_pnum);
	}
}
