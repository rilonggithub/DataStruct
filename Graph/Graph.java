import java.util.*;
public class Graph{
	public static class Node{
		private int value;
		private int in;
		private int to;
		private List<Edge> edges;
		private List<Node> nexts;

		public Node(int v,int i,int t,List<Edge> e,List<Node> n){
			value=v;
			in = i;
			to =t;
			edges=e;
			nexts=n;
		}

		public void addEdge(Edge e){
			edges.add(e);
			to++;
		}

		public void addNext(Node n){
			nexts.add(n);
		}

		public void addIn(){
			in++;
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

	public void add(int[] arr){
		Node f = nodes.get(arr[1]);
		Node t = nodes.get(arr[2]);
		Edge e = new Edge(arr[0],f,t); 

		if(f==null){
			List<Edge> eList = new ArrayList<>();
			eList.add(e);

			List<Node> nList = new ArrayList<>();
			nList.add(t);

			f = new Node(arr[1],0,1,eList,nList);
		}else{
			f.addEdge(e);
			f.addNext(t);
		}

		if(t == null){
			List<Edge> eList = new ArrayList<>();
			List<Node> nList = new ArrayList<>();
			t = new Node(arr[2],1,0,eList,nList);
		}else{
			t.addIn();
		}
	}


	public static void main(String[] args){
		Graph g = new Graph();
		int[] arr=new int[]{4,1,4};
		arr=new int[]{4,3,4};
		arr=new int[]{1,4,2};
		arr=new int[]{9,2,1};
		g.add(arr);
	}

}
