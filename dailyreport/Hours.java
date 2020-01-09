package dailyreport;

public enum Hours {
	H8(8), H9(9), H10(10), H11(11), H12(12), H13(13), H14(14), H15(15), H16(16);

	final static int count = 9;
	private String str;

	Hours(int h) {
		str = h + ":00";
	}

	String getStr() {
		return str;
	}
}