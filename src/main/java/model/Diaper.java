package model;

import java.io.Serializable;

	public class Diaper implements Serializable {

		private String dateTime;
		private String type;
		private String memo;

		public Diaper() {
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
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}

	}
