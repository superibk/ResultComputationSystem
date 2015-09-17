package Main;

public class UnitObject {

	private String course;
	private String unit;

	public UnitObject(String course, String unit) {

		this.course = course;
		this.unit = unit;

	}

	public String getCourse() {

		return this.course;
	}

	public String getUnit() {

		return this.unit;
	}

	@Override
	public String toString() {
		return getCourse();

	}

}
