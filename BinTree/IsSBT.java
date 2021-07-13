import java.util.*;
public class IsBST{

	public static class Node{
		public int value;
		public Node left;
		public Node right;

		public Node(int v){
			value = v;
			left = null;
			right = null;
		}

	}

	public static Node getRandomTree(int maxLevel){
		return randomTree(1,maxLevel);
	}

	public static Node randomTree(int currLevel,int maxLevel){
		if(currLevel > maxLevel || Math.random()>0.7) return null;

		Node head =new Node((int)(Math.random()*1000));
		head.left = randomTree(currLevel+1,maxLevel);
		head.right = randomTree(currLevel+1,maxLevel);

		return head;
	}

	//层次遍历树
	public static void levelOrder(Node root){
		if(null == root) return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		Node currEnd=root;
		Node nextEnd=null;

		while(!queue.isEmpty()){
			Node curr = queue.poll();
			//list.add(curr.data);
			System.out.print(curr.value+",");
			if(curr.left != null){
				queue.offer(curr.left);
				nextEnd = curr.left;
			}
			if(curr.right!=null){
				queue.offer(curr.right);
				nextEnd = curr.right;
			}

			if(currEnd == curr){
				currEnd = nextEnd;
				System.out.println();
			}
		}
	}



	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}












	public static class Info{
		public boolean isBST;
		public int min;
		public int max;

		public Info(boolean bst,int mi,int ma){
			isBST=bst;
			min = mi;
			max = ma;
		}
	}

	public static Info process(Node head){
		if(head == null){
			return null;
		}

		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);

		int min=0;
		int max=0;
		boolean isBST=false;

		if(leftInfo == null){
			min = head.value;

		}else{
			min = Math.min(leftInfo.min,head.value);
		}

		if(rightInfo == null){
			max = head.value;
		}else{
			max = Math.max(rightInfo.max,head.value);
		}

		if((leftInfo==null||leftInfo.max<head.value ) && 
			( rightInfo == null || rightInfo.min>head.value ) &&
			(leftInfo==null||leftInfo.isBST) && 
			( rightInfo == null || rightInfo.isBST ))
		{
			isBST=true;
		}

		return new Info(isBST,min,max);
	}


	public static void main(String[] args){
		//Node root = getRandomTree(2);

		Node head = new Node(10);
		head.left = new Node(8);
		head.right = new Node(30);
		head.left.left = new Node(4);
		head.right.left = new Node(15);
		head.right.right = new Node(60);
		head.left.left.right = new Node(7);
		//printTree(head);

		Info info =  process(head);
		printTree(head);
		System.out.println("IsBST = "+(info==null?"null":info.isBST));
	}

}
