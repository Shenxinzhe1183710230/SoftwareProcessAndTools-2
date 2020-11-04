package Resourse;

public class Plane {
	private final String planeNumber;// 飞机编号
	private final String planeModelNumber;// 机型号
	private final int seatNumber;// 座位数
	private final double planeAge;// 机龄

	public Plane(String planeNumber, String planeModelNumber, int seatNumber, double planeAge) {
		// TODO Auto-generated constructor stub
		this.planeNumber = planeNumber;
		this.planeModelNumber = planeModelNumber;
		this.seatNumber = seatNumber;
		this.planeAge = planeAge;
	}

	public String getPlaneNumber() {
		return planeNumber;
	}

	public String getPlaneModelNumber() {
		return planeModelNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public double getPlaneAge() {
		return planeAge;
	}

	@Override
	public Plane clone() {
		// TODO Auto-generated method stub
		return new Plane(planeNumber, planeModelNumber, seatNumber, planeAge);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(planeAge);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((planeModelNumber == null) ? 0 : planeModelNumber.hashCode());
		result = prime * result + ((planeNumber == null) ? 0 : planeNumber.hashCode());
		result = prime * result + seatNumber;
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
		Plane other = (Plane) obj;
		if (Double.doubleToLongBits(planeAge) != Double.doubleToLongBits(other.planeAge))
			return false;
		if (planeModelNumber == null) {
			if (other.planeModelNumber != null)
				return false;
		} else if (!planeModelNumber.equals(other.planeModelNumber))
			return false;
		if (planeNumber == null) {
			if (other.planeNumber != null)
				return false;
		} else if (!planeNumber.equals(other.planeNumber))
			return false;
		if (seatNumber != other.seatNumber)
			return false;
		return true;
	}

}
