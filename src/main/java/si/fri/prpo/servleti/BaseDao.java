package si.fri.prpo.servleti;

import java.sql.Connection;
import java.util.*;

public interface BaseDao {
    Connection getConnection();

    Entity vrni(int id);

    void vstavi(Entity ent);

    void odstrani(int id);

    void posodobi(Entity ent);

    List<Entity> vrniVse();
}
