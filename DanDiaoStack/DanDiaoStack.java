import java.util.*;

public class DanDiaoStack{
	private Stack<Integer> stack;
	private int[] arr;
	public DanDiaoStack(int[] _arr){
		stack=new Stack<Integer>();
		arr = _arr;
	}

	public int[][] getResult(){
		int[][] result = new int[arr.length][2];

		for (int i=0; i<arr.length;i++ ) {
			
			if(stack.isEmpty()){
				stack.push(i);
			}else{
				while(!stack.isEmpty()){
					if(arr[stack.peek()] > arr[i]){
						break;
					}
					int cur = stack.pop();
					result[cur][0] = stack.isEmpty() ? -1:stack.peek();
					result[cur][1] = i;
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()){
			int cur = stack.pop();
			result[cur][0] = stack.isEmpty() ? -1:stack.peek();
			result[cur][1] = -1;
		}
		return result;
	}

	
	public static void main(String[] args){
		int[] arr= new int[]{2,3,5,1,6,0,7,9};
		DanDiaoStack dan=new DanDiaoStack(arr);
		int[][] result = dan.getResult();
		for (int i=0; i<result.length;i++ ) {
			for (int j=0; j<result[i].length; j++) {
				System.out.print(result[i][j]+",");
			}
			System.out.println();
		}
	}


}
