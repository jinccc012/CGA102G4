package com.issue.model;

public class IssueVO {
	private Integer issue_no;
	private String issue_name;
	private String issue_tel;
	private String issue_mail;
	private String issue_content;
	private Integer issue_process_state;

	public Integer getIssue_no() {
		return issue_no;
	}

	public void setIssue_no(Integer issue_no) {
		this.issue_no = issue_no;
	}

	public String getIssue_name() {
		return issue_name;
	}

	public void setIssue_name(String issue_name) {
		this.issue_name = issue_name;
	}

	public String getIssue_tel() {
		return issue_tel;
	}

	public void setIssue_tel(String issue_tel) {
		this.issue_tel = issue_tel;
	}

	public String getIssue_mail() {
		return issue_mail;
	}

	public void setIssue_mail(String issue_mail) {
		this.issue_mail = issue_mail;
	}

	public String getIssue_content() {
		return issue_content;
	}

	public void setIssue_content(String issue_content) {
		this.issue_content = issue_content;
	}

	public int getIssue_process_state() {
		return issue_process_state;
	}

	public void setIssue_process_state(int issue_process_state) {
		this.issue_process_state = issue_process_state;
	}

	@Override
	public String toString() {
		return "IssueVO [issue_no=" + issue_no + ", issue_name=" + issue_name + ", issue_tel=" + issue_tel
				+ ", issue_mail=" + issue_mail + ", issue_content=" + issue_content + ", issue_process_state="
				+ issue_process_state + "]";
	}

	public IssueVO(Integer issue_no, String issue_name, String issue_tel, String issue_mail, String issue_content,
			Integer issue_process_state) {
		super();
		this.issue_no = issue_no;
		this.issue_name = issue_name;
		this.issue_tel = issue_tel;
		this.issue_mail = issue_mail;
		this.issue_content = issue_content;
		this.issue_process_state = issue_process_state;
	}

	public IssueVO() {
	}

}
