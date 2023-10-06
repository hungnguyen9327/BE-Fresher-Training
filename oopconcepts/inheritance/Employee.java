package oopconcepts.inheritance;

public class Employee {
  private String id;
  private String name;
  private int age;
  private float salary = 20000;

  public void employee() {
    System.out.println("Employee");
  }

  public Employee() {

  }

  public Employee(String id, String name, int age, float salary) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public float getSalary() {
    return salary;
  }

  public void setSalary(float salary) {
    this.salary = salary;
  }



}
