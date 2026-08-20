package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class History implements Serializable {

	private String dateTime;
	private String type;
	private String detail;
	private LocalDateTime sortDateTime;
	private int id;

	public History() {
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getSortDateTime() {
		return sortDateTime;
	}

	public void setSortDateTime(LocalDateTime sortDateTime) {
		this.sortDateTime = sortDateTime;
	}
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}
}
