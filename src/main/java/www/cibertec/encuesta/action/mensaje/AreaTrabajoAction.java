package www.cibertec.encuesta.action.mensaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.mensaje.Mensaje;
import www.cibertec.encuesta.entidad.mensaje.MensajeValoracion;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.model.MensajeModel;

@SuppressWarnings("serial")
@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class AreaTrabajoAction extends ActionSupport{
	
	private MensajeModel modelMensaje = new MensajeModel();
	private Map<String, Object> salida = new HashMap<String, Object>();
	private int idNegocio;
	private int idSede;
	private int idMensaje;
	private int idAspecto;
	private int idTema;
	private String idEstado;
	private String valoracion;
	private boolean esNinguno;
	
	private List<Sede> listaConsolidado = new ArrayList<Sede>();
	private List<Mensaje> listaMensaje = new ArrayList<Mensaje>();
	private List<MensajeValoracion> listaMensajeValoracion = new ArrayList<MensajeValoracion>();
	private Map<String, Object> session = ActionContext.getContext().getSession();

	
	@Action(value="/estableceMensajeNinguno",results = { @Result(name = SUCCESS, type = "json") })
	public String estableceMensajeNinguno(){
		log.info("En estableceMensajeNinguno");	
		try {
			modelMensaje.mensajeNinguno(idMensaje, esNinguno);
			listaMensajeValoracion =  modelMensaje.listaMensajeValoracion(idMensaje);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/eliminaMensajeValoracion",results = { @Result(name = SUCCESS, type = "json") })
	public String eliminaMensajeValoracion(){
		log.info("En eliminaMensajeValoracion");	
		try {
			MensajeValoracion obj = new MensajeValoracion();
			obj.setIdMensaje(idMensaje);
			obj.setIdAspecto(idAspecto);
			obj.setIdTema(idTema);
			modelMensaje.eliminaMensajeValoracion(obj);	
			listaMensajeValoracion =  modelMensaje.listaMensajeValoracion(idMensaje);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/registraMensajeValoracion",results = { @Result(name = SUCCESS, type = "json") })
	public String registraMensajeValoracion(){
		log.info("En registraMensajeValoracion");	
		try {
			MensajeValoracion obj = new MensajeValoracion();
			obj.setIdMensaje(idMensaje);
			obj.setIdAspecto(idAspecto);
			obj.setIdTema(idTema);
			obj.setStrValoracion(valoracion);
			modelMensaje.insertaMensajeValoracion(obj);	
			listaMensajeValoracion =  modelMensaje.listaMensajeValoracion(idMensaje);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/consultaMensajeValoracion",results = { @Result(name = SUCCESS, type = "json") })
	public String consultaMensajeValoracion(){
		log.info("En consultaMensajeValoracion");	
		try {
			listaMensajeValoracion =  modelMensaje.listaMensajeValoracion(idMensaje);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/consultaConsolidadoTotalMensaje",results = { @Result(name = SUCCESS, type = "json") })
	public String consultaConsolidadoTotalMensaje(){
		log.info("En consultaConsolidadoTotalMensaje");	
		try {
			listaConsolidado =  modelMensaje.listaMensajeConsolidadoTotal(idNegocio);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	
	@Action(value="/consultaConsolidadoMensaje",results = { @Result(name = SUCCESS, type = "json") })
	public String consultaConsolidadoMensaje(){
		log.info("En consultaConsolidadoMensaje");	
		try {
			Usuario aux  = (Usuario)session.get("objUsuario");
			if (aux == null) {
				listaConsolidado = new ArrayList<Sede>();
			}else {
				listaConsolidado =  modelMensaje.listaMensajeConsolidado(aux.getIdUsuario(), idNegocio);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	

	@Action(value="/listadoMensajePorEstado",results = { @Result(name = SUCCESS, type = "json") })
	public String listadoMensajePorEstado(){
		log.info("En listadoMensajePorEstado");	
		try {
			Usuario aux  = (Usuario)session.get("objUsuario");
			idSede = (Integer)session.get("objSede");
			if (idEstado == null) {
				idEstado = (String)session.get("objEstado");
			}else {
				session.put("objEstado", idEstado);
			}
			if (aux == null && idSede != 0) {
				listaMensaje = new ArrayList<Mensaje>();
			}else {
				listaMensaje =  modelMensaje.listaMensajesPorUsuarioSedeEstado(aux.getIdUsuario(), idSede, idEstado);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}	

	
	
	@Action(value = "/verListadoMensaje", results = { @Result(name = "success", location = "/intranetAreaTrabajoListado.jsp") })
	public String verListadoMensaje() {
		log.info("En listar verListadoMensaje Sede >> " +  idSede);
		log.info("En listar verListadoMensaje Negocio >> " +  idNegocio);
		session.put("objSede", idSede);
		session.put("objNegocio", idNegocio);
		session.put("objEstado", "Clasificado");
		return SUCCESS;
	}
	
}



