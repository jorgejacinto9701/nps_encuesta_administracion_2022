package www.cibertec.encuesta.entidad.nps;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Respuesta implements Serializable{

	private static final long serialVersionUID = 1L;

	private Horario horario;
	private Alumno alumno;
	private String respuesta;
	private double pfinal;
	private String comentario;
	private int pregunta01;
	private int pregunta02;
	private int pregunta03;
	private Date fechaRegistro;
	private String[] preguntas = new String[15];
	
	public String[] getPreguntas() {
		for (int i = 0; i < preguntas.length; i++) {
			String[] pre = respuesta.split(",");
			if(i <pre.length) {
				preguntas[i] = 	pre[i];
			}else {
				preguntas[i] = "";
			}
		}
		return preguntas;
	}

	
}
