package survey;

public class ChildClass extends FatherClass {

	private String b = "1111";
	
	public ChildClass() {
		super();
		System.out.println("ChildClass");
	}

	public ChildClass(String b) {
		super(b);
		this.b = b;
		System.out.println("ccccccccccccc");
	}
	
}
