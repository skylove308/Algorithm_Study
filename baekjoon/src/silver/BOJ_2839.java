package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int i = 0;
		int ans = 0;
		while(true) {
			if (n - 3*i < 0) {
				ans = -1;
				break;
			}
				
			if ((n - 3*i) % 5 == 0) {
				ans = i + (n - 3*i) / 5;
				break;
			}
			else {
				i++;
			}
		}
		System.out.println(ans);
	}
}
