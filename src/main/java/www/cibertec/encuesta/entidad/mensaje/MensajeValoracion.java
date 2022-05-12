package www.cibertec.encuesta.entidad.mensaje;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeValoracion {

	private int idMensaje;
	private int idAspecto;
	private int idTema;
	private String strAspecto;
	private String strTema;
	private String strValoracion;
}
