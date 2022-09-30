package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12891 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		String dna = br.readLine().trim();
		int[] dna_list = new int[4];
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			dna_list[i] = Integer.parseInt(st2.nextToken());
		}

		int cnt = 0;
		int a = 0, c = 0, g = 0, t = 0;
		for(int i=0; i<=s-p; i++) {
			if(i==0) {
				for(int j=0; j<p; j++) {
					if(dna.charAt(i+j)=='A') a++;
					else if(dna.charAt(i+j)=='C') c++;
					else if(dna.charAt(i+j)=='G') g++;
					else if(dna.charAt(i+j)=='T') t++; 
				}
				if(a >= dna_list[0] && c >= dna_list[1] && g >= dna_list[2] && t >= dna_list[3]) 
					cnt++;
			}
			else {
				if(dna.charAt(i-1)=='A') a--;
				if(dna.charAt(i-1)=='C') c--;
				if(dna.charAt(i-1)=='G') g--;
				if(dna.charAt(i-1)=='T') t--;
				if(dna.charAt(i+p-1)=='A') a++;
				if(dna.charAt(i+p-1)=='C') c++;
				if(dna.charAt(i+p-1)=='G') g++;
				if(dna.charAt(i+p-1)=='T') t++;
				if(a >= dna_list[0] && c >= dna_list[1] && g >= dna_list[2] && t >= dna_list[3]) {
					cnt++;	
				}
			}
		}
		System.out.println(cnt);
		
		
	}
}
