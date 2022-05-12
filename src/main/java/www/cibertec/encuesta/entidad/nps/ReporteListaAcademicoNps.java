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
public class ReporteListaAcademicoNps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idAlumno;
	private int nps;
	private String comentario;
	private String campus;
	private String ciudad;
	private String carrera;
	private String facultad;
	private String semestre;
	private int ciclo;
	private String tipoEstudiante;
	private String fechaRegistro;
	private String modalidad;
	private String estado;
	
	
}
