package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3289 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] arr = new int[n+1];
            for(int i=1; i<=n; i++) {
				arr[i] = i;
			}
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<m; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st2.nextToken());
				int a = Integer.parseInt(st2.nextToken());
				int b = Integer.parseInt(st2.nextToken());
				if(p==0) {
					if(find(arr, a)<=find(arr, b)) {
						arr[find(arr, b)] = arr[find(arr, a)];
					}
					else {
						arr[find(arr, a)] = arr[find(arr, b)];
					}
				}
				else {
					if(find(arr, a) == find(arr, b)) 
						sb.append(1);
					else
						sb.append(0);
				}
			}
			System.out.println("#"+test_case+" "+sb);
		}
	}
	
	static int find(int[] parent, int i) {
		if(parent[i] == i)
			return i;
		else
			return parent[i] = find(parent, parent[i]);
	}
}
