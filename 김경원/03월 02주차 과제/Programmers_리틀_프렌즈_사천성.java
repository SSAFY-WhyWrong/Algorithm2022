import java.util.*;

public class Programmers_리틀_프렌즈_사천성 {
    public static void main(String[] args) {
        String[] board = {"AB","BA"};
        int m = 2;
        int n = 2;

        String answer = solution(m,n,board);

        System.out.println(answer);


    }

    static class Node implements Comparable<Node>{
        char word;
        int x1, y1, x2, y2;

        public Node(char word, int y1, int x1){
            this.word = word;
            this.y1 = y1;
            this.x1 = x1;
        }

        public Node(char word, int y1, int x1, int y2, int x2){
            this.word = word;
            if(x1 < x2){
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }else{
                this.x2 = x1;
                this.y2 = y1;
                this.x1 = x2;
                this.y1 = y2;
            }
        }

        @Override
        public int compareTo(Node o) {
            return this.word - o.word;
        }
    }

    static public String solution(int m, int n, String[] board) {
        String answer = "IMPOSSIBLE";

        boolean[][] isBlocked = new boolean[m][n];
        Map<Character,Node> map = new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                char token = board[i].charAt(j);

                if(token == '.'){
                    continue;
                }

                isBlocked[i][j] = true;
                if(token !='*'){
                    Node node = map.get(token);
                    if(node == null){
                        node = new Node(token,i,j);
                        map.put(token,node);
                    }

                    if(node != null){
                        node = new Node(token, node.y1, node.x1, i,j);
                        map.put(token,node);
                    }



                }

            }
        }

        List<Node> list = new ArrayList<>();

        for(Node node : map.values()){
            list.add(node);
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        for(int t=0;t<list.size();t++){
            Node node = list.get(t);
            System.out.println(node.word+" "+node.y1+" "+node.y2);
            if(isPossible(node,isBlocked)){
                isBlocked[node.y1][node.x1] = false;
                isBlocked[node.y2][node.x2] = false;
                sb.append(node.word);
                list.remove(t);
                t = -1;
            }
        }

        if(list.size() == 0){
            answer = sb.toString();
        }
        return answer;
    }
    static final int UP = -1;
    static final int DOWN = 1;
    static boolean isPossible(Node node, boolean[][] isBlokced){

        if(node.y1 < node.y2){
            if(somethingInTheWay(DOWN,node, isBlokced)){
                return false;
            }
        }

        else {
            if(somethingInTheWay(UP,node, isBlokced)){
                return false;
            }
        }


        return true;
    }

    //Nirvana
    static boolean somethingInTheWay(int d, Node node, boolean[][] isBlocked){

        boolean isImpossible1 = false;
        boolean isImpossible2 = false;

        for(int j=node.x1+1;j<node.x2; j++){
            if(isBlocked[node.y1][j]){
                isImpossible1 = true;
                break;
            }
        }

        for(int j=node.x1;j<node.x2; j++){
            if(isBlocked[node.y2][j]){
                isImpossible2 = true;
                break;
            }
        }

        if(d == UP) {
            for (int i = node.y1; i > node.y2; i = i + d) {
                if (isBlocked[i][node.x2]) {
                    isImpossible1 = true;
                    break;
                }
            }

            for (int i = node.y1+d; i > node.y2; i = i + d) {
                if (isBlocked[i][node.x1]) {
                    isImpossible2 = true;
                    break;
                }
            }
        }
        if(d == DOWN) {
            for (int i = node.y1; i < node.y2; i = i + d) {
                if (isBlocked[i][node.x2]) {
                    isImpossible1 = true;
                    break;
                }
            }

            for (int i = node.y1+d; i < node.y2; i = i + d) {
                if (isBlocked[i][node.x1]) {
                    isImpossible2 = true;
                    break;
                }
            }
        }


        return (isImpossible1 & isImpossible2);
    }




    static void print(int m, int n, boolean[][] isBlocked){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(isBlocked[i][j]) {
                    System.out.print("X");
                }else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }

}
