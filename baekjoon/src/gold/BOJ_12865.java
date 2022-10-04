package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] W = new int[N];
		int[] V = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		int[] value = new int[K + 1];
		for (int i = 0; i < N; i++) {
			for (int j = W[i]; j <=K; j++) {
				if(j<2*W[i])
				value[j] = Math.max(value[j], value[j - W[i]] + V[i]);
			}
		}

		System.out.println(value[K]);

	}
}
