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

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AdministratorConsole {

    private static final Logger log = LogManager.getLogger(AdministratorConsole.class);

    private Scanner sc;

    private ProductBeanRemote productBean;
    private OrderingBeanRemote orderingBean;
    private ShippingBeanRemote shippingBean;
    private ClientBeanRemote clientBean;

    private static final String passwordAdmin = "123";

    public AdministratorConsole(ProductBeanRemote productBean, OrderingBeanRemote orderingBean, ShippingBeanRemote shippingBean, ClientBeanRemote clientBean) {
        sc = new Scanner(System.in);
        this.productBean = productBean;
        this.orderingBean = orderingBean;
        this.shippingBean = shippingBean;
        this.clientBean = clientBean;
    }

    public boolean checkPassword(String password){
        return password.equals(passwordAdmin);
    }

    private int getID(){
        System.out.print("Enter the ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public void addProduct() throws BeanException {
        System.out.print("Enter the title: ");
        String title = sc.nextLine();
        System.out.print("Enter the price: ");
        int price = sc.nextInt();
        sc.nextLine();
        productBean.addProduct(title, price);
        System.out.println("DONE!");
        log.info("Added new product into database.");
    }

    public void removeProduct() throws BeanException {
        int id = getID();
        productBean.removeProductById(id);
        System.out.println("DONE!");
        log.info("Removes product from database.");
    }

    public void updateProduct() throws BeanException {
        System.out.println("What update: ");
        System.out.println("1 - title");
        System.out.println("2 - price");
        int answer = sc.nextInt();
        sc.nextLine();

        int id = getID();

        if (answer == 1){
            System.out.println("Enter the new title: ");
            String title = sc.nextLine();
            productBean.updateProductTitle(id, title);
            System.out.println("DONE!");
        }
        if (answer == 2){
            System.out.println("Enter the new price: ");
            int price = sc.nextInt();
            sc.nextLine();
            productBean.updateProductPrice(id, price);
            System.out.println("DONE!");
        }

        if (answer != 1 && answer != 2){
            System.out.println("ERROR!");
        }

        log.info("Updated product.");
    }

    public void addClient() throws BeanException {
        System.out.print("Enter the name: ");
        String name = sc.nextLine();
        clientBean.addClient(name);
        System.out.println("DONE!");
        log.info("Added new client into database.");
    }

    public void removeClient() throws BeanException {
        int id = getID();
        clientBean.removeClientById(id);
        System.out.println("DONE!");
        log.info("Removed client from database.");
    }

    public void registerPayment() throws BeanException {
        System.out.print("Enter the ID of order: ");
        int id = sc.nextInt();
        sc.nextLine();
        orderingBean.updateOrderPayment(id);
        System.out.println("DONE!");
        log.info("Registered payment for order.");
    }

    public void updateShipping() throws ParseException, BeanException {
        System.out.println("What update: ");
        System.out.println("1 - address");
        System.out.println("2 - time");
        int answer = sc.nextInt();
        sc.nextLine();

        int id = getID();

        if (answer == 1){
            System.out.println("Enter the new address: ");
            String address = sc.nextLine();
            shippingBean.updateShippingAddress(id, address);
            System.out.println("DONE!");
        }
        if (answer == 2){
            System.out.print("Enter the date of shipping...");
            System.out.print("Day: ");
            int day = sc.nextInt();
            System.out.print("Month: ");
            int month = sc.nextInt();
            System.out.print("Year: ");
            int year = sc.nextInt() - 1900;
            sc.nextLine();
            Date d = new Date(year, month, day);
            shippingBean.updateShippingDate(id, d);
            System.out.println("DONE!");
        }

        if (answer != 1 && answer != 2){
            System.out.println("ERROR!");
        }
        log.info("Updated shipping.");
    }

    public void showAllClients() throws BeanException {
        List <Client> clients = clientBean.getAllClients();
        if (clients != null) {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
        if (clients.size() == 0) {
            System.out.println("NO CLIENTS IN DATABASE!");
        }
        log.info("Printed list of all clients.");
    }

    public void showAllProducts() throws BeanException {
        List <Product> products = productBean.getAllProducts();
        if (products != null) {
            for (Product product : products) {
                System.out.println(product);
            }
        }
        if (products.size() == 0) {
            System.out.println("NO PRODUCTS IN DATABASE!");
        }
        log.info("Printed list of all products.");
    }

    public void showAllOrdersClient() throws BeanException {
        int id = getID();

        List <Ordering> orders = orderingBean.getOrdersByClient(id);

        if (orders != null) {
            for (Ordering order : orders) {
                System.out.println(order);
            }
        }
        if (orders.size() == 0) {
            System.out.println("NO ORDERS IN DATABASE!");
        }
        log.info("Printed list of all orders for client.");
    }

    public void showAllOrdersDelay() throws BeanException {
        List <Ordering> orders = orderingBean.getOrdersWithDelay();
        if (orders != null) {
            for (Ordering order : orders) {
                System.out.println(order);
            }
        }
        if (orders.size() == 0) {
            System.out.println("NO ORDERS IN DATABASE!");
        }
        log.info("Printed list of all orders with delay.");
    }

    public void showAllOrdersCurrent() throws BeanException {
        System.out.print("Day: ");
        int day = sc.nextInt();
        System.out.print("Month: ");
        int month = sc.nextInt();
        System.out.print("Year: ");
        int year = sc.nextInt() - 1900;
        sc.nextLine();
        Date d = new Date(year, month, day);

        List <Ordering> orders = orderingBean.getOrdersCurrent(d);

        if (orders != null) {
            for (Ordering order : orders) {
                System.out.println(order);
            }
        }
        if (orders.size() == 0) {
            System.out.println("NO ORDERS IN DATABASE!");
        }
        log.info("Printed list of all orders before current date.");
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

    public void run() throws ParseException, SQLException{

        System.out.println("OK! Enter the administrator password: ");
        String password = sc.nextLine();
        if (!checkPassword(password)) {
            System.out.println("WRONG! Exit system.");
            return;
        }

        log.info("Administrator logged in system.");

        int answer = -1;

        while (answer != 0) {
            System.out.println("Choose the operation: ");
            System.out.println("1  - add product");
            System.out.println("2  - remove product");
            System.out.println("3  - update product");
            System.out.println("4  - show all products");
            System.out.println("5  - add client");
            System.out.println("6  - remove client");
            System.out.println("7  - show all clients");
            System.out.println("8  - register the payment");
            System.out.println("9  - update shipping");
            System.out.println("10 - show all orders for client");
            System.out.println("11 - show orders with delay in shipping");
            System.out.println("12 - show current orders");
            System.out.println("13 - show shippings by order");
            System.out.println("0  - exit");

            answer = sc.nextInt();
            sc.nextLine();

            try {
                switch (answer) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        showAllProducts();
                        break;
                    case 5:
                        addClient();
                        break;
                    case 6:
                        removeClient();
                        break;
                    case 7:
                        showAllClients();
                        break;
                    case 8:
                        registerPayment();
                        break;
                    case 9:
                        updateShipping();
                        break;
                    case 10:
                        showAllOrdersClient();
                        break;
                    case 11:
                        showAllOrdersDelay();
                        break;
                    case 12:
                        showAllOrdersCurrent();
                        break;
                    case 13:
                        showShippingsByOrder();
                        break;
                    default:
                        break;
                }
            }
            catch (Exception e){
                log.error("Exception: " + e);
                System.out.println("THERE IS SOME ERROR! TRY AGAIN!");
            }
        }
    }
}
