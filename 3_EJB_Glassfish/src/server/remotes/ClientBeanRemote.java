package server.remotes;

import server.exceptions.BeanException;
import server.entities.Client;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interface for ClientBean
 */
@Remote
public interface ClientBeanRemote {

    /**
     * Insert client into database
     * @param name Name of client
     */
    void addClient(String name) throws BeanException;

    /**
     * Delete client from database
     * @param id ID of client
     */
    void removeClientById(int id) throws BeanException;

    /**
     * Get ID of client by name from database
     * @param name Name of client
     * @return ID of client
     */
    int getIdOfClient(String name) throws BeanException;

    /**
     * Get all clients from database
     * @return list of clients
     */
    List<Client> getAllClients() throws BeanException;

}
