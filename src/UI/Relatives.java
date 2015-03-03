/**
 * This is a small program written to help Chinese decide the title to call their relatives.
 * Just for fun. Performance doesn't matter since the program is not large scale, a correct result is most important. 
 * 
 * Cindy Yue Ben, 03/02/2015
 * 
 * */
package UI;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

public class Relatives {

	private JFrame mainFrame;
	private final ButtonGroup btnGroupGender = new ButtonGroup();

	int gender = 0;// 0 = male, 1 = female
	private JTextArea txtT;
	String Q = "";// encode T into an integer string
	private JTextArea txtA; // to display answer
	private int maxL = 3; // the max length of Q with predefined titles

	HashMap<String, String> map = new HashMap<String, String>();// call him/her
	HashMap<String, String> mapMale = new HashMap<String, String>();
	HashMap<String, String> mapFemale = new HashMap<String, String>();
	HashMap<String, String> mapR = new HashMap<String, String>();// call self
	HashMap<String, String> mapMaleR = new HashMap<String, String>();
	HashMap<String, String> mapFemaleR = new HashMap<String, String>();
	

	public Relatives() {
		prepareGUI();
	}

	public static void main(String[] args) {
		Relatives UI = new Relatives();
		UI.iniMaps();
		UI.reset();
		UI.txtT.removeAll();
	}

