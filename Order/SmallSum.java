//基于归并排序
public class SmallSum{

	public int process(int[] arr,int left,int right){
		if(left == right){
			return 0;
		}

		int mid = left + (right - left)/2;

		return 
			process(arr,left,mid)
			+
			process(arr,mid+1,right)
			+
			merge(arr,left,mid,right);
	}

	public int merge(int[] arr,int left,int mid,int right){

		int[] help = new int[right - left +1];
		int helpi = 0;

		int p1 = left;
		int p2 = mid+1;

		int result = 0;

		while(p1 <= mid && p2 <= right){
			//
			// 大和问题
			// 数组中，每个数右边比他大的数之和 的总和
			// result += arr[p1]>=arr[p2]?0: (mid - p1+1)*arr[p2];
			// help[helpi++] = arr[p1]>=arr[p2]?arr[p1++]:arr[p2++];

			//
			//小和问题
			//数组中，每个数左边比他小的数之和 的总和
			//
			result += arr[p1]<arr[p2]? (right - p2+1)*arr[p1]:0;
			help[helpi++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
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
		return result;
	}

	public static void main(String[] args){
		SmallSum sum = new SmallSum();
		int[] arr={2,7,5,9,1,0,7,4};
		
		int ans = 0;
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<i; j++ ) {
				if(arr[j] < arr[i]){
					ans+=arr[j];
				}
			}
		}
		System.out.println("ans = "+ans);

		int result = sum.process(arr,0,arr.length -1);
		System.out.println("result = "+result);

		for (int i=0; i<arr.length; i++) {
			System.out.print("----"+arr[i]+",");
		}
	}


}
