public class Student {
	private String name;
	private String address;
	private long campusNum;
	private int boxNum;
	private long cellNum;

	//constructor
	public Student(String name, String address, long campusNum, int boxNum, long cellNum) {
		this.name = name;
		this.address = address;
		this.campusNum = campusNum;
		this.boxNum = boxNum;
		this.cellNum = cellNum;
	}
	//accessors
	public String getName() {
		return name;
	}
	public int getBoxNum() {
		return boxNum;
	}
	public String getAddress() {
		return address;
	}
	public long getCampusNum() {
		return campusNum;
	}
	public long getCellNum() {
		return cellNum;
	}
	public int getAreaCode() {
		return (int)(cellNum/10000000);
	}
	// Be sure to include a toString method here, with
	// an appropriate javadoc comment
	public String toString() {
		return "";
	}

}
