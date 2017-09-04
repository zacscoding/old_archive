package com.web.asm;

public class AsmMethodTestClass {
	private int f;
	public int getF() {
		return this.f;
	}
	
	public void checkAndSetF(int f) {
		if(f >= 0) {
			this.f = f;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
