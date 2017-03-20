package client.consoles;

import server.entities.*;
import server.exceptions.BeanException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.remotes.ClientBeanRemote;
import server.remotes.OrderingBeanRemote;
import server.remotes.ProductBeanRemote;
import server.remotes.ShippingBeanRemote;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class ClientConsole {

    private static final Logger log = LogManager.getLogger(ClientConsole.class);

    private Scanner sc;

    private ProductBeanRemote productBean;
    private OrderingBeanRemote orderingBean;
    private ShippingBeanRemote shippingBean;
    private ClientBeanRemote clientBean;

    private int id = -1;

    public ClientConsole(ProductBeanRemote productBean, OrderingBeanRemote orderingBean, ShippingBeanRemote shippingBean, ClientBeanRemote clientBean){
        sc = new Scanner(System.in);
        this.productBean = productBean;
        this.orderingBean = orderingBean;
        this.shippingBean = shippingBean;
        this.clientBean = clientBean;
    }

    public void makeOrder() throws BeanException {
        showAllProducts();
        System.out.print("Enter the ID separated by commas: ");
        String [] products = sc.nextLine().split(",");
        System.out.print("Enter the address of shipping: ");
        String address = sc.nextLine();
        orderingBean.makeOrder(id, products, address);
        System.out.println("DONE!");
        log.info("Client made order.");
    }

    public void showAllProducts() throws BeanException {
        List<Product> products = productBean.getAllProducts();
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

    public void showAllOrders() throws BeanException {
        List <Ordering> orders = orderingBean.getOrdersByClient(id);
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

    public void showShippingsByOrder() throws BeanException {
        System.out.print("ID of order: ");
        int id = sc.nextInt();
        List <Shipping> shippings = shippingBean.getShippingByOrder(id);
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
            id = clientBean.getIdOfClient(name);
        }
        catch (BeanException e){
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
            catch (BeanException e){
                log.error("Exception: " + e);
                System.out.println("THERE IS SOME ERROR! TRY AGAIN!");
            }
        }
    }
}
