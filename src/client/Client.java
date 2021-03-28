package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // Datos de conexión
    private final String HOST = "localhost";    // Dirección
    private final int PORT = 9876;              // Puerto
    private final Socket server;                // Socket


    // Constructor (?) Client con dirección y puerto correspondientes
    public Client() throws IOException {
        server = new Socket(HOST, PORT);
    }

    public void startClient() throws IOException {
        System.out.println("Iniciando conexión con el servidor...");

        // Instanciamos un objeto tipo Scanner para que el usuario pueda introducir mensajes vía consola
        Scanner consola = new Scanner(System.in);

        // Abrimos flujo de entrada en el servidor desde el cliente
        DataInputStream entradaCliente = new DataInputStream(server.getInputStream());

        // Abrimos flujo de salida del servidor hacia el cliente
        DataOutputStream salidaCliente = new DataOutputStream(server.getOutputStream());

        // El servidor pregunta el nombre al cliente
        servidorDice(entradaCliente.readUTF());
        salidaCliente.writeUTF(consola.nextLine());

        // Servidor pregunta número de tareas a realizar
        servidorDice(entradaCliente.readUTF());
        int numeroTareas = consola.nextInt();
        consola.nextLine();
        salidaCliente.writeByte(numeroTareas);
        
        for (int i = 0; i < numeroTareas; i++) {
            servidorDice(entradaCliente.readUTF());
            servidorDice(entradaCliente.readUTF());
            String descripcion = consola.nextLine();
            salidaCliente.writeUTF(descripcion);
            servidorDice(entradaCliente.readUTF());
            String estado = consola.nextLine();
            salidaCliente.writeUTF(estado);
        }
        servidorDice(entradaCliente.readUTF());
        for (int i = 0; i < numeroTareas; i++) {
            servidorDice(entradaCliente.readUTF());
        }

        System.out.println("Conexión finalizada.");
    }

    private void servidorDice (String mensaje){
        System.out.println("Servidor: " + mensaje);
    }
}
