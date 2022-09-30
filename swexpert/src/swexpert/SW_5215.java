package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Ingredient{
	int flavor;
	int cal;
}

public class SW_5215 {
	static int n, l, max_flavor;
	static Ingredient[] ingredient;
	static boolean[] select;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int test_case=1; test_case<=t; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			ingredient = new Ingredient[n];
			select = new boolean[n];
			max_flavor = 0;
			for(int i=0; i<n; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				ingredient[i] = new Ingredient();
				ingredient[i].flavor = Integer.parseInt(st2.nextToken());
				ingredient[i].cal = Integer.parseInt(st2.nextToken());
			}
			powerset(0);
			System.out.println("#"+test_case+" "+max_flavor);
		}
	}
	
	static void powerset(int idx) {
		if(idx == n) {
			int sum_cal = 0;
			int sum_flavor = 0;
			for(int i=0; i<n; i++) {
				if(select[i]) {
					sum_flavor += ingredient[i].flavor;
					sum_cal += ingredient[i].cal;
				}
			}
			if(sum_cal <= l) {
				max_flavor = max_flavor > sum_flavor ? max_flavor : sum_flavor;
			}
			return;
		}
		select[idx] = true;
		powerset(idx+1);
		select[idx] = false;
		powerset(idx+1);
	}
}
