package dao;

import connection.MyConnection;
import models.Client;
import models.Compte;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDaoImpl implements ClientDao {
    @Override
    public Compte getClient(int idCompte) {
        Compte compte = null;
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "select client.nom, client.prenom, compte.client_id, compte.no_compte, compte_type.name, compte.solde from client " +
                    "inner join compte on client.id = compte.client_id " +
                    "inner join compte_type on compte_type.id = compte.type where client.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCompte);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idClient = resultSet.getInt("client_id");
                String firstName = resultSet.getString("prenom");
                String lastName = resultSet.getString("nom");
                long accountNum = resultSet.getLong("no_compte");
                String accountType = resultSet.getString("name");
                double balance = resultSet.getDouble("solde");
                compte = new Compte(firstName, lastName, idClient, accountNum, accountType, balance);
            }
            System.out.println("Added");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return compte;
    }

    @Override
    public ArrayList<Compte> getAllClient() {
        ArrayList<Compte> comptes = new ArrayList<>();
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "select client.nom, client.prenom, compte.client_id, compte.no_compte, compte_type.name, compte.solde from client " +
                        "inner join compte on client.id = compte.client_id " +
                        "inner join compte_type on compte_type.id = compte.type";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idClient = resultSet.getInt("client_id");
                String firstName = resultSet.getString("prenom");
                String lastName = resultSet.getString("nom");
                long accountNum = resultSet.getLong("no_compte");
                String accountType = resultSet.getString("name");
                double balance = resultSet.getDouble("solde");
                comptes.add(new Compte(firstName, lastName, idClient, accountNum, accountType, balance));
            }
            System.out.println("Added");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comptes;
    }

    @Override
    public ArrayList<Compte> getAllClientBySearch(String last, String type) {
        ArrayList<Compte> comptes = new ArrayList<>();
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "select client.nom, client.prenom, compte.client_id, compte.no_compte, compte_type.name, compte.solde from client " +
                    "inner join compte on client.id = compte.client_id inner join compte_type on compte_type.id = compte.type " +
                    "where compte_type.name = ? and client.nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, last);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idClient = resultSet.getInt("client_id");
                String firstName = resultSet.getString("prenom");
                String lastName = resultSet.getString("nom");
                long accountNum = resultSet.getLong("no_compte");
                String accountType = resultSet.getString("name");
                double balance = resultSet.getDouble("solde");
                comptes.add(new Compte(firstName, lastName, idClient, accountNum, accountType, balance));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return comptes;
    }

    @Override
    public ArrayList<String> getAllComptesType() {
        ArrayList<String> types = new ArrayList<>();
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "select name from compte_type";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                types.add(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return types;
    }

    @Override
    public void addClient(Compte compte) {
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "with new_client as (insert into client (nom, prenom) values (?, ?) returning id) " +
                        "insert into compte (client_id, no_compte, type, solde) values " +
                        "((select id from new_client), ?, (select id from compte_type where name = ?), ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getLastName());
            preparedStatement.setString(2, compte.getFirstName());
            preparedStatement.setLong(3, compte.getAccountNum());
            preparedStatement.setString(4, compte.getType());
            preparedStatement.setDouble(5, compte.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateClient(Compte compte) {
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "with changed_client as (update client set prenom = ?, nom = ? where id = ? returning *) " +
                    "update compte set no_compte = ?, solde = ? " +
                    "where client_id in (select id from changed_client)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, compte.getFirstName());
            preparedStatement.setString(2, compte.getLastName());
            preparedStatement.setInt(3, compte.getIdClient());
            preparedStatement.setLong(4, compte.getAccountNum());
            preparedStatement.setDouble(5, compte.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void deleteClient(int idClient) {
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "delete from client where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteCompte(int idCompte) {
        try {
            DataSource dataSource = MyConnection.getSingleDataSource();
            Connection connection = dataSource.getConnection();
            String query = "delete from compte where client_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idCompte);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
