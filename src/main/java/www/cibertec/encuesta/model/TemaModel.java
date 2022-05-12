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
import www.cibertec.encuesta.entidad.mensaje.Tema;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class TemaModel {

	public List<Tema> listaTemasPorAspecto(int idAspecto) {
		ArrayList<Tema> data = new ArrayList<Tema>();
		Tema objTema = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select * from tema where estado = 1 and idAspecto = ? order by 2 asc";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idAspecto);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				objTema = new Tema();
				objTema.setIdTema(rs.getInt(1));
				objTema.setNombre(rs.getString(2));
				objTema.setDefinicion(rs.getString(6));
				data.add(objTema);
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

	public List<Tema> listaTemas() {
		ArrayList<Tema> data = new ArrayList<Tema>();
		Tema bean = null;
		Aspecto beanAspecto = null;
		Negocio beanNegocio = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select t.*, a.nombre, n.nombre from tema t inner join aspecto a on t.idAspecto = a.idAspecto inner join negocio n on t.idNegocio = n.idNegocio";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				beanAspecto = new Aspecto();
				beanAspecto.setIdAspecto(rs.getInt(4));
				beanAspecto.setNombre(rs.getString(7));

				beanNegocio = new Negocio();
				beanNegocio.setIdNegocio(rs.getInt(5));
				beanNegocio.setNombre(rs.getString(8));

				bean = new Tema();
				bean.setIdTema(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setEstado(rs.getInt(3));
				bean.setDefinicion(rs.getString(6));
				bean.setAspecto(beanAspecto);
				bean.setNegocio(beanNegocio);

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

	public int insertaTema(Tema obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "insert into tema values(null,?,1,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getAspecto().getIdAspecto());
			pstm.setInt(3, obj.getNegocio().getIdNegocio());
			pstm.setString(4, obj.getDefinicion());
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

	public int actualizaTema(Tema obj) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update tema set nombre=?, estado=?, idAspecto=?,idNegocio=? where idTema = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setInt(2, obj.getEstado());
			pstm.setInt(3, obj.getAspecto().getIdAspecto());
			pstm.setInt(4, obj.getNegocio().getIdNegocio());
			pstm.setInt(5, obj.getIdTema());

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

	public int eliminaTema(int idTema, int idEstado) throws Exception {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update tema set estado=? where idTema = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEstado);
			pstm.setInt(2, idTema);
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

	public List<Tema> consultaTema(String filtro) throws Exception {
		ArrayList<Tema> data = new ArrayList<Tema>();
		Tema bean = null;
		Aspecto beanAspecto = null;
		Negocio beanNegocio = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select t.*, a.nombre, n.nombre from tema t inner join aspecto a on t.idAspecto = a.idAspecto inner join negocio n on t.idNegocio = n.idNegocio  where t.nombre like ? ";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro + "%");
			log.info(pstm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				beanAspecto = new Aspecto();
				beanAspecto.setIdAspecto(rs.getInt(4));
				beanAspecto.setNombre(rs.getString(7));

				beanNegocio = new Negocio();
				beanNegocio.setIdNegocio(rs.getInt(5));
				beanNegocio.setNombre(rs.getString(8));

				bean = new Tema();
				bean.setIdTema(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setEstado(rs.getInt(3));
				bean.setDefinicion(rs.getString(6));
				bean.setAspecto(beanAspecto);
				bean.setNegocio(beanNegocio);

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
}
