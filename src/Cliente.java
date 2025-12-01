import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.InputMismatchException;
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
            //variables
            char operacion = ' ';
            String dato;
            double operando1 = 0, operando2 = 0;
            double resultado = 0;
            boolean validacionDatos;
            //Instanciamos el Printwriter para la salida de datos
            PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
            //Instanciamos el bufferedReader para recibir datos
            BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            while (true) {
                //bloque de validación del primer dato
                validacionDatos = false;

                while (!validacionDatos) {
                    try {

                        System.out.println("Introduce el  primer operando");
                        operando1 = sc.nextDouble();
                        validacionDatos = true;


                    } catch (InputMismatchException e) {
                        System.err.println("LOS OPERANDOS DEBEN SER NUMEROS NATURALES");
                        sc.next();
                    }

                }

                //validación del operador
                do {
                    System.out.println("Introduce la operación. SOLO (+,-,*,/)");
                    operacion = sc.next().charAt(0);
                } while (operacion != '+' && operacion != '-' && operacion != '*' && operacion != '/');

                //bloque de validación del segundo dato
                validacionDatos = false;

                while (!validacionDatos) {
                    try {

                        System.out.println("Introduce el  segundo operando");
                        operando2 = sc.nextDouble();

                        if (operacion == '/' && operando2 == 0) {
                            throw new ArithmeticException();
                        }
                        validacionDatos = true;


                    } catch (InputMismatchException e) {
                        System.err.println("LOS OPERANDOS DEBEN SER NUMEROS NATURALES");
                        sc.next();
                    } catch (ArithmeticException e) {
                        System.err.println("NO SE PUEDE DIVIDIR ENTRE 0");


                    }
                    resultado = Double.parseDouble(lector.readLine());
                    System.out.println("EL RESULTADO ES: " + resultado);

                }
                escritor.println(String.valueOf(operando1));
                escritor.println(operacion);
                escritor.println(String.valueOf(operando2));


                socketCliente.close();

            }

        } catch (Exception e) {

        }


    }

}
