import java.util.*;

public class MaxSubArray{

	private Stack<Integer> stack;
	private int[] arr;

	public MaxSubArray(int[] _arr){
		stack = new Stack<Integer> ();
		arr= _arr;
	}

	public int[][] getResult(){
		int[][] result = new int[arr.length][2];

		for (int i=0; i<arr.length; i++) {
			if(stack.isEmpty()){
				stack.push(i);
			}else{
				while(!stack.isEmpty()){
					if(arr[stack.peek()] < arr[i]){
						break;
					}
					int cur = stack.pop();
					result[cur][0]= stack.isEmpty()?-1:stack.peek();
					result[cur][1]= i;
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()){
			int cur = stack.pop();
			result[cur][0]= stack.isEmpty()?-1:stack.peek();
			result[cur][1]= -1;
		}

		return result;
	}

	public static void main(String[] args){
		int[] arr= new int[]{9,2, 5, 4, 7, 3, 8, 6};
		//                   9 11 16 20 27 30 38 44
		int[] sum= new int[arr.length];
		sum[0] = arr[0];
		for (int i=1;i<arr.length ;i++ ) {
			sum[i] = sum[i-1] + arr[i];
		}

		MaxSubArray sub = new MaxSubArray(arr);
		int[][] result = sub.getResult();



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
		for (int i=0; i<result.length;i++ ) {
			int L = result[i][0] == -1 ? sum[i]:sum[result[i][0]];
			int R = result[i][1] == -1 ? sum[arr.length-1]:sum[result[i][1]-1];
			if(arr[i]*(R-L) > max){
				max = arr[i]*(R-L);
				index = i;
				start = result[i][0] == -1 ? 0:result[i][0];
				end = result[i][1] == -1 ? arr.length-1:result[i][1];
			}
		}
		System.out.println("max  ="+max+"; index = "+index);
		System.out.println("range=["+start+","+end+"]");
	}
}
