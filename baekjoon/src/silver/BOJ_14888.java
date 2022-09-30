package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14888 {
	static char[] operator = {'+', '-', '*', '/'};
	static List<Character> operator_list;
	static int[] num_list;
	static int n, max, min;
	static boolean used[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num_list = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			num_list[i] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		operator_list = new ArrayList<>();
		for(int i=0; i<4; i++) {
			int t = Integer.parseInt(st2.nextToken());
			for(int j=0; j<t; j++) {
				operator_list.add(operator[i]);
			}
		}
		used = new boolean[n-1];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		perm(0, num_list[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void perm(int idx, int sum) {
		if(idx==n-1) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		for(int i=0; i<n-1; i++) {
			if(used[i]) continue;
			used[i] = true;
			perm(idx+1, calc(sum, num_list[idx+1], operator_list.get(i)));
			used[i] = false;
		}
	}
	
	static int calc(int a, int b, char op) {
		if(op=='+')
			return a+b;
		else if(op=='-')
			return a-b;
		else if(op=='*')
			return a*b;
		else if(op=='/')
			return a/b;
		return -1;
	}
}
