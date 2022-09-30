package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1210 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int n=0; n<10; n++) {
			int t = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			
			int[][] ladder = new int[100][100];
			for(int i=0; i<100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int start_j = 0;
			for(int j=0; j<100; j++) {
				if(ladder[99][j] == 2) {
					start_j = j;
				}
			}
			
			
			int i = 99;
			boolean direction_col = true;
			boolean direction_x_left = true; 
			while(i>=1) {
				if(direction_col) {
					if(start_j>=1 && start_j<=98) {
						if(ladder[i][start_j-1]==1) {
							start_j--;
							direction_col = false;
							direction_x_left = true;
						}
						else if(ladder[i][start_j+1]==1) {
							start_j++;
							direction_col= false;
							direction_x_left = false;
						}
						else {
							i--;
						}
					}
					else if(start_j==99) {
						if(ladder[i][start_j-1]==1) {
							start_j--;
							direction_col= false;
							direction_x_left = true;
						}
						else {
							i--;
						}
					}
					else if(start_j==0) {
						if(ladder[i][start_j+1]==1) {
							start_j++;
							direction_col= false;
							direction_x_left = false;
						}
						else {
							i--;
						}
					}
				}
				else {
					if(direction_x_left) {
						if(start_j>=1) {
							if(ladder[i][start_j-1]==1) {
								start_j--;
							}
							else {
								direction_col = true;
								i--;
							}
						}
						else if(start_j==0){
							direction_col = true;
							i--;
						}
					}
					else {
						if(start_j<=98) {
							if(ladder[i][start_j+1]==1) {
								start_j++;
							}
							else {
								direction_col = true;
								i--;
							}
						}
						else if(start_j==99) {
							direction_col = true;
							i--;
						}
					}
				}
			}
			
			System.out.println("#"+t+" "+start_j);

		}


	}
}
