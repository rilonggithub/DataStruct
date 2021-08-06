public class MinDistanceProblem{

	public static int minDis1(int[][] arr,int startR,int startC){
		
		return arr[startR][startC]+process1(arr,startR,startC);
	}

	public static int process1(int[][] arr,int curR,int curC){

		if(curR == arr.length-1 && curC == arr[0].length-1){
			return 0;
		}

		int ans=0;

		//到最后一行，只能往右走
		if(curR== arr.length-1 && curC < arr[0].length-1){
			ans+= arr[curR][curC+1]+process1(arr,curR,curC+1);
		}
		//到最后一列，只能往下走
		else if(curR< arr.length-1 && curC == arr[0].length-1){
			ans+= arr[curR+1][curC]+process1(arr,curR+1,curC);
		}else{
			ans += Math.min(arr[curR+1][curC]+process1(arr,curR+1,curC),
				arr[curR][curC+1]+process1(arr,curR,curC+1));
		}

		return ans;
	}
















	public static int minDis2(int[][] arr,int startR,int startC){
		int N = arr.length;
		int M = arr[0].length;

		int[][] dp = new int[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				dp[i][j] = -1;
			}
		}
		return arr[startR][startC]+process2(arr,startR,startC,dp);
	}

	public static int process2(int[][] arr,int curR,int curC,int[][] dp){

		if(curR == arr.length-1 && curC == arr[0].length-1){
			return 0;
		}

		if(dp[curR][curC]!=-1){
			return dp[curR][curC];
		}

		int ans=0;

		//到最后一行，只能往右走
		if(curR== arr.length-1 && curC < arr[0].length-1){
			ans+= arr[curR][curC+1]+process2(arr,curR,curC+1,dp);
		}
		//到最后一列，只能往下走
		else if(curR< arr.length-1 && curC == arr[0].length-1){
			ans+= arr[curR+1][curC]+process2(arr,curR+1,curC,dp);
		}else{
			ans += Math.min(arr[curR+1][curC]+process2(arr,curR+1,curC,dp),
				arr[curR][curC+1]+process2(arr,curR,curC+1,dp));
		}

		dp[curR][curC]=ans;
		return ans;
	}






















	public static int minDis3(int[][] arr,int startR,int startC){
		int N = arr.length;
		int M = arr[0].length;

		int[][] dp = new int[N][M];

		dp[startR][startC] = arr[startR][startC];
		for (int i=startC+1; i<N;i++ ) {
			dp[startR][i] = arr[startR][i]+dp[startR][i-1];
		}

		for (int i=startR+1; i<N;i++ ) {
			dp[i][startC] = arr[i][startC]+dp[i-1][startC];
		}

		for (int i=startR+1; i<N; i++) {
			for (int j=startC+1; j<M; j++) {
				dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+arr[i][j];
			}
		}
		return dp[N-1][M-1];
	}


















	public static int minDis4(int[][] arr,int startR,int startC){
		int N = arr.length;
		int M = arr[0].length;

		int[] dp = new int[M];

		dp[startC] = arr[startR][startC];
		for (int i=startC+1; i<M;i++ ) {
			dp[i] = arr[startR][i]+dp[i-1];
		}

		

		for (int i=startR+1; i<N; i++) {
			for (int j=startC; j<M; j++) {
				if(j ==startC){
					dp[j] = dp[j]+arr[i][j];
				}else{
					dp[j] = Math.min(dp[j],dp[j-1])+arr[i][j];
				}
			}
		}
		return dp[M-1];
	}






	public static void main(String[] args){
		int[][] arr=new int[][]{
			{1,  30, 100,100,100},
			{40, 100,100,100,100},
			{110,100,100,100,100},
			{1,  100,100,100,100},
			{1,  1,  1,  1,  1}
		};
		int i=0;
		int j=4;
		System.out.println("Min = "+minDis1(arr,i,j));
		System.out.println("Min = "+minDis2(arr,i,j));
		System.out.println("Min = "+minDis3(arr,i,j));
		System.out.println("Min = "+minDis4(arr,i,j));
	}
}
