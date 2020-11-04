package LocationAndTimeslot;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Timeslot {
	private Date startDate;
	private Date endDate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	public Timeslot(String startTime, String endTime) {
		try {
			this.startDate = sdf.parse(startTime);
			this.endDate = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkRep();
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	private void checkRep() {
		// TODO Auto-generated method stub
		assert startDate.before(endDate) : "time false";
	}

	public String startYearMonthDay() {
		return sdf1.format(startDate);
	}

	public String endYearMonthDay() {
		return sdf1.format(endDate);
	}

	@Override
	public String toString() {
		return new String(sdf.format(startDate) + " " + sdf.format(endDate));
	}

	@Override
	public Timeslot clone() {
		// TODO Auto-generated method stub
		return new Timeslot(sdf.format(startDate), sdf.format(endDate));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Timeslot other = (Timeslot) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

}
