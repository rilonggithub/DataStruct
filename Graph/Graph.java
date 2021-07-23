  
import java.util.*;
public class Graph{
	public static class Node{
		public int value;
		public int in;
		public int to;
		public List<Edge> edges;
		public List<Node> nexts;

		public Node(int v,int i,int t,List<Edge> e,List<Node> n){
			value=v;
			in = i;
			to =t;
			edges=e;
			nexts=n;
		}


	}

	public static class Edge{
		private int weight;
		private Node from ;
		private Node to;

		public Edge(int w,Node f,Node t){
			weight=w;
			from=f;
			to=t;
		}
	}

	private HashMap<Integer,Node> nodes;
	private HashSet<Edge> edges;

	public Graph(){
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}

	public void add(int[][] arr){
		for (int i=0; i<arr.length; i++) {
			Node f = nodes.get(arr[i][1]);
			Node t = nodes.get(arr[i][2]);
			Edge e = new Edge(arr[i][0],f,t); 
			edges.add(e);

			if(f==null){

				List<Node> nList = new ArrayList<>();
				List<Edge> eList = new ArrayList<>();
				f = new Node(arr[i][1],0,1,eList,nList);
				nodes.put(arr[i][1],f);
			}

			if(t == null){
				List<Edge> eList = new ArrayList<>();
				List<Node> nList = new ArrayList<>();
				t = new Node(arr[i][2],1,0,eList,nList);
				nodes.put(arr[i][2],t);
			}

			f.edges.add(e);
			f.nexts.add(t);
		}
		
	}


	public void BFS(){
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> exist=new HashSet<>();
		for (Node node : nodes.values() ) {
			queue.offer(node);
			while(!queue.isEmpty()){
				Node cur = queue.poll();
				if(!exist.contains(cur)){
					exist.add(cur);
					System.out.print(cur.value+",");
					for (Node n : cur.nexts) {
						queue.offer(n);
					}
				}
			}
			System.out.println();
		}
	}


	public static void main(String[] args){
		Graph g = new Graph();
		int[][] arr=new int[][]{
			{4,1,4},
			{4,4,2},
			{4,3,4},
			{4,2,1},
			{4,4,5},
			{4,1,3},
			{4,12,17},
			{4,3,7},
			{4,5,9},
			{4,2,8},
			{4,5,4},
			{4,2,3},
			{4,8,17},
			{4,2,7}
		};
		g.add(arr);
		g.BFS();
	}

}
