package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Stack<int[]> tower = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<n; i++) {
			int[] temp = new int[] {i+1, Integer.parseInt(st.nextToken())};
			if(tower.isEmpty()) {
				ans.append(0+" ");
				tower.add(temp);
				continue;
			}

			if(tower.peek()[1] > temp[1]) {
				ans.append(tower.peek()[0]+" ");
				tower.add(temp);
				
			}
			else {
				while(tower.peek()[1] <= temp[1]) {
					tower.pop();
					if(tower.isEmpty()) break;
				}
				if(tower.isEmpty()) {
					ans.append(0+" ");
					tower.add(temp);

				}
				else {
					ans.append(tower.peek()[0]+" ");
					tower.add(temp);
				}
					
			}
		}
		System.out.println(ans);
	}
}
