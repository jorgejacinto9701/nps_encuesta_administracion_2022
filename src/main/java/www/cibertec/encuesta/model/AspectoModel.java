package www.cibertec.encuesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.mensaje.Aspecto;
import www.cibertec.encuesta.entidad.mensaje.Negocio;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class AspectoModel {

	// --------------------------------------------------------
	// Aspecto
	// --------------------------------------------------------
	
	public List<Aspecto> listaAspectosActivos(int idNegocio) {
		ArrayList<Aspecto> data = new ArrayList<Aspecto>();
		Aspecto objAspecto = null;
		Negocio objNegocio = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select a.*,n.nombre from aspecto a inner join negocio n on a.idNegocio = n.idNegocio where a.estado = 1 and a.idNegocio = ? order by 2 asc";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idNegocio);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objNegocio = new Negocio();
				objNegocio.setIdNegocio(rs.getInt(4));
				objNegocio.setNombre(rs.getString(5));
				
				objAspecto = new Aspecto();
				objAspecto.setIdAspecto(rs.getInt(1));
				objAspecto.setNombre(rs.getString(2));
				objAspecto.setEstado(rs.getInt(3));
				objAspecto.setNegocio(objNegocio);
				
				data.add(objAspecto);
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

	
	public List<Aspecto> listaAspectos() {
		ArrayList<Aspecto> data = new ArrayList<Aspecto>();
		Aspecto objAspecto = null;
		Negocio objNegocio = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select a.*,n.nombre from aspecto a inner join negocio n on a.idNegocio = n.idNegocio";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objNegocio = new Negocio();
				objNegocio.setIdNegocio(rs.getInt(4));
				objNegocio.setNombre(rs.getString(5));
				
				objAspecto = new Aspecto();
				objAspecto.setIdAspecto(rs.getInt(1));
				objAspecto.setNombre(rs.getString(2));
				objAspecto.setEstado(rs.getInt(3));
				objAspecto.setNegocio(objNegocio);
				
				data.add(objAspecto);
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

	
	public int insertaAspecto(Aspecto obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "insert into aspecto values(null,?,'1',?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getNegocio().getIdNegocio());
			log.info(pstm);
			contador = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
	public int actualizaAspecto(Aspecto obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update aspecto set nombre=?, estado=?, idNegocio=? where idAspecto = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getEstado());
			pstm.setInt(3, obj.getNegocio().getIdNegocio());
			pstm.setInt(4, obj.getIdAspecto());
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
	public int eliminaAspecto(int idAspecto, int idEstado) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update aspecto set estado=? where idAspecto = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEstado);
			pstm.setInt(2, idAspecto);
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

	public List<Aspecto> consultaAspectoIgual(String filtro, int idNegocio) throws Exception {
		ArrayList<Aspecto> lista = new ArrayList<Aspecto>();
		Connection conn = null;
		Aspecto objAspecto = null;
		Negocio objNegocio = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select a.*,n.nombre from aspecto a inner join negocio n on a.idNegocio = n.idNegocio where a.nombre = ? and n.idNegocio = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro);
			pstm.setInt(2, idNegocio);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objNegocio = new Negocio();
				objNegocio.setIdNegocio(rs.getInt(4));
				objNegocio.setNombre(rs.getString(5));
				
				objAspecto = new Aspecto();
				objAspecto.setIdAspecto(rs.getInt(1));
				objAspecto.setNombre(rs.getString(2));
				objAspecto.setEstado(rs.getInt(3));
				objAspecto.setNegocio(objNegocio);
				
				lista.add(objAspecto);
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
		return lista;
	}
	public List<Aspecto> consultaAspectoLike(String filtro) throws Exception {
		ArrayList<Aspecto> lista = new ArrayList<Aspecto>();
		Connection conn = null;
		PreparedStatement pstm = null;
		Aspecto objAspecto = null;
		Negocio objNegocio = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select a.*,n.nombre from aspecto a inner join negocio n on a.idNegocio = n.idNegocio where  a.nombre like ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro+"%");
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objNegocio = new Negocio();
				objNegocio.setIdNegocio(rs.getInt(4));
				objNegocio.setNombre(rs.getString(5));
				
				objAspecto = new Aspecto();
				objAspecto.setIdAspecto(rs.getInt(1));
				objAspecto.setNombre(rs.getString(2));
				objAspecto.setEstado(rs.getInt(3));
				objAspecto.setNegocio(objNegocio);
				
				lista.add(objAspecto);
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
		return lista;
	}
	
	
}
