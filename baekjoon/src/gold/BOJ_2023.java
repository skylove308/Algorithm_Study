package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ_2023 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> interestingPrime = new LinkedList<>(Arrays.asList(2,3,5,7)); // 초기 신기한 소수
		int m = 2;
		while(m<=n) { // n의 자리수 까지 반복
			int ipSize = interestingPrime.size();
			for(int i=0; i<ipSize; i++) {
				int p = interestingPrime.remove(0); // 기존 신기한 소수들에 대하여 10배를 하고 +1 ~ +9까지 소수판별
				for(int j=1; j<10; j+=2) {
					if(isPrime(p*10+j))
						interestingPrime.add(p*10+j);
				}
			}
			m++;
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<interestingPrime.size(); i++) {
			sb.append(interestingPrime.get(i)+"\n");
		}
		System.out.println(sb);
		
	}
	
	// 소수 판별
	static boolean isPrime(int n) {
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n%i==0) return false;
		}
		return true;
	}
}
