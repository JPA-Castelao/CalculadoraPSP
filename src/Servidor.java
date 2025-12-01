import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Servidor {


    public static void sumar(int operando1, int operando2) {


    }

    public static void introduccionDeDatos() {
        try {


        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        //Establece la ip y el puerto al a través de los cuales se va a producir la comunicación
        InetSocketAddress direccion = new InetSocketAddress("localhost", 6789);


        int operando1, operando2;
        try {


            //Instancia del objeto socket servidor
            ServerSocket servidor = new ServerSocket();
            //Lo asignamos a un puerto de memoria
            servidor.bind(direccion);

            System.out.println("Esperando conexiones:...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");
            //Instanciamos el Printwriter para la salida de datos
            /*

             */
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            //Instanciamos el bufferedReader para recibir datos
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Bucle que representa el funcionamiento del servidor
            String salir = "salir";
            System.out.println("Introduce el operando 1");
            operando1 = Integer.parseInt(lector.readLine());

            while (socket.isConnected()) {

                try {
                    System.out.println("El operando 1 es: " + operando1);



                } catch (Exception e) {
                    System.err.println("ERROR");
                }

            }
            //se cierr el servidor
            servidor.close();

        } catch (Exception e) {
            System.err.println("EL MENSAJE ES: " + e.getMessage());
        }


    }


}
