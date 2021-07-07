import java.util.*;
public class TreeStruct{

	public class WrapTreeLineAndNode{

		//当前节点
		public TreeStruct currentNode;

		//当前节点的位置，从左到右的空格数量
		public int currentLeftSpace;

		//当前节点的类型：左/右孩子
		//left  : 0
		//right : 1
		//root  : -1
		public int childType;

		public WrapTreeLineAndNode(TreeStruct _node,int type,int width){
			currentNode = _node;
			childType = type;
			currentLeftSpace = width;
		}
	}

	private String data;
	private TreeStruct left;
	private TreeStruct right;


	public TreeStruct(String v){
		data=v;
		left=null;
		right=null;
	}

	//先序，使用栈的方式
	public static void perOrderByStack(TreeStruct root,ArrayList<String> list){
		Stack<TreeStruct> stack = new Stack<TreeStruct>();

		stack.push(root);
		TreeStruct curr = null;

		while(!stack.isEmpty()){
			curr = stack.pop();
			//System.out.print(curr.data+",");
			list.add(curr.data);
			if(curr.right!=null){
				stack.push(curr.right);
			}
			if(curr.left!=null){
				stack.push(curr.left);
			}
		}
	}

	//先序，递归方式
	public static void preOrder(TreeStruct root,ArrayList<String> list){
		if(root == null)
			return;
		//System.out.print(root.data+",");
		list.add(root.data);
		preOrder(root.left,list);
		preOrder(root.right,list);
	}





	//后序，递归方式
	public static void lastOrder(TreeStruct root,ArrayList<String> list){
		if(root == null) return;

		lastOrder(root.left,list);
		lastOrder(root.right,list);
		list.add(root.data);
	}

	//后序，使用栈的方式
	public static void lastOrderByStack(TreeStruct root,ArrayList<String> list){

		Stack<TreeStruct> stack = new Stack<TreeStruct>();
		Stack<TreeStruct> help = new Stack<TreeStruct>();
		stack.push(root);
		TreeStruct curr = null;
		while(!stack.isEmpty()){
			curr=stack.pop();
			help.push(curr);
			if(curr.left!=null){
				stack.push(curr.left);
			}
			if(curr.right!=null){
				stack.push(curr.right);
			}
		}

		while(!help.isEmpty()){
			list.add(help.pop().data);
		}
	}




	//中序，递归方式
	public static void inOrder(TreeStruct root,ArrayList<String> list){
		if(root == null) return ;
		inOrder(root.left,list);
		list.add(root.data);
		inOrder(root.right,list);
	}

	//中序，使用栈的方式
	public static void inOrderByStack(TreeStruct root,ArrayList<String> list){
		Stack<TreeStruct> stack = new Stack<TreeStruct>();
		TreeStruct curr = root;

		while(!stack.isEmpty() || curr != null){
			if(curr!=null){
				stack.push(curr);
				curr = curr.left;
			}
			else{
				curr = stack.pop();
				list.add(curr.data);
				curr = curr.right;
			}
		}
	}



	//层次遍历树
	public static void levelOrder(TreeStruct root,ArrayList<String> list){
		Queue<TreeStruct> queue = new LinkedList<TreeStruct>();
		queue.offer(root);

		while(!queue.isEmpty()){
			TreeStruct curr = queue.poll();
			//list.add(curr.data);
			System.out.print(curr.data+",");
			if(curr.left != null){
				queue.offer(curr.left);
			}
			if(curr.right!=null){
				queue.offer(curr.right);
			}
		}
	}


	//获取树最大宽度
	public static int getMaxWidth(TreeStruct root){
		TreeStruct currEnd=root;
		TreeStruct nextEnd=null;
		int currCount=0;
		int maxCount=0;
		Queue<TreeStruct> queue = new LinkedList<TreeStruct>();
		queue.offer(root);

		while(!queue.isEmpty()){
			TreeStruct curr = queue.poll();
			currCount++;
			if(curr.left !=null){
				queue.offer(curr.left);
				nextEnd = curr.left;
			}
			if(curr.right !=null){
				queue.offer(curr.right);
				nextEnd = curr.right;
			}

			if(curr == currEnd){
				currEnd = nextEnd;
				maxCount = Math.max(maxCount,currCount);
				currCount = 0;
				nextEnd = null;
			}
		}
		return maxCount;
	}


