package MODELO_P3;

public class Perro implements Mascota {
	
	private int codigo;
	private String nombre;
	private String tipo;
	private boolean[] hobbies;
	
	public Perro(int codigo, String nombre, boolean[] hobbies) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = "Perro";
		this.hobbies = hobbies;
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
}