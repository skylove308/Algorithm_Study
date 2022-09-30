package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1247 {
	static int[] customers;
	static int n, min;
	static boolean[] used;
	static int[][] customerPos;
	static int[] housePos;
	static int[] companyPos;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=t; test_case++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			companyPos = new int[2];
			companyPos[0] = Integer.parseInt(st.nextToken());
			companyPos[1] =	Integer.parseInt(st.nextToken());
			housePos = new int[2];
			housePos[0] = Integer.parseInt(st.nextToken());
			housePos[1] = Integer.parseInt(st.nextToken());
			customerPos = new int[n][2];
			for(int i=0; i<n; i++) {
				customerPos[i][0] = Integer.parseInt(st.nextToken());
				customerPos[i][1] = Integer.parseInt(st.nextToken());
			}
			customers = new int[n];
			used = new boolean[n];
			min = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#"+test_case+" "+min);
		}
	}
	
	static void perm(int idx) {
		if(idx == n) {
			int distance = 0;
			distance += calDistance(companyPos, customerPos[customers[0]]);
			for(int i=0; i<n-1; i++) {
				distance += calDistance(customerPos[customers[i]], customerPos[customers[i+1]]);
			}
			distance += calDistance(customerPos[customers[n-1]], housePos);
			min = Math.min(min, distance);	
			return;
		}
		for(int i=0; i<n; i++) {
			if(used[i]) continue;
			customers[idx] = i;
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
	
	static int calDistance(int[] a, int[] b) {
		return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
	}
}
