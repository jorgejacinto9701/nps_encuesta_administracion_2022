package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Docente implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idDocente;
	private String nombres;
	private String estado;
	private String correo;

}
