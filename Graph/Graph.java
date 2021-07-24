import java.util.*;

public class Graph{

	public static class UnionFind{

		public static class UNode{
			private Integer value;
			public UNode(int v){
				value = v;
			}
		}

		private HashMap<UNode,UNode> parents;
		private HashMap<Integer,UNode> uNodes;
		private HashMap<UNode,Integer> sizeMap;

		public UnionFind(int[] arr){
			parents = new HashMap<>();
			sizeMap = new HashMap<>();
			uNodes = new HashMap<>();

			for (int i=0; i<arr.length;i++ ) {
				UNode u=new UNode(arr[i]);
				parents.put(u,u);
				sizeMap.put(u,1);
				uNodes.put(arr[i],u);
			}
		}

		public UNode find(int a){
			Stack<UNode> stack = new Stack<>();
			UNode cur = uNodes.get(a);
			while(cur != parents.get(cur)){
				stack.push(cur);
				cur = parents.get(cur);
			}

			while(!stack.isEmpty()){
				parents.put(stack.pop(),cur);
			}
			return cur;
		}


		public boolean isSameSet(int a,int b){
			return find(a) == find(b);
		}

		public void union(int a,int b){
			UNode fa = find(a);
			UNode fb = find(b);

			if(fa!=fb){
				int sa = sizeMap.get(fa);
				int sb = sizeMap.get(fb);

				UNode big = sa>=sb ? fa : fb;
				UNode small = big==fa ? fb : fa;

				parents.put(small,big);
				sizeMap.put(big,sa+sb);
				sizeMap.remove(small);

			}
		}
	}



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

			if(f==null){

				List<Node> nList = new ArrayList<>();
				List<Edge> eList = new ArrayList<>();
				f = new Node(arr[i][1],0,1,eList,nList);
				nodes.put(arr[i][1],f);
			}else{
				f.to++;
			}

			if(t == null){
				List<Edge> eList = new ArrayList<>();
				List<Node> nList = new ArrayList<>();
				t = new Node(arr[i][2],1,0,eList,nList);
				nodes.put(arr[i][2],t);
			}else{
				t.in++;
			}

			Edge e = new Edge(arr[i][0],f,t); 
			edges.add(e);

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


	public void DFS(){
		Stack<Node> stack = new Stack<>();
		HashSet<Node> exist = new HashSet<>();

		for (Node node : nodes.values()) {
			if(!exist.contains(node)){
				stack.push(node);
				exist.add(node);
				System.out.print(node.value+",");

				while(!stack.isEmpty()){
					Node cur = stack.pop();
					for (Node next: cur.nexts) {
						if(!exist.contains(next)){
							stack.push(cur);
							stack.push(next);
							exist.add(next);
							System.out.print(next.value+",");
							break;
						}
					}
				}
			}
		}
	}

	public static class WeightCompar implements Comparator<Edge>{

		@Override
		public int compare(Edge e1,Edge e2){
			return e1.weight - e2.weight;
		}
	}

	public List<Edge> Kruskal(){
		int[] arr=new int[nodes.values().size()];
		int index=0;
		for (Node node : nodes.values()) {
			arr[index++] = node.value;
		}

		UnionFind union=new UnionFind(arr);
		PriorityQueue<Edge> queue = new PriorityQueue<>(new WeightCompar());

		for (Edge e : edges) {
			queue.add(e);
		}
		List<Edge> result = new ArrayList<>();

		while(!queue.isEmpty()){
			Edge curr = queue.poll();
			if(!union.isSameSet(curr.from.value,curr.to.value)){
				result.add(curr);
				union.union(curr.from.value,curr.to.value);
			}
		}
		return result;
	}


	public List<Node> topologicalSort(){
		List<Node> result = new ArrayList<>();


		Stack<Node> stack = new Stack<>();
		for (Node node : nodes.values() ) {
			if(node.in ==0){
				stack.push(node);
			}
		}

		while(!stack.isEmpty()){
			Node curr = stack.pop();
			result.add(curr);
			System.out.print(curr.value+",");

			for (Node next : curr.nexts) {
				--next.in;
				if(next.in ==0){
					stack.push(next);
				}
			}
		}
		return result;
	}


	public static void main(String[] args){
		Graph g = new Graph();
		int[][] arr=new int[][]{
			{4,1,4},
			{2,4,2},
			{40,3,4},
			{9,4,5},
			{2,1,3},
			{1,12,17},
			{11,12,13},
			{1,3,7},
			{7,5,9},
			{5,2,8},
			{100,8,17},
			{5,2,7},
			{1,17,13}
		};
		g.add(arr);
		g.DFS();
		System.out.println();
		List<Edge> eArray = g.Kruskal();
		for (Edge e:eArray) {
			System.out.println(e.from.value+"--->"+e.to.value);
		}

		g.topologicalSort();
	}

}
