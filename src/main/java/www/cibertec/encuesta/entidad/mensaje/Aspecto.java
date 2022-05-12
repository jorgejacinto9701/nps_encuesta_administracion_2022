package www.cibertec.encuesta.entidad.mensaje;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aspecto {
	private int idAspecto;
	private String nombre;
	private int estado;
	private Negocio negocio;
}
