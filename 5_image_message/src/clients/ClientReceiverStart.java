package clients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class is created for client-receiver. It gets image from server and save in file.
 */
public class ClientReceiverStart {

    /**
     * localhost
     */
    private static final String LOCALHOST = "localhost";

    /**
     * port
     */
    private static final int PORT = 8001;

    /**
     * logger
     */
    private static final Logger log = LogManager.getLogger(ClientReceiverStart.class);

    public static void main(String[] args) {
        try (Socket socket = new Socket(LOCALHOST, PORT)){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name for received image: ");
            String fileName = null;
            if (sc.hasNextLine()){
                fileName = sc.nextLine();
            }
            log.info("Get file from server...");
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
            InputStream is = socket.getInputStream();
            int length;
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.close();
            log.info("File was received...");
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}