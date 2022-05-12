package www.cibertec.encuesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.mensaje.Asignacion;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class AsignacionModel {

	
	public List<Asignacion> consultaAsignacion(int idSede) throws Exception {
		ArrayList<Asignacion> lista = new ArrayList<Asignacion>();
		Connection conn = null;
		PreparedStatement pstm = null;
		Asignacion objAsignacion = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select a.*,s.nombre,u.nombre from asignacion a inner join sede s on a.idSede = s.idSede inner join usuario u on u.idUsuario = a.idUsuario where a.idSede =?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSede);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objAsignacion = new Asignacion();
				objAsignacion.setIdAsignacion(rs.getInt(1));
				objAsignacion.setIdSede(rs.getInt(2));
				objAsignacion.setIdUsuario(rs.getInt(3));
				objAsignacion.setCantidad(rs.getInt(4));
				objAsignacion.setSede(rs.getString(5));
				objAsignacion.setUsuario(rs.getString(6));
				lista.add(objAsignacion);
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
		return lista;
	}
	
	public int registraAsignacion(int idSede, int idUsuario, int cantidad)  {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2 = null, pstm3 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			
			String sql1 = "insert into asignacion values(null,?,?,?)";
			String sql2 = "select idMensaje from mensaje where idSede=?  and idUsuario = -1";
			String sql3 = "update mensaje set idUsuario =? where idMensaje=?";
			
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, idSede);
			pstm1.setInt(2, idUsuario);
			pstm1.setInt(3, cantidad);
			log.info(log);
			contador = pstm1.executeUpdate();
			
			pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, idSede);
			log.info(log);
			rs = pstm2.executeQuery();
			int c= 0;
			int idMensaje = -1;
			while(rs.next()) {
				idMensaje = rs.getInt(1);
				
				pstm3 = conn.prepareStatement(sql3);
				pstm3.setInt(1, idUsuario);
				pstm3.setInt(2, idMensaje);
				log.info(log);
				contador = pstm3.executeUpdate();
				c++;
				if (c>=cantidad) {
					break;
				}
			}
			
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pstm1 != null)pstm1.close();
				if (pstm2 != null)pstm2.close();
				if (pstm3 != null)pstm2.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	
	
	public int eliminaAsignacion(int idAsignacion, int idSede, int idUsuario)  {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null, pstm2 = null, pstm3 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			conn.setAutoCommit(false);
			
			String sql1 = "delete from asignacion where idAsignacion = ?";
			String sql2 = "select idMensaje from mensaje where idSede=?  and idUsuario = ?";
			String sql3 = "update mensaje set idUsuario =-1 where idMensaje=?";
			
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setInt(1, idAsignacion);
			log.info(pstm1);
			contador = pstm1.executeUpdate();
			
			
			pstm2 = conn.prepareStatement(sql2);
			pstm2.setInt(1, idSede);
			pstm2.setInt(2, idUsuario);
			log.info(log);
			rs = pstm2.executeQuery();
			int idMensaje = -1;
			while(rs.next()) {
				idMensaje = rs.getInt(1);
				pstm3 = conn.prepareStatement(sql3);
				pstm3.setInt(1, idMensaje);
				log.info(log);
				contador = pstm3.executeUpdate();
			}

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.info(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (pstm1 != null)pstm1.close();
				if (pstm2 != null)pstm2.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
}
