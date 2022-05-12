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
import www.cibertec.encuesta.model.AspectoModel;
import www.cibertec.encuesta.model.MensajeModel;
import www.cibertec.encuesta.model.TemaModel;
import www.cibertec.encuesta.util.Constantes;

@SuppressWarnings("serial")
@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class AspectoAction extends ActionSupport{
	
	private List<Aspecto> lista= new ArrayList<Aspecto>();
	private String filtro ="";
	private Aspecto aspecto;
	private String id;
	private String idEstado;
	private File file;
	
	private AspectoModel aspectoModel = new AspectoModel();
	private TemaModel temaModel = new TemaModel();
	private MensajeModel mensajeModel = new MensajeModel();
	private Map<String, Object> salida = new HashMap<String, Object>();
	
	@Action(value="/subirPlantillaAspecto", results = { @Result(name = SUCCESS, type = "json") })
	public String subirAspecto(){
		log.info("En registrar aspecto");		
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);

			boolean noEsPrimero = false;
			Aspecto objAspecto = null;
			Negocio objNegocio = null;
			String celda = null;
			List<Aspecto> lstBeans = new ArrayList<Aspecto>();
			for (Row row : sheet) {
				if (noEsPrimero) {
					objAspecto = new Aspecto();
					for (Cell cell : row) {
						switch (cell.getColumnIndex() + 1) {
							case 1:
								celda = cell.getStringCellValue().trim();
								objAspecto.setNombre(celda);
							break;
							case 2:
								celda = cell.getStringCellValue().trim();
								int idNegocio= mensajeModel.buscaNegocioIgual(celda);
								objNegocio = new Negocio();
								objNegocio.setIdNegocio(idNegocio);
								objAspecto.setNegocio(objNegocio);
							break;
						}
					}
					lstBeans.add(objAspecto);
				}
				noEsPrimero = true;
			}

			int ingresados = 0;
			for (Aspecto  obj : lstBeans) {
				int intSalida = aspectoModel.insertaAspecto(obj);
				if (intSalida>0) {
					ingresados++;
				}
			}
			salida.put("mensaje", "Se ha ingresado " + ingresados + " aspecto(s).");
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			log.info(e.getMessage());
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		} finally {
			try {
				List<Aspecto> lista = aspectoModel.listaAspectos();
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
	
	@Action(value="/eliminaCrudAspecto", results = { @Result(name = SUCCESS, type = "json") })
	public String elimina(){
		log.info("En eliminar aspecto");
		try {
			aspectoModel.eliminaAspecto(Integer.parseInt(id),Integer.parseInt(idEstado));
			lista =  aspectoModel.consultaAspectoLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/actualizaCrudAspecto", results = { @Result(name = SUCCESS, type = "json") })
	public String actualiza(){
		log.info("En actualizar aspecto");	
		try {
			aspectoModel.actualizaAspecto(aspecto);
			lista =  aspectoModel.consultaAspectoLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action(value="/registraCrudAspecto", results = { @Result(name = SUCCESS, type = "json") })
	public String registra(){
		log.info("En registrar aspecto");		
		try {
			aspectoModel.insertaAspecto(aspecto);
			lista =  aspectoModel.consultaAspectoLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value="/consultaCrudAspecto", results = { @Result(name = SUCCESS, type = "json") })
	public String listar(){
		log.info("En listar aspecto");	
		try {
			lista =  aspectoModel.consultaAspectoLike(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
}



