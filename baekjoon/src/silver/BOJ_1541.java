package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class BOJ_1541 {
	public static void main(String[] args) throws IOException {
		// 문제 입력 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		List<Integer> num = new LinkedList<>();
		List<Character> op = new LinkedList<>();
		String temp = "";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='+'||s.charAt(i)=='-') { // +나 -를 만나면 그 전까지 temp에 담은 문자를 num에 Integer로 변환하여 저장
				op.add(s.charAt(i));
				num.add(Integer.parseInt(temp));
				temp = ""; // 담아주고 temp 초기화
			}
			else {
				temp += Integer.toString(s.charAt(i) - '0'); // 숫자는 temp에 String으로 저장
			}
		}
		num.add(Integer.parseInt(temp)); // 마지막에 temp에 담긴 숫자 저장
		
		// 문제 입력 끝
		
		int t = num.get(0);
		for(int i=0; i<op.size(); i++) {
			if(op.get(i)=='+')
				t+=num.get(i+1);
			if(op.get(i)=='-') { // -를 처음 만났을 때부터 뒤에 있는 모든 숫자 괄호를 사용해 빼기로 변환가능, 즉 모든 숫자를 전부 빼준다.
				for(int j=i+1; j<=op.size(); j++) {
					t-=num.get(j);
				}
				break;
			}
		}
		System.out.println(t);
	}

}
