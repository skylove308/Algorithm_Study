package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1072 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		long Z = 100 * Y / X;
		if(Z>=99)
			System.out.println(-1);
		else {
			int ans = 0;
			int left = 0;
			int right = 1000000000;
			while(left<=right) {
				int mid = (left+right)/2;
				if((Y+mid) * 100/(X+mid) != Z) {
					ans = mid;
					right = mid - 1;
				}
				else {
					left= mid +1;
				}
			}
			System.out.println(ans);
		}
		
	}
}
