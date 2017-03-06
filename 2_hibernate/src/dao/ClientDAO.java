package dao;

import entities.Client;
import exceptions.DAOException;
import utils.HibernateUtil;

import javax.persistence.*;
import java.util.List;

/**
 * DAO for work with table Client
 */
public class ClientDAO {

    /**
     * Insert client into database
     * @param name Name of client
     */
    public void addClient(String name) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Client client = new Client();
            client.setName(name);
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Delete client from database
     * @param id ID of client
     */
    public void removeClientById(int id) throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Client client = entityManager.find(Client.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(client);
            entityManager.flush();
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Get ID of client by name
     * @param name Name of client
     * @return ID of client
     */
    public int getIdOfClient(String name) throws DAOException {
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Client.findByName");
            query.setParameter(1, name);
            List<Client> clientList = (List<Client>) query.getResultList();
            return clientList.get(0).getId();
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }

    /**
     * Get all clients from database
     * @return list of clients
     */
    public List<Client> getAllClients() throws DAOException{
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            Query query = entityManager.createNamedQuery("Client.findAll");
            List<Client> clientList = (List<Client>) query.getResultList();
            return clientList;
        }
        catch (Exception ex){
            throw new DAOException(ex.getMessage());
        }
    }
}
