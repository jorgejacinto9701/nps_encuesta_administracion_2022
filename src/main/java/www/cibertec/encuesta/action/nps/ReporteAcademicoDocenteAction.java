package www.cibertec.encuesta.action.nps;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import www.cibertec.encuesta.entidad.nps.ReporteAcademicoDocente;
import www.cibertec.encuesta.entidad.nps.Respuesta;
import www.cibertec.encuesta.model.NpsModel;
import www.cibertec.encuesta.util.Constantes;
import www.cibertec.encuesta.util.JLibreria;

@ParentPackage("paquete_cibertec")
@CommonsLog
public class ReporteAcademicoDocenteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private @Getter @Setter int idSede;
	private @Getter @Setter String fileName;
	private @Getter @Setter int fileSize;
	private @Getter @Setter InputStream inputStream;

	@Action(value = "/reporteAcademicoDocenteExcel", results = {
			@Result(name = SUCCESS, type = "stream", params = { 
					"contentType", "application/vnd.ms-excel", 
					"inputName", "inputStream", 
					"contentDisposition", "attachment;filename=${fileName}",
					"contentLength","${fileSize}",
					}) })
	public String listaReporte() {
		log.info("En metodo: listaReporte");

		NpsModel model = new NpsModel();

		String[] columnText_sheet_1 = { 
				"Sede", "Curso", "Nombre el curso", "Nombre del docente","Principal",
				"Sección", "Grupo", "Modalidad", "Tipo de Clase", "Encuesta", "Alumno","Promedio", 
				"P1", "P2", "P3", "P4", "P5", "P6", "P7",
				"P8", "P9", "P10", "P11", "P12", "P13","P14","P15", 
				"Preg. Laureate 1", "Preg. Laureate 2", "Preg. Laureate 3",	"Preg. Laureate 4",	"Comentario" };
		
		int[] columnWith_sheet_1 = { 
				4000, 4000, 15000, 15000, 4000,
				4000, 4000, 5000, 5000,5000, 4000, 4000, 
				2000, 2000, 2000, 2000, 2000, 2000,	2000, 
				2000, 2000, 2000, 2000, 2000, 2000, 2000,2000, 
				6000, 6000, 6000, 6000, 40000 };
		
		String[] columnText_sheet_2 = { 
				"Id Horario","Sede", "Curso", "Nombre el curso", "Nombre del docente","Principal",
				"Sección", "Grupo", "Modalidad", "Tipo de Clase",  "Encuesta", 
				"Universo", "Encuestados", "Encuestados(%)","Promedio" };
		
		int[] columnWith_sheet_2 = { 
				4000,5000, 4000, 15000, 15000, 4000,
				4000, 4000, 5000, 5000,5000,
				4000, 4000 , 4000, 4000};
		
		String[] columnText_sheet_3 = { 
				"Id Horario", "Curso", "Nombre el curso", "Nombre del docente","Principal",
				"Grupo", "Modalidad", "Tipo de Clase",  "Encuesta", 
				"Universo", "Encuestados", "Encuestados(%)","Promedio" };
		
		int[] columnWith_sheet_3 = { 
				4000, 4000, 15000, 15000, 4000,
				4000, 5000, 5000,5000,
				4000, 4000 , 4000, 4000};
		
		

		String[] columnText_sheet_4_5 = { 
				"Nombre del docente","Promedio" };
		
		int[] columnWith_sheet_4_5  = { 
				20000,  4000};
		
		try {
			String sedeName = "Todas las Sedes";
			if (idSede != -1) {
				sedeName = model.buscaSede(idSede).getNombre();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
			Date d = new Date();

			fileName = "Reporte_Academico_" + JLibreria.toTextoConLinea(sedeName) + "_" + sdf.format(d) + ".xlsx";
			log.info("fileName : " + fileName );
			
		
			String titulo_1 = "Reporte Académico en " + sedeName + " - Total de encuestas Rendidas";
			String titulo_2 = "Reporte Académico en " + sedeName + " - Promedio de Horarios Totales";
			String titulo_3 = "Reporte Académico en " + sedeName + " - Promedio de Horarios Compactados";
			String titulo_3_1 = "Reporte Académico en " + sedeName + " - Promedio de Horarios Compactados con mínimo 25% y 2 encuestados";
			String titulo_4 = "Reporte Académico en " + sedeName + " - Promedio de Docentes con todas las encuestas";
			String titulo_5 = "Reporte Académico en " + sedeName + " - Promedio de Docentes con mínimo 25% y 2 encuestados";
			
			String titulo_Sheet_1 = "Total Encuestas";
			String titulo_Sheet_2 = "Horarios totales";
			String titulo_Sheet_3 = "Horarios Compactados";
			String titulo_Sheet_3_1 = "Horarios Válidos";
			String titulo_Sheet_4 = "Docentes Totales";
			String titulo_Sheet_5 = "Docentes Válidos";
			
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

			{
				//SHEET 1
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_1);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_1.length-1));

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

				List<Respuesta> lista = model.listaRespuestaPorSede(idSede);
				
				log.info("lista.sise : " + lista.size() );
				Respuesta bean = null;
				XSSFRow filaX = null;
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					filaX = hoja.createRow(i + 3);
					filaX.createCell(0).setCellValue(bean.getHorario().getSede().getNombre());
					filaX.createCell(1).setCellValue(bean.getHorario().getCurso().getIdCurso());
					filaX.createCell(2).setCellValue(bean.getHorario().getCurso().getNombre());
					filaX.createCell(3).setCellValue(bean.getHorario().getDocente().getNombres());
					filaX.createCell(4).setCellValue(bean.getHorario().getPrincipal());
					filaX.createCell(5).setCellValue(bean.getHorario().getSeccion());
					filaX.createCell(6).setCellValue(bean.getHorario().getGrupo());
					filaX.createCell(7).setCellValue(bean.getHorario().getModalidad());
					filaX.createCell(8).setCellValue(bean.getHorario().getTipoclase());
					filaX.createCell(9).setCellValue(bean.getHorario().getEncuesta().getNombre());
					filaX.createCell(10).setCellValue(bean.getAlumno().getIdAlumno());
					filaX.createCell(11).setCellValue(bean.getPfinal());
					filaX.createCell(12).setCellValue(bean.getPreguntas()[0]);
					filaX.createCell(13).setCellValue(bean.getPreguntas()[1]);
					filaX.createCell(14).setCellValue(bean.getPreguntas()[2]);
					filaX.createCell(15).setCellValue(bean.getPreguntas()[3]);
					filaX.createCell(16).setCellValue(bean.getPreguntas()[4]);
					filaX.createCell(17).setCellValue(bean.getPreguntas()[5]);
					filaX.createCell(18).setCellValue(bean.getPreguntas()[6]);
					filaX.createCell(19).setCellValue(bean.getPreguntas()[7]);
					filaX.createCell(20).setCellValue(bean.getPreguntas()[8]);
					filaX.createCell(21).setCellValue(bean.getPreguntas()[9]);
					filaX.createCell(22).setCellValue(bean.getPreguntas()[10]);
					filaX.createCell(23).setCellValue(bean.getPreguntas()[11]);
					filaX.createCell(24).setCellValue(bean.getPreguntas()[12]);
					filaX.createCell(25).setCellValue(bean.getPreguntas()[13]);
					filaX.createCell(26).setCellValue(bean.getPreguntas()[14]);
					filaX.createCell(27).setCellValue(bean.getPregunta01());
					filaX.createCell(28).setCellValue(bean.getPregunta02());
					filaX.createCell(29).setCellValue(bean.getPregunta03());
					filaX.createCell(30).setCellValue(bean.getPregunta04());
					filaX.createCell(31).setCellValue(bean.getComentario());
					
				}

			}

			{
				//SHEET 2
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_2);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_2.length-1));

				for (int i = 0; i < columnWith_sheet_2.length; i++) {
					hoja.setColumnWidth(i, columnWith_sheet_2[i]);
				}
				
				// FILA 0: Se crea las cabecera
				XSSFRow fila1 = hoja.createRow(0);
				XSSFCell celAuxs = fila1.createCell(0);
				celAuxs.setCellStyle(estiloCeldaIzquierda);
				celAuxs.setCellValue(titulo_2);

				// FILA 2: Se crea la filaen blanco
				XSSFRow fila2 = hoja.createRow(1);
				XSSFCell celAuxs2 = fila2.createCell(0);
				celAuxs2.setCellValue("");

				// FILA 2: Se crea las columnas
				XSSFRow fila3 = hoja.createRow(2);

				for (int i = 0; i < columnText_sheet_2.length; i++) {
					XSSFCell celda1 = fila3.createCell(i);
					celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
					celda1.setCellStyle(estiloCeldaCentrado);
					celda1.setCellValue(columnText_sheet_2[i]);
				}

				// FILA 3...n: Se crea las filas
				XSSFCellStyle celdaCentrar = excel.createCellStyle();
				celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				
				List<ReporteAcademicoDocente> lista = model.listaPromedioSeccionesPorSede(idSede,Constantes.TOPE_ENCUESTAS_TOTALES);
				
				log.info("lista.sise : " + lista.size() );
				ReporteAcademicoDocente bean = null;
				XSSFRow filaX = null;
				XSSFCell celProPor = null;
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					filaX = hoja.createRow(i + 3);
					filaX.createCell(0).setCellValue(bean.getIdHorario());
					filaX.createCell(1).setCellValue(bean.getSede());
					filaX.createCell(2).setCellValue(bean.getIdCurso());
					filaX.createCell(3).setCellValue(bean.getCurso());
					filaX.createCell(4).setCellValue(bean.getDocente());
					filaX.createCell(5).setCellValue(bean.getPrincipal());
					filaX.createCell(6).setCellValue(bean.getSeccion());
					filaX.createCell(7).setCellValue(bean.getGrupo());
					filaX.createCell(8).setCellValue(bean.getModalidad());
					filaX.createCell(9).setCellValue(bean.getTipoclase());
					filaX.createCell(10).setCellValue(bean.getEncuesta());
					filaX.createCell(11).setCellValue(bean.getUniverso());
					filaX.createCell(12).setCellValue(bean.getEncuestados());
					
					celProPor = filaX.createCell(13);
					celProPor.setCellValue(bean.getPorcentaje());
					celProPor.setCellStyle(estiloPorcentaje);
					
					
					filaX.createCell(14).setCellValue(bean.getPromedio());
				}

			}
			
			{
				//SHEET 3
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_3);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_3.length-1));

				for (int i = 0; i < columnWith_sheet_3.length; i++) {
					hoja.setColumnWidth(i, columnWith_sheet_3[i]);
				}
				
				// FILA 0: Se crea las cabecera
				XSSFRow fila1 = hoja.createRow(0);
				XSSFCell celAuxs = fila1.createCell(0);
				celAuxs.setCellStyle(estiloCeldaIzquierda);
				celAuxs.setCellValue(titulo_3);

				// FILA 2: Se crea la filaen blanco
				XSSFRow fila2 = hoja.createRow(1);
				XSSFCell celAuxs2 = fila2.createCell(0);
				celAuxs2.setCellValue("");

				// FILA 2: Se crea las columnas
				XSSFRow fila3 = hoja.createRow(2);

				for (int i = 0; i < columnText_sheet_3.length; i++) {
					XSSFCell celda1 = fila3.createCell(i);
					celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
					celda1.setCellStyle(estiloCeldaCentrado);
					celda1.setCellValue(columnText_sheet_3[i]);
				}

				// FILA 3...n: Se crea las filas
				XSSFCellStyle celdaCentrar = excel.createCellStyle();
				celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				List<ReporteAcademicoDocente> listaTotal = model.listaPromedioSeccionesPorSede(idSede,  Constantes.TOPE_ENCUESTAS_TOTALES);
				List<ReporteAcademicoDocente> lista = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> listaPrincipales = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> principales = new ArrayList<ReporteAcademicoDocente>(); 
		
				for (ReporteAcademicoDocente x : listaTotal) {
					if (x.getSeccion().equals(x.getPrincipal())) {
						principales.add(x);
						listaPrincipales.add(x);
					}
				}	
				
				int encuestados = 0;
				int universo = 0;
				double sp = 0;
				double porcentaje = 0;
				externo:for (ReporteAcademicoDocente p : principales) {
					encuestados = 0;
					sp = 0.0;
					universo = 0;
					porcentaje = 0.0;
					
					for (ReporteAcademicoDocente r1 : listaTotal) {
						if (p.getTipoclase() == null || r1.getTipoclase() == null) {
							break externo;
						}
						if ( p.getIdCurso().equals(r1.getIdCurso()) 
								&&  p.getPrincipal().equals(r1.getPrincipal()) 
								&&  p.getTipoclase().equals(r1.getTipoclase()) 
								&&  p.getDocente().equals(r1.getDocente()) ) {
							universo += r1.getUniverso();
							encuestados += r1.getEncuestados();
							sp += (r1.getEncuestados() * r1.getPromedio());
						}
					}
					porcentaje = Double.parseDouble(JLibreria.toDosDigitos( encuestados*1.0/universo));
					for (ReporteAcademicoDocente r2 : listaPrincipales) {
						if (p.equals(r2)) {
							r2.setEncuestados(encuestados);
							r2.setPromedio(Double.parseDouble(JLibreria.toDosDigitos( (sp / encuestados))));
							r2.setUniverso(universo);
							r2.setPorcentaje(porcentaje);
							break;
						}
					}

				}
				lista.addAll(listaPrincipales);
				
				log.info("lista.sise : " + lista.size() );
				ReporteAcademicoDocente bean = null;
				XSSFRow filaX = null;
				XSSFCell celProPor = null;
						
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					filaX = hoja.createRow(i + 3);
					filaX.createCell(0).setCellValue(bean.getIdHorario());
					filaX.createCell(1).setCellValue(bean.getIdCurso());
					filaX.createCell(2).setCellValue(bean.getCurso());
					filaX.createCell(3).setCellValue(bean.getDocente());
					filaX.createCell(4).setCellValue(bean.getPrincipal());
					filaX.createCell(5).setCellValue(bean.getGrupo());
					filaX.createCell(6).setCellValue(bean.getModalidad());
					filaX.createCell(7).setCellValue(bean.getTipoclase());
					filaX.createCell(8).setCellValue(bean.getEncuesta());
					filaX.createCell(9).setCellValue(bean.getUniverso());
					filaX.createCell(10).setCellValue(bean.getEncuestados());
					
					celProPor = filaX.createCell(11);
					celProPor.setCellValue(bean.getPorcentaje());
					celProPor.setCellStyle(estiloPorcentaje);
					
					filaX.createCell(12).setCellValue(bean.getPromedio());
				}

			}
			
			
			{
				//SHEET 4
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_3_1);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_3.length-1));

				for (int i = 0; i < columnWith_sheet_3.length; i++) {
					hoja.setColumnWidth(i, columnWith_sheet_3[i]);
				}
				
				// FILA 0: Se crea las cabecera
				XSSFRow fila1 = hoja.createRow(0);
				XSSFCell celAuxs = fila1.createCell(0);
				celAuxs.setCellStyle(estiloCeldaIzquierda);
				celAuxs.setCellValue(titulo_3_1);

				// FILA 2: Se crea la filaen blanco
				XSSFRow fila2 = hoja.createRow(1);
				XSSFCell celAuxs2 = fila2.createCell(0);
				celAuxs2.setCellValue("");

				// FILA 2: Se crea las columnas
				XSSFRow fila3 = hoja.createRow(2);

				for (int i = 0; i < columnText_sheet_3.length; i++) {
					XSSFCell celda1 = fila3.createCell(i);
					celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
					celda1.setCellStyle(estiloCeldaCentrado);
					celda1.setCellValue(columnText_sheet_3[i]);
				}

				// FILA 3...n: Se crea las filas
				XSSFCellStyle celdaCentrar = excel.createCellStyle();
				celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				List<ReporteAcademicoDocente> listaTotal = model.listaPromedioSeccionesPorSede(idSede,  Constantes.TOPE_ENCUESTAS_TOTALES);
				List<ReporteAcademicoDocente> lista = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> listaPrincipales = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> principales = new ArrayList<ReporteAcademicoDocente>(); 
		
				for (ReporteAcademicoDocente x : listaTotal) {
					if (x.getSeccion().equals(x.getPrincipal())) {
						principales.add(x);
						listaPrincipales.add(x);
					}
				}	
				
				int encuestados = 0;
				int universo = 0;
				double sp = 0;
				double porcentaje = 0;
				for (ReporteAcademicoDocente p : principales) {
					encuestados = 0;
					sp = 0.0;
					universo = 0;
					porcentaje = 0.0;
					
					for (ReporteAcademicoDocente r1 : listaTotal) {
						if ( p.getIdCurso().equals(r1.getIdCurso()) &&  p.getPrincipal().equals(r1.getPrincipal()) &&  p.getTipoclase().equals(r1.getTipoclase()) &&  p.getDocente().equals(r1.getDocente()) ) {
							universo += r1.getUniverso();
							encuestados += r1.getEncuestados();
							sp += (r1.getEncuestados() * r1.getPromedio());
						}
					}
					porcentaje = Double.parseDouble(JLibreria.toDosDigitos( encuestados*1.0/universo));
					for (ReporteAcademicoDocente r2 : listaPrincipales) {
						if (p.equals(r2)) {
							r2.setEncuestados(encuestados);
							r2.setPromedio(Double.parseDouble(JLibreria.toDosDigitos( (sp / encuestados))));
							r2.setUniverso(universo);
							r2.setPorcentaje(porcentaje);
							break;
						}
					}

				}
				
				ArrayList<ReporteAcademicoDocente> listaPrincipalesValidos = new ArrayList<ReporteAcademicoDocente>();
				for (ReporteAcademicoDocente x : listaPrincipales) {
					if (x.getPorcentaje()>=0.25 &&  x.getEncuestados()>1) {
						listaPrincipalesValidos.add(x);	
					}
				}
				
				lista.addAll(listaPrincipalesValidos);
				
				log.info("lista.sise : " + lista.size() );
				ReporteAcademicoDocente bean = null;
				XSSFRow filaX = null;
				XSSFCell celProPor = null;
						
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					filaX = hoja.createRow(i + 3);
					filaX.createCell(0).setCellValue(bean.getIdHorario());
					filaX.createCell(1).setCellValue(bean.getIdCurso());
					filaX.createCell(2).setCellValue(bean.getCurso());
					filaX.createCell(3).setCellValue(bean.getDocente());
					filaX.createCell(4).setCellValue(bean.getPrincipal());
					filaX.createCell(5).setCellValue(bean.getGrupo());
					filaX.createCell(6).setCellValue(bean.getModalidad());
					filaX.createCell(7).setCellValue(bean.getTipoclase());
					filaX.createCell(8).setCellValue(bean.getEncuesta());
					filaX.createCell(9).setCellValue(bean.getUniverso());
					filaX.createCell(10).setCellValue(bean.getEncuestados());
					
					celProPor = filaX.createCell(11);
					celProPor.setCellValue(bean.getPorcentaje());
					celProPor.setCellStyle(estiloPorcentaje);
					
					filaX.createCell(12).setCellValue(bean.getPromedio());
				}

			}
			
			
			{
				//SHEET 5
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_4);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_4_5.length-1));

				for (int i = 0; i < columnWith_sheet_4_5.length; i++) {
					hoja.setColumnWidth(i, columnWith_sheet_4_5[i]);
				}
				
				// FILA 0: Se crea las cabecera
				XSSFRow fila1 = hoja.createRow(0);
				XSSFCell celAuxs = fila1.createCell(0);
				celAuxs.setCellStyle(estiloCeldaIzquierda);
				celAuxs.setCellValue(titulo_4);

				// FILA 2: Se crea la filaen blanco
				XSSFRow fila2 = hoja.createRow(1);
				XSSFCell celAuxs2 = fila2.createCell(0);
				celAuxs2.setCellValue("");

				// FILA 2: Se crea las columnas
				XSSFRow fila3 = hoja.createRow(2);

				for (int i = 0; i < columnText_sheet_4_5.length; i++) {
					XSSFCell celda1 = fila3.createCell(i);
					celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
					celda1.setCellStyle(estiloCeldaCentrado);
					celda1.setCellValue(columnText_sheet_4_5[i]);
				}

				// FILA 3...n: Se crea las filas
				XSSFCellStyle celdaCentrar = excel.createCellStyle();
				celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				List<ReporteAcademicoDocente> lista = model.listaPromedioDocentes(Constantes.TOPE_ENCUESTAS_TOTALES);
				
				log.info("lista.sise : " + lista.size() );
				ReporteAcademicoDocente bean = null;
				XSSFRow filaX = null;
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					if (bean.getPromedio() != 0) {
						filaX = hoja.createRow(i + 3);
						filaX.createCell(0).setCellValue(bean.getDocente());
						filaX.createCell(1).setCellValue(bean.getPromedio());	
					}
					
				}

			}
			
			{
				//SHEET 6
				XSSFSheet hoja = excel.createSheet(titulo_Sheet_5);
				hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, columnWith_sheet_4_5.length-1));

				for (int i = 0; i < columnWith_sheet_4_5.length; i++) {
					hoja.setColumnWidth(i, columnWith_sheet_4_5[i]);
				}
				
				// FILA 0: Se crea las cabecera
				XSSFRow fila1 = hoja.createRow(0);
				XSSFCell celAuxs = fila1.createCell(0);
				celAuxs.setCellStyle(estiloCeldaIzquierda);
				celAuxs.setCellValue(titulo_5);

				// FILA 2: Se crea la filaen blanco
				XSSFRow fila2 = hoja.createRow(1);
				XSSFCell celAuxs2 = fila2.createCell(0);
				celAuxs2.setCellValue("");

				// FILA 2: Se crea las columnas
				XSSFRow fila3 = hoja.createRow(2);

				for (int i = 0; i < columnText_sheet_4_5.length; i++) {
					XSSFCell celda1 = fila3.createCell(i);
					celda1.setCellType(XSSFCell.CELL_TYPE_STRING);
					celda1.setCellStyle(estiloCeldaCentrado);
					celda1.setCellValue(columnText_sheet_4_5[i]);
				}

				// FILA 3...n: Se crea las filas
				XSSFCellStyle celdaCentrar = excel.createCellStyle();
				celdaCentrar.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				celdaCentrar.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				log.info("idSede : " + idSede );
				
				
				List<ReporteAcademicoDocente> listaTotal = model.listaPromedioSeccionesPorSede(idSede,  Constantes.TOPE_ENCUESTAS_TOTALES);
				List<ReporteAcademicoDocente> lista = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> listaDocentes = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> listaSeparados = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> listaPrincipales = new ArrayList<ReporteAcademicoDocente>();
				List<ReporteAcademicoDocente> principales = new ArrayList<ReporteAcademicoDocente>(); 
		
				for (ReporteAcademicoDocente x : listaTotal) {
					if (x.getSeccion().equals(x.getPrincipal())) {
						principales.add(x);
						listaPrincipales.add(x);
					}
				}	
				
				int encuestados = 0;
				int universo = 0;
				double sp = 0;
				double porcentaje = 0;
				for (ReporteAcademicoDocente p : principales) {
					encuestados = 0;
					sp = 0.0;
					universo = 0;
					porcentaje = 0.0;
					
					for (ReporteAcademicoDocente r1 : listaTotal) {
						if ( p.getIdCurso().equals(r1.getIdCurso()) &&  p.getPrincipal().equals(r1.getPrincipal()) &&  p.getTipoclase().equals(r1.getTipoclase()) &&  p.getDocente().equals(r1.getDocente()) ) {
							universo += r1.getUniverso();
							encuestados += r1.getEncuestados();
							sp += (r1.getEncuestados() * r1.getPromedio());
						}
					}
					porcentaje = Double.parseDouble(JLibreria.toDosDigitos( encuestados*1.0/universo));
					for (ReporteAcademicoDocente r2 : listaPrincipales) {
						if (p.equals(r2)) {
							r2.setEncuestados(encuestados);
							r2.setPromedio(Double.parseDouble(JLibreria.toDosDigitos( (sp / encuestados))));
							r2.setUniverso(universo);
							r2.setPorcentaje(porcentaje);
							break;
						}
					}

				}
				
				
				for (ReporteAcademicoDocente y : listaPrincipales) {
					porcentaje = y.getEncuestados()*1.0/ y.getUniverso();
					if (porcentaje>=0.25 && y.getEncuestados()>1) {
						listaSeparados.add(y);
					}
				}
				
				Set<String> docentes = new HashSet<String>();
				for (ReporteAcademicoDocente z : listaSeparados) {
					docentes.add(z.getDocente());
				}
				
				for (String x : docentes) {
					for (ReporteAcademicoDocente z : listaSeparados) {
						if (x.equals(z.getDocente())) {
							listaDocentes.add(z);
							break;
						}
					}
				}
				log.info("docentes.size : " + docentes.size() );
				log.info("listaDocentes.sise : " + listaDocentes.size() );
				
			  for (String x : docentes) {
					encuestados = 0;
					sp = 0.0;
					for (ReporteAcademicoDocente z : listaSeparados) {
						if (x.equals(z.getDocente())) {
							encuestados += z.getEncuestados();
							sp += (z.getEncuestados() * z.getPromedio());
						}
					}
					for (ReporteAcademicoDocente z : listaDocentes) {
						if (x.equals(z.getDocente())) {
							z.setEncuestados(encuestados);
							z.setPromedio(Double.parseDouble(JLibreria.toDosDigitos( (sp / encuestados))));
							break;
						}
					}
				}
				
			  	lista.addAll(listaDocentes);
			  	Collections.sort(lista, new Comparator<ReporteAcademicoDocente>() {
					@Override
					public int compare(ReporteAcademicoDocente o1, ReporteAcademicoDocente o2) {
							return o2.getPromedio().compareTo(o1.getPromedio());
					}
			 	});
			  	
				log.info("lista.sise : " + lista.size() );
				ReporteAcademicoDocente bean = null;
				XSSFRow filaX = null;
				for (int i = 0; i < lista.size(); i++) {
					bean = lista.get(i);
					if (bean.getPromedio() != 0) {
						filaX = hoja.createRow(i + 3);
						filaX.createCell(0).setCellValue(bean.getDocente());
						filaX.createCell(1).setCellValue(bean.getPromedio());	
					}
				}

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
			log.info("Size : " + fileSize );
	
		} catch (Exception e) {
			e.printStackTrace();
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
