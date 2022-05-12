package www.cibertec.encuesta.entidad.mensaje;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensaje {

	private int idMensaje;
	private String idAlumno;
	private String mensaje;
	private int idSede;
	private int idUsuario;
	private String estado;
	private String tipo;
	private List<MensajeValoracion> valoracion;
	private String strTipo;
	private int nps;
	
	public String getStrTipo() {
		if (tipo.equals("NA")) {
			strTipo = "NA";
		}else {
			strTipo = "";
		}
		return strTipo;	
	}

	
	
	
}
