package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_9229 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		for(int test_case=1; test_case<=t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int[] snack = new int[n];
			for(int i=0; i<n; i++) {
				snack[i] = Integer.parseInt(st2.nextToken());
			}
			int max = -1;
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					if(snack[i]+snack[j] <= m) {
						max = max > snack[i] + snack[j] ? max : snack[i] + snack[j];
					}
				}
			}
			System.out.println("#"+test_case+" "+max);
		}

	}
	
}
