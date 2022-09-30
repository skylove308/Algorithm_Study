package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {
	static int n, m;
	static int[] sequence, result;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sequence = new int[n];
		result = new int[m];
		sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sequence[i] = i+1;
		}
		comb(0, 0);
		System.out.println(sb);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt==m) {
			for(int i=0; i<m; i++) {
				sb.append(result[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=idx; i<n; i++) {
			result[cnt] = sequence[i];
			comb(idx, cnt+1);
		}
	}
}
