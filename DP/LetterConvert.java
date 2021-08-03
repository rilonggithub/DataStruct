public class LetterConvert{

	public static int letterConvert1(char[] arr){
		return process(arr,0);
	}


	public static int process(char[] arr,int index){

		//因为已经遍历了整个数组，说明全部都可以转换成功
		if(index== arr.length) return 1;

		if(arr[index]=='0'){
			return 0;
		}

		int p1 = process(arr,index+1);
		int p2=0;
		if(index+1 < arr.length && (arr[index]-'0')*10 + arr[index+1]-'0' < 27){
			p2= process(arr,index+2);
		}
		return p1+p2;

	}



	public static int letterConvert2(char[] arr){
		int[] dp=new int[arr.length];
		for (int i=0; i<dp.length;i++ ) {
			dp[i]=-1;
		}
		return process(arr,0);
	}


	public static int process2(char[] arr,int index,int[] dp){

		if(dp[index] !=-1) 
			return dp[index];

		//因为已经遍历了整个数组，说明全部都可以转换成功
		if(index== arr.length) 
			return 1;

		if(arr[index]=='0'){
			return 0;
		}

		int p1 = process2(arr,index+1,dp);
		int p2=0;
		if(index+1 < arr.length && (arr[index]-'0')*10 + arr[index+1]-'0' < 27){
			p2= process2(arr,index+2,dp);
		}
		dp[index]= p1+p2;
		return p1+p2;

	}



	public static int letterConvert3(char[] arr){
		int N = arr.length;
		int[] dp=new int[N+1];

		dp[N]=1;

		for (int index=N-1; index>=0; index--) {
			if(arr[index]!='0'){
				int p1 = dp[index+1];
				int p2=0;
				if(index+1 < arr.length && (arr[index]-'0')*10 + arr[index+1]-'0' < 27){
					p2= dp[index+2];
				}
				dp[index]= p1+p2;
			}
		}

		return dp[0];
	}

	public static void main(String[] args){

		char[] arr ="22333410".toCharArray();
		System.out.println("count = "+ letterConvert1(arr));
	}
}

