package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReporteListaMensajeExtensionNps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String negocio;
	private String campus;
	private String escuela;
	private String familia;
	private String curso;
	private String modalidad;
	private String idAlumno;
	private String idMensaje;
	private String comentario;
	private int nps;
	private String pregunta;
	private String aspecto;
	private String tema;
	private String tipo;
	private String valoracion;
	private String usuario;

}
