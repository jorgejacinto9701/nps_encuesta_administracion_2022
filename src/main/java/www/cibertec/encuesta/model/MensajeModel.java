package www.cibertec.encuesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.mensaje.Mensaje;
import www.cibertec.encuesta.entidad.mensaje.MensajeValoracion;
import www.cibertec.encuesta.entidad.mensaje.Negocio;
import www.cibertec.encuesta.entidad.nps.Sede;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class MensajeModel {

	
	public int buscaSedeIgual(String sede, int idNegocio) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select idSede from sede where nombre = ? and idNegocio = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, sede);
			pstm.setInt(2, idNegocio);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
		return salida;
	}
	
	public int buscaNegocioIgual(String negocio) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = -1;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select idNegocio from negocio where nombre = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, negocio);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
		return salida;
	}
	
	public List<Negocio> listaNegocios() {
		ArrayList<Negocio> data = new ArrayList<Negocio>();
		Negocio bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from negocio where idNegocio >0 order by 2 asc ";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Negocio();
				bean.setIdNegocio(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
	
	public int cantidadAsignadoSede(int idSede) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = 0;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select count(*) from mensaje where idSede=? and idUsuario <> -1";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSede);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
		return salida;
	}

	public int cantidadAprobadosPorUsuarioSede(int idUsuario,int idSede) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = 0;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select count(*) from mensaje where idUsuario =? and idSede=? and estado='Clasificado';";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			pstm.setInt(2, idSede);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
		return salida;
	}
	
	
	public List<Sede> listaMensajeConsolidadoTotal(int idNegocio) {
		ArrayList<Sede> data = new ArrayList<Sede>();
		Sede bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql1 = null; 
			
			if (idNegocio>0) {
				sql1 = "SELECT m.idSede, s.nombre, n.idNegocio, n.nombre, count(m.idSede) FROM mensaje m inner join sede s on m.idSede = s.idSede inner join negocio n on n.idNegocio = s.idNegocio where n.idNegocio=? group by 1 order by 2 asc";
				pstm = conn.prepareStatement(sql1);
				pstm.setInt(1, idNegocio);	
			}else {
				sql1 = "SELECT m.idSede, s.nombre, n.idNegocio, n.nombre, count(m.idSede) FROM mensaje m inner join sede s on m.idSede = s.idSede inner join negocio n on n.idNegocio = s.idNegocio group by 1 order by 2 asc";
				pstm = conn.prepareStatement(sql1);
			}
			
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Sede();
				bean.setIdSede(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setIdNegocio(rs.getInt(3));
				bean.setNegocio(rs.getString(4));
				bean.setTotal(rs.getInt(5));
				bean.setAprobadas(cantidadAsignadoSede(rs.getInt(1)));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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
	
	public List<Sede> listaMensajeConsolidado(int idUsuario, int idNegocio) {
		ArrayList<Sede> data = new ArrayList<Sede>();
		Sede bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql1 = null; 
			
			if (idNegocio>0) {
				sql1 = "SELECT m.idSede, s.nombre, n.idNegocio, n.nombre, count(m.idSede) FROM mensaje m inner join sede s on m.idSede = s.idSede inner join negocio n on n.idNegocio = s.idNegocio where m.idUsuario =? and n.idNegocio=? group by 1 order by 2 asc";
				pstm = conn.prepareStatement(sql1);
				pstm.setInt(1, idUsuario);
				pstm.setInt(2, idNegocio);	
			}else {
				sql1 = "SELECT m.idSede, s.nombre, n.idNegocio, n.nombre, count(m.idSede) FROM mensaje m inner join sede s on m.idSede = s.idSede inner join negocio n on n.idNegocio = s.idNegocio where m.idUsuario =? group by 1 order by 2 asc";
				pstm = conn.prepareStatement(sql1);
				pstm.setInt(1, idUsuario);
			}
			
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Sede();
				bean.setIdSede(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setIdNegocio(rs.getInt(3));
				bean.setNegocio(rs.getString(4));
				bean.setTotal(rs.getInt(5));
				bean.setAprobadas(cantidadAprobadosPorUsuarioSede(idUsuario,rs.getInt(1)));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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

	public List<Mensaje> listaMensajesPorUsuarioSedeEstado(int idUsuario, int idSede, String idEstado) {
		ArrayList<Mensaje> data = new ArrayList<Mensaje>();
		Mensaje bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from mensaje where idUsuario = ? and idSede = ? and estado = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			pstm.setInt(2, idSede);
			pstm.setString(3, idEstado);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Mensaje();
				bean.setIdMensaje(rs.getInt(1));
				bean.setIdAlumno(rs.getString(2));
				bean.setMensaje(rs.getString(3));
				bean.setIdSede(rs.getInt(4));
				bean.setIdUsuario(rs.getInt(5));
				bean.setEstado(rs.getString(6));
				bean.setTipo(rs.getString(7));
				bean.setValoracion(listaMensajeValoracion(rs.getInt(1)));
				bean.setNps(rs.getInt(8));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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

	public int eliminaMensajeValoracion(MensajeValoracion obj) throws Exception {
		int contador = -1;
		int canValoraciones = 0;
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2 = null,pstm3 = null,pstm4 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			String sql1 = "delete from mensaje_valoracion where idMensaje = ? and idAspecto = ? and idTema = ?";
			String sql2 = "select count(*) from mensaje_valoracion where idMensaje = ?";
			String sql3 = "update mensaje set estado='No Clasificado', tipo='No Clasificado' where idMensaje = ?";
			String sql4 = "update mensaje set estado='Clasificado', tipo='Valido' where idMensaje = ?";
			
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, obj.getIdMensaje());
			pstm1.setInt(2, obj.getIdAspecto());
			pstm1.setInt(3, obj.getIdTema());
			log.info(pstm1);
			contador = pstm1.executeUpdate();
			
			pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, obj.getIdMensaje());
			log.info(pstm2);
			rs = pstm2.executeQuery();
			if (rs.next()) {
				canValoraciones = rs.getInt(1);
			}
			if (canValoraciones ==0) {
				pstm3 = conn.prepareStatement(sql3);
				pstm3.setInt(1, obj.getIdMensaje());
				log.info(pstm3);
				contador = pstm3.executeUpdate();
			}else {
				pstm4 = conn.prepareStatement(sql4);
				pstm4.setInt(1, obj.getIdMensaje());
				log.info(pstm4);
				contador = pstm4.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm1 != null) pstm1.close();
				if (pstm2 != null) pstm2.close();
				if (pstm3 != null) pstm3.close();
				if (pstm4 != null) pstm4.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	public int insertaMensajeValoracion(MensajeValoracion obj) throws Exception {
		int contador = -1;
		int canValoraciones = 0;
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2 = null,pstm3 = null,pstm4 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			String sql1 = "insert into mensaje_valoracion values(?,?,?,?)";
			String sql2 = "select count(*) from mensaje_valoracion where idMensaje = ?";
			String sql3 = "update mensaje set estado='No Clasificado', tipo='No Clasificado' where idMensaje = ?";
			String sql4 = "update mensaje set estado='Clasificado', tipo='Valido' where idMensaje = ?";
			
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, obj.getIdMensaje());
			pstm1.setInt(2, obj.getIdAspecto());
			pstm1.setInt(3, obj.getIdTema());
			pstm1.setString(4, obj.getStrValoracion());
			log.info(pstm1);
			contador = pstm1.executeUpdate();

			pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, obj.getIdMensaje());
			log.info(pstm2);
			rs = pstm2.executeQuery();
			if (rs.next()) {
				canValoraciones = rs.getInt(1);
			}
			if (canValoraciones ==0) {
				pstm3 = conn.prepareStatement(sql3);
				pstm3.setInt(1, obj.getIdMensaje());
				log.info(pstm3);
				contador = pstm3.executeUpdate();
			}else {
				pstm4 = conn.prepareStatement(sql4);
				pstm4.setInt(1, obj.getIdMensaje());
				log.info(pstm4);
				contador = pstm4.executeUpdate();
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm1 != null) pstm1.close();
				if (pstm2 != null) pstm2.close();
				if (pstm3 != null) pstm3.close();
				if (pstm4 != null) pstm4.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	

	
	public int actualizaMensajeValoracion(Mensaje obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update mensaje set estado=?, tipo=? where idMensaje = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getEstado());
			pstm.setString(2, obj.getTipo());
			pstm.setInt(3, obj.getIdMensaje());
			log.info(log);
			contador = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	
	public int actualizaMensajeAsignacion(Mensaje obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update mensaje set idUsuario=? where idMensaje = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, obj.getIdUsuario());
			pstm.setInt(2, obj.getIdMensaje());
			log.info(log);
			contador = pstm.executeUpdate();
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	
	public int mensajeNinguno(int idMensaje, boolean estado) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2 = null,pstm3 = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			String sql1 = "delete from  mensaje_valoracion where idMensaje = ?";
			String sql2 = "update mensaje set estado='Clasificado', tipo='NA' where idMensaje = ?";
			String sql3 = "update mensaje set estado='No Clasificado', tipo='No Clasificado' where idMensaje = ?";
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, idMensaje);
			log.info(pstm1);
			contador = pstm1.executeUpdate();
			if(estado) {
				pstm2 = conn.prepareStatement(sql2);
				pstm2.setInt(1, idMensaje);
				contador = pstm2.executeUpdate();
				log.info(pstm2);
			}else {
				pstm3 = conn.prepareStatement(sql3);
				pstm3.setInt(1, idMensaje);
				contador = pstm3.executeUpdate();
				log.info(pstm3);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pstm1 != null)pstm1.close();
				if (pstm2 != null)pstm2.close();
				if (pstm3 != null)pstm3.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	

	
	public List<MensajeValoracion> listaMensajeValoracion(int idMensaje) {
		ArrayList<MensajeValoracion> data = new ArrayList<MensajeValoracion>();
		MensajeValoracion bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "SELECT m.*, a.nombre, t.nombre  FROM mensaje_valoracion m inner join  aspecto a on m.idAspecto = a.idAspecto inner join  tema t on m.idTema = t.idTema where m.idMensaje = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idMensaje);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new MensajeValoracion();
				bean.setIdMensaje(rs.getInt(1));
				bean.setIdAspecto(rs.getInt(2));
				bean.setIdTema(rs.getInt(3));
				bean.setStrValoracion(rs.getString(4));
				bean.setStrAspecto(rs.getString(5));
				bean.setStrTema(rs.getString(6));
				data.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
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

	public List<Sede> listaSedesPorNegocio(int idNegocio) {
		ArrayList<Sede> data = new ArrayList<Sede>();
		Sede bean = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from sede where idNegocio = ? and idSede>0 order by 2 asc";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idNegocio);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Sede();
				bean.setIdSede(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				data.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
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
