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



	public static void preOrder(TreeStruct root){
		if(root == null)
			return;
		System.out.print(root.data+",");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static TreeStruct getNode(){
		String v = Character.toString((char)('A'+((int)(Math.random()*26))));
		return new TreeStruct(v);
	}

	public static void randomBinTree(TreeStruct root,int maxNodes){

		TreeStruct p = root;
		int left = maxNodes/2;
		int right = maxNodes - left;

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

	public static void main(String[] args) {


		 TreeStruct root = new TreeStruct("AAAA");
		randomBinTree(root,5);
		preOrder(root);
	}

}
