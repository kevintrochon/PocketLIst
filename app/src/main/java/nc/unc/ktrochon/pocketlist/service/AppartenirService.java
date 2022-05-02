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

    public void add(Context context,int numeroListe, int numeroProduit, int quantity) {
        repository = new AppartenirRepository(context);
        repository.addAppartenir(numeroListe,numeroListe,quantity);
    }
}
