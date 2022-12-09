package application;

public class PersonLife {
	/**
	 * Creates a new person allowing for new values to be assigned to that person
	 * @param
	 * @return
	 */
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