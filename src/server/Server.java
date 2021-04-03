package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // Datos de conexión
    private final int PORT = 9876;       // Puerto
    private ServerSocket serverSocket;   // Socket del servidor
    private Socket clientSocket;         // Socket del cliente

    // Constructor (?) Server
    Server() throws IOException {
        serverSocket = new ServerSocket(PORT);  // Creamos el socket para el servidor en el puerto correspondiente
    }

    // Método para iniciar el servidor
    public void startServer() throws IOException {

        // Metemos el código dentro de un bucle infinito para que la conexión se mantenga siempre activa.
        // Detendremos manualmente la conexión al finalizar el programa
        while (true){
            System.out.println("Servidor iniciado. Esperando cliente...");

            // Iniciamos el socket del servidor y esperamos una conexión desde un cliente
            clientSocket = serverSocket.accept();
            System.out.println("¡Habemus conexión! Esperando nombre del cliente...");

            // Abrimos flujo de entrada en el servidor desde el cliente
            DataInputStream entradaServidor = new DataInputStream(clientSocket.getInputStream());
            // Abrimos flujo de salida del servidor hacia el cliente
            DataOutputStream salidaServidor = new DataOutputStream(clientSocket.getOutputStream());

            // Servidor pregunta al cliente su nombre y recibe la respuesta
            salidaServidor.writeUTF("Por favor, introduce tu nombre:");
            clienteDice(entradaServidor.readUTF());

            // Servidor pregunta el número de tareas a realizar
            System.out.println("Número de tareas a realizar:");
            salidaServidor.writeUTF("Indique el número de tareas a realizar:");
            int numeroTareas = entradaServidor.readByte();
            clienteDice(String.valueOf(numeroTareas));

            ArrayList<Tarea> tareas = new ArrayList<>();
            for (int i = 0; i < numeroTareas; i++) {
                salidaServidor.writeUTF("Tarea número " + (i + 1) + ":");
                salidaServidor.writeUTF("¿Descripción de la tarea?");
                String descripcion = entradaServidor.readUTF();
                clienteDice(descripcion);
                salidaServidor.writeUTF("¿Estado de la tarea?");
                String estado = entradaServidor.readUTF();
                clienteDice(estado);
                Tarea tarea = new Tarea(descripcion, estado);
                tareas.add(tarea);
            }
            salidaServidor.writeUTF("Enviando las siguientes tareas:");
            int contador = 1;
            for (int i = 0; i < numeroTareas; i++) {
                salidaServidor.writeUTF("Tarea "+ contador + ". " + tareas.get(i).toString());
                contador++;
            }

            // Mensaje en el servidor cuando el cliente se desconecta
            System.out.println("¡Cliente desconectado!");

        }
    }

    private void clienteDice (String mensaje){
        System.out.println("Cliente: " + mensaje);
    }

}

// https://www.programarya.com/Cursos-Avanzados/Java/Sockets
