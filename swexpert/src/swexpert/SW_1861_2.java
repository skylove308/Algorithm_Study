package swexpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Room {
	int x;
	int y;
	public Room(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class SW_1861_2 {
    static int n, max, min;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=t; test_case++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            for(int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            min = Integer.MAX_VALUE;
            dfs();
            System.out.println("#"+test_case+" "+min+" "+max);
             
        }
    }
     
    static void dfs() {
        Stack<Room> stack = new Stack<>();
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			if(visited[i][j]) continue;
    	        stack.push(new Room(i, j));
    	        visited[i][j] = true;
    	        int cnt = 1;
    	        int min_num = Integer.MAX_VALUE;
    	        while(!stack.isEmpty()) {
    	        	Room room = stack.pop();
    	        	min_num = min_num < arr[room.x][room.y] ? min_num : arr[room.x][room.y];
    	            for(int k=0; k<4; k++) {
    	                int nx = room.x + dx[k];
    	                int ny = room.y + dy[k];
    	                if(nx<0||ny<0||nx>=n||ny>=n) continue;
    	                if(visited[nx][ny]) continue;
    	                if(arr[nx][ny]==arr[room.x][room.y]+1||arr[nx][ny]==arr[room.x][room.y]-1) {
    	                	stack.push(new Room(nx, ny));
    	                	visited[nx][ny] = true;
    	                    cnt++;
    	                }
    	            }
    	        }
    	        if(max < cnt) {
    	        	max = cnt;
    	        	min = min_num;
    	        }
    	        else if(max == cnt) {
    	        	if(min > min_num) {
    	        		min = min_num;
    	        	}
    	        }
    		}
    	}
    }
}
