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
public class ReporteListaExtensionNps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idAlumno;
	private int nps;
	private String comentario;
	private String campus;
	private String curso;
	private String familia;
	private String escuela;
	private String modalidad;
	private String fechaRegistro;
	private String estado;

}
