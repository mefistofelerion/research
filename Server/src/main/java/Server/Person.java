public class Person{

	private String nombre;
	private int edad;
	private String email;

	public Person(){}

	public Person(String nombre, int edad, String email){
		this.nombre =  nombre;
		this.edad = edad;
		this.email = email;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return this.nombre;
	}

	public void setEdad(int edad){
		this.edad = edad;
	}

	public int getEdad(){
		return this.edad;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}
}