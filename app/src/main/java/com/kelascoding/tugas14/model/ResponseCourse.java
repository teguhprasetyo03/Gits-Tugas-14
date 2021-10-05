package com.kelascoding.tugas14.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCourse {

	@SerializedName("Response")
	private List<Course> response;

	public List<Course> getResponse(){
		return response;
	}
}