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
import www.cibertec.encuesta.entidad.nps.ReporteAvanceExtensionNps;
import www.cibertec.encuesta.model.NpsModel;

@ParentPackage("paquete_cibertec")
@CommonsLog
@Getter 
@Setter
public class ReporteExtensionNpsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int idSede;
	private String fileName;
	private int fileSize;
	private InputStream inputStream;

	@Action(value = "/reporteExtensionNpsExcel", results = {
			@Result(name = SUCCESS, type = "stream", params = {
			"contentType", "application/vnd.ms-excel", 
			"inputName", "inputStream", 
			"contentDisposition","attachment;filename=${fileName}",
			"contentLength", "${fileSize}", }) })
	public String listaReporteAcademicoNps() {
		log.info("En metodo: listaReporteAcademicoNps");

		NpsModel model = new NpsModel();

		String[] columnText_sheet_1 = { 
				"Familia", "Curso", "Universo", 
				"Encuestados", "Avance(%)", "Promotores","Neutros", 
				"Detractores","Promotores(%)", "Neutros(%)","Detractores(%)","NPS" };

		int[] columnWith_sheet_1 = { 6500, 15500, 4000,
								     4000, 4000, 4000, 4000, 
								     4000, 4000, 4000, 4000, 3000 };

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date d = new Date();

			fileName = "Reporte_NPS_Extension_" + sdf.format(d) + ".xlsx";
			log.info("fileName : " + fileName);

			String titulo = "Reporte de avance NPS Extensión ";

			String titulo_Sheet = "NPS Extensión";

			XSSFWorkbook excel = new XSSFWorkbook();

			XSSFFont fuente = excel.createFont();
			fuente.setFontHeightInPoints((short) 10);
			fuente.setFontName("Arial");
			fuente.setBold(true);
			fuente.setColor(IndexedColors.WHITE.getIndex());

			XSSFCellStyle estiloPorcentaje = excel.createCellStyle();
			estiloPorcentaje.setDataFormat(excel.createDataFormat().getFormat("0%"));

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

			// SHEET 1
			XSSFSheet hoja = excel.createSheet(titulo_Sheet);
			hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_1.length - 1));

			for (int i = 0; i < columnWith_sheet_1.length; i++) {
				hoja.setColumnWidth(i, columnWith_sheet_1[i]);
			}

			// FILA 0: Se crea las cabecera
			XSSFRow fila1 = hoja.createRow(0);
			XSSFCell celAuxs = fila1.createCell(0);
			celAuxs.setCellStyle(estiloCeldaIzquierda);
			celAuxs.setCellValue(titulo);

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

			List<ReporteAvanceExtensionNps> lista = model.listaReporteNpsExtension();

			log.info("lista.sise : " + lista.size());
			ReporteAvanceExtensionNps bean = null;
			XSSFRow filaX = null;
			XSSFCell celAvance = null,celProPor = null,celNeuPor = null,celDetPor = null;
			for (int i = 0; i < lista.size(); i++) {
				bean = lista.get(i);
				filaX = hoja.createRow(i + 3);
				filaX.createCell(0).setCellValue(bean.getFamilia());
				filaX.createCell(1).setCellValue(bean.getCurso());
				filaX.createCell(2).setCellValue(bean.getUniverso());
				filaX.createCell(3).setCellValue(bean.getEncuestados());
				
				celAvance = filaX.createCell(4);
				celAvance.setCellValue(bean.getAvance());
				celAvance.setCellStyle(estiloPorcentaje);

				filaX.createCell(5).setCellValue(bean.getPromotores());
				filaX.createCell(6).setCellValue(bean.getNeutros());
				filaX.createCell(7).setCellValue(bean.getDetractores());
				
				celProPor = filaX.createCell(8);
				celProPor.setCellValue(bean.getPromotoresPorcentaje());
				celProPor.setCellStyle(estiloPorcentaje);
				
				celNeuPor = filaX.createCell(9);
				celNeuPor.setCellValue(bean.getNeutrosPorcentaje());
				celNeuPor.setCellStyle(estiloPorcentaje);
				
				celDetPor = filaX.createCell(10);
				celDetPor.setCellValue(bean.getDetractoresPorcentaje());
				celDetPor.setCellStyle(estiloPorcentaje);
				
				filaX.createCell(11).setCellValue(bean.getNps());
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
