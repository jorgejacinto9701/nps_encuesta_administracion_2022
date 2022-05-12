package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idUsuario;
	private String nombre;
	private String login;
	private String clave;
	private int idSede;
	private String sede;
	private String negocio;
	private int estado;
	private int asignados;
	private int clasificados;
	private int pendientes;
	private String avance;
}
