package nc.unc.ktrochon.pocketlist.entity;

import java.io.Serializable;

public class ListProduit implements Serializable {
    private int id;
    private String name;

    public ListProduit() {
    }

    public ListProduit(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
