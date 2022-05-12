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
public class ReporteAcademicoDocente implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idHorario;
	private int idEncuesta;
	private String idCurso;
	private String curso;
	private String seccion;
	private int grupo;
	private String tipoclase;
	private String modalidad;
	private String encuesta;
	private String docente;
	private String sede;
	private double p01;
	private double p02;
	private double p03;
	private double p04;
	private double p05;
	private double p06;
	private double p07;
	private double p08;
	private double p09;
	private double p10;
	private double p11;
	private double p12;
	private double p13;
	private double p14;
	private double p15;
	private int universo;
	private int encuestados;
	private double porcentaje;
	private Double promedio;
	private String principal;
	
	public double getAcumulado() {
		return encuestados * promedio;
	}

	
}
