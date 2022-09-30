package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_8958 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int i = 0; i < n; i++) {
			int cnt = 0;
			int point = 0;
			char[] str = new StringTokenizer(br.readLine()).nextToken().toCharArray();
			for(char c: str) {
				if(c == 'O') {
					cnt++;
					point += cnt;
				}
				else if(c == 'X') {
					cnt = 0;
				}
			}
			System.out.println(point);
		}
	}
}
