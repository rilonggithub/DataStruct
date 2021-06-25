//求数组中的所有逆序对
//基于归并排序
public class CountInversions{

	public int process(int[] arr,int left,int right){

		if(left == right) return 0;

		int mid = left + (right - left)/2;

		return 
		process(arr,left,mid)+
		process(arr,mid+1,right)+
		merge(arr,left,mid,right);

	}

	public int merge(int[] arr,int left,int mid,int right){

		int[] help = new int[right - left +1];
		int helpi = 0;

		int p1 = left;
		int p2 = mid+1;

		int count = 0;

		while(p1 <= mid && p2 <=right){
			if(arr[p1] > arr[p2] ){
				// int start = p2;
				// while(start++ <= right){
				// 	//System.out.print("["+arr[p1]+","+arr[start++]+"]");
				// 	count++;
				// }
				count += right - p2 +1;
			}
			help[helpi++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
		}

		while(p1<=mid){
			help[helpi++] = arr[p1++];
		}

		while(p2 <= right){
			help[helpi++] = arr[p2++];
		}

		for (int i=0; i<help.length;i++ ) {
			arr[left+i] = help[i];
		}

		return count;
	}

	public int test(int[] arr){
		int count =0;
		for (int i=0; i<arr.length;i++ ) {
			for (int j=i+1; j<arr.length;j++ ) {
				if(arr[i] > arr[j]){
					//System.out.print("["+arr[i]+","+arr[j]+"]");
					count++;
				}
			}
		}
		return count;
	}

	public Boolean isTrue(){
		int count = 9999;
		int max = 50;
		for (int i=0; i<count; i++) {
			CountInversions ci=new CountInversions();
			int[] arr =new int[max];
			for (int j=0;j<max;j++) {
				arr[j] = (int)(Math.random()*9999);
			}
			 int c = ci.test(arr);
			 int c1 =ci.process(arr,0,arr.length-1);
			 if(c!=c1) return false;
		}
		return true;
	}

	public static void main(String[] args){
		CountInversions ci=new CountInversions();
		int[] arr={1,9,2,8,5,70,6,39,2,9,5};
		System.out.println("result = "+ci.isTrue());

		// ci.test(arr);
		// System.out.println();

		// ci.process(arr,0,arr.length-1);
		// System.out.println();

		// for (int i=0; i<arr.length; i++) {
		// 	System.out.print(arr[i]+",");
		// }
	}

}
