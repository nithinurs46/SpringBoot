package com;

public class MathUtils {

	public int add(int a, int b) {
		return a + b;
	}

	public double computeCircleArea(double radius) {
		return Math.PI * radius * radius;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public static void main(String[] args) {
		MathUtils util = new MathUtils();
		System.out.println(util.add(1, 2));
	}
}
