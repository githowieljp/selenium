package SprintBootApi;

public class User1 {
	private String name;
	private int age;

	// 构造函数
	public User1(String name,int age){
	this.name = name;
	this.age = age;
	}

	// Getters 和 Setters
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
}
