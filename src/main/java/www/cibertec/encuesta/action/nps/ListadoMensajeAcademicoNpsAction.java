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
import www.cibertec.encuesta.entidad.nps.ReporteListaMensajeAcademicoNps;
import www.cibertec.encuesta.model.NpsModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class ListadoMensajeAcademicoNpsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int idSede;
	private String fileName;
	private int fileSize;
	private InputStream inputStream;

	@Action(value = "/listadoMensajeAcademicoNpsExcel", results = {
			@Result(name = SUCCESS, type = "stream", params = {
			"contentType", "application/vnd.ms-excel", 
			"inputName", "inputStream", 
			"contentDisposition","attachment;filename=${fileName}",
			"contentLength", "${fileSize}", }) })
	public String listadoExtensionNpsExcel() {
		log.info("En metodo: listadoExtensionNpsExcel");

		NpsModel model = new NpsModel();

		String[] columnText_sheet_1 = { "Negocio","Campus","Ciudad", "Carrera",
										"Facultad","Ciclo","Tipo estudiante", "Modalidad", "ID Alumno",
										"Id Mensaje","Comentario","NPS","Pregunta",
										"Aspecto","Tema" ,"Tipo","Valoración", "Usuario"};

		int[] columnWith_sheet_1 = { 3000, 6000, 6000, 15000, 
									 15000, 4000, 6000,4000, 6000, 
									 6000,20000, 3000 , 15000, 
									 10000 , 15000, 4000, 3000, 10000};

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date d = new Date();

			fileName = "Listado_Valoracion_Mensaje_NPS_Academico_" + sdf.format(d) + ".xlsx";
			log.info("fileName : " + fileName);

			String titulo_1 = "Listado de valoración de mensaje NPS Académico";

			String titulo_Sheet_1 = "Valoración";

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

			List<ReporteListaMensajeAcademicoNps> lista = model.listaMensajeNpsAcademico();

			log.info("lista.sise : " + lista.size());
			ReporteListaMensajeAcademicoNps bean = null;
			XSSFRow filaX = null;
			for (int i = 0; i < lista.size(); i++) {
				bean = lista.get(i);
				filaX = hoja.createRow(i + 3);
				
				XSSFCell celda0 = filaX.createCell(0);
				celda0.setCellStyle(estiloCeldaCentradoSimple);
				celda0.setCellValue(bean.getNegocio());
				
				filaX.createCell(1).setCellValue(bean.getCampus());
				filaX.createCell(2).setCellValue(bean.getCiudad());
				filaX.createCell(3).setCellValue(bean.getCarrera());
				
				filaX.createCell(4).setCellValue(bean.getFacultad());
			
				XSSFCell celda5 = filaX.createCell(5);
				celda5.setCellStyle(estiloCeldaCentradoSimple);
				celda5.setCellValue(bean.getCiclo());
				
				XSSFCell celda6 = filaX.createCell(6);
				celda6.setCellStyle(estiloCeldaCentradoSimple);
				celda6.setCellValue(bean.getTipoEstudiante());
				
				XSSFCell celda7 = filaX.createCell(7);
				celda7.setCellStyle(estiloCeldaCentradoSimple);
				celda7.setCellValue(bean.getModalidad());
				
				XSSFCell celda8 = filaX.createCell(8);
				celda8.setCellStyle(estiloCeldaCentradoSimple);
				celda8.setCellValue(bean.getIdAlumno());
				
				XSSFCell celda9 = filaX.createCell(9);
				celda9.setCellStyle(estiloCeldaCentradoSimple);
				celda9.setCellValue(bean.getIdMensaje());
				
				filaX.createCell(10).setCellValue(bean.getComentario());
				
				XSSFCell celda11 = filaX.createCell(11);
				celda11.setCellStyle(estiloCeldaCentradoSimple);
				celda11.setCellValue(bean.getNps());
				
				filaX.createCell(12).setCellValue(bean.getPregunta());
				
				filaX.createCell(13).setCellValue(bean.getAspecto());
				filaX.createCell(14).setCellValue(bean.getTema());
				filaX.createCell(15).setCellValue(bean.getTipo());
				filaX.createCell(16).setCellValue(bean.getValoracion());
				filaX.createCell(17).setCellValue(bean.getUsuario());
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
