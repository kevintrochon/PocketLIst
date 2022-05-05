package nc.unc.ktrochon.pocketlist.service;

import android.content.Context;

import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.Appartenir;
import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.entity.Produit;
import nc.unc.ktrochon.pocketlist.repository.AppartenirRepository;

public class AppartenirService {

    private AppartenirRepository repository;
    private ListServices listServices = new ListServices();
    private ProduitServices produitServices = new ProduitServices();

    public AppartenirService() {
    }

    public List<Appartenir> getAllAppartenir(Context context,int numeroListe){
        repository = new AppartenirRepository(context);
        List<Appartenir> listAppartenir = repository.getAppartenanceByListe(numeroListe);
        return listAppartenir;
    }

    public Appartenir getAppartenir(Context context,int numeroListe, int numeroProduit){
        repository = new AppartenirRepository(context);
        Appartenir appartenance = repository.getAppartenirByListe(numeroListe, numeroProduit);
        return appartenance;
    }

    public void add(Context context,int numeroListe, int numeroProduit, int quantity) {
        repository = new AppartenirRepository(context);
        repository.addAppartenir(numeroListe,numeroProduit,quantity);
    }

    public void updateProductInListe(Context context,int numeroListe, int numeroProduit, int nouveauNumeroProduit){
        repository = new AppartenirRepository(context);
        repository.updateProductList(numeroListe,numeroProduit,nouveauNumeroProduit);
    }

    public void updateQuantity(Context context,int numeroListe, int numeroProduit, int newQuantity){
        repository = new AppartenirRepository(context);
        repository.updateQuantity(numeroListe,numeroProduit,newQuantity);
    }

    public void deleteProductInList(Context context ,int numeroProduit,int numeroListe){
        repository = new AppartenirRepository(context);
        repository.deletedProductInList(numeroProduit,numeroListe);
    }

    public void deleteList(Context context ,int numeroListe){
        repository = new AppartenirRepository(context);
        repository.deletedList(numeroListe);
    }

}
