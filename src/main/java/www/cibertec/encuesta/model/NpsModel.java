package www.cibertec.encuesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.nps.Alumno;
import www.cibertec.encuesta.entidad.nps.Curso;
import www.cibertec.encuesta.entidad.nps.Docente;
import www.cibertec.encuesta.entidad.nps.Encuesta;
import www.cibertec.encuesta.entidad.nps.Horario;
import www.cibertec.encuesta.entidad.nps.Opcion;
import www.cibertec.encuesta.entidad.nps.ReporteAcademicoDocente;
import www.cibertec.encuesta.entidad.nps.ReporteAvanceAcademicoNps;
import www.cibertec.encuesta.entidad.nps.ReporteAvanceExtensionNps;
import www.cibertec.encuesta.entidad.nps.ReporteListaAcademicoNps;
import www.cibertec.encuesta.entidad.nps.ReporteListaExtensionNps;
import www.cibertec.encuesta.entidad.nps.ReporteListaMensajeAcademicoNps;
import www.cibertec.encuesta.entidad.nps.ReporteListaMensajeExtensionNps;
import www.cibertec.encuesta.entidad.nps.Respuesta;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class NpsModel {
	
	public List<ReporteListaMensajeAcademicoNps> listaMensajeNpsAcademico() {
		ArrayList<ReporteListaMensajeAcademicoNps> data = new ArrayList<ReporteListaMensajeAcademicoNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_completo_mensaje_nps_academico() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteListaMensajeAcademicoNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteListaMensajeAcademicoNps();
				objRep.setNegocio(rs.getString(1));
				objRep.setCampus(rs.getString(2));
				objRep.setCiudad(rs.getString(3));
				objRep.setCarrera(rs.getString(4));
				
				objRep.setFacultad(rs.getString(5));
				objRep.setCiclo(rs.getString(6));
				objRep.setTipoEstudiante(rs.getString(7));
				objRep.setModalidad(rs.getString(8));
				objRep.setIdAlumno(rs.getString(9));
				
				objRep.setIdMensaje(rs.getString(10));
				objRep.setComentario(rs.getString(11));
				objRep.setNps(rs.getInt(12));
				objRep.setPregunta(rs.getString(13));
				
				objRep.setAspecto(rs.getString(14));
				objRep.setTema(rs.getString(15));
				objRep.setTipo(rs.getString(16));
				objRep.setValoracion(rs.getString(17));
				objRep.setUsuario(rs.getString(18));
				
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
	
	
	public List<ReporteListaMensajeExtensionNps> listaMensajeNpsExtension() {
		ArrayList<ReporteListaMensajeExtensionNps> data = new ArrayList<ReporteListaMensajeExtensionNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_completo_mensaje_nps_extension() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteListaMensajeExtensionNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteListaMensajeExtensionNps();
				objRep.setNegocio(rs.getString(1));
				objRep.setCampus(rs.getString(2));
				objRep.setEscuela(rs.getString(3));
				objRep.setFamilia(rs.getString(4));
				objRep.setCurso(rs.getString(5));
				objRep.setModalidad(rs.getString(6));
				objRep.setIdAlumno(rs.getString(7));
				objRep.setIdMensaje(rs.getString(8));
				objRep.setComentario(rs.getString(9));
				objRep.setNps(rs.getInt(10));
				objRep.setPregunta(rs.getString(11));
				objRep.setAspecto(rs.getString(12));
				objRep.setTema(rs.getString(13));
				objRep.setTipo(rs.getString(14));
				objRep.setValoracion(rs.getString(15));
				objRep.setUsuario(rs.getString(16));
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
	

	public List<ReporteListaExtensionNps> listaAlumnposNpsExtension() {
		ArrayList<ReporteListaExtensionNps> data = new ArrayList<ReporteListaExtensionNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_completo_nps_extension() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteListaExtensionNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteListaExtensionNps();
				objRep.setIdAlumno(rs.getString(1));
				objRep.setNps(rs.getInt(2));
				objRep.setComentario(rs.getString(3));
				objRep.setCampus(rs.getString(4));
				objRep.setCurso(rs.getString(5));
				objRep.setFamilia(rs.getString(6));
				objRep.setEscuela(rs.getString(7));
				objRep.setModalidad(rs.getString(8));
				objRep.setFechaRegistro(rs.getString(9));
				objRep.setEstado(rs.getString(10));
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
	
	public List<ReporteListaAcademicoNps> listaAlumnposNpsAcademico() {
		ArrayList<ReporteListaAcademicoNps> data = new ArrayList<ReporteListaAcademicoNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_completo_nps_academico() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteListaAcademicoNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteListaAcademicoNps();
				objRep.setIdAlumno(rs.getString(1));
				objRep.setNps(rs.getInt(2));
				objRep.setComentario(rs.getString(3));
				objRep.setCampus(rs.getString(4));
				objRep.setCiudad(rs.getString(5));
				objRep.setCarrera(rs.getString(6));
				objRep.setFacultad(rs.getString(7));
				objRep.setSemestre(rs.getString(8));
				objRep.setCiclo(rs.getInt(9));
				objRep.setTipoEstudiante(rs.getString(10));
				objRep.setFechaRegistro(rs.getString(11));
				objRep.setModalidad(rs.getString(12));
				objRep.setEstado(rs.getString(13));
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
	
	public List<ReporteAvanceAcademicoNps> listaReporteNpsAcademico() {

		ArrayList<ReporteAvanceAcademicoNps> data = new ArrayList<ReporteAvanceAcademicoNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_nps_academico() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteAvanceAcademicoNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteAvanceAcademicoNps();
				objRep.setSede(rs.getString(1));
				objRep.setCarrera(rs.getString(2));
				objRep.setModalidad(rs.getString(3));
				
				objRep.setUniverso(rs.getInt(4));
				objRep.setEncuestados(rs.getInt(5));
				objRep.setAvance(rs.getDouble(6));
				
				objRep.setPromotores(rs.getInt(7));
				objRep.setNeutros(rs.getInt(8));
				objRep.setDetractores(rs.getInt(9));
				
				objRep.setPromotoresPorcentaje(rs.getDouble(10));
				objRep.setNeutrosPorcentaje(rs.getDouble(11));
				objRep.setDetractoresPorcentaje(rs.getDouble(12));
				
				objRep.setNps(rs.getInt(13));
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public List<ReporteAvanceExtensionNps> listaReporteNpsExtension() {

		ArrayList<ReporteAvanceExtensionNps> data = new ArrayList<ReporteAvanceExtensionNps>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";

			sql = "{ call sp_lista_nps_extension() }";
			pstm = conn.prepareCall(sql);

			log.info(pstm);
			rs = pstm.executeQuery();

			ReporteAvanceExtensionNps objRep = null;
			while (rs.next()) {
				objRep = new ReporteAvanceExtensionNps();
				objRep.setFamilia(rs.getString(1));
				objRep.setCurso(rs.getString(2));
				
				objRep.setUniverso(rs.getInt(3));
				objRep.setEncuestados(rs.getInt(4));
				objRep.setAvance(rs.getDouble(5));
				
				objRep.setPromotores(rs.getInt(6));
				objRep.setNeutros(rs.getInt(7));
				objRep.setDetractores(rs.getInt(8));
				
				objRep.setPromotoresPorcentaje(rs.getDouble(9));
				objRep.setNeutrosPorcentaje(rs.getDouble(10));
				objRep.setDetractoresPorcentaje(rs.getDouble(11));
				
				objRep.setNps(rs.getInt(12));
				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	
	public List<Sede> listaSedes() {
		ArrayList<Sede> data = new ArrayList<Sede>();
		Sede bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from sede";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Sede();
				bean.setIdSede(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public Usuario valida(String login, String clave) {
		Usuario bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from usuario where login=? and clave =? and estado = 1 ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setClave(rs.getString(4));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return bean;
	}

	public List<Opcion> listaOpcionesPorUsuario(int idUsuario) {
		ArrayList<Opcion> data = new ArrayList<Opcion>();
		Opcion bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select o.idopcion, o.descripcion, o.tipo , o.ruta  from opcion o,acceso a, rol r, permiso p where p.idrol = r.idrol and r.idrol = a.idrol and  o.idopcion = a.idopcion and p.idusuario =? order by 2 asc";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Opcion();
				bean.setIdOpcion(rs.getInt(1));
				bean.setDescripcion(rs.getString(2));
				bean.setTipo(rs.getString(3));
				bean.setRuta(rs.getString(4));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public Sede buscaSede(int idSede) {
		Sede bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from sede where idsede = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSede);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new Sede();
				bean.setIdSede(rs.getInt(1));
				bean.setNombre(rs.getString(2));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return bean;
	}

	public List<Respuesta> listaRespuestaPorSede(int idSede) {

		ArrayList<Respuesta> data = new ArrayList<Respuesta>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";
			if (idSede == -1) {
				sql = "{ call sp_lista_encuestas_totales() }";
				pstm = conn.prepareCall(sql);
			} else {
				sql = "{ call sp_lista_encuestas_por_sede(?) }";
				pstm = conn.prepareCall(sql);
				pstm.setInt(1, idSede);
			}

			log.info(pstm);
			rs = pstm.executeQuery();
			Alumno objAlu = null;
			Encuesta objEnc = null;
			Curso objCur = null;
			Horario objHor = null;
			Sede objSed = null;
			Docente objDoc = null;
			Respuesta objRep = null;
			while (rs.next()) {
				objRep = new Respuesta();
				objAlu = new Alumno();
				objHor = new Horario();
				objEnc = new Encuesta();
				objCur = new Curso();
				objSed = new Sede();
				objDoc = new Docente();
				objSed.setNombre(rs.getString(1));
				objHor.setIdHorario(rs.getInt(2));
				objEnc.setNombre(rs.getString(3));
				objCur.setIdCurso(rs.getString(4));
				objCur.setNombre(rs.getString(5));
				objDoc.setNombres(rs.getString(6));
				objHor.setSeccion(rs.getString(7));
				objHor.setGrupo(rs.getString(8));
				objHor.setModalidad(rs.getString(9));
				objHor.setTipoclase(rs.getString(10));
				objAlu.setIdAlumno(rs.getString(11));
				objRep.setRespuesta(rs.getString(12));
				objRep.setPfinal(rs.getDouble(13));
				objRep.setPregunta01(rs.getInt(14));
				objRep.setPregunta02(rs.getInt(15));
				objRep.setPregunta03(rs.getInt(16));
				objRep.setComentario(rs.getString(17));
				objHor.setPrincipal(rs.getString(18));
				
				objHor.setCurso(objCur);
				objHor.setDocente(objDoc);
				objHor.setEncuesta(objEnc);
				objHor.setSede(objSed);

				objRep.setHorario(objHor);
				objRep.setAlumno(objAlu);

				data.add(objRep);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public List<ReporteAcademicoDocente> listaPromedioSeccionesPorSede(int idSede, double tope) {
		ArrayList<ReporteAcademicoDocente> data = new ArrayList<ReporteAcademicoDocente>();
		ReporteAcademicoDocente bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "";
			if (idSede == -1) {
				sql = "{ call sp_lista_secciones_total(?) }";
				pstm = conn.prepareCall(sql);
				pstm.setDouble(1, tope);
			} else {
				sql = "{ call sp_lista_secciones_por_sede(?,?) }";
				pstm = conn.prepareCall(sql);
				pstm.setInt(1, idSede);
				pstm.setDouble(2, tope);
			}

			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new ReporteAcademicoDocente();
				bean.setIdHorario(rs.getInt(1));
				bean.setSede(rs.getString(2));
				bean.setIdCurso(rs.getString(3));
				bean.setCurso(rs.getString(4));
				bean.setDocente(rs.getString(5));
				bean.setSeccion(rs.getString(6));
				bean.setGrupo(rs.getInt(7));
				bean.setModalidad(rs.getString(8));
				bean.setTipoclase(rs.getString(9));
				bean.setEncuesta(rs.getString(10));
				bean.setUniverso(rs.getInt(11));
				bean.setEncuestados(rs.getInt(12));
				bean.setPorcentaje(rs.getDouble(13));
				bean.setPromedio(rs.getDouble(14));
				bean.setPrincipal(rs.getString(15));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

	public List<ReporteAcademicoDocente> listaPromedioDocentes(int tope) {
		ArrayList<ReporteAcademicoDocente> data = new ArrayList<ReporteAcademicoDocente>();
		ReporteAcademicoDocente bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "{ call sp_lista_promedio_final(?) }";
			pstm = conn.prepareCall(sql);
			pstm.setInt(1, tope);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new ReporteAcademicoDocente();
				bean.setDocente(rs.getString(1));
				bean.setPromedio(rs.getDouble(2));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}

}
