package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Encuesta implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idEncuesta;
	private String nombre;
	private int numeroPreguntas;
	private String formula;
	private double porcentajeValidas;
	private Date fechaRegistro;
	private String firma;
	private String estado;
}
