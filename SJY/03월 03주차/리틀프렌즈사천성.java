import java.util.*;

class Solution {
    
    ArrayList<Point> list;
    boolean[][] visit;
    char[][] map;
    int row, col;
    int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    
    public String solution(int m, int n, String[] board) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        
        list = new ArrayList<>();
        map = new char[101][101];
        visit = new boolean[row+1][col+1];
        row = m;
        col = n;
        
        for(int i=1;i<=row;i++) {
            for(int j=1;j<=col;j++) {
                map[i][j] = board[i-1].charAt(j-1);
                
                if(map[i][j] >= 'A' && map[i][j] <= 'Z') {
                    list.add(new Point(i, j, map[i][j]));
                    cnt++;
                }
            }
        }
        
        list.sort(null);
        
        while(true) {
            boolean flag = false;
            
            for(int a=0;a<list.size();a++) {
                Point p = list.get(a);
                int i = p.y;
                int j = p.x;
                char c = p.c;
                
                if(c < 'A' || c > 'Z') continue;
                
                if(!visit[i][j]) {
                    boolean remove = dfs(c, i, j, -1, -1);
                    
                    if(remove) {
                        flag = true;
                        cnt -= 2;
                        sb.append(c);
                        map[i][j] = '.';
                        list.remove(a);
                        break;
                    }
                }
            }
            
            if(!flag) break;
        }
        
        String answer = "IMPOSSIBLE";
        if(cnt == 0) {
            answer = sb.toString();
        }
        return answer;
    }
    
    public boolean dfs(char c, int y, int x, int dir, int rotate) {
        if(dir != -1 && map[y][x] == c) {
            map[y][x] = '.';
            return true;
        }
        
        boolean result = false;
        visit[y][x] = true;
        
        for(int a=0;a<4;a++) {
            int ny = y + dy[a];
            int nx = x + dx[a];
            
            if(ny < 1 || nx < 1 || ny > row || nx > col || visit[ny][nx]) continue;
            if(map[ny][nx] != c && map[ny][nx] != '.') continue;
            
            if(rotate >= 1) {
                if(a == dir) {
                    result |= dfs(c, ny, nx, a, rotate);
                }
            } else {
                result |= dfs(c, ny, nx, a, (a == dir) ? rotate : rotate+1);
            } 
        }
        
        visit[y][x] = false;
        return result;
    }
}

class Point implements Comparable<Point> {
    int y, x;
    char c;
    
    public Point(int y, int x, char c) {
        this.y = y;
        this.x = x;
        this.c = c;
    }
    
    @Override
    public int compareTo(Point o) {
        return this.c - o.c;
    }
}