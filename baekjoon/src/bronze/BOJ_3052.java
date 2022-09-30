package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3052 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] remainder42 = new int[42];
		int cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 10; i++) {
			int j = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()) % 42;
			if(remainder42[j] == 0) {
				remainder42[j]++;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
