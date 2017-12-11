package com.example.xyinc.enums;

public enum GenderEnum {

	M("Male"), 
	F("Female");

	private String description;

	private GenderEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
