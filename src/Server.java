import java.net.*;
import java.io.*;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) {
        HashMap<String, Double> cambi = new HashMap<>();
        // Euro
        cambi.put("EURUSD", 1.10); cambi.put("EURGBP", 0.85);
        cambi.put("EURJPY", 160.0); cambi.put("EURCHF", 0.95);
        // Dollaro
        cambi.put("USDEUR", 0.91); cambi.put("USDGBP", 0.78);
        cambi.put("USDJPY", 145.0); cambi.put("USDCHF", 0.87);
        // Sterlina
        cambi.put("GBPEUR", 1.17); cambi.put("GBPUSD", 1.28);
        cambi.put("GBPJPY", 185.0); cambi.put("GBPCHF", 1.11);

        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Server pronto (EUR, USD, GBP, JPY, CHF)...");

            while (true) {
                try (Socket client = server.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    String da = in.readLine();
                    String a = in.readLine();
                    double importo = Double.parseDouble(in.readLine());

                    if (da.equals(a)) {
                        out.println("Risultato: " + importo + " (Stessa valuta)");
                    } else if (cambi.containsKey(da + a)) {
                        double risultato = importo * cambi.get(da + a);
                        out.println("Risultato: " + risultato + " " + a);
                    } else {
                        out.println("Cambio non disponibile.");
                    }
                } catch (Exception e) { System.out.println("Errore client."); }
            }
        } catch (Exception e) {
            System.out.println("Porta occupata! Chiudi i processi Java attivi.");
        }
    }
}