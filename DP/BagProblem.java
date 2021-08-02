
public class BagProblem{

	public static int bag1(int[] w,int[] v,int rest){
		return process1(w,v,0,rest);
	}


	public static int process1(int[] w,int[] v,int index,int rest){

		if(rest < 0){
			return -1;
		}

		if(index == v.length){
			return 0;
		}

		int p1 = process1(w,v,index+1,rest);
		int p2 = 0;
		int next = process1(w,v,index+1,rest-w[index]);
		if(next!=-1){
			p2 = v[index]+next;
		}
		return Math.max(p1,p2);

	}















	public static int bag2(int[] w,int[] v,int rest){

		int[][] dp=new int[v.length+1][rest+1];
		for (int i=0; i<dp.length; i++) {
			for (int j=0; j<dp[0].length; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(w,v,0,rest,dp);
	}

	public static int process2(int[] w,int[] v,int index,int rest,int[][] dp){

		if(rest < 0){
			return -1;
		}

		if(dp[index][rest]!=-1){
			return dp[index][rest];
		}
		

		if(index == v.length){
			return 0;
		}
		int ans=0;
		int p1 = process2(w,v,index+1,rest,dp);
		int p2 = 0;
		int next = process2(w,v,index+1,rest-w[index],dp);
		if(next!=-1){
			p2 = v[index]+next;
		}
		ans= Math.max(p1,p2);
		dp[index][rest]=ans;
		return ans;

	}















	public static int bag3(int[] w,int[] v,int bag){

		int[][] dp=new int[v.length+1][bag+1];
		int N = v.length;
		int M = bag;

		for (int index=N-1; index>=0;index-- ) {
			for (int rest=M; rest>=0;rest-- ) {

				int p1 = dp[index+1][rest];
				int p2 = 0;
				int next = rest-w[index]>=0?dp[index+1][rest-w[index]]:-1;
				if(next!=-1){
					p2 = v[index]+next;
				}
				dp[index][rest] = Math.max(p1,p2);
			}
		}

		return dp[0][bag];
	}





	public static void main(String[] args){
		int[] w=new int[]{1,2,3,4,5};
		int[] v=new int[]{9,2,14,12,10};
		int bag=12;
		System.out.println("v = "+bag1(w,v,bag));
		System.out.println("v2 = "+bag2(w,v,bag));
		System.out.println("v3 = "+bag3(w,v,bag));
	}
}

