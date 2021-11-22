package model;
public class Person {

	private String name;
	private boolean npc, loser;

	public Person(String name) {
		this.name = name;
		npc = false;
	}

	public Person() {
		npc = true;
		name = "Computer";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNpc() {
		return npc;
	}

	public boolean isLoser() {
		return loser;
	}

	@Override
	public String toString() {
		return name;
	}
}
