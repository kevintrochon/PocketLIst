package nc.unc.ktrochon.pocketlist.service;

import android.content.Context;

import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.repository.ProduitRepository;

public class ProduitServices {

    private ProduitRepository repository;
    private Produit produit;

    public Produit getProduitByName(Context context,String name){
        repository = new ProduitRepository(context);
        produit = repository.getProduitByName(name);
        return produit;
    }

    public List<Produit> getAllProduit(Context context){
        repository = new ProduitRepository(context);
        List<Produit> produits = repository.getAllProduit();
        return produits;
    }

}
