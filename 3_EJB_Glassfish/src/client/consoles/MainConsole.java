package client.consoles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.remotes.ClientBeanRemote;
import server.remotes.OrderingBeanRemote;
import server.remotes.ProductBeanRemote;
import server.remotes.ShippingBeanRemote;

import javax.ejb.EJB;
import java.util.Scanner;

public class MainConsole {

    private static final Logger log = LogManager.getLogger(MainConsole.class);

    @EJB
    private static ProductBeanRemote productBean;

    @EJB
    private static OrderingBeanRemote orderingBean;

    @EJB
    private static ShippingBeanRemote shippingBean;

    @EJB
    private static ClientBeanRemote clientBean;

    public static void main (String [] args) {

        log.info("Start Main Console.");

        try {
            Scanner sc = new Scanner(System.in);
            int answer = -1;

            System.out.println("Hello! Are you administrator or client?");
            System.out.println("1 - administrator");
            System.out.println("2 - client");
            System.out.println("0 - exit");
            answer = sc.nextInt();

            if (answer == 1) {
                AdministratorConsole admin = new AdministratorConsole(productBean, orderingBean, shippingBean, clientBean);
                log.info("Start Administrator Console.");
                admin.run();
            }

            if (answer == 2) {
                ClientConsole client = new ClientConsole(productBean, orderingBean, shippingBean, clientBean);
                log.info("Start Client Console.");
                client.run();
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
