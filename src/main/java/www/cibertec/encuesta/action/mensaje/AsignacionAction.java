package www.cibertec.encuesta.action.mensaje;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.mensaje.Asignacion;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.model.AsignacionModel;
import www.cibertec.encuesta.model.MensajeModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class AsignacionAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int idSede;
	private int idUsuario;
	private int idAsignacion;
	private int cantidad;
	private int idNegocio;
	
	private MensajeModel mensajeModel = new MensajeModel();
	private AsignacionModel asignacionMoldel = new AsignacionModel();
	private List<Sede> listaConsolidado = new ArrayList<Sede>();
	private List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();
	
	@Action(value="/eliminaAsignacion",results = { @Result(name = SUCCESS, type = "json") })
	public String eliminaAsignacion(){
		log.info("En eliminaAsignacion");	
		try {
			asignacionMoldel.eliminaAsignacion(idAsignacion, idSede, idUsuario);
			listaAsignacion = asignacionMoldel.consultaAsignacion(idSede);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/registraAsignacion",results = { @Result(name = SUCCESS, type = "json") })
	public String registraAsignacion(){
		log.info("En registraAsignacion");	
		try {
			asignacionMoldel.registraAsignacion(idSede, idUsuario, cantidad);
			listaAsignacion = asignacionMoldel.consultaAsignacion(idSede);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/listaAsignacion",results = { @Result(name = SUCCESS, type = "json") })
	public String listaAsignacion(){
		log.info("En listaAsignacion");	
		try {
			listaAsignacion = asignacionMoldel.consultaAsignacion(idSede);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
