package com.bayrktlihn.entity;

public enum AgeCategory {
	V_UNDER_18, V_18_TO_65, V_65_OR_OVER;

	@Override
	public String toString() {
		if (this == V_UNDER_18) {
			return "Under 18";
		} else if (this == V_18_TO_65) {
			return "18 to 65";
		} else if (this == V_65_OR_OVER) {
			return "65 or Over";
		}

		return "";

	}

}
