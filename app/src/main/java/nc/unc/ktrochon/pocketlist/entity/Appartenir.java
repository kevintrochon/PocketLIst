package nc.unc.ktrochon.pocketlist.entity;

public class Appartenir {

    int key_app, key_list, key_prod, quantite;

    public Appartenir() {
    }

    public int getKey_app() {
        return key_app;
    }

    public void setKey_app(int key_app) {
        this.key_app = key_app;
    }

    public int getKey_list() {
        return key_list;
    }

    public void setKey_list(int key_list) {
        this.key_list = key_list;
    }

    public int getKey_prod() {
        return key_prod;
    }

    public void setKey_prod(int key_prod) {
        this.key_prod = key_prod;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
