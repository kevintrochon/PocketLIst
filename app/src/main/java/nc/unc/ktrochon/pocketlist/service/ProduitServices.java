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

    public List<Produit> getAllProduit(Context context, int numeroListe){
        repository = new ProduitRepository(context);
        List<Produit> produits = repository.getAllProduit(numeroListe);
        return produits;
    }

    public List<Produit> getAll(Context context){
        repository = new ProduitRepository(context);
        List<Produit> produits = repository.getAll();
        return produits;
    }

    public void addProduit(Context context , Produit produit){
        repository = new ProduitRepository(context);
        repository.addProduit(produit);
    }

    public void deleteProduit(Context context, String nom){
        repository = new ProduitRepository(context);
        repository.deleteProduit(nom);
    }

    public void miseAJourNomProduit(Context context, Produit produit,Produit newProduit){
        repository = new ProduitRepository(context);
        repository.updateNomProduit(produit,newProduit);
    }

    public void updateDescriptionProduct(Context context, Produit produit,String newDescription){
        repository = new ProduitRepository(context);
        repository.updateDescriptionProduit(produit,newDescription);
    }

}
