public class RobotWalkProblem{


	public static int robotWalk1(int N,int start,int end,int rest){
		return process1(N,start,end,rest);
	}

	public static int process1(int N,int cur,int end,int rest){

		if(rest ==0){
			return cur == end ? 1 : 0;
		}
		else if(cur == 0){
			return process1(N,1,end,rest-1);
		}else

		if(cur == N){
			return process1(N,N-1,end,rest-1);
		}else{

			return  process1(N,cur-1,end,rest-1)+process1(N,cur+1,end,rest-1);
		}
	}














	public static int robotWalk2(int N,int start,int end,int rest){
		int[][] dp=new int[N+1][rest+1];
		for (int i=0;i<=N ;i++ ) {
			for (int j=0; j<=rest; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(N,start,end,rest,dp);
	}

	public static int process2(int N,int cur,int end,int rest,int[][] dp){

		if(dp[cur][rest]!=-1){
			return dp[cur][rest];
		}

		int ans=0;
		if(rest ==0){
			ans= cur == end ? 1 : 0;
		}
		else if(cur == 0){
			ans= process2(N,1,end,rest-1,dp);
		}else

		if(cur == N){
			ans= process2(N,N-1,end,rest-1,dp);
		}else{

			ans=  process2(N,cur-1,end,rest-1,dp)+process2(N,cur+1,end,rest-1,dp);
		}
		dp[cur][rest]=ans;
		return ans;
	}












	public static int robotWalk3(int N,int start,int end,int step){
		int[][] dp=new int[N+1][step+1];

		//这里初始化的是第一列
		//则说明：
		//在后面的双重循环中，都是按照列的顺序，从左往右初始化

		for (int cur=0;cur<=N ;cur++ ) {
			dp[cur][0]= cur==end?1:0;
		}


		//按照列的顺序，从左往右初始化，第0列已经初始化
		//从第1列出发，将0-N行的第一列初始化，以此类推
		for (int rest=1; rest<=step;rest++) {
			for (int cur=0;cur<=N ;cur++ ) {
				int ans=0;
				if(cur == 0){
					ans= dp[1][rest-1];
				}else

				if(cur == N){
					ans= dp[N-1][rest-1];
				}else{

					ans=  dp[cur-1][rest-1]+dp[cur+1][rest-1];
				}
				dp[cur][rest]=ans;
			}
		}
		
		return dp[start][step];
	}






	public static void main(String[] args){
		int N=4;
		int start=2;
		int end=4;
		int rest=8;
		System.out.println("count = "+robotWalk1(N,start,end,rest));
		System.out.println("count = "+robotWalk2(N,start,end,rest));
		System.out.println("count = "+robotWalk3(N,start,end,rest));
	}
}
