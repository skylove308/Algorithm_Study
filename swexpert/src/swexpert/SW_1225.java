package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1225 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s = br.readLine()) != null) {
			int t = Integer.parseInt(s.trim());
			int[] list = new int[8];
			int[] temp = new int[8];
			boolean finish=false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<8; i++) {
				list[i] = Integer.parseInt(st.nextToken());
			}
			while(!finish) {
				for(int i=0; i<5; i++) {
					temp[0] = list[1];
					temp[1] = list[2];
					temp[2] = list[3];
					temp[3] = list[4];
					temp[4] = list[5];
					temp[5] = list[6];
					temp[6] = list[7];
					
					if(list[0]-(i+1) <= 0) {
						temp[7] = 0;
						finish = true;
						list = temp.clone();
						break;
					}
					else {
						temp[7] = list[0]-(i+1);
					}
					list = temp.clone();
				}
			}
			System.out.println("#"+t+" "+list[0]+" "+list[1]+" "+list[2]+" "+list[3]+" "+list[4]+" "+list[5]+" "+list[6]+" "+list[7]);

		}
	}
}