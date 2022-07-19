package org.vtiger.practice;

public class Dynamic {
	public static void main(String[] args) {
	String name="My self %s %f";
	String change=String.format(name,"Nani",5.400);
	System.out.println(change);

}
}
