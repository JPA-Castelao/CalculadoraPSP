import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Servidor {


    public static double sumar(double operando1, double operando2) {

        return operando1 + operando2;

    }

    public static double resta(double operando1, double operando2) {

        return operando1 - operando2;

    }

    public static double multiplicacion(double operando1, double operando2) {

        return operando1 * operando2;

    }

    public static double division(double operando1, double operando2) {

        return operando1 / operando2;

    }


    public static void calculadora() {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6789);

        try {
            //Instancia del socket
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(dir);
            Socket sock = serverSocket.accept();


            //Decalarion del PrintWriter
            PrintWriter escritor = new PrintWriter(sock.getOutputStream(), true);
            //Declaracion del bufferedReader
            BufferedReader lector = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            //variables
            double operando1;
            char operacion;
            double operando2;
            double resultado = 0;

            while (true) {
                operando1 = Double.parseDouble(lector.readLine());
                operacion = lector.readLine().charAt(0);
                operando2 = Double.parseDouble(lector.readLine());
                switch (operacion) {

                    case '+':
                        resultado = sumar(operando1, operando2);
                        break;
                    case '-':
                        resultado = resta(operando1, operando2);
                        break;
                    case '*':
                        resultado = multiplicacion(operando1, operando2);
                        break;
                    case '/':
                        resultado = division(operando1, operando2);
                        break;

                    default:
                        resultado = 0;


                        escritor.println(String.valueOf(resultado));
                }


            }


        } catch (Exception e) {
            System.err.println("ERROR DEL SERVIDOR " + e.getMessage());
        }


    }


    public static void main(String[] args) {


        calculadora();


    }
}