	//获取树最大高度
	public static int getMaxLevel(TreeStruct root){
		TreeStruct currEnd=root;
		TreeStruct nextEnd=null;
		int maxLevel=0;
		Queue<TreeStruct> queue = new LinkedList<TreeStruct>();
		queue.offer(root);

		while(!queue.isEmpty()){
			TreeStruct curr = queue.poll();
			if(curr.left !=null){
				queue.offer(curr.left);
				nextEnd = curr.left;
			}
			if(curr.right !=null){
				queue.offer(curr.right);
				nextEnd = curr.right;
			}

			if(curr == currEnd){
				currEnd = nextEnd;
				maxLevel++;
				nextEnd = null;
			}
		}
		return maxLevel;
	}




	//打印树
	public static int printBinTree(TreeStruct root){

		int maxWidth = 180;
		int perWidth = 5;
		int usedWidth = 0;
		TreeStruct currEnd=root;
		TreeStruct nextEnd=null;

		Queue<WrapTreeLineAndNode> nodeQueue = new LinkedList<WrapTreeLineAndNode>();
		Queue<WrapTreeLineAndNode> lineQueue = new LinkedList<WrapTreeLineAndNode>();
		nodeQueue.offer(new WrapTreeLineAndNode(root,-1,(maxWidth-root.data.length())/2));

		while(!nodeQueue.isEmpty()){

			WrapTreeLineAndNode curr = nodeQueue.poll();
			int midd = curr.currentNode.currentLeftSpace;

			if(curr.currentNode.left !=null){

				nodeQueue.offer(new WrapTreeLineAndNode(curr.currentNode.left,0,midd-perWidth));
				lineQueue.offer(new WrapTreeLineAndNode(curr.currentNode.left,0,midd-perWidth));
				nextEnd = curr.currentNode.left;

			}
			if(curr.currentNode.right !=null){
				
				nodeQueue.offer(new WrapTreeLineAndNode(curr.currentNode.right,1,midd+perWidth));
				lineQueue.offer(new WrapTreeLineAndNode(curr.currentNode.right,1,midd+perWidth));
				nextEnd = curr.currentNode.right;
			}

			if(curr.currentNode == currEnd){
				printLine(lineQueue);
				currEnd = nextEnd;
				nextEnd = null;
			}
		}
		return maxCount;
	}

	private static void printLine(Queue<WrapTreeLineAndNode> lineQueue){
		System.out.println();
		while(!lineQueue.isEmpty()){
			WrapTreeLineAndNode line = line.poll();
			if(line.childType == 0){
				printStr(getStr("/",line.currentLeftSpace));
			}else{
				printStr(getStr("\\",line.currentLeftSpace));
			}
		}
	}

	private static String getStr(String str,int spaceCount){
		for (int i=0; i<spaceCount; i++) {
			str= " "+str;
		}
		return str;
	}

	private static void printStr(String v){
		System.out.print(v);
	}



	public static TreeStruct getNode(){
		String v = Character.toString((char)('A'+((int)(Math.random()*26))));
		return new TreeStruct(v);
	}

	public static void randomBinTree(TreeStruct root,int maxLevel){

		TreeStruct p = root;
		int left = maxLevel;
		int right = maxLevel;

		int random = (int)(Math.random()*9999);

		while(left-- > 0){
			TreeStruct node =getNode();
			if(random % 7 >0){
				p.left = node;
				p.right=getNode();
				p=node;
			}
			else{
				p.right = node;
				p = node;
			}

		}
		p = root.right;
		while(right-- > 0){
			TreeStruct node =getNode();
			if(random % 3 >0){
				p.right = node;
				p.left=getNode();
				p=node;
			}
			else{
				p.left = node;
				p = node;
			}

		}
	}

	public static Boolean compar(ArrayList<String> list,ArrayList<String> list2){
		if(list.size()!= list2.size()) return false;
		int size = list.size()-1;
		while(size >0){
			if(list.get(size) != list2.get(size--)) return false;
		}
		return true;
	}

	public static Boolean isTrue(){
		int max = 1000000;
		for (int i=0; i<max;i++ ) {
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> list2 = new ArrayList<String>();
			TreeStruct root = new TreeStruct("AAAA");
			randomBinTree(root,500);
			inOrder(root,list);
			inOrderByStack(root,list2);
			if(!compar(list,list2)){
				return false;
			}
		}
		return true;
		
	}

	public static void printTreeTest(){
		ArrayList<String> list = new ArrayList<String>();
		TreeStruct root = new TreeStruct("AAAA");
		randomBinTree(root,4);
		printBinTree(root);
		//System.out.print("MaxLevel = "+getMaxLevel(root));
		//System.out.print("MaxWidth = "+getMaxWidth(root));
	}

	public static void main(String[] args) {

		//System.out.print("result = "+isTrue());
		printTreeTest();
		
	}







}
