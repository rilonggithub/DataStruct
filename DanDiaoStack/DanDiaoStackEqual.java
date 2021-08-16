import java.util.*;

public class DanDiaoStackEqual{

	private Stack<LinkedList<Integer>> stack;
	private int[] arr;

	public DanDiaoStackEqual(int[] _arr){
		arr = _arr;
		stack = new Stack<LinkedList<Integer>>();
	}

	public int[][] getResult(){

		int[][] result = new int[arr.length][2];
		boolean isNotEqual=true;
		for (int i=0; i<arr.length;i++ ) {
			LinkedList<Integer> list = new LinkedList<Integer>();
			isNotEqual=true;
			if(stack.isEmpty()){
				list.offerLast(i);
				stack.push(list);
			}else{
				while(!stack.isEmpty()){
					LinkedList<Integer> curList = stack.peek();
					int curIndex = curList.peekLast();

					if(arr[curIndex] == arr[i]){
						curList.offerLast(i);
						isNotEqual=false;
						break;
					}else if(arr[curIndex] < arr[i]){
						break;
					}else{
						curList = stack.pop();
						for (int cur : curList ) {
							result[cur][0] = stack.isEmpty()?-1:stack.peek().peekLast();
							result[cur][1] = i;
						}
					}
				}
				if(isNotEqual){
					list.offerLast(i);
					stack.push(list);
				}
			}
		}
		while(!stack.isEmpty()){
			LinkedList<Integer> curList = stack.pop();
			for (int cur : curList ) {
				result[cur][0] = stack.isEmpty()?-1:stack.peek().peekLast();
				result[cur][1] = -1;
			}
		}

		return result;
	}



	public static void main(String[] args){
		int[] arr= new int[]{2,3,3,3,3,1,3,0,7,3};
		DanDiaoStackEqual dan=new DanDiaoStackEqual(arr);
		int[][] result = dan.getResult();
		for (int i=0; i<result.length;i++ ) {
			for (int j=0; j<result[i].length; j++) {
				System.out.print(result[i][j]+",");
			}
			System.out.println();
		}
	}
}
