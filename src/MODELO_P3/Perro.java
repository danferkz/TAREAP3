package MODELO_P3;

public class Perro implements Mascota {
	
	private int codigo;
	private String nombre;
	private String tipo;
	private boolean[] hobbies;
	private boolean[] genero;
	private int edad;
	
	public Perro(int codigo, String nombre, boolean[] genero, boolean[] hobbies, int edad) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = "Perro";
		this.genero = genero;
		this.hobbies = hobbies;
		this.edad = edad;
	}

	@Override
	public int getCodigo() {
		return codigo;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getTipo() {
		return tipo;
	}

	@Override
	public boolean[] getHobbies() {
		return hobbies;
	}

	@Override
	public String getHobbiesData() {
		String texto = "";
		for(int i = 0; i < hobbies.length; i++) {
			boolean b = hobbies[i];
			switch(i) {
				case 0: texto +="\tComida Selectiva" + "\t" + b + "\n"; break;
				case 1: texto +="\tPaseos Familiares" + "\t" + b + "\n"; break;
				case 2: texto +="\tEs muy Hiperactivo" + "\t" + b + "\n"; break;
			}
		}
		return texto;
	}

	@Override
	public boolean[] getGenero() {
		return genero;
	}
	
	@Override
	public String revisionSexo() {
		String sexo = "";	
		if (genero[0]) {
			sexo = "M";
		} else if (genero[1]) {
			sexo = "F";
		}
		return sexo;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String listadoHobbies() {
		String hobb = "";
		if (hobbies[0]==true) {
			hobb += "Comida Selectiva";
		}
		if (hobbies[0]==true && hobbies[1]==true) {
			hobb+=", ";
		}
		if (hobbies[1]==true) {
			hobb += "Paseos familiares";
		}
		if (hobbies[1]==true && hobbies[2]==true) {
			hobb+=", ";
		}if (hobbies[2]==true) {
			hobb += "Hiperactivo";
		}
		return hobb;
	}
	
}