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
import www.cibertec.encuesta.entidad.mensaje.Aspecto;
import www.cibertec.encuesta.entidad.mensaje.Negocio;
import www.cibertec.encuesta.entidad.mensaje.Tema;
import www.cibertec.encuesta.model.AspectoModel;
import www.cibertec.encuesta.model.MensajeModel;
import www.cibertec.encuesta.model.TemaModel;
import www.cibertec.encuesta.util.Constantes;

@SuppressWarnings("serial")
@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class TemaAction extends ActionSupport{
	
	private List<Tema> lista= new ArrayList<Tema>();
	private String filtro ="";
	private Tema tema;
	private String id;
	private String idEstado;
	private File file;
	private String idAspecto;
	
	private AspectoModel aspectoModel = new AspectoModel();
	private TemaModel temaModel = new TemaModel();
	private MensajeModel mensajeModel = new MensajeModel();
	private Map<String, Object> salida = new HashMap<String, Object>();
	
	@Action(value="/subirPlantillaTema", results = { @Result(name = SUCCESS, type = "json") })
	public String subirTema(){
		log.info("En registrar temas");		
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			String celda = null;
			boolean noEsPrimero = false;
			Tema objTema = null;
			Negocio objNegocio = null;
			Aspecto objAspecto = null;
			int idNegocio = -1;
			List<Tema> lstBeans = new ArrayList<Tema>();
			
			for (Row row : sheet) {
				if (noEsPrimero) {
					objTema = new Tema();
					for (Cell cell : row) {
						switch (cell.getColumnIndex() + 1) {
							case 1:
								celda = cell.getStringCellValue().trim();
								objTema.setNombre(celda);
							break;
							case 2:
								celda = cell.getStringCellValue().trim();
								objAspecto = new Aspecto();
								objAspecto.setNombre(celda);
								objTema.setAspecto(objAspecto);
							break;	
							case 3:
								celda = cell.getStringCellValue().trim();
								if (idNegocio == -1) {
									idNegocio= mensajeModel.buscaNegocioIgual(celda);	
								}
								objNegocio = new Negocio();
								objNegocio.setIdNegocio(idNegocio);
								objTema.getAspecto().setNegocio(objNegocio);
								objTema.setNegocio(objNegocio);
							break;
							case 4:
								celda = cell.getStringCellValue().trim();
								objTema.setDefinicion(celda);
							break;
						}
					}
					lstBeans.add(objTema);
				}
				noEsPrimero = true;
			}

			int aspectosIngresados = 0;
			for (Tema  obj : lstBeans) {
				List<Aspecto> lstAspecto =  aspectoModel.consultaAspectoIgual(obj.getAspecto().getNombre(),idNegocio);
				if(lstAspecto.isEmpty()) {
					int intAspecto = aspectoModel.insertaAspecto(obj.getAspecto());
					if (intAspecto>0) {
						aspectosIngresados++;
					}
				}
			}
			int temasIngresados = 0;
			for (Tema  obj : lstBeans) {
				List<Aspecto> lstAspecto =  aspectoModel.consultaAspectoIgual(obj.getAspecto().getNombre(),idNegocio);
				if(!lstAspecto.isEmpty()) {
					obj.getAspecto().setIdAspecto(lstAspecto.get(0).getIdAspecto());
					int intTema = temaModel.insertaTema(obj);
					if (intTema>0) {
						temasIngresados++;
					}
				}
				
			}
			salida.put("mensaje", "Se ha ingresado " + aspectosIngresados + " aspectos(s) y se ha ingresado " + temasIngresados + " tema(s).");
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			log.info(e.getMessage());
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		} finally {
			try {
				List<Tema> lista = temaModel.listaTemas();
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
	
	@Action(value="/eliminaCrudTema",results = { @Result(name = SUCCESS, type = "json") })
	public String elimina(){
		log.info("En eliminar tema");
		try {
			temaModel.eliminaTema(Integer.parseInt(id),Integer.parseInt(idEstado));
			lista =  temaModel.consultaTema(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/actualizaCrudTema",results = { @Result(name = SUCCESS, type = "json") })
	public String actualiza(){
		log.info("En actualizar tema");	
		try {
			temaModel.actualizaTema(tema);
			lista =  temaModel.consultaTema(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value="/registraCrudTema",results = { @Result(name = SUCCESS, type = "json") })
	public String registra(){
		log.info("En registrar tema");		
		try {
			temaModel.insertaTema(tema);
			lista =  temaModel.consultaTema(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/consultaCrudTema",results = { @Result(name = SUCCESS, type = "json") })
	public String listar(){
		log.info("En listar tema");	
		try {
			lista =  temaModel.consultaTema(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/cargaTemaActivosPorAspecto",results = { @Result(name = SUCCESS, type = "json") })
	public String cargaTema(){
		log.info("En listar tema");	
		try {
			lista =  temaModel.listaTemasPorAspecto(Integer.parseInt(idAspecto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
}



