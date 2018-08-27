package dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RightOfPermission {
	private long sicilNo; // PersonelbilgisiDto'sunda var.
	private String ValidDate;
	private int DayCountOfDeserved;
	private int DayCountOfDeservedForYear;
	public long getSicilNo() {
		return sicilNo;
	}
	public void setSicilNo(long sicilNo) {
		this.sicilNo = sicilNo;
	}
	public String getValidDate() {
		return ValidDate;
	}
	public void setValidDate(String validDate) {
		ValidDate = validDate;
	}
	public int getDayCountOfDeserved() {
		return DayCountOfDeserved;
	}
	public void setDayCountOfDeserved(int dayCountOfDeserved) {
		DayCountOfDeserved = dayCountOfDeserved;
	}
	public int getDayCountOfDeservedForYear() {
		return DayCountOfDeservedForYear;
	}
	public void setDayCountOfDeservedForYear(int dayCountOfDeservedForYear) {
		DayCountOfDeservedForYear = dayCountOfDeservedForYear;
	}



}
