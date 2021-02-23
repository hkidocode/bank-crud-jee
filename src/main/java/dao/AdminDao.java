package dao;

import models.Client;
import models.Compte;

import java.util.ArrayList;

public interface AdminDao {
    Compte getClient(int idCompte);
    ArrayList<Compte> getAllClient();
    ArrayList<Compte> getAllClientBySearch(String last, String type);
    ArrayList<String> getAllComptesType();
    void addClient(Compte compte);
    void updateClient(Compte compte);
    void deleteClient(int idClient);
    void deleteCompte(int idCompte);

}