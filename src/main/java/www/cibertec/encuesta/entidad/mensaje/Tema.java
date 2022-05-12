package www.cibertec.encuesta.entidad.mensaje;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tema {
	private int idTema;
	private String nombre;
	private int estado;
	private Aspecto aspecto;
	private Negocio negocio;
	private String definicion;
}
