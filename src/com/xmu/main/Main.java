package com.xmu.main;

import com.xmu.logic.ParserFactory;

public class Main {

	public static void main(String[] args) {
		ParserFactory factory = new ParserFactory(
				"http://www.chinaports.com/shipline/1/20/26/shipstatView");
		factory.start();
	}
}
