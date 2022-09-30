package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2164 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		Queue<Integer> arr = new LinkedList<>();
		int temp;
		for(int i=1; i<=n; i++) {
			arr.add(i);
		}
		while(arr.size()>1) {
			arr.poll();
			temp = arr.poll();
			arr.add(temp);
		}
		System.out.println(arr.peek());
	}
}
