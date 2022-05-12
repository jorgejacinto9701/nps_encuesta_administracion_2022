package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Horario implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idHorario;
	private Curso curso;
	private Docente docente;
	private Ciclo ciclo;
	private Encuesta encuesta;
	private Sede sede;
	private String seccion;
	private String grupo;
	private String modalidad;
	private String tipoclase;
	private String principal;
	
}
