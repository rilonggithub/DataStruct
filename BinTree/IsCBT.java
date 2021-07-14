import java.util.*;

public class IsCBT{

	public static class Node{
		public int value;
		public Node left;
		public Node right;

		public Node(int v){
			value = v;
			left=null;
			right=null;
		}
	}

	public static Node getRandomTree(int maxLevel){
		return randomTree(1,maxLevel);
	}

	public static Node randomTree(int currLevel,int maxLevel){
		if(currLevel>maxLevel || Math.random() > 0.9)
			return null;

		Node head = new Node((int)Math.random()*1000);
		head.left = randomTree(currLevel+1,maxLevel);
		head.right = randomTree(currLevel+1,maxLevel);

		return head;
	}

	public static boolean isCBT_Old(Node head){
		if(head == null)
			return true;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(head);

		Node l=null;
		Node r=null;
		Node curr=null;
		boolean leaf=false;


		while(!queue.isEmpty()){
			curr = queue.poll();
			l = curr.left;
			r = curr.right;

			if(
				(leaf && (l !=null || r != null))
				||
				(l == null && r != null)
			){
				return false;
			}

			if(l != null){
				queue.add(l);
			}
			if(r != null){
				queue.add(r);
			}

			if(l == null || r == null){
				leaf=true;
			}
		}
		return true;
	}

	public static class Info{
		public boolean isFull;
		public boolean isCBT;
		public int hight;

		public Info(boolean full,boolean cbt,int h){
			isFull = full;
			isCBT = cbt;
			hight = h;
		}
	}

	public static boolean isCBT_New(Node head){
		if(head == null){
			return true;
		}
		return process(head).isCBT;


	}

	public static Info process(Node head){
		if(head == null){
			return new Info(true,true,0);
		}

		Info l = process(head.left);
		Info r = process(head.right);

		boolean isFull=false;
		boolean isCBT=false;
		int hight = Math.max(l.hight,r.hight)+1;

		if(l.isFull && r.isFull && l.hight == r.hight){
			isFull = true;
		}

		if(isFull){
			isCBT = true;
		}
		else{

			if(l.isCBT && r.isCBT){

				if(l.isFull && r.isFull && l.hight == r.hight +1 )
					isCBT = true;

				if(l.isCBT && r.isFull && l.hight == r.hight +1 )
					isCBT = true;

				if(l.isFull && r.isCBT && l.hight == r.hight)
					isCBT = true;
			}
		}

		return new Info(isFull,isCBT,hight);
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










	public static boolean isTrue(){
		int max=10;
		for (int i=0; i<max; i++) {
			Node root = getRandomTree(4);
			boolean _old = isCBT_Old(root);
			boolean _new = isCBT_New(root);

			if(_old != _new){
				return false;
			}
			if(_old&&_new){
				printTree(root);
			}
		}
		return true;
	}


	public static void main(String[] args){
		System.out.println("Result = "+isTrue());
	}

}
