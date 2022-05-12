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
import www.cibertec.encuesta.entidad.nps.Opcion;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.model.NpsModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class LoginIntranetAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private String mensaje;
	private NpsModel model = new NpsModel();
	private Map<String, Object> session = ActionContext.getContext().getSession();

	@Action(value = "/loginUsuario", results = { @Result(name = "success", location = "/intranetHome.jsp"),
			@Result(name = "error", location = "/intranetLogin.jsp") })
	public String login() {
		log.info("En login usuario");
		try {
			Usuario aux = model.valida(usuario.getLogin(), usuario.getClave());
			if (aux == null) {
				mensaje = "El usuario no existe";
				return ERROR;
			} else {
				List<Opcion> menus = model.listaOpcionesPorUsuario(aux.getIdUsuario());
				session.put("objMenus", menus);
				session.put("objUsuario", aux);
				return SUCCESS;
			}
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value = "/logout", results = { @Result(name = "success", location = "/intranetLogin.jsp") })
	public String logout() {
		log.info("En listar logout ");
		session.clear();
		mensaje = "El usuario salió de sesión";
		return SUCCESS;
	}

}
