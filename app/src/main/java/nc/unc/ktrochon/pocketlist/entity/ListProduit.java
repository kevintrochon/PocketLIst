package nc.unc.ktrochon.pocketlist.entity;

import java.io.Serializable;

public class ListProduit implements Serializable {
    private int id;
    private int key_prod;
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

    public int getKey_prod() {
        return key_prod;
    }

    public void setKey_prod(int key_prod) {
        this.key_prod = key_prod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
