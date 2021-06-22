import java.util.*;

public class StackByArray{
	
	Stack<Integer> stack = new Stack<Integer>();
	int[] arr = new int[10000];
	int size=0;
	
	public void push_stack(int v){
		stack.push(v);
	}

	public void push(int v){
		if(size<arr.length){
			//System.out.println("push "+v);
			arr[size++] = v;
		}else{
			//System.out.println("push "+v);
			System.out.println("栈已满");
		}
	}

	public int pop_stack(){
		if(!stack.isEmpty()){
			return stack.pop();
		}
		return -1;
	}

	public int pop(){
		
		if(size>0){
			int v = arr[--size];
			//System.out.println("pop "+v);
			return v;
		}else{
			//System.out.println("栈为空");
			return -1;
		}
	}

	// public int getPushPIndex(int index){
	// 	return (index+1) >= arr.length ? 0 : index+1;
	// }


	


	public void RandomOption(int pushCount, int popCount,BecomeStackByArray stack){

		while(true){
			int r = (int)(Math.random()*9999);
			if(r%2 == 0){
				if(pushCount >0 ){
					stack.push(r);
					stack.push_stack(r);
					pushCount--; 
				}
			}else{
				if(popCount >0){
					stack.pop();
					stack.pop();
					popCount--; 
				}
			}

			if(pushCount == 0 && popCount ==0) {
				return;
			}
		}

	}



	public static void main(String args[]){
		int count = 1000000;
		while(count-- > 0){
			BecomeStackByArray stack = new BecomeStackByArray();
			stack.RandomOption(10000,5000,stack);
			int pop_array = stack.pop();
			int pop_stack = stack.pop_stack();

			if(pop_array != pop_stack){

				break;
			}

		}
		if(count > 0){
			System.out.println("出错了...");
		}else{
			System.out.println("成功了...");
		}
	}
}
