package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661 {
	static int N;
	static String ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = "";
		dfs("", 0);
		System.out.println(ans);

	}
	
	static void dfs(String s, int idx) {
		if(!ans.equals(""))
			return;
		
		if(idx == N) {
			if(isGood(s)) {
				ans = s;
				return;
			}
		}
		
		if(!isGood(s)) 
			return;
		
		dfs(s+"1", idx+1);
		dfs(s+"2", idx+1);
		dfs(s+"3", idx+1);
	}
	
	static boolean isGood(String s) {
		int len = 1;
		while(len <= s.length()/2) {
			for(int i=0; i<s.length(); i++) {
				if(i+2*len > s.length())
					break;
				String str = s.substring(i, i+len);
				String str2 = s.substring(i+len, i+2*len);
				if(str.equals(str2))
					return false;
			}
			len++;
		}
		return true;
	}
}
