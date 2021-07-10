import java.util.*;
public class TreeStruct{

	public class WrapTreeNode{

		//当前节点
		public TreeStruct currentNode;

		//当前节点的位置，从左到右的空格数量
		public int x;

		//当前层前一个节点
		public WrapTreeNode currLevelPrevNode;

		//父节点
		public WrapTreeNode parentNode;

		//当前节点的类型：左/右孩子
		//left  : 0
		//right : 1
		//root  : -1
		public int childType;

		public WrapTreeNode(
			TreeStruct _node,
			int type,
			int _x,
			WrapTreeNode parent,
			WrapTreeNode prevNode){
			currentNode = _node;
			childType = type;
			x = _x;
			currLevelPrevNode = prevNode;
			parentNode = parent;
		}
	}

	public class TreeLine{

		public TreeLine currLevelPrevLine;

		//当前节点的位置，从左到右的空格数量
		public int x;

		//当前节点的类型：左/右孩子
		//left  : 0
		//right : 1
		//root  : -1
		public int childType;
		public TreeLine(int _x,int type){
			x =_x;
			childType = type;
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
	public  void perOrderByStack(TreeStruct root,ArrayList<String> list){
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
	public  void preOrder(TreeStruct root,ArrayList<String> list){
		if(root == null)
			return;
		//System.out.print(root.data+",");
		list.add(root.data);
		preOrder(root.left,list);
		preOrder(root.right,list);
	}





	//后序，递归方式
	public  void lastOrder(TreeStruct root,ArrayList<String> list){
		if(root == null) return;

		lastOrder(root.left,list);
		lastOrder(root.right,list);
		list.add(root.data);
	}

	//后序，使用栈的方式
	public  void lastOrderByStack(TreeStruct root,ArrayList<String> list){

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
	public  void inOrder(TreeStruct root,ArrayList<String> list){
		if(root == null) return ;
		inOrder(root.left,list);
		list.add(root.data);
		inOrder(root.right,list);
	}

	//中序，使用栈的方式
	public  void inOrderByStack(TreeStruct root,ArrayList<String> list){
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
	public  void levelOrder(TreeStruct root,ArrayList<String> list){
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
	public  int getMaxWidth(TreeStruct root){
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
	public  int getMaxLevel(TreeStruct root){
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

	//将一棵树序列化
	public Queue<String> getSerialization(TreeStruct root){
		if(null == root) return null;
		Queue<String> queue = new LinkedList<String>();
		serialzation(root,queue);
		return queue;
	}

	private void serialzation(TreeStruct root,Queue<String> queue){
		if(root == null)
			queue.offer("#");
		else{
			queue.offer(root.data);
			serialzation(root.left,queue);
			serialzation(root.right,queue);
		}
	}

	//反序列化成一棵树
	public TreeStruct unSerialztaion(Queue<String> queue){
		if(null == queue || queue.size() == 0) return null;
		return unSerial(queue);
	}

	private TreeStruct unSerial(Queue<String> queue){
		String v = queue.poll();
		if(v=="#") return null;
		TreeStruct root = new TreeStruct(v);
		root.left = unSerial(queue);
		root.right = unSerial(queue);
		return root;
	}


	public Boolean isCompleteBinTree(TreeStruct root){
		if(root == null ||
			root.left == null && root.right ==null ||
			root.left !=null && root.right ==null) return true;

		boolean isleaf=false;

		Queue<TreeStruct> queue = new LinkedList<TreeStruct>();
		queue.offer(root);


		while(!queue.isEmpty()){

			TreeStruct curr = queue.poll();
			if(curr.right!=null && curr.left == null) 
				return false;

			if(curr.left == null && curr.right==null){
				isleaf=true;
			}
			if(isleaf && (curr.left !=null || curr.right!=null))
					return false;
			

			if(curr.left !=null)
				queue.offer(curr.left);
			if(curr.right!=null)
				queue.offer(curr.right);
		}
		return true;
	}



	private String getChildStr(TreeStruct root){
		String str=root.data;
		if(root.left != null) str = "^"+str;
		if(root.right != null) str = str+"^";
		return str;
	}

	private void printNode(WrapTreeNode node,int currLevel){

		int perWidth = 20/currLevel;
		if(node.parentNode == null){
			printStr(getStr(getChildStr(node.currentNode),node.x));
			return;
		}

		if(node.currLevelPrevNode == null){
			//left
			if(node.childType == 0){
				int space = node.parentNode.x - perWidth;
				printStr(getStr(getChildStr(node.currentNode),space));
			}
			//right
			else{
				int space = node.parentNode.x + perWidth;
				printStr(getStr(getChildStr(node.currentNode),space));
			}
		}else{
			int space = node.parentNode.x - node.currLevelPrevNode.x+perWidth;
			if(space+node.currLevelPrevNode.x >node.parentNode.x && currLevel > 2){
				space  = node.parentNode.x - node.currLevelPrevNode.x;
			}
			
			printStr(getStr(getChildStr(node.currentNode),space));
		}

	}

	//打印树
	public  void printBinTree(TreeStruct root){

		int maxWidth = 180;
		int perLeftWidth = 10;
		int perRightWidth = 20;
		int usedWidth = 0;
		TreeStruct currEnd=root;
		TreeStruct nextEnd=null;
		int currLevel =1;
		WrapTreeNode currLevelPrev=null;


		Queue<WrapTreeNode> nodeQueue = new LinkedList<WrapTreeNode>();
		Queue<TreeLine> lineQueue = new LinkedList<TreeLine>();

		nodeQueue.offer(
			new WrapTreeNode(
				root,-1,(maxWidth-root.data.length())/2,null,null));

		while(!nodeQueue.isEmpty()){

			WrapTreeNode curr = nodeQueue.poll();
			printNode(curr,currLevel);
			//printStr(getStr(curr.currentNode.data,curr.x));
			int midd = curr.x;

			if(curr.currentNode.left !=null){
				WrapTreeNode l = new 
				WrapTreeNode(curr.currentNode.left,
					0,
					midd-perLeftWidth,
					curr,
					currLevelPrev);
				
				
				nodeQueue.offer(l);
				//lineQueue.offer(new TreeLine(midd-perWidth,0));
				nextEnd = curr.currentNode.left;
				currLevelPrev = l;


			}
			if(curr.currentNode.right !=null){
				WrapTreeNode r = new 
				WrapTreeNode(curr.currentNode.right,
					1,
					midd+perRightWidth,
					curr,
					currLevelPrev);
				

				nodeQueue.offer(r);
				//lineQueue.offer(new TreeLine(midd+perWidth,1));
				nextEnd = curr.currentNode.right;
				currLevelPrev = r;
			}

			if(curr.currentNode == currEnd){
				printLine(lineQueue);
				currEnd = nextEnd;
				nextEnd = null;
				currLevelPrev = null;
				currLevel++;
			}
		}
	}

	private  void printLine(Queue<TreeLine> lineQueue){
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		// while(!lineQueue.isEmpty()){
		// 	TreeLine line = lineQueue.poll();
		// 	if(line.childType == 1){
		// 		printStr(getStr("/",line.x));
		// 	}else{
		// 		printStr(getStr("\\",line.x));
		// 	}
		// }
		// System.out.println();
	}

	private  String getStr(String str,int spaceCount){
		for (int i=0; i<spaceCount; i++) {
			str= " "+str;
		}
		return str;
	}

	private  void printStr(String v){
		System.out.print(v);
	}




	public static TreeStruct getNode(){
		String v = Character.toString((char)('A'+((int)(Math.random()*26))));
		return new TreeStruct(v);
	}

	public static void randomBinTree(TreeStruct root,int maxLevel){

		TreeStruct p = root;
		int left = maxLevel;


		int random = (int)(Math.random()*9999);
		int ont = (int)(Math.random()*8)+1;

		while(left > 0){
			if(left == maxLevel){
				p.right = getNode();
				p.left=getNode();
				p = p.left;
			}
			else{
				if(random/left % ont >0){
					p.left = getNode();
					p=p.left;
				}
				else{
					p.right = getNode();
					p.left=getNode();
					p = p.right;
				}
			}
			left--;
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

	public static TreeStruct getRandomBinTree(){
		TreeStruct root = new TreeStruct("AAAA");

		root.left = new TreeStruct("A1");
		root.right = new TreeStruct("A2");
		randomBinTree(root.left,2);
		randomBinTree(root.right,1);

		return root;
	}
	public static Boolean isTrue(){
		int max = 1000000;
		for (int i=0; i<max;i++ ) {
			ArrayList<String> list = new ArrayList<String>();
			ArrayList<String> list2 = new ArrayList<String>();
				
			TreeStruct root = getRandomBinTree();
			root.inOrder(root,list);
			root.inOrderByStack(root,list2);
			if(!compar(list,list2)){
				return false;
			}
		}
		return true;
		
	}

	

	public static void printSerialztion(TreeStruct root){
		//TreeStruct root = getRandomBinTree();
		Queue<String> queue = root.getSerialization(root);
		while(!queue.isEmpty())
		{
			System.out.print(queue.poll()+",");
		}
	}

	public static void getUnSerialzation(Queue<String> queue){
		TreeStruct root = (new TreeStruct("")).unSerialztaion(queue);
		root.printBinTree(root);
	}

	public static TreeStruct printTreeTest(){
		TreeStruct root = getRandomBinTree();
		root.printBinTree(root);
		return root;
	}

	public static void main(String[] args) {

		TreeStruct root = printTreeTest();
		printSerialztion(root);
		System.out.print("result = "+root.isCompleteBinTree(root));
		
	}







}
