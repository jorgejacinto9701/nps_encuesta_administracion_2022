package www.cibertec.encuesta.action.nps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.model.UsuarioModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class ReporteMensajeClasificadoresAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int idSede;
	private String fileName;
	private int fileSize;
	private InputStream inputStream;

	@Action(value = "/listadoMensajeClasificadoresExcel", results = {
			@Result(name = SUCCESS, type = "stream", params = {
			"contentType", "application/vnd.ms-excel", 
			"inputName", "inputStream", 
			"contentDisposition","attachment;filename=${fileName}",
			"contentLength", "${fileSize}", }) })
	public String listadoMensajeClasificadores() {
		log.info("En metodo: listadoMensajeClasificadores");

		UsuarioModel model = new UsuarioModel();

		String[] columnText_sheet_1 = { "Nombre","Login", "Clave",
										"Estado","Negocio","Sede",
										"Asignados","Clasificados", "Pendiente", "Avance"};

		int[] columnWith_sheet_1 = { 8000, 5000, 5000, 
									 5000, 6000, 6000,
									 5000 , 5000, 5000 ,5000};

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date d = new Date();

			fileName = "Listado_Avance_Clasificadores_" + sdf.format(d) + ".xlsx";
			log.info("fileName : " + fileName);

			String titulo_1 = "Listado de avance de clasificadores";

			String titulo_Sheet_1 = "Clasificadores";

			XSSFWorkbook excel = new XSSFWorkbook();

			XSSFFont fuente = excel.createFont();
			fuente.setFontHeightInPoints((short) 10);
			fuente.setFontName("Arial");
			fuente.setBold(true);
			fuente.setColor(IndexedColors.WHITE.getIndex());

			XSSFCellStyle estiloCeldaIzquierda = excel.createCellStyle();
			estiloCeldaIzquierda.setWrapText(true);
			estiloCeldaIzquierda.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			estiloCeldaIzquierda.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
			estiloCeldaIzquierda.setFont(fuente);
			estiloCeldaIzquierda.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
			estiloCeldaIzquierda.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

			XSSFCellStyle estiloCeldaCentrado = excel.createCellStyle();
			estiloCeldaCentrado.setWrapText(true);
			estiloCeldaCentrado.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			estiloCeldaCentrado.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
			estiloCeldaCentrado.setFont(fuente);
			estiloCeldaCentrado.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
			estiloCeldaCentrado.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

			XSSFCellStyle estiloCeldaCentradoSimple = excel.createCellStyle();
			estiloCeldaCentradoSimple.setWrapText(true);
			estiloCeldaCentradoSimple.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			estiloCeldaCentradoSimple.setVerticalAlignment(XSSFCellStyle.ALIGN_CENTER);
			
			// SHEET 1
			XSSFSheet hoja = excel.createSheet(titulo_Sheet_1);
			hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_1.length - 1));

			for (int i = 0; i < columnWith_sheet_1.length; i++) {
				hoja.setColumnWidth(i, columnWith_sheet_1[i]);
			}

			// FILA 0: Se crea las cabecera
			XSSFRow fila1 = hoja.createRow(0);
			XSSFCell celAuxs = fila1.createCell(0);
			celAuxs.setCellStyle(estiloCeldaIzquierda);
			celAuxs.setCellValue(titulo_1);

			// FILA 2: Se crea la filaen blanco
			XSSFRow fila2 = hoja.createRow(1);
			XSSFCell celAuxs2 = fila2.createCell(0);
			celAuxs2.setCellValue("");

			// FILA 2: Se crea las columnas
			XSSFRow fila3 = hoja.createRow(2);

			for (int i = 0; i < columnText_sheet_1.length; i++) {
				XSSFCell celda1 = fila3.createCell(i);
				celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
				celda1.setCellStyle(estiloCeldaCentrado);
				celda1.setCellValue(columnText_sheet_1[i]);
			}

			// FILA 3...n: Se crea las filas
			XSSFCellStyle celdaCentrar = excel.createCellStyle();
			celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			List<Usuario> lista = model.listaUsuarioClasificadores();

			log.info("lista.sise : " + lista.size());
			Usuario bean = null;
			XSSFRow filaX = null;
			for (int i = 0; i < lista.size(); i++) {
				bean = lista.get(i);
				filaX = hoja.createRow(i + 3);
				filaX.createCell(0).setCellValue(bean.getNombre());
				
				XSSFCell celda1 = filaX.createCell(1);
				celda1.setCellStyle(estiloCeldaCentradoSimple);
				celda1.setCellValue(bean.getLogin());

				XSSFCell celda2 = filaX.createCell(2);
				celda2.setCellStyle(estiloCeldaCentradoSimple);
				celda2.setCellValue(bean.getClave());
				
				XSSFCell celda3 = filaX.createCell(3);
				celda3.setCellStyle(estiloCeldaCentradoSimple);
				celda3.setCellValue(bean.getEstado()==1?"Activo":"Inactivo");
				
				filaX.createCell(4).setCellValue(bean.getNegocio());
				filaX.createCell(5).setCellValue(bean.getSede());
				
				XSSFCell celda6 = filaX.createCell(6);
				celda6.setCellStyle(estiloCeldaCentradoSimple);
				celda6.setCellValue(bean.getAsignados());

				XSSFCell celda7 = filaX.createCell(7);
				celda7.setCellStyle(estiloCeldaCentradoSimple);
				celda7.setCellValue(bean.getClasificados());

				XSSFCell celda8 = filaX.createCell(8);
				celda8.setCellStyle(estiloCeldaCentradoSimple);
				celda8.setCellValue(bean.getPendientes());

				XSSFCell celda9 = filaX.createCell(9);
				celda9.setCellStyle(estiloCeldaCentradoSimple);
				celda9.setCellValue(bean.getAvance());

			}
			
			HttpServletResponse response = ServletActionContext.getResponse();
     		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

			log.info("Se crearon todas las filas ");

			ByteArrayOutputStream boas = new ByteArrayOutputStream();
			excel.write(boas);

			log.info("Se construyó el archivo ");

			inputStream = new ByteArrayInputStream(boas.toByteArray());
			fileSize = inputStream.available();
			log.info("Size : " + fileSize);

		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				log.info(e.getMessage());
			}
		}

		return SUCCESS;
	}

}
