package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
	static int l, c;
	static char[] keys;
	static char[] result;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken()); // 서로 다른 l개의 알파벳 소문자들로 구성
		c = Integer.parseInt(st.nextToken()); // c자리의 문자열
		keys = new char[c]; // 암호로 사용되는 알파벳 배열
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0; i<c; i++) {
			keys[i] = st2.nextToken().charAt(0);
		}
		Arrays.sort(keys); // 문자열 정렬
		result = new char[l];
		sb = new StringBuilder();
		// 문제 입력 끝
		
		comb(0, 0);
		
		// 출력
		System.out.println(sb);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == l) {
			boolean isContainsVowels = false;
			int countConsonants = 0;
			for(int i=0; i<l; i++) {
				if(isVowels(result[i])) {
					isContainsVowels = true;
				}
				else {
					countConsonants++;
				}
			}
			if(isContainsVowels&&countConsonants>=2) {
				for(int i=0; i<l; i++) {
					sb.append(result[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i=idx; i<c; i++) {
			result[cnt] = keys[i];
			comb(i+1, cnt+1);
		}
	}
	
	static boolean isVowels(char c) {
		if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') 
			return true;
		return false;
	}
}
