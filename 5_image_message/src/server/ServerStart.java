package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *  class is created for server. It creates sockets, waits for sender and receiver and copy sender-stream to receiver-stream.
 */
public class ServerStart {

    private static final int PORT = 8001;

    private static final Logger log = LogManager.getLogger(ServerStart.class);

    public static void main(String[] args) {

        try  {

            ServerSocket serv = new ServerSocket(PORT);
            log.info("Initialized on port " + PORT);

            Socket sender = serv.accept();
            log.info(sender.getInetAddress().getHostName() + " connected");

            Socket receiver = serv.accept();
            log.info(receiver.getInetAddress().getHostName() + " connected");

            OutputStream os = receiver.getOutputStream();
            InputStream is = sender.getInputStream();

            int length;
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.close();

            log.info("Stop server!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
