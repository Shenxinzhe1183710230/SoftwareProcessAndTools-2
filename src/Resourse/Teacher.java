package Resourse;

public class Teacher {
	private final String IDnumber;
	private final String name;
	private final String gender;
	private final String teacherTitle;

	public Teacher(String iDnumber, String name, String gender, String teacherTitle) {
		super();
		IDnumber = iDnumber;
		this.name = name;
		this.gender = gender;
		this.teacherTitle = teacherTitle;
	}

	public String getIDnumber() {
		return IDnumber;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getTeacherTitle() {
		return teacherTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IDnumber == null) ? 0 : IDnumber.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teacherTitle == null) ? 0 : teacherTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (IDnumber == null) {
			if (other.IDnumber != null)
				return false;
		} else if (!IDnumber.equals(other.IDnumber))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teacherTitle == null) {
			if (other.teacherTitle != null)
				return false;
		} else if (!teacherTitle.equals(other.teacherTitle))
			return false;
		return true;
	}

}
