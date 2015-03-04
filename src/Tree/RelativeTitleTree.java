package Tree;

import java.util.ArrayList;

public class RelativeTitleTree {
	public ArrayList<node> Nodes = new ArrayList<node>();
	public ArrayList<edge> Edges = new ArrayList<edge>();
	
	/**
	 * @param gender: your own gender, 0 male, 1 female
	 * */
	RelativeTitleTree(int gender){
		//node: 9 self
		node n = new node(9,gender,"自己");//initial node
		this.Nodes.add(n);
		if(gender == 0)
			n = new node(0,1-gender,"老婆/妻子/夫人/太太/媳妇");
		else
			n = new node(0,1-gender,"老公/丈夫/先生");
		this.Nodes.add(n);
		edge e = new edge(0,9,0,0,gender);//id, source, end, relation to u
		this.Edges.add(e);
		
		n = new node(1,0,"父亲/爸爸/爹");
		this.Nodes.add(n);
		e = new edge(1,9,1,1,gender);
		this.Edges.add(e);
		
		n = new node(2,1,"母亲/妈妈/娘");
		this.Nodes.add(n);
		e = new edge(2,9,2,2,gender);
		this.Edges.add(e);
		
		n = new node(3,0,"哥哥/兄长");
		this.Nodes.add(n);
		e = new edge(3,9,3,3,gender);
		this.Edges.add(e);
		
		n = new node(4,1,"姐姐");
		this.Nodes.add(n);
		e = new edge(4,9,4,4,gender);
		this.Edges.add(e);
		
		n = new node(5,0,"弟弟");
		this.Nodes.add(n);
		e = new edge(5,9,5,5,gender);
		this.Edges.add(e);
		
		n = new node(6,1,"妹妹");
		this.Nodes.add(n);
		e = new edge(6,9,6,6,gender);
		this.Edges.add(e);
		
		n = new node(7,0,"儿子");
		this.Nodes.add(n);
		e = new edge(7,9,7,7,gender);
		this.Edges.add(e);
		
		n = new node(8,1,"女儿");
		this.Nodes.add(n);
		e = new edge(8,9,8,8,gender);
		this.Edges.add(e);
		
		//node 0
		node nc = this.Nodes.get(0);
		n = new node(this.Nodes.size(),0,"");//initial node
		this.Nodes.add(n);
		if(gender == 0)
			n = new node(0,1-gender,"老婆/妻子/夫人/太太/媳妇");
		else
			n = new node(0,1-gender,"老公/丈夫/先生");
		this.Nodes.add(n);
		e = new edge(this.Edges.size(),nc.id,n.id,1,nc.gender);
		this.Edges.add(e);
		
	}
	/**
	 * Decide the person you are looking for is male or female.
	 * 
	 * @param g
	 *            = your gender, 0 for male, 1 for female.
	 * @param q
	 *            is an encoded string.
	 * @return 0 for male, 1 for female, -1 for exception.
	 * */
	private int HeOrShe(int g, String q) {
		if (q.length() < 1)
			return g;// self

		char lastChar = q.charAt(q.length() - 1);
		if (lastChar > 0) {
			if (lastChar % 2 == 0) {// Female
				return 1; // she
			} else {
				return 0; // he
			}
		}
		// lastChar == 0
		int s = 1;
		for (int i = q.length() - 1; i >= 0; i--) {
			if (q.charAt(i) > 0) {
				return (g + s) % 2;
			} else {
				s++;
			}
		}
		return -1;
	}
	
	/**
	 * Decide for a node, what I am to him/her
	 * */
	public String decideRelationUsingEdges(int nid){
		return null;
	}
	
	/**
	 * e.g. Alternative input: the input string on the edges.
	 * */
	public String reverseRelationOnEdges(int source, int end){
		return null;
	}
}
