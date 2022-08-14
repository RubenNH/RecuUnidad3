package rec.recupera;

import rec.recupera.MySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dao {
    Connection conn;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;

    public List<Bean> findAll() {
        List<Bean> personas = new LinkedList<>();
        Bean persona;
        try {
            conn = new MySQL().getConnection();
            String query = "SELECT * FROM persona;";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                persona = new Bean();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setSubname(rs.getString("subname"));
                persona.setCurp(rs.getString("curp"));
                persona.setCumple(rs.getString("cumple"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName())
                    .log(Level.SEVERE, "Error findAll", e);
        } finally {
            closeConnections();
        }
        return personas;
    }

    public boolean save(Bean persona) {
        try {
            conn = new MySQL().getConnection();
            String query = "INSERT INTO persona (nombre, subname, curp, cumple)" +
                    " VALUES (?,?,?,?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getSubname());
            pstm.setString(3, persona.getCurp());
            pstm.setString(4, persona.getCumple());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName())
                    .log(Level.SEVERE, "Error save", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    public Bean findOne(Long id) {
        try {
            conn = new MySQL().getConnection();
            String query = "SELECT * FROM persona WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Bean persona = new Bean();
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setSubname(rs.getString("subname"));
                persona.setCurp(rs.getString("curp"));
                persona.setCumple(rs.getString("cumple"));
                return persona;
            }
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName())
                    .log(Level.SEVERE, "Error findOne", e);
        } finally {
            closeConnections();
        }
        return null;
    }

    public boolean update(Bean persona) {
        try {
            conn = new MySQL().getConnection();
            String query = "UPDATE Persona SET nombre = ?, subname = ?, curp = ?," +
                    "cumple = ? WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, persona.getNombre());
            pstm.setString(2, persona.getSubname());
            pstm.setString(3, persona.getCurp());
            pstm.setString(4, persona.getCumple());
            pstm.setLong(5, persona.getId());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName())
                    .log(Level.SEVERE, "Error update", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    public boolean delete(Long id) {
        try {
            conn = new MySQL().getConnection();
            String query = "DELETE FROM persona WHERE id = ?";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName())
                    .log(Level.SEVERE, "Error delete method");
            return false;
        } finally {
            closeConnections();
        }
    }

    public void closeConnections() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }
}
