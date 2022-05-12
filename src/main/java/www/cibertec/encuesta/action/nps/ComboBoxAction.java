package www.cibertec.encuesta.action.nps;

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
import www.cibertec.encuesta.entidad.mensaje.Aspecto;
import www.cibertec.encuesta.entidad.mensaje.Negocio;
import www.cibertec.encuesta.entidad.mensaje.Tema;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.model.AspectoModel;
import www.cibertec.encuesta.model.MensajeModel;
import www.cibertec.encuesta.model.NpsModel;
import www.cibertec.encuesta.model.TemaModel;
import www.cibertec.encuesta.model.UsuarioModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class ComboBoxAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private NpsModel modelNps = new NpsModel();
	private TemaModel modelTema = new TemaModel();
	private MensajeModel modelMensaje = new MensajeModel();
	private UsuarioModel modelUsuario = new UsuarioModel();
	private AspectoModel modelAspecto = new AspectoModel();
	
	private List<Sede> listaSede;
	private List<Aspecto> listaAspecto;
	private List<Tema> listaTemas;
	private List<Negocio> listaNegocio;
	private List<Usuario> listaUsuario;
	private List<Usuario> listaUsuarioPorSede;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private int idSede;
	
	@Action(value = "/cargaNegocios", results = { @Result(name = SUCCESS, type = "json") })
	public String listAllNegocios() {
		log.info("En metodo: listAllNegocios");
		listaNegocio = modelMensaje.listaNegocios();
		return SUCCESS;
	}
	
	@Action(value = "/cargaSedes", results = { @Result(name = SUCCESS, type = "json") })
	public String listAllSedes() {
		log.info("En metodo: listAllSedes");
		listaSede = modelNps.listaSedes();
		return SUCCESS;
	}
	
	@Action(value = "/cargaAspectos", results = { @Result(name = SUCCESS, type = "json") })
	public String listAllAspectos() {
		log.info("En metodo: listAllAspectos");
		listaAspecto = modelAspecto.listaAspectos();
		return SUCCESS;
	}
	
	@Action(value = "/cargaTemas", results = { @Result(name = SUCCESS, type = "json") })
	public String listAllTemas() {
		log.info("En metodo: listAllTemas");
		listaTemas = modelTema.listaTemas();
		return SUCCESS;
	}
	
	@Action(value = "/cargaAspectosActivos", results = { @Result(name = SUCCESS, type = "json") })
	public String listAllAspectosActivos() {
		log.info("En metodo: listAllAspectosActivos");
		int idNegocio = (Integer)session.get("objNegocio");
		listaAspecto = modelAspecto.listaAspectosActivos(idNegocio);
		return SUCCESS;
	}
	
	@Action(value = "/cargaUsuarios", results = { @Result(name = SUCCESS, type = "json") })
	public String listAlllistaUsuario() {
		log.info("En metodo: listAlllistaUsuario");
		listaUsuario = modelUsuario.listaUsuarioClasificadores();
		return SUCCESS;
	}
	
	@Action(value = "/cargaUsuariosPorSede", results = { @Result(name = SUCCESS, type = "json") })
	public String cargaUsuariosPorSede() {
		log.info("En metodo: cargaUsuariosPorSede");
		listaUsuarioPorSede = modelUsuario.listaUsuarioPorSede(idSede);
		return SUCCESS;
	}
	
}
