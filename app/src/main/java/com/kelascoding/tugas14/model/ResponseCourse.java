package com.kelascoding.tugas14.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCourse {

	@SerializedName("Response")
	private List<Course> response;

	public List<Course> getResponse(){
		return response;
	}
}