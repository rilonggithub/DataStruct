//给定一个数组arr，找出数组中任何一个数arr[i]的右边，存在多少个数j（arr[j]*2 < arr[i] ）
//基于归并排序修改
//
public class BiggerThanRightTwice{

	public int process(int[] arr,int left,int right){

		if(left == right) return 0;

		int mid = left + (right - left)/2;

		return 
		process(arr,left,mid)
		+
		process(arr,mid+1,right)
		+
		merge(arr,left,mid,right);
	}


	public int merge(int[] arr,int left,int mid,int right){


		int ans = 0;
		int windowR = mid+1;

		//在左组为[L.....M] 右组为[M+1......R]的情况下
		//两组兼有序
		//windowR一开始指向右组的第一个元素
		//当左组的arr[i] 大于 右组的arr[windowR]*2时，并且windowR没有越界
		//windowR指针右移（且windowR不后退，表示左组i位置的数囊括了windowR个数时，
		//那么左组(i+1)位置的数最少也可以囊括windowR个数）
		//
		//等于说，如果左组的i对应的windowR为2，那么 左组中（i+1）对应的windowR最起码大于等于2

		for (int i=left; i<=mid;i++ ) {
			while(windowR <=right && arr[i]>(arr[windowR]*2)){
				windowR++;
			}
			ans += windowR-mid-1;
		}


		int[] help = new int[right-left +1];
		int hi = 0;

		int p1 = left;
		int p2 = mid+1;

		while(p1<=mid && p2 <=right){

			help[hi++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
		}

		while(p1<=mid){
			help[hi++] = arr[p1++];
		}

		while(p2<=right){
			help[hi++] = arr[p2++];
		}

		for (int i=0; i<help.length;i++ ) {
			arr[left+i] = help[i];
		}
		return ans;
	}

	public static void main(String[] args){

		BiggerThanRightTwice  bt = new BiggerThanRightTwice();
		int[] arr = {9,3,2,7,2,1,11,4,7,5,3};

		int ans = bt.process(arr,0,arr.length - 1);

		// bt.process2(arr);
		for (int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+",");
		}
		System.out.print("Ans = "+ans);
	}

}
