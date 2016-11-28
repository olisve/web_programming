package clients;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.*;
import java.util.Scanner;

import exceptions.PathToFileException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class is created for client-sender. It send image to server from file.
 */
public class ClientSenderStart {

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
    private static final Logger log = LogManager.getLogger(ClientSenderStart.class);

    /**
     * return path of image by current index
     * @param index index of current element
     * @return return path of image
     */
    public static Path getPathFromList(int index){
        Path path;
        switch (index){
            case 1:
                path = Paths.get("images\\kitten.jpg");
                break;
            case 2:
                path = Paths.get("images\\puppy.jpg");
                break;
            case 3:
                path = Paths.get("images\\chicken.jpg");
                break;
            case 4:
                path = Paths.get("images\\hamster.jpg");
                break;
            default:
                path = null;
        }
        return path;
    }

    public static void main(String[] args) throws IOException, PathToFileException {
        try (Socket socket = new Socket(LOCALHOST, PORT)){

            OutputStream outStream = socket.getOutputStream();

            Scanner sc = new Scanner(System.in);

            Path path = null;
            int answer = -1;

            System.out.println("1 -  enter name of own image");
            System.out.println("2 - choose ready image from list");

            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                if (answer == 1){
                    System.out.print("Enter the name of file: ");
                    sc.nextLine();
                    if (sc.hasNextLine()) {
                        String fileName = sc.nextLine();
                        path = Paths.get(fileName);
                    }
                }
                if (answer == 2){
                    System.out.println("Choose the image: ");
                    System.out.println("1 - cute kitten");
                    System.out.println("2 - funny puppy");
                    System.out.println("3 - little chicken");
                    System.out.println("4 - chubby hamster");
                    try {
                        if (sc.hasNextInt()) {
                            answer = sc.nextInt();
                            path = getPathFromList(answer);
                        }
                        if (path == null) {
                            throw new PathToFileException("Wrong path to file!");
                        }
                    }
                    catch (PathToFileException e){
                        log.error(e.getMessage(), e);
                        return;
                    }
                }
            }

            log.info("Write file to stream..");
            try {
                outStream.write(Files.readAllBytes(path));
            }
            catch (NoSuchFileException e){
                log.error(e.getMessage(), e);
            }
            catch (NullPointerException e){
                log.error(e.getMessage(), e);
            }
            outStream.write('\0');
            log.info("Done!");

        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}