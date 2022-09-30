package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_6603 {
	static int[] lotto, result;
	static int k;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S;
		while(!(S = br.readLine()).equals("0")) {
			StringTokenizer st = new StringTokenizer(S);
			k = Integer.parseInt(st.nextToken());
			lotto = new int[k];
			result = new int[6];
			for(int i=0; i<k; i++) {
				lotto[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0);
			System.out.println();
		}
	}
	
	static void comb(int idx, int cnt) {
		if(cnt==6) {
			for(int i=0; i<6; i++) {
				System.out.print(result[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=idx; i<k; i++) {
			result[cnt] = lotto[i];
			comb(i+1, cnt+1);
		}
	}
}
