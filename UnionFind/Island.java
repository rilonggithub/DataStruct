import java.util.*;
public class Island{

	public static class Node{
		private Integer value;
		public Node(int c){
			value = c;
		}
	}

	private HashMap<Node,Node> parents;
	private HashMap<Integer,Node> nodes;
	private HashMap<Node,Integer> sizeMap;

	public Island(int[][] arr){
		parents = new HashMap<Node,Node>();
		nodes = new HashMap<Integer,Node>();
		sizeMap = new HashMap<Node,Integer>();

		for (int i=0; i<arr.length;i++ ) {
			for (int j=0; j<arr[i].length; j++) {
				if(arr[i][j]==1){
					Node node = new Node(arr[i][j]);
					parents.put(node,node);
					nodes.put(i*arr.length+j,node);
					sizeMap.put(node,1);
				}
			}
		}


		for (int i=0; i<arr[0].length-1; i++) {
			if(arr[0][i]==1 && arr[0][i+1]==1){
				Union(i,i+1);
				//System.out.println("top1");
			}
		}

		for (int i=1; i<arr.length-1; i++) {
			if(arr[i][0]==1 && arr[i-1][0]==1){
				int index = i* arr.length;
				int topIndex = (i-1)* arr.length;
				Union(index,topIndex);
				//System.out.println("left1");
			}
		}


		for (int i=1; i<arr.length;i++ ) {
			for (int j=1; j<arr[i].length; j++) {

				if(arr[i][j]==1 && arr[i-1][j]==1){
					int index = i* arr.length+j;
					int topIndex = (i-1)* arr.length+j;
					Union(index,topIndex);
					//System.out.println("top2");
				}

				if(arr[i][j]==1 && arr[i][j-1]==1){
					int index = i* arr.length+j;
					Union(index,index-1);
					//System.out.println("left2");
				}
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
			sizeMap.put(big,sizeMap.get(big)+sizeMap.get(small));
			sizeMap.remove(small);
		}
	}

	public int size(){
		return sizeMap.size();
	}

	public int getMax(){
		int max=-1;
		for(Integer key:sizeMap.values()){
			max = Math.max(key,max);
		}
		return max;
	}




	public static void main(String[] args){
		
		int[][] matrix={
			{1,1,0,0,1},
			{1,0,1,0,1},
			{0,1,1,0,1},
			{0,0,1,1,1},
			{1,0,0,1,1}
		};
		Island unionFind = new Island(matrix);
		System.out.println("size ="+ unionFind.size());
		System.out.println("max ="+ unionFind.getMax());
	}

}
