package com.bayrktlihn.entity;

public enum EmploymentCategory {
	EMPLOYED {
		@Override
		public String toString() {
			return "Employed";
		}
	},
	SELF_EMPLOYED {
		@Override
		public String toString() {
			return "Self Employed";
		}
	},
	UNEMPLOYED {
		@Override
		public String toString() {
			return "Unemployed";
		}
	},
	OTHER {
		@Override
		public String toString() {
			return "Other";
		}
	};

	@Override
	public String toString() {
		if (this == EMPLOYED) {
			return "Employed";
		} else if (this == SELF_EMPLOYED) {
			return "Self Employed";
		} else if (this == UNEMPLOYED) {
			return "Unemployed";
		} else if (this == OTHER) {
			return "Other";
		}
		return "";
	}
}
