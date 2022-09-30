package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW_1218 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine().trim());
			char[] list = new char[n];
			String s = br.readLine().trim();
			int a=0,b=0,c=0,d=0;
			int ans=0;
			for(int i=0; i<n; i++) {
				list[i] = s.charAt(i);
				if(list[i]=='(') {
					a++;
				}
				else if(list[i]==')') {
					if(a==0) break; 
					a--;
				}
				else if(list[i]=='{'){
					b++;
				}
				else if(list[i]=='}') {
					if(b==0) break;
					b--;
				}
				else if(list[i]=='[') {
					c++;
				}
				else if(list[i]==']'){
					if(c==0) break;
					c--;
				}
				else if(list[i]=='<') {
					d++;
				}
				else if(list[i]=='>'){
					if(d==0) break;
					d--;
				}
			}
			if(a==0 && b==0 && c==0 && d==0) ans=1;
			
			System.out.println("#"+t+" "+ans);
		}
	}
}
