package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Sede implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idSede;
	private String nombre;
	private int total;
	private int aprobadas;
	private String asignadas;
	private String negocio;
	private int idNegocio;
	
	public String getAsignadas() {
		StringBuilder sb = new StringBuilder();
		sb.append(aprobadas).append(" / ").append(total);
		asignadas = sb.toString(); 
		return asignadas; 
	}
}
