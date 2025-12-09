import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Cliente {
    //Instancia del Scanner
    static Scanner sc = new Scanner(System.in);

    //validacion de operadores
    public static double validacionOperador() {
        double operando = 0;
        boolean validacionDatos = false;
        while (!validacionDatos) {
            try {
                operando = sc.nextDouble();
                validacionDatos = true;


            } catch (InputMismatchException e) {
                System.err.println("LOS OPERANDOS DEBEN SER NUMEROS NATURALES");
                sc.next();
            }

        }
        return operando;
    }

    //validacion del operando
    public static char validacionOperacion() {

        char operacion = ' ';
        boolean validar = false;
        while (validar == false || operacion != 's') {

            String input;
            System.out.println();
            System.out.println("+ Suma");
            System.out.println("- Resta");
            System.out.println("* Multiplicacion");
            System.out.println("/ Division");
            System.out.println("s salir");
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                operacion = 'k';
            } else {
                operacion = input.charAt(0);
            }
            switch (operacion) {
                case '+':
                    operacion = '+';
                    validar = true;
                    break;
                case '-':
                    operacion = '-';
                    validar = true;

                    break;
                case '*':
                    operacion = '*';
                    validar = true;

                    break;
                case '/':
                    operacion = '/';
                    validar = true;
                    break;
                case 's':
                    operacion = 's';
                    break;
                default:
                    System.out.println("SOLO (+,-,*,/,s)");
                    continue;
            }
            break;
        }

        return operacion;
    }

    public static void main(String[] args) {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6789);
        try {

            //Instancia del socket
            Socket socketCliente = new Socket();

            //Emparejado del socket con la dirección y puerto
            socketCliente.connect(dir);
            System.out.println("Conectado al servidor");
            //variables
            char operacion = ' ';
            double operando1 = 0, operando2 = 0;
            double resultado = 0;
            boolean validacionDatos;
            //Instanciamos el Printwriter para la salida de datos
            PrintWriter escritor = new PrintWriter(socketCliente.getOutputStream(), true);
            //Instanciamos el bufferedReader para recibir datos
            BufferedReader lector = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

            while (operacion != 's') {


                //validación y petición del operador

                operacion = validacionOperacion();
                if (operacion == 's') {
                    escritor.println(operacion);
                    escritor.println(String.valueOf(0));
                    escritor.println(String.valueOf(0));
                    break;
                } else {


                    //bloque de validación del primer dato
                    System.out.println("Introduce el  primer operando");
                    operando1 = validacionOperador();

                    //bloque de validación del segundo dato
                    System.out.println("Introduce el  segundo operando");
                    operando2 = validacionOperador();


                    escritor.println(operacion);
                    escritor.println(String.valueOf(operando1));
                    escritor.println(String.valueOf(operando2));

                    sc.nextLine();
                    resultado = Double.parseDouble(lector.readLine());
                    System.out.println();
                    System.out.printf("\n EL RESULTADO ES: %.2f ", resultado);
                }

            }
            socketCliente.close();


        } catch (Exception e) {

        }


    }
}

