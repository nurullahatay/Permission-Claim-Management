package dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RightOfPermission {
	private long sicilNo; // PersonelbilgisiDto'sunda var.
	private String validDate;
	private int dayCountOfDeserved;
	private int dayCountOfDeservedForYear;
	public long getSicilNo() {
		return sicilNo;
	}
	public void setSicilNo(long sicilNo) {
		this.sicilNo = sicilNo;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public int getDayCountOfDeserved() {
		return dayCountOfDeserved;
	}
	public void setDayCountOfDeserved(int dayCountOfDeserved) {
		this.dayCountOfDeserved = dayCountOfDeserved;
	}
	public int getDayCountOfDeservedForYear() {
		return dayCountOfDeservedForYear;
	}
	public void setDayCountOfDeservedForYear(int dayCountOfDeservedForYear) {
		this.dayCountOfDeservedForYear = dayCountOfDeservedForYear;
	}



}