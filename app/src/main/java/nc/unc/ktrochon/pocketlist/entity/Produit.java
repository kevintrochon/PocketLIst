package nc.unc.ktrochon.pocketlist.entity;

import java.io.Serializable;

public class Produit implements Serializable {

    private String nomProduit;
    private String description;
    private int id;
    private CategoryProduit category;

    public Produit(String nomProduit, String description) {
        this.nomProduit = nomProduit;
        this.description = description;
    }

    public Produit(String nomProduit, String description, CategoryProduit category) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.category = category;
    }

    public Produit(String nomProduit, String description, int id, CategoryProduit category) {
        this.nomProduit = nomProduit;
        this.description = description;
        this.id = id;
        this.category = category;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryProduit getCategory() {
        return category;
    }

    public void setCategory(CategoryProduit category) {
        this.category = category;
    }
}
