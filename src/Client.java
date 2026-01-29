import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket s = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Valute: EUR, USD, GBP, JPY, CHF");

            System.out.print("Da: ");
            out.println(sc.nextLine().toUpperCase());

            System.out.print("A: ");
            out.println(sc.nextLine().toUpperCase());

            System.out.print("Importo: ");
            out.println(sc.nextLine());

            System.out.println(in.readLine());

        } catch (Exception e) {
            System.out.println("Errore: Il server è spento.");
        }
    }
}