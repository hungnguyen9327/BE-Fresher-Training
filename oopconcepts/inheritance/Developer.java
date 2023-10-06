package oopconcepts.inheritance;

public class Developer extends DevTeam {
  private float bonus;

  public void dev() {
    System.out.println("Developer");
  }

  public Developer(float bonus) {
    super();
    this.bonus = bonus;
  }

  public float getBonus() {
    return bonus;
  }

  public void setBonus(float bonus) {
    this.bonus = bonus;
  }

}
