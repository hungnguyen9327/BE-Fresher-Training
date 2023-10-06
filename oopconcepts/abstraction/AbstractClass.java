package oopconcepts.abstraction;

interface Temp {
  void change1();

  void change2();
}


abstract class Shape implements Temp {
  private String t1;

  Shape(String temp) { // have constructor
    t1 = temp;
    System.out.println("Shape instance created...");
  }

  abstract void draw();// abstract method

  public void change1() {// have methods
    System.out.println("1st Shape Changed...");
  }

  // ..and have data member (field)
}


class Triangle extends Shape {
  Triangle(String temp) {
    super(temp);
    // TODO Auto-generated constructor stub
  }

  @Override
  void draw() {

    System.out.println("Drawing Triangle...");
  }

  public void change2() {
    System.out.println("Triangle Shape Changed...");// override for abstract class
  }
}


class Circle extends Shape {

  Circle(String temp) {
    super(temp);
    // TODO Auto-generated constructor stub
  }

  @Override
  void draw() {
    System.out.println("Drawing Circle...");
  }

  public void change2() {
    System.out.println("Triangle Shape Changed...");// override for abstract class
  }
}


public class AbstractClass {
  public static void main(String args[]) {
    // Shape t;
    // t = new Triangle();
    // t.draw();
    // t = new Circle();
    // t.draw();
  }
}
