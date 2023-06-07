

package MODELO_P3;

public interface Mascota {

    int getCodigo();
    String getNombre();
    void setNombre(String nombre);
    String getTipo();
    boolean[] getHobbies();
    String getHobbiesData();
    boolean[] getGenero();
    String revisionSexo();
    int getEdad();
    void setEdad(int edad);
    String listadoHobbies();
    
}
