package com.example.testfx;


public class Person {

    private String name;
    private String fatherName;
    private String cnic;
    private String dateOfBirth;
    private String gender;
    private String city;

    public Person(String name, String fatherName, String cnic, String dateOfBirth,
                  String gender, String city) {
        this.name = name;
        this.fatherName = fatherName;
        this.cnic = cnic;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.city = city;
    }


    public String getName() { return name; }
    public String getFatherName() { return fatherName; }
    public String getCnic() { return cnic; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return "Name: " + name + " Father: " + fatherName + " CNIC: " + cnic +
                " Date: " + dateOfBirth + " Gender: " + gender + " City: " + city;
    }
}
