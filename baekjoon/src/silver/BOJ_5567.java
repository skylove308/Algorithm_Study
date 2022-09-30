package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_5567 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] relation = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			relation[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			relation[a].add(b);
			relation[b].add(a);
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<relation[1].size(); i++) {
			set.add(relation[1].get(i));
			for(int j=0; j<relation[relation[1].get(i)].size(); j++) {
				set.add(relation[relation[1].get(i)].get(j));
			}
		}
		
		if(relation[1].size()==0)
			System.out.println(set.size());
		else
			System.out.println(set.size()-1);
	}
}
