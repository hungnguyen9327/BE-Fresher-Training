package oopconcepts.polymorphism;

class G {
}


interface Printable {
}


class H implements Printable {
  public void h() {
    System.out.println("a method");
  }
}


class K implements Printable {
  public void k() {
    System.out.println("b method");
  }
}


class Call {
  void invoke(Printable p) {// upcasting
    if (p instanceof H) {
      H h = (H) p;// Downcasting
      h.h();
    }
    if (p instanceof K) {
      K k = (K) p;// Downcasting
      k.k();
    }
  }
}


public class InstanceOf {
  public static void main(String args[]) {
    /*
     * //example G g1 = new G(); System.out.println(g1 instanceof G);//true //instanceof with var
     * have null value G g2 = null; System.out.println(g2 instanceof G);//false
     */
    // real in use of instanceof
    Printable p = new K();
    Call c = new Call();
    c.invoke(p);
  }
}
