import java.util.*;

public class RecursionDemo{

/**************************************** SubSequence  **************************************/

	public static HashSet<String> getSubSequence(String str){
		HashSet<String> arr=new HashSet<String>();
		subSeqProcess(str.toCharArray(),0,"",arr);
		return arr;
	}

	public static void subSeqProcess(char[] str,int length,String path,HashSet<String> list){
		if(str.length ==length){
			list.add(path);
			return ;
		}

		char ch = str[length];
		subSeqProcess(str,length+1,path,list);
		subSeqProcess(str,length+1,path+String.valueOf(ch),list);
	}






/**************************************** Permutation  *************************************/

	public static HashSet<String> getPermutation(String str){
		HashSet<String> arr=new HashSet<String>();
		ArrayList<Character> strArr=new ArrayList<Character>();

		for (int i=0; i<str.toCharArray().length; i++) {
			strArr.add(str.toCharArray()[i]);
		}
		permuProcess(strArr,"",arr);
		return arr;
	}

	public static void permuProcess(ArrayList<Character> str,String path,HashSet<String> list){

		if(str.isEmpty()){
			list.add(path);
		}else{
			int length = str.size();
			for (int i=0; i<length;i++ ) {

				char ch = str.get(i);
				str.remove(i);
				permuProcess(str,path+ch,list);
				str.add(i,ch);
			}
		}

	}






/**************************************** reboot1  *************************************/

	public static int reboot(int size,int start,int aim,int rest){
		
		return process(size,start,aim,rest);
	}

	public static int process(int size,int start,int aim,int rest){

		
		int ans;

		if(rest == 0){
			ans= start==aim?1:0;
		}

		else if(start == 1){
			ans= process(size,start+1,aim,rest-1);
		}

		else if(start == size){
			ans= process(size,size-1,aim,rest-1);
		}
		else{
			ans= process(size,start-1,aim,rest-1) + process(size,start+1,aim,rest-1);
		}
		
		return ans;
	}



/**************************************** reboot2  *************************************/

	public static long reboot2(int size,int start,int aim,int rest){
		long[][] dp = new long[size+1][rest+1];
		for (int i=0; i<=size; i++) {
			for (int j=0; j<=rest; j++) {
				dp[i][j]=-1;
			}
		}
		return process2(size,start,aim,rest,dp);
	}

	public static long process2(int size,int start,int aim,int rest,long[][] dp){

		if(dp[start][rest]!=-1)
			return dp[start][rest];

		long ans;
		if(rest == 0){
			ans= start==aim?1:0;
		}else if(start == 1){
			ans= process2(size,start+1,aim,rest-1,dp);
		}else if(start == size){
			ans= process2(size,size-1,aim,rest-1,dp);
		}
		else{
			ans= process2(size,start-1,aim,rest-1,dp) + process2(size,start+1,aim,rest-1,dp);
		}

		dp[start][rest]=ans;
		return ans;
	}



/**************************************** reboot3  *************************************/

	public static long reboot3(int size,int start,int aim,int rest){
		
		long[][] dp = new long[size+1][rest+1];
		
		dp[aim][0]= 1;

		for (int i=1; i<=rest; i++) {
			dp[1][i] = dp[2][i - 1];
			for (int j=2; j<size; j++) {
				
					dp[j][i] = dp[j-1][i-1]+dp[j+1][i-1];
			}
			dp[size][i] = dp[size - 1][i - 1];
		}
		return dp[start][rest];
	}










/**************************************** play  *************************************/


// 根据规则，返回获胜者的分数
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];

		for (int i=0;i<N ;i++ ) {
			for (int j=0; j<N;j++ ) {
				fmap[i][j]=gmap[i][j]=-1;
			}
		}
		int first = f1(arr, 0, arr.length - 1,fmap,gmap);
		int second = g1(arr, 0, arr.length - 1,fmap,gmap);
		
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R,int[][] fmap,int[][] gmap) {
		
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int p1 = arr[L] + g1(arr, L + 1, R, fmap, gmap);
			int p2 = arr[R] + g1(arr, L, R - 1, fmap, gmap);
			ans = Math.max(p1, p2);
		}
		fmap[L][R] = ans;
		return ans;
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R,int[][] fmap,int[][] gmap) {
		
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f1(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
			int p2 = f1(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
			ans = Math.min(p1, p2);
		}
		gmap[L][R] = ans;
		return ans;
	}

	
	public static int[] getArray(){
		int max = 5;
		int[] arr=new int[max];
		for (int i=0; i<max; i++) {
			arr[i] = (int)(Math.random()*100);
		}
		return arr;
	}







/**************************************** Bag  *************************************/

	public static int bag1(int[] w,int[] v,int bag){
		return process3(w,v,0,bag);
	}


	public static int process3(int[] w,int[] v,int index,int rest){
		if(rest<0){
			return -1;
		}

		if(index == w.length){
			return 0;
		}

		int p1 = process3(w,v,index+1,rest);
		int p2=0;
		int next = process3(w,v,index+1,rest-w[index]);
		if(next != -1){
			p2 = v[index]+next;
		}

		return Math.max(p1,p2);
	}




/**************************************** Cut  *************************************/

	public static int cut(int[]w,int[] v,int restLength){
		List<String> list= new ArrayList<String>();
		int[] dp = new int[restLength+1];
		for (int i=0; i<=restLength; i++) {
			dp[i]=-1;
		}
		int max= process4(w,v,restLength,"",list,dp);

		for (String str:list ) {
			System.out.println(str);
		}
		System.out.println();
		for (int i=0; i<=restLength; i++) {
			System.out.print(dp[i]+",");
		}
		System.out.println();
		return max;
	}

	public static void f(String path,List<String> list){
		if(list.isEmpty()){
				list.add(path);
		}else{
			int x=0;
			for (String str: path.split(",") ) {
				x+=Integer.valueOf(str);
			}
			int y=0;
			for (String str:list.get(0).split(",") ) {
				y+=Integer.valueOf(str);
			}
			if(x>y){
				list.clear();
				list.add(path);
			}
		}
	}

	public static int process4(int[]w,int[] v,int rest,String path,List<String> list,int[] dp){
		
		if(dp[rest]!=-1){
			return dp[rest];
		}
		if(rest == 0 ){
			f(path,list);
			return 0;
		}
		int max=0;
		for (int i=1; i<=v.length; i++) {

			int r = rest-w[i-1];
			if(r>=0){

				String p=path+v[i-1]+",";
				System.out.println("path = "+p+"$r="+r);
				
				int p1=v[i-1]+process4(w,v,r,p,list,dp);
				max= Math.max(max,p1);

			}
		}
		dp[rest]= max;
		return max;
	}








	public static void main(String[] args){
		// HashSet<String> arr = getSubSequence("acccddeeffccc");
		// for (String str : arr ) {
		// 	System.out.println("\""+str+"\"");
		// }



		// HashSet<String> arr = getPermutation("1114");
		// for (String str : arr ) {
		// 	System.out.println("\""+str+"\"");
		// }

		// System.out.println("count = "+reboot2(5,2,4,100));
		// System.out.println("count = "+reboot3(5,2,4,100));

		// int[] _arr = getArray();
		// int result=win1(_arr);

		// int[] w=new int[]{1,2,3,4,5};
		// int[] v=new int[]{1,5,8,9,10};
		// System.out.println(bag1(w,v,4));

		//				   1 2 3 4  5  6
		int[] w=new  int[]{1,2};
		int[] v1=new int[]{2,5};
		System.out.println("max="+cut(w,v1,4));

	}
}
