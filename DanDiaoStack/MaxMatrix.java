import java.util.*;
public class MaxMatrix{

	private int[][] arr;
	private int[][] compress;


	public MaxMatrix(int[][] _arr){
		arr = _arr;
		getCompress();
	}

	public int getResult(){
		int max=0;
		for (int i=0; i<compress.length;i++ ) {
			int[][] result = process(compress[i]);
			int v = getMaxSub(result,compress[i]);
			max = Math.max(v,max);
		}
		return max;
	}

	private int[][] process(int[] temp){
		
		int[][] result = new int[temp.length][2];
		int N  = temp.length;
		Stack<LinkedList<Integer>> stack = new Stack<LinkedList<Integer>>();
		LinkedList<Integer> curr;
		boolean isEqual=false;
		for (int i=0; i<N; i++) {
			isEqual=false;
			if(stack.isEmpty()){
				curr = new LinkedList<Integer>();
				curr.offerLast(i);
				stack.push(curr);
			}else{
				while(!stack.isEmpty()){
					curr = stack.peek();
					if(temp[curr.peekLast()] < temp[i]){
						break;
					}else if(temp[curr.peekLast()] == temp[i]){
						curr.offerLast(i);
						isEqual=true;
						break;
					}else{
						curr = stack.pop();
						for (int v:curr ) {
							result[v][0] = stack.isEmpty()? -1 :stack.peek().peekLast();
							result[v][1] = i;
						}
					}
				}
				if(!isEqual){
					curr = new LinkedList<Integer>();
					curr.offerLast(i);
					stack.push(curr);
				}
			}
		}
		while(!stack.isEmpty()){
			
			curr = stack.pop();
			for (int v:curr ) {
				result[v][0] = stack.isEmpty()? -1 :stack.peek().peekLast();
				result[v][1] = -1;
			}
		
		}
		return result;
	}


	private int getMaxSub(int[][] temp,int[] org){
		//第一行[x][y]：存放开始坐标
		//第二行[x][y]：存放结束坐标
		//第三行[2][0]：存放值
		//int[][] result = new int[3][2];
		int startX;
		int startY;
		int endX;
		int endY;
		int max=0;
		for (int i=0; i<org.length;i++ ) {
			int L = temp[i][0] == -1 ?0 :temp[i][0]+1;
			int R = temp[i][1] == -1 ? org.length : temp[i][1];
			max = Math.max(max,org[i]*(R-L));
		}
		return max;
	}


	private void getCompress(){
		int N=arr.length;
		int M = arr[0].length;
		compress = new int[N][M];

		for (int i=0; i<N; i++) {
			compress[0][i] = arr[0][i];
		}

		for (int i=1; i<N; i++) {
			//System.out.println();
			for (int j=0; j<M;j++ ) {
				compress[i][j] = arr[i][j] == 0 ? 0: compress[i-1][j] + 1;
				//System.out.print(compress[i][j]+",");
			}
		}
	}





	public static void main(String[] args){
		int[][] arr = new int[][]{
			{1,1,0,1,1,0,1,1,0,1,1},
			{1,1,0,1,1,1,1,1,0,1,1},
			{1,1,1,1,1,1,1,1,1,1,0},
			{1,1,0,1,1,1,1,1,1,1,1},
			{1,1,0,1,1,0,1,1,1,1,1},
		};

		MaxMatrix mm = new MaxMatrix(arr);
		int max = mm.getResult();
		System.out.println("max = "+max);
	}
}
