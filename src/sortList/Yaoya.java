package sortList;

public class Yaoya {

	private int food_id;
	private String name;
	private int type_id;
	private int cost;

	public Yaoya(int food_id, String name, int type_id, int cost) {
		this.food_id = food_id;
		this.name = name;
		this.type_id = type_id;
		this.cost = cost;
	}
	public Yaoya() {

	}

	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}



}
