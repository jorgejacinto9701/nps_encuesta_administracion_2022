package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Alumno implements Serializable{

	private static final long serialVersionUID = 1L;
	private String idAlumno;
	private String escuela;
	private String familia;
	private String codigoCurso;
	private String nombreCurso;
	private String seccion;
	private String modalidad;
	private String campana;
	private String dni;
	private byte nps;
	private String comentario;
	private String fechaNacimiento;
	private byte ciclo;
	private byte tipo;
	private Carrera carrera;
	
}
