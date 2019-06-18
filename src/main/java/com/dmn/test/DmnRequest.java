package com.dmn.test;

import java.io.Serializable;

public class DmnRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String itype;

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

}
