public class IsBalanceTree{
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

	public static Node randomTree(int currLevel,int maxLevel){
		if(currLevel > maxLevel || Math.random() > 0.7)
			return null;

		Node head = new Node((int)(Math.random()*1000));
		head.left = randomTree(currLevel+1,maxLevel);
		head.right = randomTree(currLevel+1,maxLevel);

		return head;
	}

	public static Node getRandomTree(int maxLevel){
		return randomTree(1,maxLevel);
	}










	private static class IsBalanceInfo{
		public boolean isBalance;
		public int high;

		public IsBalanceInfo(boolean balance,int h){
			isBalance=balance;
			high = h;
		}
	}

	// public static IsBalanceInfo isBalanceTree(Node head){

	// }

	public static IsBalanceInfo isBalanceTreeProcess(Node head){

		if(head == null){
			return new IsBalanceInfo(true,0);
		}

		IsBalanceInfo leftInfo = isBalanceTreeProcess(head.left);
		IsBalanceInfo rightInfo = isBalanceTreeProcess(head.right);

		boolean isBalance = true;

		if(!leftInfo.isBalance || !rightInfo.isBalance) isBalance=false;
		if(Math.abs(leftInfo.high - rightInfo.high) >1) isBalance=false;

		int high = Math.max(leftInfo.high , rightInfo.high) + 1;

		return new IsBalanceInfo(isBalance,high);
	}


	public static void main(String[] args){
		Node root = getRandomTree(5);
		System.out.println("isBalance = "+isBalanceTreeProcess(root).isBalance);
	}

}

