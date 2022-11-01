package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1644 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[N+1];
		for(int i=2; i<=N; i++) { // �������׳׽��� ü
			if(arr[i])
				continue;
			for(int j=2; i*j<=N; j++) {
				arr[i*j] = true;
			}
		}
		
		ArrayList<Integer> prime = new ArrayList<>();
		for(int i=2; i<=N; i++) {
			if(!arr[i])
				prime.add(i);
		}
		
		int size = prime.size();
		
		// 0~, 1~, 2~, ... ���� sum�̻��϶����� �ݺ��ؼ� �����ְ� sum ������ cnt++
		int cnt = 0;
		for(int i=0; i<size; i++) {
			int sum = 0;
			for(int j=i; j<size; j++) {
				if(sum >=N)
					break;
				sum += prime.get(j);
			}
			if(sum==N)
				cnt++;
		}
		
		System.out.println(cnt);
	}
}
