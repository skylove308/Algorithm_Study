package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class BOJ_15663 {
	static int n, m;
	static int[] arr, result;
	static boolean[] used;
	static LinkedHashSet<String> set;
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st2.nextToken());
		}
		Arrays.sort(arr); // arr 오름차순 정렬
		result = new int[m];
		used = new boolean[n];
		// set에 add하는 순서대로 지속적으로 정렬된다. 
		// treeSet을 사용하면 기존의 정렬이 깨지고 오름차순으로 정렬하므로 사용하면 안된다.
		set = new LinkedHashSet<>(); 
		// 문제 입력 끝
		
		perm(0);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(String s: set) {
			sb.append(s+"\n");
		}
		System.out.println(sb);
		
	}
	
	static void perm(int idx) {
		if(idx == m) {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<m; i++) {
				sb.append(result[i]+" ");
			}
			set.add(sb.toString());
			return;
		}
		for(int i=0; i<n; i++) {
			if(used[i]) continue;
			result[idx] = arr[i];
			used[i] = true;
			perm(idx+1);
			used[i] = false;
		}
	}
}
