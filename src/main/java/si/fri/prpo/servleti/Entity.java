package si.fri.prpo.servleti;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
