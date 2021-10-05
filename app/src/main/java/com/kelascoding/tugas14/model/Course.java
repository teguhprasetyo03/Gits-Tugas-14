package com.kelascoding.tugas14.model;

import com.google.gson.annotations.SerializedName;

public class Course {

	@SerializedName("chapter")
	private String chapter;

	@SerializedName("instructor")
	private String instructor;

	@SerializedName("name_course")
	private String nameCourse;

	@SerializedName("id")
	private int id;

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@SerializedName("media")
	private String media;

	public String getChapter(){
		return chapter;
	}

	public String getInstructor(){
		return instructor;
	}

	public String getNameCourse(){
		return nameCourse;
	}

	public int  getId(){
		return id;
	}

	public String getMedia(){
		return media;
	}
}