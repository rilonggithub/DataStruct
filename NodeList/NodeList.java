import java.util.HashMap;

public class NodeList{

	public class Node{
		public int data;
		public Node next;
	}


	private Node tail=null;

	public Node CreateLinkListHead(){
		Node head =  new Node();
		head.data=0;
		head.next = null;

		tail = head;
		return head;
	}

	public Node getNode(int data){
		Node node =  new Node();
		node.data=data;
		node.next = null;

		return node;
	}

	public void insertNode(Node head,Node node,int type){
		

		//头插法
		if(type == 0){
			Node p = head.next;
			head.next = node;
			node.next = p;
		//尾插法	
		}else{
			Node p = head;
			Node q = head;
			while(p != null){
				q=p;
				p = p.next;
			}
			q.next = node;
		}

	}

	public void printList(Node head){
		Node p = head;
		while(p != null){
			System.out.print(p.data+",");
			p = p.next;
		}
	}

	public Node getLoopLinkList(){
		NodeList list = new NodeList();
		Node head = list.CreateLinkListHead();
		list.insertNode(head,list.getNode(2),1);
		Node loop = list.getNode(23);
		list.insertNode(head,loop,1);
		list.insertNode(head,list.getNode(25),1);
		list.insertNode(head,loop,1);
		return head;
	}

	public int getRandom(){
		return (int)(Math.random()*9999);
	}


	public Node getNoLoopRandomLinkList(int length){
		NodeList list = new NodeList();
		Node head = list.CreateLinkListHead();
		for (int i=0;i<length ;i++ ) {
			list.insertNode(head,list.getNode(list.getRandom()),1);
		}
		return head;
	}

	public Node getNoLoopLinkList(){
		NodeList list = new NodeList();
		Node head = list.CreateLinkListHead();
		list.insertNode(head,list.getNode(2),1);
		list.insertNode(head,list.getNode(6),1);
		list.insertNode(head,list.getNode(18),1);
		list.insertNode(head,list.getNode(33),1);
		list.insertNode(head,list.getNode(59),1);
		list.insertNode(head,list.getNode(200),1);
		return head;
	}

	public Node getNoLoopLinkList2(){
		NodeList list = new NodeList();
		Node head = list.CreateLinkListHead();
		list.insertNode(head,list.getNode(1),1);
		list.insertNode(head,list.getNode(16),1);
		list.insertNode(head,list.getNode(38),1);
		list.insertNode(head,list.getNode(63),1);
		list.insertNode(head,list.getNode(99),1);
		list.insertNode(head,list.getNode(190),1);
		return head;
	}


	public Node getCrossingTwoLinkList(Node head1){
		NodeList list = new NodeList();
		Node head2 = list.CreateLinkListHead();
		list.insertNode(head2,list.getNode(2),1);
		list.insertNode(head2,list.getNode(21),1);
		list.insertNode(head2,list.getNode(22),1);
		Node loop = list.getNode(23);
		list.insertNode(head2,loop,1);
		list.insertNode(head2,list.getNode(24),1);
		list.insertNode(head2,list.getNode(25),1);


		list.insertNode(head1,list.getNode(3),1);
		list.insertNode(head1,list.getNode(31),1);
		list.insertNode(head1,list.getNode(32),1);
		list.insertNode(head1,loop,1);
		return head2;
	}



	//获取链表中成环的第一个节点
	public Node getCycleNode(Node head){
		if(head == null || head.next == null || head.next.next == null)
			return null;

		Node n1 = head.next;
		Node n2 = head.next.next;

		while(n1!= n2){
			if(n1 == null || n2 == null)
				return null;
			n1 = n1.next;
			n2 = n2.next.next;
		}

		n2 = head;

		while(n1!=n2){
			n1 = n1.next;
			n2 = n2.next;
		}

		return n1;
	}

