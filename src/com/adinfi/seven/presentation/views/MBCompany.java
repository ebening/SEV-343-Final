package com.adinfi.seven.presentation.views;

import javax.annotation.PostConstruct;

import com.adinfi.defines.GlobalDefines;

public class MBCompany {
	@PostConstruct
	private void init() {

	}
	
	public  String getCompany() {
		return GlobalDefines.COMPANY;
	}
}
