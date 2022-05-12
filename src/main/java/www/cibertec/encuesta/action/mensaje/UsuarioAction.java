package www.cibertec.encuesta.action.mensaje;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.model.MensajeModel;
import www.cibertec.encuesta.model.UsuarioModel;
import www.cibertec.encuesta.util.Constantes;
import www.cibertec.encuesta.util.FunctionUtil;

@SuppressWarnings("serial")
@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class UsuarioAction extends ActionSupport{
	
	private List<Usuario> lista= new ArrayList<Usuario>();
	private List<Sede> listaSede = new ArrayList<Sede>();
	private String filtro ="";
	private Usuario usuario;
	private String id;
	private String idEstado;
	private int idNegocio;
	private File file;
	private UsuarioModel usuarioModel = new UsuarioModel();
	private MensajeModel mensajeModel = new MensajeModel();
	private Map<String, Object> salida = new HashMap<String, Object>();
	
	
	
	@Action(value="/subirPlantillaUsuario", results = { @Result(name = SUCCESS, type = "json") })
	public String subirUsuarios(){
		log.info("En registrar usuarios");		
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			String dni = null;
			String negocio = null;
			String sede = null;
			boolean noEsPrimero = false;
			Usuario objUsuario = null;
			String apellidos = "";
			String nombres = "";
			int idNegocio = -1;
			int idSede = -1;
			List<Usuario> lstUsuario = new ArrayList<Usuario>();
			
			for (Row row : sheet) {
				if (noEsPrimero) {
					objUsuario = new Usuario();
					for (Cell cell : row) {
						switch (cell.getColumnIndex() + 1) {
							case 1:
								if ( cell.getCellType() == Cell.CELL_TYPE_STRING ) {
									dni = cell.getStringCellValue().trim();
								}else if ( cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
									dni = String.valueOf((int)cell.getNumericCellValue());
								}
								objUsuario.setLogin(dni);
								objUsuario.setClave("Nps20212-"+dni);
							break;
							case 2:
								nombres = cell.getStringCellValue().trim();
							break;	
							case 3:
								apellidos = cell.getStringCellValue().trim();
								objUsuario.setNombre(FunctionUtil.setFormatoTextoEnBaseDatos(apellidos + ", " +nombres));
							break;
							case 4:
								negocio = cell.getStringCellValue().trim();
								idNegocio = mensajeModel.buscaNegocioIgual(negocio);
								if (idNegocio == -1) {
									salida.put("mensaje", "Celda (" + (cell.getRowIndex() + 1) + "," + (cell.getColumnIndex() + 1)	+ ") no existe el negocio : " + negocio);
									return SUCCESS;
								}
							break;
							case 5:
								sede = cell.getStringCellValue().trim();
								idSede = mensajeModel.buscaSedeIgual(sede, idNegocio);
								if (idSede == -1) {
									salida.put("mensaje", "Celda (" + (cell.getRowIndex() + 1) + "," + (cell.getColumnIndex() + 1)	+ ") no existe la sede : " + sede);
									return SUCCESS;
								}else {
									objUsuario.setIdSede(idSede);
								}
							break;
						}
					}
					lstUsuario.add(objUsuario);
				}
				noEsPrimero = true;
			}

			int usuarioIngresados = 0;
			for (Usuario  obj : lstUsuario) {
				usuarioModel.insertaUsuario(obj);
			}
			salida.put("mensaje", "Se ha ingresado " + usuarioIngresados + " usuario(s)");
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			log.info(e.getMessage());
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		} finally {
			try {
				List<Usuario> lista = usuarioModel.listaUsuarioClasificadores();
				salida.put("lista", lista);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	@Action(value="/eliminaCrudUsuario", results = { @Result(name = SUCCESS, type = "json") })
	public String elimina(){
		log.info("En eliminar aspecto");
		try {
			usuarioModel.eliminaUsuario(Integer.parseInt(id),Integer.parseInt(idEstado));
			lista =  usuarioModel.consultaUsuarioLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/actualizaCrudUsuario", results = { @Result(name = SUCCESS, type = "json") })
	public String actualiza(){
		log.info("En actualizar aspecto");	
		try {
			usuarioModel.actualizaUsuario(usuario);
			lista =  usuarioModel.consultaUsuarioLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value="/registraCrudUsuario", results = { @Result(name = SUCCESS, type = "json") })
	public String registra(){
		log.info("En registrar aspecto");		
		try {
			usuarioModel.insertaUsuario(usuario);
			lista =  usuarioModel.consultaUsuarioLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/consultaSedePorNegocio", results = { @Result(name = SUCCESS, type = "json") })
	public String consultaSedePorNegocio(){
		log.info("En listar consultaSedePorNegocio");	
		try {
			listaSede =  mensajeModel.listaSedesPorNegocio(idNegocio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/consultaUsuarioLike", results = { @Result(name = SUCCESS, type = "json") })
	public String consultaUsuarioLike(){
		log.info("En consultaUsuarioLike");
		try {
			lista =  usuarioModel.consultaUsuarioLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}



