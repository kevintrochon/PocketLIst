package nc.unc.ktrochon.pocketlist.entity;

import java.io.Serializable;
import java.util.List;

public class ListProduit implements Serializable {
    private List<Produit> produits;
    private String name;

    public ListProduit() {
    }

    public ListProduit(String name) {
        this.name = name;
    }

    public ListProduit(List<Produit> produits, String name) {
        this.produits = produits;
        this.name = name;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
