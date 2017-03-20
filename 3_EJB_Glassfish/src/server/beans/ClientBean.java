package server.beans;

import server.entities.Client;
import server.exceptions.BeanException;
import server.remotes.ClientBeanRemote;

import javax.persistence.*;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Bean for work with table Client
 */
@Stateless
public class ClientBean implements ClientBeanRemote{

    @PersistenceContext(unitName = "testjpa")
    EntityManager entityManager;

    public void addClient(String name) throws BeanException {
        try {
            Client client = new Client();
            client.setName(name);
            entityManager.persist(client);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public void removeClientById(int id) throws BeanException {
        try {
            Client client = entityManager.find(Client.class, id);
            entityManager.remove(client);
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public int getIdOfClient(String name) throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Client.findByName");
            query.setParameter(1, name);
            List<Client> clientList = (List<Client>) query.getResultList();
            return clientList.get(0).getId();
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }

    public List<Client> getAllClients() throws BeanException {
        try {
            Query query = entityManager.createNamedQuery("Client.findAll");
            List<Client> clientList = (List<Client>) query.getResultList();
            return clientList;
        }
        catch (Exception ex){
            throw new BeanException(ex.getMessage());
        }
    }
}
