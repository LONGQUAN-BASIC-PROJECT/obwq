package com.desksoft.entity.xml.en;

import java.util.List;

public class EnglishWord {

	private String tname;
	private String comment ;
	private String yinbiao ;
	
	private List<Example> examples ;


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getYinbiao() {
		return yinbiao;
	}

	public void setYinbiao(String yinbiao) {
		this.yinbiao = yinbiao;
	}

	public List<Example> getExamples() {
		return examples;
	}

	public void setExamples(List<Example> examples) {
		this.examples = examples;
	}
}
