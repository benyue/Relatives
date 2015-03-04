package Tree;

public class edge {
	edge(int id, int s, int e, int r, int gender) {
		this.id = id;
		this.sourceID = s;
		this.endID = e;
		this.relation = r;
		this.reverseRelation = this.reverseRelation(gender, r);
	}

	int id = -1;
	int sourceID = -1;
	int endID = -1;
	int relation = -1;// source->end, end is source's relation
	// 0 = spouse,
	// 1 = dad, 2 = mom,
	// 3 = elder brother, 4 = elder sister,
	// 5 = younger brother, 6 = younger brother,
	// 7 = son, 8 = daughter

	int reverseRelation = -1;// end->source

	protected int reverseRelation(int gender, int r) {
		switch (r) {
		case 0:
			return 0;
		case 1:
		case 2:
			if (gender == 0)
				return 7;
			else
				return 8;
		case 3:
		case 4:
			if (gender == 0)
				return 5;
			else
				return 6;
		case 5:
		case 6:
			if (gender == 0)
				return 3;
			else
				return 4;
		case 7:
		case 8:
			if (gender == 0)
				return 1;
			else
				return 2;
		default:
			return -1;
		}
	}
}
