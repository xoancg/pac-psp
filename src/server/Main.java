package server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Instanciamos el servidor
        Server serverSocket = new Server();

        // Iniciamos el servidor
        serverSocket.startServer();
    }
}
