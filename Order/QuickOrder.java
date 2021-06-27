public class QuickOrder{

	public void sort(int[] arr,int left,int right){
		if(left >= right) return;
		
		int[] scop = process(arr,left,right);
		sort(arr,left,scop[0]-1);
		sort(arr,scop[1]+1,right);
	}

	public int[] process(int[] arr,int left,int right){
		
		int less = left -1;
		int R = right-1;
		int more = right -1;
		int index = left;

		swap(arr, (int)(Math.random()* right),R);

		while(index<more){
			if(arr[index] == arr[R]){
				index++;
			}else if(arr[index] < arr[R]){
				swap(arr,index++,++less);
			}else if(arr[index] > arr[R]){
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

	public static void main(String[] args){
		QuickOrder t=new QuickOrder();
		int[] arr={2,4,46,8};
		//int[] result = t.process(arr,0,arr.length);
		t.sort(arr,0,arr.length);

		System.out.println();

		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+",");
		}

		System.out.println();

		//System.out.println("["+result[0]+","+result[1]+"],ans = "+arr[result[0]]);
	}
}
