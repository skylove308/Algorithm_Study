package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1149 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] house = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N; i++) {
			house[i][0] += Math.min(house[i-1][1], house[i-1][2]);
			house[i][1] += Math.min(house[i-1][0], house[i-1][2]);
			house[i][2] += Math.min(house[i-1][0], house[i-1][1]);
		}
		
		System.out.println(Collections.min(Arrays.asList(house[N-1][0],house[N-1][1],house[N-1][2])));
	}
}
