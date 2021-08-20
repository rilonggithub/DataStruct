import java.util.*;

public class SubMatrixSum{

	private int[][] arr;
	private int[][] compress;

	public SubMatrixSum(int[][] _arr){
		arr = _arr;
		getCompress();
	}

	private void getCompress(){
		int N=arr.length;
		int M = arr[0].length;
		compress = new int[N][M];

		for (int i=0; i<M; i++) {
			compress[0][i] = arr[0][i];
		}

		for (int i=1; i<N; i++) {
			//System.out.println();
			for (int j=0; j<M;j++ ) {
				compress[i][j] = (arr[i][j] == 0 ? 0: compress[i-1][j] + 1);
				//System.out.print(compress[i][j]+",");
			}
		}
	}

	public int getSubResult(int[] temp){

		int[][] result=getArray(temp);
		int sum = 0;

		// for (int i=0; i<result.length;i++ ) {
		// 	for (int j=0; j<result[i].length; j++) {
		// 		System.out.print(result[i][j]+",");
		// 	}
		// 	System.out.println();
		// }

		for (int i=0; i<result.length;i++ ) {
			if(result[i][1]==result[i][0]&&result[i][0]==0) continue;
			int size = (result[i][1]==-1 ? temp.length : result[i][1]) - result[i][0];
			int low = Math.max(result[i][0]==-1?0:temp[result[i][0]],result[i][1]==-1?0:temp[result[i][1]]);
			int high = temp[i];

			sum+=getNum(low,high,size-1);

		}
		return sum;
	}


	public int getResult(){
		int sum = 0;

		for (int i=0; i<compress.length;i++ ) {
			sum+= getSubResult(compress[i]);
		}
		return sum;
	}



	private int[][] getArray(int[] temp){
		Stack<Integer> stack = new Stack<Integer> ();
		int[][] result = new int[temp.length][2];
		for (int i=0; i<temp.length; i++) {
			if(stack.isEmpty()){
				stack.push(i);
			}else{
				while(!stack.isEmpty()){
					if(temp[stack.peek()] < temp[i]){
						break;
					}
					int cur = stack.pop();
					if(temp[cur] != temp[i]){
						result[cur][0]= stack.isEmpty()?-1:stack.peek();
						result[cur][1]= i;
					}
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

	private int getNum(int start,int end,int length){
		//System.out.println("start="+start+",end="+end+",length="+length);
		return (length*(length+1)/2) * (end-start);
	}

	public static void main(String[] args){
		int[][] arr = new int[][]{
			{1,1,0,1,1,0,1,1,0,1,1},
			{1,1,0,1,1,1,1,1,0,1,1},
			{0,1,1,1,1,1,1,1,1,1,0},
			{1,1,0,1,1,1,1,1,1,1,1},
			{1,1,0,1,1,0,1,1,1,0,1},
		};
		SubMatrixSum ss = new SubMatrixSum(arr);
		System.out.println("Sum = "+ss.getResult());
		System.out.println("Sum = "+numSubmat(arr));
	}






































	public static int numSubmat(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			return 0;
		}
		int nums = 0;
		int[] height = new int[mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
			}
			nums += countFromBottom(height);
		}
		return nums;

	}

	public static int countFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int nums = 0;
		int[] stack = new int[height.length];
		int si = -1;
		for (int i = 0; i < height.length; i++) {
			while (si != -1 && height[stack[si]] >= height[i]) {
				int cur = stack[si--];
				if (height[cur] > height[i]) {
					int left = si == -1 ? -1 : stack[si];
					int n = i - left - 1;
					int down = Math.max(left == -1 ? 0 : height[left], height[i]);
					nums += (height[cur] - down) * num(n);
				}

			}
			stack[++si] = i;
		}
		while (si != -1) {
			int cur = stack[si--];
			int left = si == -1  ? -1 : stack[si];
			int n = height.length - left - 1;
			int down = left == -1 ? 0 : height[left];
			nums += (height[cur] - down) * num(n);
		}
		return nums;
	}

	public static int num(int n) {
		return ((n * (1 + n)) >> 1);
	}

}
