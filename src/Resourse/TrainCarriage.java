package Resourse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainCarriage {
	private final String number;// 车厢编号
	private final String type;// 车厢类型
	private final int peopleNumber;// 定员数
	private Date data;// 出场年份
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public TrainCarriage(String number, String type, int peopleNumber, String data) {
		this.number = number;
		this.type = type;
		this.peopleNumber = peopleNumber;
		try {
			this.data = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public Date getData() {
		return data;
	}

	public String datatoString() {
		return sdf.format(data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + peopleNumber;
		result = prime * result + ((sdf == null) ? 0 : sdf.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TrainCarriage other = (TrainCarriage) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (peopleNumber != other.peopleNumber)
			return false;
		if (sdf == null) {
			if (other.sdf != null)
				return false;
		} else if (!sdf.equals(other.sdf))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
