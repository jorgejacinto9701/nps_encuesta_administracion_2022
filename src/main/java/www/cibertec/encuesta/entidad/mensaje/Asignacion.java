package www.cibertec.encuesta.entidad.mensaje;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asignacion {
	
	private int idAsignacion;
	private int idUsuario;
	private int idSede;
	private int cantidad;
	private String usuario;
	private String sede;

}