	private void prepareGUI() {
		Dimension D = new Dimension(400, 500);
		mainFrame = new JFrame("Relatives");
		mainFrame
				.setTitle("\u4EB2\u5C5E\u5173\u7CFB\uFF08\u4E1C\u5317\u7248\uFF09");
		mainFrame.setMinimumSize(D);
		mainFrame.setMaximumSize(D);
		mainFrame.setPreferredSize(D);
		mainFrame.setSize(D);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.getContentPane().setMaximumSize(D);
		mainFrame.getContentPane().setMinimumSize(D);
		mainFrame.getContentPane().setLayout(null);

		JLabel label = new JLabel(
				"\u8BF7\u9009\u62E9\u60A8\u7684\u6027\u522B\uFF1A");
		label.setBounds(10, 11, 106, 14);
		mainFrame.getContentPane().add(label);

		JLabel label_1 = new JLabel("\u6211\u7684");
		label_1.setBounds(10, 36, 32, 14);
		mainFrame.getContentPane().add(label_1);

		JRadioButton rdbtnMale = new JRadioButton("\u7537 Male");
		rdbtnMale.setMnemonic(KeyEvent.VK_M);
		rdbtnMale.setActionCommand("M");
		rdbtnMale.setSelected(true);
		rdbtnMale.addActionListener(new GenderSelection());
		btnGroupGender.add(rdbtnMale);
		rdbtnMale.setBounds(122, 7, 85, 23);
		mainFrame.getContentPane().add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("\u5973 Female");
		rdbtnFemale.setMnemonic(KeyEvent.VK_F);
		rdbtnFemale.setActionCommand("F");
		rdbtnFemale.addActionListener(new GenderSelection());
		btnGroupGender.add(rdbtnFemale);
		rdbtnFemale.setBounds(209, 7, 91, 23);
		btnGroupGender.setSelected(rdbtnMale.getModel(), true);
		mainFrame.getContentPane().add(rdbtnFemale);	

		JButton btnSpouse = new JButton("\u914D\u5076\uFF08\u7684\uFF09");// 0
		btnSpouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("配偶(的) ");
				Q += "0";
			}
		});
		btnSpouse.setBounds(40, 102, 99, 23);
		mainFrame.getContentPane().add(btnSpouse);

		JButton btnDad = new JButton("\u7236\u4EB2\uFF08\u7684\uFF09");// 1
		btnDad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("父亲(的) ");
				Q += "1";
			}
		});
		btnDad.setBounds(40, 136, 99, 23);
		mainFrame.getContentPane().add(btnDad);

		JButton btnMom = new JButton("\u6BCD\u4EB2\uFF08\u7684\uFF09");// 2
		btnMom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("母亲(的) ");
				Q += "2";
			}
		});
		btnMom.setBounds(139, 136, 99, 23);
		mainFrame.getContentPane().add(btnMom);

		JButton btnEBro = new JButton("\u54E5\u54E5\uFF08\u7684\uFF09");// 3
		btnEBro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("哥哥(的) ");
				Q += "3";
			}
		});
		btnEBro.setBounds(40, 170, 99, 23);
		mainFrame.getContentPane().add(btnEBro);

		JButton btnES = new JButton("\u59D0\u59D0\uFF08\u7684\uFF09");// 4
		btnES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("姐姐(的) ");
				Q += "4";
			}
		});
		btnES.setBounds(139, 170, 99, 23);
		mainFrame.getContentPane().add(btnES);

		JButton btnYBro = new JButton("\u5F1F\u5F1F\uFF08\u7684\uFF09");// 5
		btnYBro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("弟弟(的) ");
				Q += "5";
			}
		});
		btnYBro.setBounds(40, 204, 99, 23);
		mainFrame.getContentPane().add(btnYBro);

		JButton btnYS = new JButton("\u59B9\u59B9\uFF08\u7684\uFF09");// 6
		btnYS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("妹妹(的) ");
				Q += "6";
			}
		});
		btnYS.setBounds(139, 204, 99, 23);
		mainFrame.getContentPane().add(btnYS);

		JButton btnSon = new JButton("\u513F\u5B50\uFF08\u7684\uFF09");// 7
		btnSon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("儿子(的) ");
				Q += "7";
			}
		});
		btnSon.setBounds(40, 238, 99, 23);
		mainFrame.getContentPane().add(btnSon);

		JButton btnDaughter = new JButton("\u5973\u513F\uFF08\u7684\uFF09");// 8
		btnDaughter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtT.append("女儿(的) ");
				Q += "8";
			}
		});
		btnDaughter.setBounds(139, 238, 99, 23);
		mainFrame.getContentPane().add(btnDaughter);

		JButton btnAsk = new JButton("\u662F\u6211\u7684\uFF1F");
		btnAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Q = clean(Q);
				if (Q == null) {
					txtA.setText("请点击选择亲缘关系");
					return;
				}
				if (HeOrShe(gender, Q) == 1) {// Female
					txtA.setText(findAnswer(gender, Q) + ".\n" + "\n我是她的    "
							+ findPredefinedReverse(gender, Q) + "");
				} else {
					txtA.setText(findAnswer(gender, Q) + ".\n" + "\n我是他的    "
							+ findPredefinedReverse(gender, Q) + "");
				}

			}
		});
		btnAsk.setBounds(40, 290, 325, 23);
		mainFrame.getContentPane().add(btnAsk);

		JButton btnNew = new JButton("\u91CD\u6765");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnNew.setBounds(139, 427, 99, 23);
		mainFrame.getContentPane().add(btnNew);

		txtT = new JTextArea();
		txtT.setWrapStyleWord(true);
		txtT.setRows(2);
		txtT.setLineWrap(true);
		txtT.setEditable(false);
		txtT.setBounds(40, 36, 325, 53);
		mainFrame.getContentPane().add(txtT);
		txtT.setColumns(10);
		JScrollPane scrollPane = new JScrollPane(txtT);
		scrollPane.setBounds(40, 36, 325, 53);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainFrame.getContentPane().add(scrollPane);

		txtA = new JTextArea();
		txtA.setBounds(266, 167, 118, 60);
		mainFrame.getContentPane().add(txtA);
		txtA.setColumns(10);
		JScrollPane scrollPaneA = new JScrollPane(txtA);
		scrollPaneA.setBounds(40, 324, 325, 60);
		mainFrame.getContentPane().add(scrollPaneA);

		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public class GenderSelection implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "F":
				gender = 1;
				reset();
				break;
			default:
				gender = 0;
				reset();
				break;
			}
		}

	}

	/**
	 * Reset for a new search
	 * */
	public void reset() {
		txtT.setText("");
		Q = "";
		txtA.setText("");
	}

	/**
	 * Deal with certain patterns in the encoded string. Marked as Rules
	 * in initializing.
	 * */
	private String clean(String q) {
		while (q.contains("00")) {
			// spouse's spouse = self
			q = q.replaceAll("00", "");
		}
		// 33=3,44=4,55=5,66=6
		while (q.contains("33")) {
			q = q.replaceAll("33", "3");
		}
		while (q.contains("44")) {
			q = q.replaceAll("44", "4");
		}
		while (q.contains("55")) {
			q = q.replaceAll("55", "5");
		}
		while (q.contains("66")) {
			q = q.replaceAll("66", "6");
		}
		// rule: 31 = 41 = 51 = 61 = 1
		// rule: 32 = 42 = 52 = 62 = 2
		while (q.contains("31")) {
			q = q.replaceAll("31", "1");
		}
		while (q.contains("41")) {
			q = q.replaceAll("41", "1");
		}
		while (q.contains("51")) {
			q = q.replaceAll("51", "1");
		}
		while (q.contains("61")) {
			q = q.replaceAll("61", "1");
		}
		while (q.contains("32")) {
			q = q.replaceAll("32", "2");
		}
		while (q.contains("42")) {
			q = q.replaceAll("42", "2");
		}
		while (q.contains("52")) {
			q = q.replaceAll("52", "2");
		}
		while (q.contains("62")) {
			q = q.replaceAll("62", "2");
		}
		// 43 = 3, 34 = 4, 65 = 5, 56 = 6
		while (q.contains("43")) {
			q = q.replaceAll("43", "3");
		}
		while (q.contains("34")) {
			q = q.replaceAll("34", "4");
		}
		while (q.contains("65")) {
			q = q.replaceAll("65", "5");
		}
		while (q.contains("56")) {
			q = q.replaceAll("56", "6");
		}
		
		//rule: 73 = 75 = 7, 74 = 76 = 8
		//rule: 83 = 85 = 7, 84 = 86 = 8
		while (q.contains("73")) {
			q = q.replaceAll("73", "7");
		}
		while (q.contains("75")) {
			q = q.replaceAll("75", "7");
		}
		while (q.contains("74")) {
			q = q.replaceAll("74", "8");
		}
		while (q.contains("76")) {
			q = q.replaceAll("76", "8");
		}
		while (q.contains("83")) {
			q = q.replaceAll("83", "7");
		}
		while (q.contains("85")) {
			q = q.replaceAll("85", "7");
		}
		while (q.contains("84")) {
			q = q.replaceAll("84", "8");
		}
		while (q.contains("86")) {
			q = q.replaceAll("86", "8");
		}
		
		return q;
	}

	/**
	 * Find the title for the encoded string. A dynamic algorithm.
	 * 
	 * @param g
	 *            = gender, 0 = male, 1 = female
	 * @param q
	 *            \in {0,1,...,8}*
	 * */
	public String findAnswer(int g, String q) {
		String a = "";
		int gt = g;
		while (!q.isEmpty()) {// fwd or bkd
			for (int i = q.length() >= maxL ? maxL : q.length(); i > 0; i--) {
				// length of sub string
				String qtemp = q.substring(q.length() - i);
				gt = this.HeOrShe(gt, q.substring(0, q.length()	- qtemp.length()));
				String atemp = findPredefinedAnswer(gt, qtemp);
				if (atemp != null) {
					if (q.length() == Q.length()) {
						a = atemp;
					} else {
						a = atemp + "的\n" + a;
					}
					q = q.substring(0, q.length()
							- qtemp.length());
					break;
				}
			}
		}

		return a;
	}

	/**
	 * Find a predefined title
	 * */
	private String findPredefinedAnswer(int g, String q) {
		String a = null;
		if (map.get(q) != null) {
			a = map.get(q);
			return a;
		}

		if (g == 0 && mapMale.get(q) != null) {// male
			a = mapMale.get(q);
			return a;
		}
		if (g == 1 && mapFemale.get(q) != null) {// female
			a = mapFemale.get(q);
			return a;
		}

		if (q.startsWith("0")) {
			q = Q.substring(1, Q.length());
			if (map.get(q) != null) {
				a = map.get(q);
			}
		}
		return a;
	}

	public String findPredefinedReverse(int g, String q) {
		if (mapR.get(q) != null)
			return mapR.get(q);
		if (g == 0 && mapMaleR.get(q) != null)// male
			return mapMaleR.get(q);
		if (g == 1 && mapFemaleR.get(q) != null)// female
			return mapFemaleR.get(q);

		if (q.endsWith("0")) {
			q = q.substring(0, q.length() - 1);
		}

		return null;
	}

	/**
	 * Decide the person you are looking for is male or female.
	 * 
	 * @param g
	 *            = gender, 0 for male, 1 for female.
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
	 * Predefine common titles. Rules are implemented later in function Clean().
	 * */
	private void iniMaps() {
		map.put("", "自己");
		mapR.put("", "自己");
		
		mapMale.put("0", "老婆/妻子/夫人/太太/媳妇");
		mapMaleR.put("0", "老公/丈夫/先生");
		mapFemale.put("0", "老公/丈夫/先生");
		mapFemaleR.put("0", "老婆/妻子/夫人/太太/媳妇");
		// Rule: "00" = ""

		map.put("1", "父亲/爸爸/爹");
		mapMaleR.put("1", "儿子");
		mapFemaleR.put("1", "女儿");
		map.put("2", "母亲/妈妈/娘");
		mapMaleR.put("2", "儿子");
		mapFemaleR.put("2", "女儿");
		map.put("3", "哥哥/兄长");
		mapMaleR.put("3", "弟弟");
		mapFemaleR.put("3", "妹妹");
		map.put("4", "姐姐/姊");
		mapMaleR.put("4", "弟弟");
		mapFemaleR.put("4", "妹妹");
		map.put("5", "弟弟");
		mapMaleR.put("5", "哥哥/兄长");
		mapFemaleR.put("5", "姐姐");
		map.put("6", "妹妹");
		mapMaleR.put("6", "哥哥/兄长");
		mapFemaleR.put("6", "姐姐");
		map.put("7", "儿子");
		mapMaleR.put("7", "父亲/爸爸/爹");
		mapFemaleR.put("7", "母亲/妈妈/娘");
		map.put("8", "女儿");
		mapMaleR.put("8", "父亲/爸爸/爹");
		mapFemaleR.put("8", "母亲/妈妈/娘");

		mapMale.put("01", "岳丈/岳父");
		mapMaleR.put("01", "女婿");
		mapFemale.put("01", "公公");
		mapFemaleR.put("01", "儿媳妇");

		mapMale.put("02", "岳母/丈母娘");
		mapMaleR.put("02", "女婿");
		mapFemale.put("02", "婆婆");
		mapFemaleR.put("02", "儿媳妇");

		mapMale.put("03", "大舅哥/大舅子/哥哥");
		mapMaleR.put("03", "妹夫");
		mapFemale.put("03", "大伯/哥哥");
		mapFemaleR.put("03", "弟妹/弟媳");

		mapMale.put("04", "大姨姐/姐姐");
		mapMaleR.put("04", "妹夫");
		mapFemale.put("04", "大姑姐/姐姐");
		mapFemaleR.put("04", "弟妹/弟媳");

		mapMale.put("05", "小舅子/弟弟");
		mapMaleR.put("05", "姐夫");
		mapFemale.put("05", "小叔子/弟弟");
		mapFemaleR.put("05", "嫂子");

		mapMale.put("06", "小姨子/妹妹");
		mapMaleR.put("06", "姐夫");
		mapFemale.put("06", "小姑子/妹妹");
		mapFemaleR.put("06", "嫂子");
		// blood only
		same("07", "7");
		same("08", "8");

		map.put("11", "祖父/爷爷");
		mapMaleR.put("11", "孙子");
		mapFemaleR.put("11", "孙女");
		map.put("12", "祖母/奶奶");
		mapMaleR.put("12", "孙子");
		mapFemaleR.put("12", "孙女");
		map.put("13", "伯父/大爷");
		map.put("14", "姑妈/姑姑");
		map.put("15", "叔父/叔叔");
		map.put("16", "姑妈/姑姑");
		mapMaleR.put("13", "侄子");
		mapFemaleR.put("13", "侄女");
		sameMR("14", "13");
		sameMR("15", "13");
		sameMR("16", "13");

		mapMale.put("17", "自己或兄弟");
		mapFemale.put("17", "兄弟");
		mapMaleR.put("17", "自己或兄弟");
		mapFemaleR.put("17", "姐妹");
		mapMale.put("18", "姐妹");
		mapFemale.put("18", "自己或姐妹");
		mapMaleR.put("18", "兄弟");
		mapFemaleR.put("18", "自己或姐妹");

		map.put("21", "外公/姥爷");
		mapMaleR.put("21", "外孙子");
		mapFemaleR.put("21", "外孙女");
		map.put("22", "外婆/姥姥");
		mapMaleR.put("22", "外孙子");
		mapFemaleR.put("22", "外孙女");
		map.put("23", "舅父/舅舅");
		mapMaleR.put("23", "外甥");
		mapFemaleR.put("23", "外甥女");
		same("25", "23");
		map.put("24", "姨母/阿姨");
		sameMR("24", "23");
		same("26", "24");
		mapMale.put("27", "自己或兄弟");
		mapFemale.put("27", "兄弟");
		mapMaleR.put("27", "自己或兄弟");
		mapFemaleR.put("27", "姐妹");
		mapMale.put("28", "姐妹");
		mapFemale.put("28", "自己或姐妹");
		mapMaleR.put("28", "兄弟");
		mapFemaleR.put("28", "自己或姐妹");

		same("10", "2");
		same("20", "1");
		map.put("30", "嫂子");
		mapMaleR.put("30", findAnswer(1, "05"));
		mapFemaleR.put("30", findAnswer(1, "06"));
		map.put("40", "姐夫");
		mapMaleR.put("40", findAnswer(0, "05"));
		mapFemaleR.put("40", findAnswer(0, "06"));
		map.put("50", "弟妹/弟媳");
		mapMaleR.put("50", findAnswer(1, "03"));
		mapFemaleR.put("50", findAnswer(1, "04"));
		map.put("60", "妹夫");
		mapMaleR.put("60", findAnswer(0, "03"));
		mapFemaleR.put("60", findAnswer(0, "04"));

		// rule: 3* = 3, 4* = 4, 5* = 5, 6* = 6
		// rule: 31 = 41 = 51 = 61 = 1
		// rule: 32 = 42 = 52 = 62 = 2
		// rule: 43 = 3, 34 = 4, 65 = 5, 56 = 6
		
		mapMale.put("53","自己或兄弟");
		mapFemale.put("53","兄弟");
		mapMaleR.put("53","自己或兄弟");
		mapFemaleR.put("53","姐妹");
		same("63","53");
		same("35","53");
		same("45","53");

		mapMale.put("54","姐妹");
		mapFemale.put("54","自己或姐妹");
		mapMaleR.put("54","兄弟");
		mapFemaleR.put("54","自己或姐妹");
		same("64","54");
		same("36","54");
		same("46","54");
		
		map.put("37", "侄子");
		mapMaleR.put("37", map.get("15"));
		mapFemaleR.put("37", map.get("16"));
		map.put("47", "外甥");
		mapMaleR.put("47", map.get("25"));
		mapFemaleR.put("47", map.get("26"));
		sameM("57", "37");
		mapMaleR.put("57", map.get("13"));
		mapFemaleR.put("57", map.get("14"));
		same("67", "47");
		map.put("38", "侄女");
		sameMR("38", "37");
		map.put("48", "外甥女");
		sameMR("48", "47");
		same("58", "38");
		same("68", "48");

		map.put("70", mapFemaleR.get("01"));
		mapMaleR.put("70", mapFemale.get("01"));
		mapFemaleR.put("70", mapFemale.get("02"));
		map.put("80", mapMaleR.get("01"));
		mapMaleR.put("80", mapMale.get("01"));
		mapFemaleR.put("80", mapMale.get("02"));
		
		mapMale.put("71",map.get(""));
		mapFemale.put("71",mapFemale.get("0"));
		mapMaleR.put("71", map.get(""));
		mapFemaleR.put("71",mapMale.get("0"));
		same("81","71");
		
		mapMale.put("72",mapMale.get("0"));
		mapFemale.put("72",map.get(""));
		mapMaleR.put("72", mapFemale.get("0"));
		mapFemaleR.put("72", map.get(""));
		same("82","72");
		
		//rule: 73 = 75 = 7, 74 = 76 = 8
		//rule: 83 = 85 = 7, 84 = 86 = 8

		map.put("77", "孙子");
		mapMaleR.put("77",map.get("11"));
		mapFemaleR.put("77",map.get("12"));
		//774 = 776  = 78孙女
		map.put("78", "孙女");
		sameMR("78","77");
		map.put("87", "外孙子");
		mapMaleR.put("87",map.get("21"));
		mapFemaleR.put("87",map.get("22"));
		map.put("88", "外孙女");
		sameMR("88","87");

		mapFemale.put("030", "大伯嫂/妯娌/嫂子");
		mapFemale.put("050", "妯娌/弟妹/弟媳");
		mapMale.put("040", "连桥/连襟/姐夫");
		mapMale.put("060", "连桥/连襟/妹夫");
		
		//TODO:高祖、曾祖、祖父、自己、子、孙、重孙、玄孙
		map.put("111", "曾祖父");
		map.put("112", "曾祖母");
		map.put("1111", "高祖父");
		map.put("1112", "高祖母");
		map.put("777", "重孙");
		map.put("7777", "玄孙");

		/*
		 * map.put("", ""); mapMaleR.put("", ""); mapFemaleR.put("", "");
		 */
	}

	/**
	 * Initialize maps' entries of a using those of b. a has the same titles of
	 * b.
	 * */
	private void sameM(String a, String b) {
		if (map.get(b) != null)
			map.put(a, map.get(b));
		if (mapMale.get(b) != null)
			mapMale.put(a, mapMale.get(b));
		if (mapFemale.get(b) != null)
			mapFemale.put(a, mapFemale.get(b));
	}

	/**
	 * Initialize mapRs' entries of a using those of b. a has the same reverse
	 * titles of b.
	 * */
	private void sameMR(String a, String b) {
		if (mapR.get(b) != null)
			mapR.put(a, mapR.get(b));
		if (mapMaleR.get(b) != null)
			mapMaleR.put(a, mapMaleR.get(b));
		if (mapFemaleR.get(b) != null)
			mapFemaleR.put(a, mapFemaleR.get(b));
	}

	/**
	 * Initialize maps' and mapRs' entries of a using those of b. a has the same
	 * titles and reverse titles of b.
	 * */
	private void same(String a, String b) {
		sameM(a, b);
		sameMR(a, b);
	}
}