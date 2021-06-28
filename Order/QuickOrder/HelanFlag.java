//荷兰国旗问题
//在一个数组arr中，给定一个X，要求：
//1、小于X的放数组左边
//2、等于X的放中间
//3、大于X的放右边
//
//是快速排序的基础，和思路
//
public class HelanFlag{

	public int[] process(int[] arr,int L,int R){

		if(L > R) return new int[]{-1,-1};
		if(L == R) return new int[]{L,R};

		int less = L-1;
		int more = R;
		int index = L;

		int random = L + (int)(Math.random()*(R-L+1));
		//System.out.println("random = "+arr[random]);
		swap(arr,random,R);


		while(index<more){

			if(arr[index] == arr[R]){
				index++;
			}
			else if(arr[index]<arr[R]){
				swap(arr,index++,++less);
			}
			else{
				swap(arr,index,--more);
			}

		}
		swap(arr,more,R);

		return new int[]{less+1,more};

	}

	public void swap(int[] arr,int l,int r){
		int t = arr[l];
		arr[l] = arr[r];
		arr[r] = t;
	}

	public static int[] getArr(int length,int max){
		int[] arr= new int[length];
		for (int i=0; i<length-1; i++) {
			arr[i] = (int)(Math.random()*max);
		}
		return arr;
	}

	public static Boolean compar(int[] arr,int[] scop){
		for (int i=0; i<scop[0];i++ ) {
			if(arr[i] >= arr[scop[0]]){
				return false;
			}
		}

		for (int i=scop[1]+1; i<arr.length;i++ ) {
			if(arr[i] <= arr[scop[1]]){
				return false;
			}
		}
		return true;
	}
	
	public static Boolean isTrue(){
		int max = 999999;
		for (int i=0; i<max;i++ ) {

			HelanFlag t=new HelanFlag();
			int[] arr=getArr(50,9999);
			int[] scop = t.process(arr,0,arr.length-1);

			if(!compar(arr,scop)) return false;
			
		}
		return true;

	}

	public static void main(String[] args){
		
		
		System.out.println("isTrue = "+isTrue());
	}
}
