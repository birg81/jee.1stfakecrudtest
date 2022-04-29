package model;

public class Person {
	private String firstname, lastname, gender;
	private int id, age, h;
	public Person(int id, String firstname, String lastname, String gender,
			int age, int h) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.h = h;
	}
	public int getId() {
		return id;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getGender() {
		return gender;
	}
	public int getAge() {
		return age;
	}
	public int getH() {
		return h;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setH(int h) {
		this.h = h;
	}
}