package nc.unc.ktrochon.pocketlist.service;

import android.content.Context;
import android.util.Log;

import java.util.List;

import nc.unc.ktrochon.pocketlist.entity.ListProduit;
import nc.unc.ktrochon.pocketlist.repository.ListRepository;

public class ListServices {

    private ListRepository repository;
    private ListProduit listProduit;

    public ListProduit getListProduitByName(Context context,String title){
        repository = new ListRepository(context);
        listProduit = repository.getListesProduitByName(title);
        return listProduit;
    }

    public ListProduit getListProduitByID(Context context,int id){
        repository = new ListRepository(context);
        listProduit = repository.getListesProduitByID(id);
        return listProduit;
    }

    public List<ListProduit> getAllList(Context context){
        repository = new ListRepository(context);
        List<ListProduit> lists = repository.getAllListesProduit();
        return lists;
    }

    public void setList(Context context , ListProduit newListProduit){
        repository = new ListRepository(context);
        repository.addListe(newListProduit);
    }

    public void deleteList(Context context, ListProduit ListProduit){
        repository = new ListRepository(context);
        repository.deleteListe(listProduit);
    }

    public void miseAJourListProduit(Context context, ListProduit ListProduit,String title){
        repository = new ListRepository(context);
        repository.updateTitleList(listProduit,title);
    }

    public int getLastID(Context context){
        repository = new ListRepository(context);
        return repository.getLastID();
    }

}
