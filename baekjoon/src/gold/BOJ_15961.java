package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_15961 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int[] sushi_type = new int[D+1];
		
		int cnt = 0;
		int max = 0;
		for(int i=0; i<K; i++) {
			if(sushi_type[sushi[i]]==0)
				cnt++;
			sushi_type[sushi[i]]++;
		}
		if(sushi_type[C]==0)
			max = cnt + 1;
		else
			max = cnt;
		
		for(int i=0; i<N; i++) {
			sushi_type[sushi[i]]--;
			if(sushi_type[sushi[i]]==0)
				cnt--;
			if(sushi_type[sushi[(K+i)%N]]==0)
				cnt++;
			sushi_type[sushi[(K+i)%N]]++;
			if(sushi_type[C]==0)
				max = Math.max(max, cnt+1);
			else
				max = Math.max(max, cnt);
		}
		
		System.out.println(max);
	}
}
