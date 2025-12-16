import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Servidor {

//TODO pasar socket al gestor
    public static void main(String[] args) throws IOException {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6789);
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(dir);
        System.out.println("SERVIDOR ESPERANDO CLIENTES");
        while (true) {

//TODO añadir comprobaciones de conexion

            Socket cliente = serverSocket.accept();
            System.out.println("CONEXION EXITOSA");
            GestorClientes hilo = new GestorClientes(cliente);
            hilo.start();


        }


    }
}