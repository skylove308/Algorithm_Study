package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1158 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		int k = Integer.parseInt(st.nextToken());
		LinkedList<Integer> l = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			l.add(i);
		}
		int index = 0;
		int size = l.size();
		int cnt = 0;
		int[] ans = new int[n];
		while(!l.isEmpty()) {
			index += k-1;
			while(index >= size) {
				index -= size;
			}
			ans[cnt++] = l.get(index);
			l.remove(index);
			size--;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i=0; i<n-1; i++) {
			sb.append(ans[i]+", ");
		}
		sb.append(ans[n-1]+">");
		
		System.out.println(sb);
	}
}
