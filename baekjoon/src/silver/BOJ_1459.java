package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1459 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		long W = Long.parseLong(st.nextToken());
		long S = Long.parseLong(st.nextToken());
		

		if(2*W>S) {
			long Z = Math.max(X, Y);
			if(Z%2==0)
				System.out.println(Math.min(W*Math.abs(X-Y)+S*Math.min(X, Y), S*Z));
			else
				System.out.println(Math.min(W*Math.abs(X-Y)+S*Math.min(X, Y), S*(Z-1)+W));
		}else {
			System.out.println(W*(X+Y));
		}
		
		
	}
}
