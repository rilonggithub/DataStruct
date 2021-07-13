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


	public static class Info{
		public boolean isFull;
		public boolean isCBT;
		public int high;

		public Info(boolean full,boolean cbt,int h){
			isFull = full;
			isCBT = cbt;
			high = h;
		}
	}


	public static Info process(Node head){
		if(head == null){
			return new Info(true,true,0);
		}

		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);

		boolean isFull=false;
		boolean isCBT=false;
		int high = Math.max(leftInfo.high,rightInfo.high)+1;

		if(leftInfo.isFull &&
		   rightInfo.isFull &&
		   leftInfo.high == rightInfo.high){
			isFull=true;
		}

		if(isFull){
			isCBT=true;
		}else{

			if(leftInfo.isCBT && rightInfo.isCBT){

				if(leftInfo.isFull && rightInfo.isCBT &&
					leftInfo.high == rightInfo.high)
					isCBT=true;

				if(leftInfo.isFull && rightInfo.isFull &&
					leftInfo.high == rightInfo.high)
					isCBT=true;

				if(leftInfo.isCBT && rightInfo.isFull &&
					leftInfo.high == rightInfo.high+1)
					isCBT = true;
			}
		}
		return new Info(isFull,isCBT,high);
	}

	public static boolean isCBT(Node head){
		return process(head).isCBT;
	}

	public static boolean isCBT2(Node head){
		if (head == null) {
			return true;
		}
		Queue<Node> queue=new LinkedList<Node>();
		queue.offer(head);
		boolean leaf = false;

		while(!queue.isEmpty()){
			Node curr = queue.poll();

			Node l = curr.left;
			Node r = curr.right;

			if((leaf && (l!=null || r!=null))
				||
				(l==null && r!=null))
				return false;

			if(l != null)
				queue.offer(l);
			if(r!=null)
				queue.offer(r);

			if(l ==null && r == null)
				leaf=true;
		}
		return true;
	}

	public static Node getRandmoTree(int max){
		return randomTree(1,max);
	}

	public static Node randomTree(int currLevel,int maxLevel){
		if(currLevel>maxLevel || Math.random()>0.5)
			return null;

		Node head = new Node((int)Math.random()*100);
		head.left = randomTree(currLevel+1,maxLevel);
		head.right = randomTree(currLevel+1,maxLevel);

		return head;
	}

	public static boolean isTrue(){
		int max = 100000;
		for (int i=0; i<max; i++) {
			Node root = getRandmoTree(10);
			if(isCBT(root) != isCBT2(root))
				return false;
		}
		return true;
	}

	public static void main(String[] args){
		System.out.println("Result = "+isTrue());
	}

}
