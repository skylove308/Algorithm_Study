package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2941 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String s = st.nextToken();
		int index = 0;
		int num = 0;
		String[] alphabet = {"c=", "c-", "d-", "lj", "nj", "s=", "z="};
		if (s.length() <= 1) {
			num = s.length();
		}
		else {
			while(index < s.length() - 1) {
				if (Arrays.asList(alphabet).contains(s.substring(index, index + 2))) {
					index += 2;
					num++;
					
				}
				else if (index < s.length() - 2 && (s.substring(index, index + 3).equals("dz="))) {
					index += 3;
					num++;
					
				}
				else {
					index += 1;
					num++;
				}
			}
			if (index == s.length() - 1) {
				num++;
			}
			
		}

		
		System.out.println(num);
	}
}
