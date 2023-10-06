package oopconcepts.polymorphism;

class Parent {
  void show() {
    System.out.println("Parent");
  }
}


class Child extends Parent {

  @Override
  void show() {
    System.out.println("Child");
  }
}


public class Overriding {
  public static void main(String args[]) {
    Parent obj1 = new Parent();
    Parent obj2 = new Child();
    obj1.show();
    obj2.show();
  }
}
