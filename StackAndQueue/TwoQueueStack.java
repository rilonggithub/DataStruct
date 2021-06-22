import java.util.*;
public class TwoQueueStack{
	
	Queue<Integer> queue = new LinkedList<Integer>();
	Queue<Integer> help_queue = new LinkedList<Integer>();

	Stack<Integer> stack = new Stack<Integer>();

	public void push(int v){
		queue.offer(v);
	}

	public void stackPush(int v){

		stack.push(v);
	}
	public void stackPop(){
		if(!stack.isEmpty()){
			stack.pop();
		}
	}


	public int pop(){

		if(queue.isEmpty()) return -1;

		while(queue.size() > 1){
			help_queue.offer(queue.poll());
		}

		int v = queue.poll();
		Queue<Integer> temp = queue;
		queue = help_queue;
		help_queue = temp;
		return v;
	}

	public int size(){
		return queue.size() + help_queue.size();
	}

	public Stack<Integer> getStack(){
		return stack;
	}

	public Boolean RandomOptions(){
		int max = 100000;
		for (int j=0;j<max ;j++ ) {
			TwoQueueStack stack = new TwoQueueStack();
			for (int i=0;i<300 ;i++ ) {
				int r = (int)(Math.random()*99999);
				if(r%4==0){
					//System.out.println("push--- j = "+j +",r="+r);
					stack.push(r);
					stack.stackPush(r);
				}else{
					//System.out.println("pop--- j = "+j);
					stack.pop();
					stack.stackPop();
				}
			}
			Stack<Integer> _stack=stack.getStack();

			if(stack.size() != _stack.size()){
				System.out.println("_stack.size = "+_stack.size() +"stack.size="+stack.size());
				return false;
			}

			while(!_stack.isEmpty()){
				int v = _stack.pop();
				int v1 = stack.pop();
				//System.out.println("v = "+v +"v1"+v1);
				if(v!=v1) return false;
			}
		}
			
		return true;
	}


	public static void main(String[] args){
		TwoQueueStack stack = new TwoQueueStack();
		System.out.print("result = "+stack.RandomOptions());
		
		
	}
}
