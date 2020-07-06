package com.bayrktlihn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {
	private String name;
	private int id;
	private boolean checked;

	@Override
	public String toString() {
		return name;
	}

}
