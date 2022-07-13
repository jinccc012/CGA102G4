package com.qa.model;

import java.io.Serializable;

public class QAVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String qa_no;
	private String qa_title;
	private String qa_content;
	private String qa_tag;
	public String getQa_no() {
		return qa_no;
	}
	public void setQa_no(String qa_no) {
		this.qa_no = qa_no;
	}
	public String getQa_title() {
		return qa_title;
	}
	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}
	public String getQa_content() {
		return qa_content;
	}
	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}
	public String getQa_tag() {
		return qa_tag;
	}
	public void setQa_tag(String qa_tag) {
		this.qa_tag = qa_tag;
	}
	@Override
	public String toString() {
		return "QAVO [qa_no=" + qa_no + ", qa_title=" + qa_title + ", qa_content=" + qa_content + ", qa_tag=" + qa_tag
				+ "]";
	}
    
}
