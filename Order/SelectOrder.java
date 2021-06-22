import java.util.Arrays;
import java.util.Comparator;

public class SelectOrder{

	public void bolubo(int[] arr){
		for (int i=0;i<arr.length; i++ ) {
			for (int j=0; j<= arr.length-1  ; j++ ) {
				if(arr[i] < arr[j]){
					swap(arr,i,j);
				}
			}
		}
	}

	public void select(int[] arr){
		for (int i=0; i<arr.length ; i++) {
			int minIndex = i;
			for (int j = i;j<arr.length ;j++ ) {
				minIndex = arr[minIndex]<arr[j] ? minIndex : j;
			}
			swap(arr,i,minIndex);
		}
	}

	public void swap(int[] arr, int i,int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j]=temp;
	}

	public void printArr(int[] arr){
		for (int i=0;i<arr.length ;i++ ) {
			System.out.print(arr[i]+",");
		}
		System.out.println();
	}

	public int[] createArray(int length,int max){
		int[] arr = new int[length];
		for (int i=0; i<length; i++ ) {
			arr[i] = (int)(Math.random()*max);
		}
		return arr;
	}

	public int[] copyArr(int[] arr){
		int[] copy = new int[arr.length];
		for (int i=0; i<arr.length; i++ ) {
			copy[i] = arr[i];
		}
		return copy;
	}

	public Boolean Compara(int[] one, int[] two){
		for (int i=0;i<one.length ;i++ ) {
			if(one[i] != two[i])
				return false;
		}
		return true;
	}


	public static void main(String[] args){
		boolean isPass = true;
		SelectOrder select = new SelectOrder();
		for (int i=0; i<999999;i++ ) {

			int[] arr= select.createArray(30,10000);
			int[] copy = select.copyArr(arr);
			int[] copy_orign = select.copyArr(arr);

			Arrays.sort(arr);
			//select.bolubo(arr);
			select.select(copy);

			if(!select.Compara(arr,copy)){
				select.printArr(copy_orign);
				isPass=false;
				break;
			}

		}

		if(isPass){
			System.out.println("successfuly");
		}
	}
}
