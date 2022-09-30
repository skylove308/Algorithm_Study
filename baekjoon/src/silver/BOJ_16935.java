package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16935 {
	static String[][] arr, ans;
	static int[] calList;
	static int n, m, r;
	public static void main(String[] args) throws IOException {
		
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		if(n > m) {
			arr = new String[n][n];
			ans = new String[n][n];
		}
		else {
			arr = new String[m][m];
			ans = new String[m][m];
		}
		
		for(int i=0; i<n; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = st2.nextToken();
			}
		}
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		calList = new int[r];
		for(int i=0; i<r; i++) {
			calList[i] = Integer.parseInt(st3.nextToken());
		}
		// 문제 입력 끝
		
		for(int i=0; i<r; i++) {
			if(calList[i]==3||calList[i]==4) {
				int temp_m = m;
				int temp_n = n;
				n = temp_m;
				m = temp_n;
			}
			calculation(calList[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(ans[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void calculation(int num) {
		if(num==1) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans[i][j] = arr[n-1-i][j];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
		else if(num==2) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans[i][j] = arr[i][m-1-j];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
		else if(num==3) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans[i][j] = arr[m-1-j][i];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
		else if(num==4) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans[i][j] = arr[j][n-1-i];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
		else if(num==5) {
			for(int i=0; i<n/2; i++) {
				for(int j=0; j<m/2; j++) {
					ans[i][j] = arr[i+n/2][j];
				}
			}
			for(int i=0; i<n/2; i++) {
				for(int j=m/2; j<m; j++) {
					ans[i][j] = arr[i][j-m/2];
				}
			}
			for(int i=n/2; i<n; i++) {
				for(int j=0; j<m/2; j++) {
					ans[i][j] = arr[i][j+m/2];
				}
			}
			for(int i=n/2; i<n; i++) {
				for(int j=m/2; j<m; j++) {
					ans[i][j] = arr[i-n/2][j];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
		else if(num==6) {
			for(int i=0; i<n/2; i++) {
				for(int j=0; j<m/2; j++) {
					ans[i][j] = arr[i][j+m/2];
				}
			}
			for(int i=0; i<n/2; i++) {
				for(int j=m/2; j<m; j++) {
					ans[i][j] = arr[i+n/2][j];
				}
			}
			for(int i=n/2; i<n; i++) {
				for(int j=0; j<m/2; j++) {
					ans[i][j] = arr[i-n/2][j];
				}
			}
			for(int i=n/2; i<n; i++) {
				for(int j=m/2; j<m; j++) {
					ans[i][j] = arr[i][j-m/2];
				}
			}
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					arr[i][j] = ans[i][j];
				}
			}
		}
	}
}
