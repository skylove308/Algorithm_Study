package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16968 {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String s = br.readLine().trim();
		 int ans = 1;
		 boolean continuous_c=false;
		 boolean continuous_d=false;
		 for(int i=0; i<s.length(); i++) {
			 char c = s.charAt(i);
			 if(c=='c') {
				 if(!continuous_c) ans *= 26;
				 else ans *= 25;
				 continuous_c=true;
				 continuous_d=false;
			 }
			 else if(c=='d') {
				 if(!continuous_d) ans *= 10;
				 else ans *= 9;
				 continuous_c=false;
				 continuous_d=true;
			 }
		 }
		 if(s.length()==0) ans=0;
		 System.out.println(ans);
	}
}
