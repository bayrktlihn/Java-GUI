package com.bayrktlihn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6475787546954360775L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String occupation;
	@Enumerated(EnumType.STRING)
	@Column(name = "age_category")
	private AgeCategory ageCategory;
	@Enumerated(EnumType.STRING)
	@Column(name = "employment_category")
	private EmploymentCategory employmentCategory;
	@Column(name = "us_citizen")
	private boolean usCitizen;
	@Column(name = "tax_id")
	private String taxId;
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory employmentCategory,
			boolean usCitizen, String taxId, Gender gender) {
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.employmentCategory = employmentCategory;
		this.usCitizen = usCitizen;
		this.taxId = taxId;
		this.gender = gender;
	}

}
