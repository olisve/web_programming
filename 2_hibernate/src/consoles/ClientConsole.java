package consoles;

import dao.ClientDAO;
import dao.OrderingDAO;
import dao.ProductDAO;
import dao.ShippingDAO;
import entities.Ordering;
import entities.Product;
import entities.Shipping;
import exceptions.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.HibernateUtil;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class ClientConsole {

    private static final Logger log = LogManager.getLogger(ClientConsole.class);

    private Scanner sc;

    private ProductDAO  productDAO;
    private OrderingDAO orderingDAO;
    private ShippingDAO shippingDAO;
    private ClientDAO   clientDAO;

    private int id = -1;

    public ClientConsole(){
        sc = new Scanner(System.in);
        productDAO  = new ProductDAO();
        orderingDAO = new OrderingDAO();
        shippingDAO = new ShippingDAO();
        clientDAO   = new ClientDAO();
    }

    public void makeOrder() throws DAOException {
        showAllProducts();
        System.out.print("Enter the ID separated by commas: ");
        String [] products = sc.nextLine().split(",");
        System.out.print("Enter the address of shipping: ");
        String address = sc.nextLine();
        orderingDAO.makeOrder(id, products, address);
        System.out.println("DONE!");
        log.info("Client made order.");
    }

    public void showAllProducts() throws DAOException{
        List<Product> products = productDAO.getAllProducts();
        if (products != null) {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        if (products.size() == 0) {
            System.out.println("NO CLIENTS IN DATABASE!");
        }
        log.info("Printed list of products.");
    }

    public void showAllOrders() throws DAOException{
        List <Ordering> orders = orderingDAO.getOrdersByClient(id);
        if (orders != null) {
            for (Ordering order : orders) {
                System.out.println(order);
            }
        }
        if (orders.size() == 0) {
            System.out.println("NO ORDERS IN DATABASE!");
        }
        log.info("Printed list of orders.");
    }

    public void showShippingsByOrder() throws DAOException{
        System.out.print("ID of order: ");
        int id = sc.nextInt();
        List <Shipping> shippings = shippingDAO.getShippingByOrder(id);
        if (shippings != null) {
            for (Shipping shipping : shippings) {
                System.out.println(shipping);
            }
        }
        if (shippings.size() == 0) {
            System.out.println("NO SHIPPINGS IN DATABASE!");
        }
        log.info("Printed list of shippings by order.");
    }

    public void run() throws ParseException, SQLException {

        System.out.println("OK! Enter your name: ");
        String name = sc.nextLine();

        try {
            id = clientDAO.getIdOfClient(name);
        }
        catch (DAOException e){
            System.out.println("THERE IS SOME ERROR! TRY AGAIN! Exit system.");
            return;
        }

        if (id == -1){
            System.out.println("WRONG! No such client in database! Exit system.");
            return;
        }

        log.info("Client logged in system.");

        int answer = -1;

        while (answer != 0) {
            System.out.println("Choose the operation: ");
            System.out.println("1 - make order");
            System.out.println("2 - show all products");
            System.out.println("3 - show all my orders");
            System.out.println("4 - show shippings by order");
            System.out.println("0  - exit");

            answer = sc.nextInt();
            sc.nextLine();

            try {
                switch (answer) {
                    case 1:
                        makeOrder();
                        break;
                    case 2:
                        showAllProducts();
                        break;
                    case 3:
                        showAllOrders();
                        break;
                    case 4:
                        showShippingsByOrder();
                        break;
                    default:
                        break;
                }
            }
            catch (DAOException e){
                log.error("SQL exception (request or table failed): " + e);
                System.out.println("THERE IS SOME ERROR! TRY AGAIN!");
                HibernateUtil.closeEntityManager();
            }
        }
        HibernateUtil.closeEntityManager();
    }
}
