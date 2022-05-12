package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ReporteListaMensajeAcademicoNps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String negocio;
	private String campus;
	private String ciudad;
	private String carrera;
	private String facultad;
	private String ciclo;
	private String tipoEstudiante;
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
