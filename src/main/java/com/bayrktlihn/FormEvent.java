package com.bayrktlihn;

import java.util.EventObject;

import lombok.Getter;

@Getter
public class FormEvent extends EventObject {

	private String name;
	private String occupation;
	private int ageCategoryId;
	private String employmentCategory;
	private boolean usCitizen;
	private String taxId;
	private String gender;

	public FormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public FormEvent(Object source, String name, String occupation, int ageCategoryId, String employmentCategory,
			boolean usCitizen, String taxId, String gender) {
		super(source);
		// TODO Auto-generated constructor stub

		this.name = name;
		this.occupation = occupation;
		this.ageCategoryId = ageCategoryId;
		this.employmentCategory = employmentCategory;
		this.taxId = taxId;
		this.usCitizen = usCitizen;
		this.gender = gender;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6867854980576103119L;

}
