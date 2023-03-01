package com.springboot.project.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
	@Id
	private String id;
	
	private String created;
	
	private int karma;

	private String about;
	
	private List<Integer> submitted;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getKarma() {
		return karma;
	}
	public void setKarma(int karma) {
		this.karma = karma;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<Integer> getSubmitted() {
		return submitted;
	}
	public void setSubmitted(List<Integer> submitted) {
		this.submitted = submitted;
	}
	

}
