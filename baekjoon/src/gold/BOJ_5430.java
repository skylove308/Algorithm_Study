package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_5430 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		for(int i=0; i<test_case; i++) {
			String p = new StringTokenizer(br.readLine()).nextToken();
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			LinkedList<String> l = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), "[|]|,");
			while(st.hasMoreElements()) {
				l.add(st.nextToken());
			}
			
			boolean check_error = false;
			boolean reverse = false;
			for(int j=0; j<p.length(); j++) {
				if(p.charAt(j)=='R') {
					reverse = !reverse;
				}
				else if(p.charAt(j)=='D') {
					if(!reverse) {
						if(!l.isEmpty()) {
							l.removeFirst();
						}
						else {
							System.out.println("error");
							check_error = true;
							break;
						}
					}
					else {
						if(!l.isEmpty()) {
							l.removeLast();
						}
						else {
							System.out.println("error");
							check_error = true;
							break;
						}
					}

					
				}
			}
			if(!check_error) {
				if(l.isEmpty()) System.out.println("[]");
				else {
					StringBuilder ans = new StringBuilder();
					if(!reverse) {
						ans.append("[");
						for(String s: l) {
							ans.append(s);
							ans.append(",");
						}
						ans.setLength(ans.length()-1);
						ans.append("]");
						System.out.println(ans);
					}
					else {
						Collections.reverse(l);
						ans.append("[");
						for(String s: l) {
							ans.append(s);
							ans.append(",");
						}
						ans.setLength(ans.length()-1);
						ans.append("]");
						System.out.println(ans);
					}
				}
				
			}
		}
	}
}
