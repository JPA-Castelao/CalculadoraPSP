import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6789);
        try {

            //Instancia del socket
            Socket socketCliente = new Socket();
            //Instancia del Scanner
            Scanner sc = new Scanner(System.in);
            //Emparejado del socket con la dirección y puerto
            socketCliente.connect(dir);
            System.out.println("Conectado al servidor");

            char operacion;
            String dato;
            String operando1 = "", operando2;


            //Instanciamos el Printwriter para la salida de datos
            PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
            //Instanciamos el bufferedReader para recibir datos
            BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            String condicion = "";
            while (!condicion.equalsIgnoreCase("salir")) {
                try {

                    if (operando1.equalsIgnoreCase("")) {
                        System.out.println("Introduce el primer operando");
                        operando1 = sc.nextLine();
                        escritor.print(operando1);

                    } else {
                        operando1 = lector.readLine();
                        System.out.println("Operando 1 es :" + operando1);

                    }


                    condicion = sc.nextLine();
                } catch (NumberFormatException e) {
                    System.err.println("");
                }

            }

        } catch (Exception e) {

        }


    }

}
