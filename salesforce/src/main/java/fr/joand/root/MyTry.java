package fr.joand.root;

import java.util.*;
import java.util.regex.Pattern;

public class MyTry {


    public static void main(String[] argv) throws Exception {
        Pattern validPhone = Pattern.compile("\\+\\d{1,3}\\-\\d{9,11}");

        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] paysDeMaZone = sc.nextLine().split(";");
        List<String> validZones = Arrays.asList(paysDeMaZone);

        Set<Client> allCustomer = new HashSet<>();

        int nbDoublon = 0;
        int nbInvalidTelephone = 0;
        int nbHorsZone = 0;

        for (int i = 0; i < N; i++) {
            String[] client = sc.nextLine().split(";");
            Client customer = new Client(client[0], client[1], client[2], client[3], client[4]);
            boolean isUnique = allCustomer.add(customer);
            if (!isUnique) {
                nbDoublon++;
            } else {
                if (!validPhone.matcher(customer.getTelephone()).matches()) {
                    nbInvalidTelephone++;
                }
                if (!validZones.contains(customer.getPays())) {
                    nbHorsZone++;
                }
            }
        }
        System.out.println(nbDoublon + " " + nbInvalidTelephone + " " + nbHorsZone);

    }
}

class Client {

    public Client(String nom, String prenom, String societe, String telephone, String pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.societe = societe;
        this.telephone = telephone;
        this.pays = pays;
    }

    String nom;
    String prenom;
    String societe;

    String telephone;
    String pays;

    public String getTelephone() {
        return telephone;
    }

    public String getPays() {
        return pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nom, client.nom) &&
                Objects.equals(prenom, client.prenom) &&
                Objects.equals(societe, client.societe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, societe);
    }
}
