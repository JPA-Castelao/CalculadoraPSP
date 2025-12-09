import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GestorClientes extends Thread {
    private Socket sock;

    public GestorClientes(Socket socket) {

        this.sock = socket;
    }

    public static void operaciones(PrintWriter escritor, BufferedReader lector) {
        double operando1;
        String stringOperacion;
        char operacion;
        double operando2;
        double resultado = 0;
        try {
            while (true) {

                operacion = lector.readLine().charAt(0);
                operando1 = Double.parseDouble(lector.readLine());
                operando2 = Double.parseDouble(lector.readLine());


                switch (operacion) {

                    case '+':
                        resultado = operando1 + operando2;
                        break;
                    case '-':
                        resultado = operando1 - operando2;
                        break;
                    case '*':
                        resultado = operando1 * operando2;
                        break;
                    case '/':
                        resultado = operando1 / operando2;
                        break;
                    case 's':
                        break;

                    default:
                        resultado = 0;


                }
                escritor.println(String.valueOf(resultado));


            }
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
            } else {
                System.err.println("ERROR DE LECTURA " + e.getMessage());

            }

        }


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
            operaciones(escritor, lector);
            sock.close();
            serverSocket.close();

        } catch (Exception e) {
            System.err.println("ERROR DEL SERVIDOR " + e.getMessage());
        }


    }

    @Override
    public void run() {

        try ( //Decalarion del PrintWriter
              PrintWriter escritor = new PrintWriter(this.sock.getOutputStream(), true);
              //Declaracion del bufferedReader
              BufferedReader lector = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));) {


            operaciones(escritor, lector);
            calculadora();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}



