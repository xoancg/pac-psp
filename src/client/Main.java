package client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Instanciamos el cliente
        Client client = new Client();

        // Iniciamos la conexión desde el cliente
        client.startClient();
    }
}
