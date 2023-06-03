package MODELO_P3;

public class Gato implements Mascota {
	
	private int codigo;
	private String nombre;
	private String tipo;
	private boolean[] hobbies;
	private boolean[] genero; 
	
	public Gato(int codigo, String nombre, boolean[] genero ,boolean[] hobbies) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = "Gato";
		this.hobbies = hobbies;
		this.genero = genero;
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

	public boolean[] getGenero() {
		return genero;
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
	public String revisionSexo() {
		String sexo = "";	
		if (genero[0]==true) {
				sexo = "M";
			}
			else if (genero[1]==true) {
				sexo = "F";
			}
		return sexo;
	}
}