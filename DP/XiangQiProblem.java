import java.util.*;
public class XiangQiProblem{



	public static void main(String[] args){
		int row = 9;
		int clo = 2;
		int step = 5;
		//System.out.println("Step = "+xiangqi1(row,clo,step));
		System.out.println("Step = "+xiangqi2(row,clo,step));
		System.out.println("Step = "+dp(row,clo,step));

		System.out.println("enable connect = "+xiangqi4(row,clo));

		List<String> list=new ArrayList<>();
		System.out.println("Min Step = "+xiangqi5(row,clo,list));
		System.out.println("Steps = "+list.get(0));
	}



	public static int xiangqi1(int row,int clo,int step){
		int N = 9;
		int M = 8;
		
		return process1(N,M,0,0,row,clo,step);
	}


	public static int process1(int N,int M,int cur_row,int cur_clo,int end_row,int end_clo,int rest){
		if(cur_row> N || cur_row<0 || cur_clo >M || cur_clo <0)
			return 0;

		if(rest ==0){
			return cur_row == end_row && cur_clo == end_clo ?1 :0;
		}
		
		int ans=
		 process1(N,M,cur_row+1,cur_clo-2,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row+2,cur_clo-1,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row+2,cur_clo+1,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row+1,cur_clo+2,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row-1,cur_clo+2,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row-2,cur_clo+1,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row-2,cur_clo-1,end_row,end_clo,rest-1)+
		 process1(N,M,cur_row-1,cur_clo-2,end_row,end_clo,rest-1);
		 
		return ans;
			
	}

















	public static int xiangqi2(int row,int clo,int step){
		int N = 9;
		int M = 8;
		int[][][] dp=new int[N+1][M+1][step+1];
		for (int i=0;i<=N ;i++ ) {
			for (int j=0; j<=M; j++) {
				for (int k=0; k<=step; k++) {
					dp[i][j][k]=-1;
				}
				
			}
		}
		return process2(N,M,0,0,row,clo,step,dp);
	}


	public static int process2(int N,int M,int cur_row,int cur_clo,int end_row,int end_clo,int rest,int[][][] dp){
		if(cur_row> N || cur_row<0 || cur_clo >M || cur_clo <0)
			return 0;

		if(dp[cur_row][cur_clo][rest] !=-1){
			return dp[cur_row][cur_clo][rest];
		}

		if(rest ==0){
			return cur_row == end_row && cur_clo == end_clo ?1 :0;
		}
		
		int ans=
			process2(N,M,cur_row+1,cur_clo-2,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row+2,cur_clo-1,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row+2,cur_clo+1,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row+1,cur_clo+2,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row-1,cur_clo+2,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row-2,cur_clo+1,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row-2,cur_clo-1,end_row,end_clo,rest-1,dp)+
			process2(N,M,cur_row-1,cur_clo-2,end_row,end_clo,rest-1,dp);
		 
		dp[cur_row][cur_clo][rest] = ans;
		return ans;
			
	}
















