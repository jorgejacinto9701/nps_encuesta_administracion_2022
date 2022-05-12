package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Curso implements Serializable{

	private static final long serialVersionUID = 1L;

	private String idCurso;
	private String nombre;
	private String modalidad;
	private String estado;
}
