package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String channel_s = new StringTokenizer(br.readLine()).nextToken();
		int channel_length = channel_s.length(); // 채널의 길이
		int channel = Integer.parseInt(channel_s); // 채널
		int result = Math.abs(channel - 100);
		ArrayList<Integer> button = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());	
		if(n==0) { // 버튼 고장이 없을 경우
			if (Math.abs(channel - 100) > channel_length) {
				System.out.println(channel_length);
			}
			else {
				System.out.println(result);
			}
		}
		else {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				button.remove(Integer.valueOf(Integer.parseInt(st.nextToken())));
			}
			int min_diff = 100000000;
			int push_button = 100; // 채널 100에서 시작
			for(int i = 0; i<= 100 + channel*2; i++) {
				String s = Integer.toString(i);
				boolean check = true; // 고장난 버튼을 누르게 되는지 확인
				for(int j=0; j<s.length(); j++) {
					if(!button.contains(s.charAt(j) - '0')) {
						check = false;
						break;
					}
				}
				if(check) {
					if(Math.abs(i-channel) < min_diff) {
						min_diff = Math.abs(i-channel);
						push_button = i;
					}
				}
			}
			if(push_button == 0) {
				result = Math.min(result, 1+channel);
			}
			else {
				result = Math.min(result,(int)(Math.log10(push_button)+1) + Math.abs(push_button - channel));
			}
			System.out.println(result);
		}
	}
}
