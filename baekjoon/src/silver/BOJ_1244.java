package silver;

import java.util.Scanner;

public class BOJ_1244 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n_switch = sc.nextInt();
		int[] arr_switch = new int[n_switch];
		for (int i = 0; i < n_switch; i++) {
			arr_switch[i] = sc.nextInt();
		}
		int student = sc.nextInt();
		for (int i = 0; i < student; i++) {
			int m = sc.nextInt();
			if (m == 1) {
				int n = sc.nextInt();
				for(int j = 1; j <= n_switch; j++) {
					if(j % n == 0) {
						arr_switch[j-1] = arr_switch[j-1] == 1 ? 0 : 1;
					}
				}
			}
			else if(m == 2) {
				int n = sc.nextInt();
				int j = 0;
				while (n - j - 1 >= 1 && n + j + 1<= n_switch) {
					if(arr_switch[n - j - 2] == arr_switch[n + j]) {
						j++;
					}
					else {
						break;
					}
				}
				for (int k = n - j - 1; k < n + j; k++) {
					arr_switch[k] = arr_switch[k] == 1 ? 0 : 1;
				}
			}
		}
		
		for (int i = 0; i < n_switch; i++) {
			System.out.print(arr_switch[i] + " ");
			if((i+1) % 20 == 0) {
				System.out.println();
			}
		}

	}
		
}

