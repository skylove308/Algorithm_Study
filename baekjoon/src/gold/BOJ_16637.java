package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_16637 {
	static int n, max;
	static char[] exp;
	static List<Character> operand, operator, operator2;
	static List<char[]> operands;
	static List<Integer> operands2; 
	static boolean[] select;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()); // 수식의 길이
		exp = new char[n];
		String s = new StringTokenizer(br.readLine()).nextToken();
		operand = new ArrayList<>(); // 피연산자 리스트 초기화
		operator = new LinkedList<>(); // 연산자 리스트 초기화
		for(int i=0; i<n; i++) {
			if(i%2==0) 
				operand.add(s.charAt(i)); // 피연산자 담기
			
			else
				operator.add(s.charAt(i)); // 연산자 담기
		}
		select = new boolean[operand.size()]; // 다양한 종류의 연산식 생성에 사용될 select 배열
		max = Integer.MIN_VALUE; // 정답 최댓값 구하기 초기화
		
		calc_max(0);
		System.out.println(max);

	}
	
	static void calc_max(int idx) {
		if(idx==operand.size()) {
			operands = new LinkedList<>(); // 괄호로 묶인 피연산자들 리스트에 담기
			operands2 = new LinkedList<>();
			operator2 = new LinkedList<>(operator); // 피연산자 리스트 복사
			int i = 0;
			while(i < operand.size()) {
				if(select[i]) { // 괄호로 묶였으면 operands 리스트에 묶어서 저장
					operands.add(new char[] {operand.get(i), operand.get(i+1)});
					i+=2;
				}
				else { // 괄호로 안묶였으면 operands 리스트에 따로 저장
					operands.add(new char[] {operand.get(i)});
					i++;
				}
			}
			for(int j=0; j<operands.size(); j++) {
				if(operands.get(j).length==1) { // 괄호로 묶이지 않았으면 일단 피연산자 int로 변경해서 operands2 리스트에 저장
					operands2.add(operands.get(j)[0] - '0');
				}
				else if(operands.get(j).length==2) { // 괄호로 묶였으면 먼저 계산하고 operands2 리스트에 저장
					operands2.add(calc(operands.get(j)[0]-'0', operands.get(j)[1]-'0', operator2.remove(j)));
				}
			}
			int ans = 0;
			if(operands2.size()==1) { // operands2 사이즈가 1이면 더 이상 계산 필요 없음
				ans = operands2.get(0);
			}
			else { // 앞에서부터 차례대로 계산해서 ans에 저장
				ans = calc(operands2.get(0), operands2.get(1), operator2.get(0));
				for(int j=2; j<operands.size(); j++) {
					ans = calc(ans, operands2.get(j), operator2.get(j-1));	
				}
			}
			max = max > ans ? max : ans; // 모든 경우의 수에서 최대값 max에 저장
			return;
		}
		
		if(idx==operand.size()-1) {
			select[idx] = false;
			calc_max(idx+1);
		}
		else {
			select[idx] = false;
			calc_max(idx+1);
			select[idx] = true;
			calc_max(idx+2);
		}


	}
	
	// 연산자로 분류한 계산기
	static int calc(int a, int b, char op) {
		if(op == '+') {
			return a+b;
		}
		else if(op == '-') {
			return a-b;
		}
		else if(op == '*') {
			return a*b;
		}
		return -1;
	}

}
