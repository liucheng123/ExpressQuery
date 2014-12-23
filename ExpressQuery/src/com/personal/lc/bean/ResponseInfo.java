package com.personal.lc.bean;

import java.io.Serializable;
import java.util.List;

import com.google.gson.JsonElement;

public class ResponseInfo {
	private String resultcode;
	private String reason;
	private Integer error_code;
	private JsonElement result;

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public JsonElement getResult() {
		return result;
	}

	public void setResult(JsonElement result) {
		this.result = result;
	}

	public class Result implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 9122421838687211581L;
		
		
		private String company;
		private String com;
		private String no;
		private Integer status;
		private List<LogisticsInfo> list;

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getCom() {
			return com;
		}

		public void setCom(String com) {
			this.com = com;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public List<LogisticsInfo> getList() {
			return list;
		}

		public void setList(List<LogisticsInfo> list) {
			this.list = list;
		}

	}

	public class LogisticsInfo implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -909146663531836387L;
		private String datetime;
		private String remark;
		private String zone;

		public String getDatetime() {
			return datetime;
		}

		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getZone() {
			return zone;
		}

		public void setZone(String zone) {
			this.zone = zone;
		}

	}

}
