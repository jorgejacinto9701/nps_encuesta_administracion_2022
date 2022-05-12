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
public class ReporteAvanceAcademicoNps implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sede;
	private String carrera;
	private String modalidad;
	private int universo;
	private int encuestados;
	private double avance;
	private int promotores;
	private int neutros;
	private int detractores;
	
	private double promotoresPorcentaje;
	private double neutrosPorcentaje;
	private double detractoresPorcentaje;
	
	private int nps;

}
