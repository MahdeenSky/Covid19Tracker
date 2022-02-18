import java.util.*;

public class Person 
{
	private String firstName;
	private String lastName;
	private int age;
	private double weight;
	private double heightCM;
	private String gender;
	private boolean smoker;
	private ArrayList<String> dailySymptoms;
	
	public Person() {
		
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setHeight(double heightCM) {
		this.heightCM = heightCM;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}
	
	public void dailySymptoms(String symptoms) {
		this.dailySymptoms.add(symptoms);
	}
	
	
}


