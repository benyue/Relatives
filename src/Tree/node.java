package Tree;

import java.util.ArrayList;

public class node {
	public node(int id, int g, String r) {
		this.id = id;
		this.gender = g;
		this.relation2u = r;//what is he/she to me
	}
	
	int id = -1;
	int gender = 0;//0 male, 1 female
	String relation2u = "";
	
	int sameBranchNode = -1;//e.g. 130 has same branch as 13
}