	public static int dp(int a, int b, int k) {
		int N = 9;
		int M = 8;
		int[][][] dp = new int[N+1][M+1][k + 1];
		dp[a][b][0] = 1;
		for (int rest = 1; rest <= k; rest++) {
			for (int x = 0; x <= N; x++) {
				for (int y = 0; y <= M; y++) {
					int ans = 
						process3(N,M,dp, x + 2, y + 1, rest - 1)+
						process3(N,M,dp, x + 1, y + 2, rest - 1)+
						process3(N,M,dp, x - 1, y + 2, rest - 1)+
						process3(N,M,dp, x - 2, y + 1, rest - 1)+
						process3(N,M,dp, x - 2, y - 1, rest - 1)+
						process3(N,M,dp, x - 1, y - 2, rest - 1)+
						process3(N,M,dp, x + 1, y - 2, rest - 1)+
						process3(N,M,dp, x + 2, y - 1, rest - 1);
					dp[x][y][rest] = ans;
				}
			}
		}
		return dp[0][0][k];
	}
	public static int process3(int N,int M,int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > N || y < 0 || y > M) {
			return 0;
		}
		return dp[x][y][rest];
	}
















	public static boolean xiangqi4(int row,int clo){
		int N = 9;
		int M = 8;
		int[][] dp=new int[N+1][M+1];
		for (int i=0;i<=N ;i++ ) {
			for (int j=0; j<=M; j++) {
					dp[i][j]=-1;
			}
		}
		boolean[] result= new boolean[]{false};
		process4(N,M,0,0,row,clo,result,dp);
		return result[0];
	}


	public static void process4(int N,int M,int cur_row,int cur_clo,int end_row,int end_clo,boolean[] result,int[][] dp){
		if(cur_row> N || cur_row<0 || cur_clo >M || cur_clo <0)
			return ;

		if(result[0]) return;
		if(dp[cur_row][cur_clo]!=-1) return;

		if(cur_row == end_row && cur_clo == end_clo){
			result[0]=true;
			return ;
		}
		else{
		 dp[cur_row][cur_clo]=0;
		 process4(N,M,cur_row+1,cur_clo-2,end_row,end_clo,result,dp);
		 process4(N,M,cur_row+2,cur_clo-1,end_row,end_clo,result,dp);
		 process4(N,M,cur_row+2,cur_clo+1,end_row,end_clo,result,dp);
		 process4(N,M,cur_row+1,cur_clo+2,end_row,end_clo,result,dp);
		 process4(N,M,cur_row-1,cur_clo+2,end_row,end_clo,result,dp);
		 process4(N,M,cur_row-2,cur_clo+1,end_row,end_clo,result,dp);
		 process4(N,M,cur_row-2,cur_clo-1,end_row,end_clo,result,dp);
		 process4(N,M,cur_row-1,cur_clo-2,end_row,end_clo,result,dp);
		 
		}
			
	}


	public static int xiangqi5(int row,int clo,List<String> list){
		int N = 90;
		int M = 80;
		row=80;
		clo=78;
		int step=0;
		for (int i=0; i<Integer.MAX_VALUE; i++) {
			process5(N,M,0,0,row,clo,i,list,"");
			step=i;
			if(!list.isEmpty()) 
				break;
		}
		
		return step;
	}


	public static void process5(
		int N,
		int M,
		int cur_row,
		int cur_clo,
		int end_row,
		int end_clo,
		int step,
		List<String> list,
		String path){
		if(cur_row> N || cur_row<0 || cur_clo >M || cur_clo <0)
			return ;

		if(cur_row == end_row && cur_clo == end_clo){
			list.add(path);
			return;
		}

		if(!list.isEmpty() || step==0) return;

		
		 process5(N,M,cur_row+1,cur_clo-2,end_row,end_clo,step-1,list,path+"("+(cur_row+1)+","+(cur_clo-2)+")");
		 process5(N,M,cur_row+2,cur_clo-1,end_row,end_clo,step-1,list,path+"("+(cur_row+2)+","+(cur_clo-1)+")");
		 process5(N,M,cur_row+2,cur_clo+1,end_row,end_clo,step-1,list,path+"("+(cur_row+2)+","+(cur_clo+1)+")");
		 process5(N,M,cur_row+1,cur_clo+2,end_row,end_clo,step-1,list,path+"("+(cur_row+1)+","+(cur_clo+2)+")");
		 process5(N,M,cur_row-1,cur_clo+2,end_row,end_clo,step-1,list,path+"("+(cur_row-1)+","+(cur_clo+2)+")");
		 process5(N,M,cur_row-2,cur_clo+1,end_row,end_clo,step-1,list,path+"("+(cur_row-2)+","+(cur_clo+1)+")");
		 process5(N,M,cur_row-2,cur_clo-1,end_row,end_clo,step-1,list,path+"("+(cur_row-2)+","+(cur_clo-1)+")");
		 process5(N,M,cur_row-1,cur_clo-2,end_row,end_clo,step-1,list,path+"("+(cur_row-1)+","+(cur_clo-2)+")");
		 
		
			
	}

	
}


