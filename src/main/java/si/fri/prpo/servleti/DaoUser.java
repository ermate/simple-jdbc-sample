package si.fri.prpo.servleti;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.util.*;
import java.util.logging.*;

public class DaoUser implements BaseDao {
    private static DaoUser instance;

    public static DaoUser getInstance() {
        if(instance == null) {
            instance = new DaoUser();
        }
        return instance;
    }

    private Connection connection;
    private Logger log = Logger.getLogger(DaoUser.class.getName());

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (Exception e) {
            log.severe("Can't get connection" + e.getMessage());
            System.out.println("Can't get connection");
        }
        return null;
    }

    private User getUporabnikFromRS(ResultSet rs) throws SQLException {
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabniskoime");
        return new User(ime, priimek, uporabniskoIme);
    }

    @Override
    public Entity vrni(int id) {
        PreparedStatement ps = null;

        try {
            if (connection == null)
                connection = getConnection();

            String sql = "SELECT * FROM uporabniki.public.uporabnik WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Not existing user");
            }
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return null;
    }

    @Override
    public void vstavi(Entity ent) {

        PreparedStatement ps = null;
        User u = (User) ent;

        try {
            if(connection == null) {
                connection = getConnection();
            }
            //System.out.println("2 isClosed = " + connection.isClosed());

            String sql = "INSERT INTO uporabniki.public.uporabnik (ime, priimek, uporabniskoime)";
            ps = connection.prepareStatement(sql);

            ps.setString(1, u.getIme());
            ps.setString(2, u.getPriimek());
            ps.setString(3, u.getUporabniskoIme());
            ps.executeUpdate();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void odstrani(int id) {
        PreparedStatement ps = null;

        try {
            if (connection == null)
                connection = getConnection();

            String sql = "DELETE * FROM uporabniki.public.uporabnik WHERE id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void posodobi(Entity ent) {
        PreparedStatement ps = null;

        try {
            if (connection == null)
                connection = getConnection();

            String sql = "UPDATE uporabniki.public.uporabnik SET ime=?, priimek=?, uporabniskoime=?";
            ps = connection.prepareStatement(sql);

            User u = (User) ent;
            ps.setString(1, u.getIme());
            ps.setString(2, u.getPriimek());
            ps.setString(3, u.getUporabniskoIme());
            ps.executeUpdate();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public List<Entity> vrniVse() {
        List<Entity> entitete = new ArrayList<Entity>();
        Statement st = null;

        try {
            if (connection == null)
                connection = getConnection();

            st = connection.createStatement();
            String sql = "SELECT * FROM uporabniki.public.uporabnik";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                entitete.add(getUporabnikFromRS(rs));
            }
        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return entitete;
    }
}
