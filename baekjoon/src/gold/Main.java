package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Tree {
		int x, y, age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
	static int n, m, k;
	static int[][] a, food;
	static ArrayList<Integer>[][] tree;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 땅의  크기
		m = Integer.parseInt(st.nextToken()); // 초기 나무의 갯수
		k = Integer.parseInt(st.nextToken()); // K년 후 나무의 갯수 구함
		a = new int[n+1][n+1]; // 겨울에 S2D2가 주는 양분 배열
		food = new int[n+1][n+1]; // 현재 땅에 있는 양분 배열
		tree = new ArrayList[n+1][n+1]; // 땅의 좌표마다 심어져 있는 나무의 age리스트 
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				tree[i][j] = new ArrayList<>(); // tree배열 초기화
			}
		}
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken()); // 겨울마다 주는 양분 배열 입력 받기
			}
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // x좌표
			int y = Integer.parseInt(st.nextToken()); // y좌표
			int age = Integer.parseInt(st.nextToken()); // 초기 tree 배열 입력 받기
			tree[x][y].add(age);
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				Collections.sort(tree[i][j], (o1, o2)->{ // 초기 tree배열 나이순으로 내림차순 정렬
					return o2-o1;
				});
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				food[i][j] = 5; // 초기 상태 양분 5로 초기화
			}
		}
		
		for(int i=0; i<k; i++) { // k년 동안 반복
			spring_and_summer();
			fall();
			winter();
		}
		
		int ans = 0; // 살아 남은 나무의 수
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				ans += tree[i][j].size(); // 각 칸마다 나무의 갯수를 더해준다.
			}
		}
		
		System.out.println(ans);
	}

	static void spring_and_summer() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				int f = 0; // 여름에 추가해줄 죽은 나무로 만든 양분 
				int idx = tree[i][j].size();
				for(int k=tree[i][j].size()-1; k>=0; k--) { // tree뒤에 들어가는게 나이가 제일 적음
					int age = tree[i][j].get(k);
					if(food[i][j] < age) { // 현재 땅에 있는 양분보다 나무의 나이가 많으면 나무가 죽음
						f += age/2; // 나무의 나이의 절반만큼 양분으로 추가
					}
					else {
						food[i][j] -= age; // 땅의 양분이 나무의 나이만큼 사라짐
						tree[i][j].set(k, age+1); // 나무 나이 1 증가
						idx = k;
					}
				}
				tree[i][j] = new ArrayList<>(tree[i][j].subList(idx, tree[i][j].size()));
				food[i][j] += f; // 여름에 죽은 나무로 만든 양분 추가
			}
		}
	}

	static void fall() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int k=0; k<tree[i][j].size(); k++) {
					int age = tree[i][j].get(k);
					if(age%5==0) { // 나무의 나이가 5의 배수이면 번식한다.
						for(int l=0; l<8; l++) { // 8방향 델타 탐색
							int nx = i + dx[l];
							int ny = j + dy[l];
							if(nx<1||ny<1||nx>n||ny>n) continue; // 나무가 범위 바깥이면 번식 X
							tree[nx][ny].add(1); // 나무 번식
						}
					}
				}
			}
		}
	}

	static void winter() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				food[i][j] += a[i][j]; // 겨울에  S2D2가 양분 추가
			}
		}
	}
}