package com.javastudy.hw1;

public class pwd implements Manager{

	@Override
	public void excute(String[] args, StringBuffer currDir) {
		System.out.println(currDir);
	}

}
