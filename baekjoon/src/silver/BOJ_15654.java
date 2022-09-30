package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15654 {
	static int n, m;
	static int[] sequence, result;
	static boolean[] used;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sequence = new int[n];
		for(int i=0; i<n; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sequence);
		result = new int[m];
		used = new boolean[n];
		sb = new StringBuilder();
		perm(0);
		System.out.println(sb);
	}
	
	static void perm(int idx) {
		if(idx == m) {
			for(int i=0; i<m; i++) {
				sb.append(result[i]+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(used[i]) continue;
			result[idx] = sequence[i];
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
}