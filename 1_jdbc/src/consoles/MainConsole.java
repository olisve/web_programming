package consoles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class MainConsole {

    private static final Logger log = LogManager.getLogger(MainConsole.class);

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
                AdministratorConsole admin = new AdministratorConsole();
                log.info("Start Administrator Console.");
                admin.run();
            }

            if (answer == 2) {
                ClientConsole client = new ClientConsole();
                log.info("Start Client Console.");
                client.run();
            }
        }
        catch (Exception e){
            log.error(e.getMessage());
        }

    }


}
