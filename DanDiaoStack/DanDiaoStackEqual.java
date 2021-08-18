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
		int[] arr= new int[]{3,5,1,4,2,3,7,5,2,4,5,2,0};
		DanDiaoStackEqual dan=new DanDiaoStackEqual(arr);
		int[][] result = dan.getResult();
		for (int i=0; i<result.length;i++ ) {
			for (int j=0; j<result[i].length; j++) {
				System.out.print(result[i][j]+",");
			}
			System.out.println();
		}


		int max = 0;
		int index = 0;
		int start = 0;
		int end = 0;

		for (int i=0; i<arr.length;i++ ) {
			int L = result[i][0] == -1 ? 0:result[i][0]+1;
			int R = result[i][1] == -1 ? arr.length:result[i][1];
			if(arr[i]*(R-L) > max){
				max = arr[i]*(R-L);
				index = i;
				start = L;
				end = R;
			}

			System.out.println("max  ="+max+"; index = "+i+"(R-L) = "+(R-L));
		}

		System.out.println("max  ="+max+"; index = "+index);
		System.out.println("range=["+(start)+","+(end-1)+"]");
	}
}
