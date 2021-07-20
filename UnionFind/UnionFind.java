import java.util.*;
public class UnionFind<V>{

	public static class Node<V>{
		public V value;
		public Node(V v){
			value= v;
		}
	}

	private HashMap<Node<V>,Node<V>> parents;
	private HashMap<V,Node<V>> nodes;
	private HashMap<Node<V>,Integer> sizeMap;

	public UnionFind(List<V> arr){
		parents = new HashMap<Node<V>,Node<V>>();
		nodes = new HashMap<V,Node<V>>();
		sizeMap = new HashMap<Node<V>,Integer>();
		for (V cur : arr ) {
			Node<V> n = new Node<V>(cur);
			nodes.put(cur,n);
			parents.put(n,n);
			sizeMap.put(n,1);
		}
	}

	private Node<V> Find(V node){
		Stack<Node<V>> stack = new Stack<Node<V>>();
		Node<V> cur = nodes.get(node);
		while(cur != parents.get(cur)){
			stack.push(cur);
			cur = parents.get(cur);
		}

		while(!stack.isEmpty()){
			parents.put(stack.pop(),cur);
		}
		return cur;
	}

	public void Union(V a,V b){
		Node<V> fa = Find(a);
		Node<V> fb = Find(b);

		if(fa != fb){
			int sa = sizeMap.get(fa);
			int sb = sizeMap.get(fb);

			Node<V> big = sa>=sb ? fa : fb;
			Node<V> small = big==fa ? fb : fa;

			parents.put(small,big);
			sizeMap.remove(small);
		}
	}

	public int size(){
		return sizeMap.size();
	}









	public static void main(String[] args){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(11);
		arr.add(111);
		arr.add(1111);
		arr.add(21);
		arr.add(31);
		arr.add(41);
		UnionFind<Integer> unionFind = new UnionFind<Integer>(arr);
		System.out.println("size ="+ unionFind.size());
		unionFind.Union(1,11);
		System.out.println("size ="+ unionFind.size());
		unionFind.Union(1111,11);
		System.out.println("size ="+ unionFind.size());
		unionFind.Union(1,1111);
		System.out.println("size ="+ unionFind.size());


	}
}
