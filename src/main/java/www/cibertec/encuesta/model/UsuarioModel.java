package www.cibertec.encuesta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.apachecommons.CommonsLog;
import www.cibertec.encuesta.entidad.nps.Usuario;
import www.cibertec.encuesta.util.ConexionDB;

@CommonsLog
public class UsuarioModel {
	
	public int insertaUsuario(Usuario obj)  {
		int contador = -1;
		int idMaximo = -1;
		Connection conn = null;
		PreparedStatement pstm1 = null,pstm2 = null,pstm3 = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql1 = "insert into usuario values(null,?,?,?,'1',?)";
			String sql2 = "select max(idusuario) from usuario";
			String sql3 = "insert into permiso values(?,'6')";
			
			pstm1 = conn.prepareStatement(sql1);
			pstm1.setString(1, obj.getNombre());
			pstm1.setString(2, obj.getLogin());
			pstm1.setString(3, obj.getClave());
			pstm1.setInt(4, obj.getIdSede());
			log.info(pstm1);
			contador = pstm1.executeUpdate();
			
			pstm2 = conn.prepareStatement(sql2);
			rs = pstm2.executeQuery();
			log.info(pstm2);
			if (rs.next()) {
				idMaximo = rs.getInt(1);
			}
			
			pstm3 = conn.prepareStatement(sql3);
			pstm3.setInt(1, idMaximo);
			log.info(pstm3);
			
			contador = pstm3.executeUpdate();
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm1 != null) pstm1.close();
				if (pstm2 != null) pstm2.close();
				if (pstm3 != null) pstm3.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
			}
		}
		return contador;
	}
	public int actualizaUsuario(Usuario obj)  {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update usuario set nombre=?, login=?, clave=?, idSede=?, estado=? where idUsuario = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getLogin());
			pstm.setString(3, obj.getClave());
			pstm.setInt(4, obj.getIdSede());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getIdUsuario());
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
	public int eliminaUsuario(int idUsuario, int idEstado)  {
		int contador = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "update usuario set estado=? where idUsuario = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idEstado);
			pstm.setInt(2, idUsuario);
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
	
	public List<Usuario> listaUsuarioPorSede(int idSede)  {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select idUsuario, nombre from usuario where idSede =? and estado = 1";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSede);
			log.info(pstm);
			rs = pstm.executeQuery();
			Usuario bean = null;
			while (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				lista.add(bean);
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
	

	
	public List<Usuario> consultaUsuarioLike(String filtro)  {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select u.*,s.nombre, n.nombre  from usuario u inner join sede s on u.idSede= s.idSede inner join negocio n on s.idNegocio = n.idNegocio inner join permiso p on u.idusuario = p.idusuario  where p.idrol=6 and u.nombre like ? order by 2 asc";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, filtro+"%");
			log.info(pstm);
			rs = pstm.executeQuery();
			Usuario bean = null;
			while (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setClave(rs.getString(4));
				bean.setEstado(rs.getInt(5));
				bean.setIdSede(rs.getInt(6));
				bean.setSede(rs.getString(7));
				bean.setNegocio(rs.getString(8));
				bean.setAsignados(cantidadAsignados(rs.getInt(1)));
				bean.setClasificados(cantidadClasificados(rs.getInt(1)));
				bean.setPendientes(bean.getAsignados() - bean.getClasificados());
				bean.setAvance(bean.getAsignados()==0?"0%":((int)(bean.getClasificados()*100.0/bean.getAsignados()) + "%"));
				lista.add(bean);
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
	
	public List<Usuario> listaUsuarioClasificadores()  {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select u.*,s.nombre, n.nombre  from usuario u inner join sede s on u.idSede= s.idSede inner join negocio n on s.idNegocio = n.idNegocio inner join permiso p on u.idusuario = p.idusuario  where p.idrol=6 order by 2 asc";
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Usuario bean = null;
			while (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setLogin(rs.getString(3));
				bean.setClave(rs.getString(4));
				
				
				bean.setEstado(rs.getInt(5));
				bean.setIdSede(rs.getInt(6));
				bean.setSede(rs.getString(7));
				bean.setNegocio(rs.getString(8));
				
				bean.setAsignados(cantidadAsignados(rs.getInt(1)));
				bean.setClasificados(cantidadClasificados(rs.getInt(1)));
				bean.setPendientes(bean.getAsignados() - bean.getClasificados());
				bean.setAvance(bean.getAsignados()==0?"0%":((int)(bean.getClasificados()*100.0/bean.getAsignados()) + "%"));
				lista.add(bean);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
			}
		}
		return lista;
	}
	
	
	public int cantidadAsignados(int idUsuario) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = 0;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select count(*) from mensaje where idUsuario=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}
	
	public int cantidadClasificados(int idUsuario) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstm = null;
		int salida = 0;
		try {
			conn = ConexionDB.getConexion();
			String sql = "select count(*) from mensaje where idUsuario=? and estado ='Clasificado'";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			log.info(pstm);
			rs = pstm.executeQuery();
			if (rs.next()) {
				salida = rs.getInt(1);
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				if (rs != null)		rs.close();
				if (pstm != null)	pstm.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}
	
	
}
