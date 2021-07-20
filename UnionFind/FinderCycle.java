import java.util.*;
public class FinderCycle{

	public static class Node{
		public Integer Integeralue;
		public Node(Integer Integer){
			Integeralue= Integer;
		}
	}

	private HashMap<Node,Node> parents;
	private HashMap<Integer,Node> nodes;
	private HashMap<Node,Integer> sizeMap;

	public FinderCycle(List<Integer> arr){
		parents = new HashMap<Node,Node>();
		nodes = new HashMap<Integer,Node>();
		sizeMap = new HashMap<Node,Integer>();
		for (Integer cur : arr ) {
			Node n = new Node(cur);
			nodes.put(cur,n);
			parents.put(n,n);
			sizeMap.put(n,1);
		}
	}


	public FinderCycle(List<Integer> arr,int row){
		parents = new HashMap<Node,Node>();
		nodes = new HashMap<Integer,Node>();
		sizeMap = new HashMap<Node,Integer>();

		

		for (int i=0; i<row; i++) {
			Node n = new Node(i);
			nodes.put(i,n);
			parents.put(n,n);
			sizeMap.put(n,1);
		}

		for (int i=0; i<arr.size(); i++) {
			int curRow = i/row;
			int curClo = i%row;
			if(arr.get(i) == 1){
				Union(curRow,curClo);
			}
		}
	}

	private Node Find(Integer node){
		Stack<Node> stack = new Stack<Node>();
		Node cur = nodes.get(node);
		while(cur != parents.get(cur)){
			stack.push(cur);
			cur = parents.get(cur);
		}

		while(!stack.isEmpty()){
			parents.put(stack.pop(),cur);
		}
		return cur;
	}

	public void Union(Integer a,Integer b){
		Node fa = Find(a);
		Node fb = Find(b);

		if(fa != fb){
			int sa = sizeMap.get(fa);
			int sb = sizeMap.get(fb);

			Node big = sa>=sb ? fa : fb;
			Node small = big==fa ? fb : fa;

			parents.put(small,big);
			sizeMap.remove(small);
		}
	}

	public int size(){
		return sizeMap.size();
	}









	public static void main(String[] args){
		
		int[][] matrix={
			{1,1,0,0,0},
			{1,1,1,0,0},
			{0,1,1,0,0},
			{0,0,0,1,0},
			{0,0,0,0,1}
		};
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i=0; i<matrix.length; i++) {
			for (int j=0;j<matrix[i].length ;j++ ) {
				arr.add(matrix[i][j]);
			}
		}
		FinderCycle unionFind = new FinderCycle(arr,5);
		System.out.println("size ="+ unionFind.size());
	}
}