	//判断链表是否有环
	//这个方法只能判断是否有环，不能找到入环的第一个节点
	public Boolean hasCycle(Node head){

		if(head == null || head.next == null)
			return false;

		Node slow = head;
		Node fast = head.next;

		while(slow != fast){
			if(slow == null || fast == null)
				return false;

			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}

	//反转链表
	public Node reverse(Node head){
		Node prev = null;
		Node curr = head;

		while(curr!=null){
			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	//两链表是否相交的HashMap解法
	public Boolean isCrossingByMashMap(Node head1,Node head2){
		HashMap<Node,Integer> map = new HashMap<Node,Integer>();

		Node curr=head1;
		while(curr!=null){
			map.put(curr,1);
			curr = curr.next;
		}

		curr = head2;
		while(curr!=null){
			if(map.containsKey(curr)){
				return true;
			}
			curr = curr.next;
		}

		return false;
	}


	//两链表是否相交的双指针解法
	public Node isCrossing(Node head1,Node head2){
		
		Node curr1=head1;
		Node curr2=head2;

		//这里包含了两种场景：
		//1、两个链表都到了最后节点，表示没有相交，都为null，直接返回
		//2、两个链表指向同一个节点，表示相交，返回任意一个即可
		while(curr1 != curr2){

			if(curr1 == null){
				curr1=head2;
			}else{
				curr1 = curr1.next;
			}

			if(curr2 == null){
				curr2=head1;
			}else{
				curr2 = curr2.next;
			}

			//因此，这里不需要写
			// if(curr1 == curr2 && curr1 != null && curr2 != null)
			// 来判断是否已经找到相交节点
		}

		return curr1;
	}



	//合并两个有序链表，返回新有序链表的头节点
	public Node mergeTwoLists(Node head1,Node head2){

		NodeList list = new NodeList();
		Node head = list.CreateLinkListHead();

		Node curr1 = head1;
		Node curr2 = head2;
		Node curr = head;

		while(curr1 != null && curr2 != null){
			Node n = null;
			if(curr1.data > curr2.data){
				n = list.getNode(curr2.data);
				curr2 = curr2.next;
			}else{
				n = list.getNode(curr1.data);
				curr1 = curr1.next;
			}

			list.insertNode(curr,n,1);
		}

		if(curr1 == null){
			while(curr2!=null){
				Node n = list.getNode(curr2.data);
				list.insertNode(curr,n,1);
				curr2 = curr2.next;
			}
		}
		if(curr2 == null){
			while(curr1!=null){
				Node n = list.getNode(curr1.data);
				list.insertNode(curr,n,1);
				curr1 = curr1.next;
			}
		}

		return head;
	}


	//查找链表倒数第K个元素
	public Node findKth(Node head, int k){
		int th = k;
		int length = 0;
		Node h = head;
		while(h!=null){
			length++;
			th --;
			h = h.next;
		}

		if(th>0) return null;

		h = head;
		Node p = null;
		int index = length - k;
		while(index>=0){
			p = h;
			h = h.next;
			index--;
		}
		return p;

	}

	//查找链表倒数第K个元素
	//优化版本
	public Node findKth2(Node head,int k){

		Node p = head;
		Node q = head;

		while(--k !=0 && p!=null){
			p = p.next;
		}

		if(p == null) return null;

		while(p.next!=null){
			p = p.next;
			q = q.next;
		}
		return q;
	}


	//删除某个链表中给定的（非末尾）节点
	public void delete(Node node){
		Node next = node.next;
		node.data = next.data;
		node.next = next.next;
	}


	//给定一个单链表，把所有的奇数节点和偶数节点分别排在一起
	public void oddEvenList(Node head){
		Node odd = head;
		Node odd_end = null;
		Node even = head.next;

		while(odd != null && even != null){
			Node t_odd = even.next;
			Node t_even = t_odd.next;

			odd.next = t_odd;
			even.next = t_even;

			odd = t_odd;
			even = t_even;

			odd_end = odd;
		}

		odd_end.next = head.next;
	}


	public static void _reverse(NodeList list){
		Node head = list.getNoLoopLinkList();
		head=list.reverse(head);
		list.printList(head);
		System.out.println("----------- _reverse end");
	}

	public static void _cross(NodeList list){
		Node head1 = list.CreateLinkListHead();
		Node head2 = list.getCrossingTwoLinkList(head1);
		Node cross = list.isCrossing(head1,head2);
		System.out.println("----------- _cross end");
	 	System.out.print(cross!=null ? cross.data:null);
	}

	public static void _cycle(NodeList list){

		Node head = list.getLoopLinkList();
		Node node = list.getCycleNode(head);
		System.out.println(node == null ?"No cycle node":node.data);
		System.out.println("----------- _cycle end");
	}

	public static void _merge(NodeList list){
		Node h1 = list.getNoLoopLinkList();
		list.printList(h1);
		System.out.println("----------- h1 end");

		Node h2 = list.getNoLoopLinkList2();
		list.printList(h2);
		System.out.println("----------- h2 end");

		Node h = list.mergeTwoLists(h1,h2);
		list.printList(h);
		System.out.println("----------- _merge end");
	}

	public static void _findKth(NodeList list){
		Node h = list.getNoLoopLinkList();
		list.printList(h);
		System.out.println("----------- h end");
		list.oddEvenList(h);
		list.printList(h);
	}

	public static void main(String[] args){
		NodeList list = new NodeList();
		_findKth(list);
		
	}
}

