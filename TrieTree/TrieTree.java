//
//前缀树 TrieTree
//
import java.util.*;

public class TrieTree{


	private class Node{
		//经过该字符的次数
		private int pass;
		//以该字符结束的次数
		private int end;
		//下一个字符
		private HashMap<Integer,Node> route;

		public  Node(int _pass,int _end){
			pass = _pass;
			end = _end;
			route = new HashMap<Integer,Node>();
		}
	}

	private Node head = null;


	public  TrieTree(){
		head = new Node(0,0);
	}

	public void add(String str){
		if(str.isEmpty()) return;
		char[] charArr = str.toCharArray();
		Node p = head;
		for (int i=0; i<charArr.length; i++) {
			if(p.route.get((int)charArr[i]) == null){
				p.route.put((int)charArr[i], new Node(0,0));
			}
			p = p.route.get((int)charArr[i]);
			p.pass += 1;
		}
		p.end += 1;
	}

	public void del(String str){
		//判断即将删除的字符串是否存在树中
		if(searchStr(str)>0){
			char[] charArr = str.toCharArray();
			Node p = head;
			Node n = null;
			
			for (int i=0; i<charArr.length; i++) {
				n = p.route.get((int)charArr[i]);
				if(n!=null && --n.pass == 0){
					p.route.put((int)charArr[i],null);
				}
				p = n;
			}
			p.end-=1;
		}
	}
	

	public int searchStr(String str){
		if(str.isEmpty()) return 0;
		char[] charArr = str.toCharArray();
		Node p = head;
		for (int i=0; i<charArr.length; i++) {
			if(p.route.get((int)charArr[i])==null) return 0;
			p = p.route.get((int)charArr[i]);
		}
		return p.end;
	}

	public int searchPre(String pre){
		if(pre.isEmpty()) return 0;
		char[] charArr = pre.toCharArray();
		Node p = head;

		for (int i=0; i<charArr.length; i++) {
			if(p.route.get((int)charArr[i])==null) return 0;
			p = p.route.get((int)charArr[i]);
		}
		return p.pass;
	}

	public static String getArray(int max){
		char[] arr=new char[max];
		for (int i=0; i<max; i++) {
			arr[i] = (char)(Math.random()*127);
		}
		return String.valueOf(arr);
	}


	public static Boolean compareEquals(HashMap<String,Integer> map,TrieTree trie,String str){
		HashMap<String,Integer> result = new HashMap<String,Integer>();
		int count = 0;
		for (String _str : map.keySet()) {
			if(_str == str){
				result.put(str,++count);
			}
		}
		//System.out.println("str = "+str+",mapCount = "+result.get(str)+",treeCount="+trie.searchStr(str));
		return trie.searchStr(str) == result.get(str);
	}


	public static Boolean comparePref(HashMap<String,Integer> map,TrieTree trie,String str){
		HashMap<String,Integer> result = new HashMap<String,Integer>();
		int count = 0;
		for (String _str : map.keySet()) {
			if(_str.startsWith(str)){
				result.put(str,++count);
			}
		}
		return trie.searchPre(str) == result.get(str);
	}


	public static Boolean isTrue(){
		int loopCount = 99999;
		for (int i=0; i<loopCount; i++) {
			String str = getArray((int)(Math.random()*100));
			if(str.isEmpty()) continue;

			TrieTree trie=new TrieTree();
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			int random = (int)Math.random()*9999;
			if(random%2==0){
				trie.add(str);
				map.put(str,1);
			}
			else{
				trie.del(str);
				map.remove(str);
			}
			if(!compareEquals(map,trie,str)){
				return false;
			}

			if(!comparePref(map,trie,str)){
				return false;
			}

		}
		return true;

	}

	public static void main(String[] args){
		System.out.println("result = "+isTrue());
	}
}
