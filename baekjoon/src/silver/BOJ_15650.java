package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {
	static int[] sequence, result;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			sequence = new int[n];
			for(int i=0; i<n; i++) {
				sequence[i] = i+1;
			}
			result = new int[m];
			comb(0, 0);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt==m) {
			for(int i=0; i<m; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=idx; i<n; i++) {
			result[cnt] = sequence[i];
			comb(i+1, cnt+1);
		}
	}
}
