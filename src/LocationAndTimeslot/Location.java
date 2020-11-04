package LocationAndTimeslot;

public class Location {
	private final String name;// 名称
	private final Boolean shared;// 是否可共享

	public Location(String name, Boolean shared) {
		this.name = name;
		this.shared = shared;
	}

	public String getName() {
		return name;
	}

	public Boolean getShared() {
		return shared;
	}

	@Override
	public Location clone() {
		return new Location(name, shared);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shared == null) ? 0 : shared.hashCode());
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
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shared == null) {
			if (other.shared != null)
				return false;
		} else if (!shared.equals(other.shared))
			return false;
		return true;
	}

}
