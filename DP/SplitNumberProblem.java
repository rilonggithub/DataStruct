public class SplitNumberProblem{

	public static int splitNum(int num){
		int[] arr=new int[num];
		for (int i=0; i<arr.length;i++ ) {
			arr[i] = i+1;
		}


		return process(arr,num,0,"");
	}


	public static int process(int[] arr,int rest,int index,String path){

		if(rest == 0){
			//System.out.println(path);
			return 1;
		}

		int ways=0;


		for (int ci = index;  ci<arr.length&&rest - arr[ci]>=0; ci++) {
			ways += process(arr,rest-arr[ci],ci,path+arr[ci]+",");
			
		}
		return ways;
	}























	public static int splitNum2(int num){
		int[] arr=new int[num];
		for (int i=0; i<arr.length;i++ ) {
			arr[i] = i+1;
		}

		int[][] dp = new int[num+1][num+1];
		for (int i=0; i<=num; i++) {
			for (int j=0; j<=num; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(arr,num,0,"",dp);
	}


	public static int process2(int[] arr,int rest,int index,String path,int[][] dp){

		if(dp[rest][index]!=-1){
			return dp[rest][index];
		}

		if(rest == 0){
			//System.out.println(path);
			return 1;
		}

		int ways=0;


		for (int ci = index;  ci<arr.length&&rest - arr[ci]>=0; ci++) {
			ways += process2(arr,rest-arr[ci],ci,path+arr[ci]+",",dp);
			
		 }

		
		dp[rest][index] = ways;
		return ways;
	}

























	public static int splitNum3(int num){
		
		return process3(1,num,"");
	}


	public static int process3(int pre,int rest,String path){

		if(rest == 0){
			//System.out.println(path);
			return 1;
		}
		if(pre > rest){
			return 0;
		}
		if(rest == pre){
			//System.out.println(path);
			return 1;
		}

		int ways=0;


		for (int first = pre;  first<=rest; first++) {
			ways += process3(first,rest-first,path+first+",");
			
		}
		return ways;
	}



















	

	public static void main(String[] args){
		int num = 70;
		//System.out.println("C = "+splitNum(num));
		System.out.println("C = "+splitNum2(num));
		System.out.println("C = "+splitNum3(num));
	}
}
