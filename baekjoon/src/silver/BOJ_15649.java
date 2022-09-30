package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {
	static int[] result;
	static boolean[] used;
	static int n, m;
	public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		result = new int[m];
		used = new boolean[n];
		perm(0);
	}
	
	static void perm(int idx) {
		if(idx==m) {
			for(int i=0; i<m; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(used[i]) continue;
			result[idx] = i+1;
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
		
	}
}
