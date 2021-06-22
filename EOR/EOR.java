public class EOR{


	//亦或运算交换两个数字
	public static void swap(int[] arr,int i,int j){
		arr[i] = arr[i]^arr[j];
		arr[j] = arr[i]^arr[j];
		arr[i] = arr[i]^arr[j];
	}

	//一个数组只有一个数字出现了奇数次，其他数字都出现偶数次
	//找到出现奇数次的数字
	public static void oneOdd(){
		int[] arr = {1,2,5,5,5,2,1,6,6,3,7,7,3,8};
		int eor=0;

		//一个数组只有一个数字出现了奇数次，其他数字都出现偶数次
		//使用全数组的元素进行异或运算，即可得到出现奇数次的数字
		//
		//原理：偶数次的数字异或之后全是0，奇数次的数字可以理解为（N*2+1，N>=0）,异或之后等价于（N^0）=N
		//
		//
		// 异或运算性质：
		// 1、0^N = N
		// 2、N^N = 0
		// 3、异或运算支持交换律，即 A^B^(C^D) = A^B^C^D

		for (int i=0;i<arr.length;i++ ) {
			eor=eor^arr[i];
		}

		System.out.println("one = "+eor);

	}


	//一个数组只有2个数字出现了奇数次，其他数字都出现偶数次
	//找到出现奇数次的2个数字
	public static void twoOdd(){
		int[] arr = {1,2,5,5,5,2,1,6,6,3,7,7,3,8};
		int eor=0;

		// 异或运算性质：
		// 1、0^N = N
		// 2、N^N = 0
		// 3、异或运算支持交换律，即 A^B^(C^D) = A^B^C^D

		//因为有两个数字A、B出现了奇数次，那么eor的值就是这两个出现奇数次数字的异或结果，即eor = A^B
		for (int i=0;i<arr.length;i++ ) {
			eor=eor^arr[i];
		}

		// eor&(-eor) 和 eor&(～eor+1) 的作用是一样的
		// 都是得到 eor 二进制的 最右边的 1
		// 因此，_rightOne中存放的eor最右边的1，可以表示 A、B当中肯定只有一个数字最右边的该位置上也是1
		// 再者，数组中这个位置出现1的其他数字，都是出现偶数次，异或运算结果为0
		// 所以，就会找出A、B中的其中一个
		// 再通过eor^_eor，就可以找出另一个

		int _rightOne = eor&(-eor);
		int _eor = 0;

		for (int i=0; i<arr.length ;i++ ) {
			if((arr[i] & _rightOne) >0){
				_eor^=arr[i];
			}
		}
		System.out.println("one = "+_eor + ",two = "+ (eor^_eor));
	}


	//一个数组只有一种数字出现了k次，其他数字都出现m次，且k<m
	//找到出现k次的数字
	public static void eorKM(){
		int k = 2;
		int m = 4;
		int[] arr = {1,1,1,1,8,8,8,8,-10,-10,-10,-10,199,199};
		int[] binargArr=new int[32];
		

		for (int num : arr) {
			//因为是整形数组，整形是32位，
			//所以遍历整个数组，将每一个数进行32次右移，每次右移一位，和1进行与运算
			//即可得出该数字每位上的1
			//然后进行统计，得出每位上出现1的次数
			for (int i=0; i<32; i++) {
				binargArr[i] += ((num >> i)&1);
			}
		}


		int result=0;
		//遍历位记录数组
		//如果该位上出现的1的次数 %m 的结果不为0，则说明该位上肯定出现了k次的1
		//因此需要将该位上的1进行累计，即加上 (1 << i)
		for (int i=0; i<32; i++) {
			if(binargArr[i]%m !=0){
				result += (1 << i);
			}
		}

		System.out.println(result);
	}


	
	public static void main(String[] args){
		eorKM();
	}
}
