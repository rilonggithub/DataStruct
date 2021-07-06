import java.util.*;
public class TreeStruct{

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
		p = root;
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

	public static void main(String[] args) {

		System.out.print("result = "+isTrue());
		
	}

}