package application;

public class PersonLife {
	
	private double life;
	private String name;
	
	public PersonLife(double life, String name) {
		this.life = life;
		this.name = name;
	}
	
	public double getLife() {
		return life;
		
	}
	
	public void setLife(double life) {
		this.life = life;
	}
	public String getName() {
		return name;
	}
}
