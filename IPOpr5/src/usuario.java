import javax.swing.*;
import java.awt.Color;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class usuario {
    private User usuario1;
    private JPanel panel1;
    private JLabel labelUserImg;
    private JPanel JPanelReservas;
    private JTextPane reservasTextPane;
    private JPanel panel2;
    private int iIdiomas = 10;
    private int jIdiomas = 100;
    private String[][] idiomas = new String[iIdiomas][jIdiomas];
    public static boolean esEntero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private void cargaridiomas(String[][] v, int tami, int tamj) {

        String linea;
        int j = 0, i = 0;

        try (BufferedReader branch = new BufferedReader(new FileReader("src/Idiomas.txt"))) {
            while ((linea = branch.readLine()) != null) {
                if(linea.length()==2 && !linea.equals("ES") && !esEntero(linea)) {
                    i++;
                    j=0;
                }
                // Convierte la línea a un entero y almacénalo en el vector
                if (!esEntero(linea)) {
                    v[i][j] = linea;
                    j++;
                }
            }
            iIdiomas = i;
            jIdiomas = j;
        }
        catch(IOException | NumberFormatException e){
            e.printStackTrace();
        }
    }

    public void cambiaridiomas(String[][] v, int idioma, int tam){

        int i =25;
        reservasTextPane.setText(v[idioma][i]);
        Icon icono = new ImageIcon(v[idioma][38]);
        labelUserImg.setIcon(icono);

    }
    public JPanel getPanel1(){ return panel1; }

    public usuario(){
        File file = new File("src/codigoIdioma.txt");
        String codigoIdioma = "";

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                codigoIdioma = scanner.nextLine();
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
            e.printStackTrace();
        }

        cargaridiomas(idiomas, iIdiomas, jIdiomas);
        cambiaridiomas(idiomas, Integer.parseInt(codigoIdioma), jIdiomas);
    }

    private void createUIComponents() {

        usuario1 = new User("pepe", "ppp007@red.ujaen.es",new Vector<Reservas>());

        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(new JLabel(" "+usuario1.getUsername()+" "));
        panel2.add(new JLabel(" "+usuario1.getEmail()+" "));

        /**
         * carcar las reservas del usuario
         */
        try {
            File file2 = new File("src/reservas.txt");
            Scanner scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(";");

                // Crear un objeto RegistroSala con los valores de la línea
                String sala = values[0];
                int anio = Integer.parseInt(values[1]);
                int valor1 = Integer.parseInt(values[2]);
                int valor2 = Integer.parseInt(values[3]);
                String hora = values[4];

                Reservas reserva = new Reservas(sala, anio, valor1, valor2, hora);
                usuario1.getReservas().add(reserva);
            }
            // Cerrar el Scanner
            scanner.close();
        }
        catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        JPanelReservas = new JPanel();
        JPanelReservas.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JPanelReservas.setLayout(new BoxLayout(JPanelReservas, BoxLayout.Y_AXIS));

        for(int i = 0; i < usuario1.getReservas().size(); i++){
            JPanelReservas.add(new JLabel(" Sala: "+usuario1.getReservas().get(i).getNombreSala()+" "));
            JPanelReservas.add(new JLabel(" Fecha: "+Integer.toString(usuario1.getReservas().get(i).getAnio())+"/"+Integer.toString(usuario1.getReservas().get(i).getMes())+"/"+Integer.toString(usuario1.getReservas().get(i).getDia())+" "));
            JPanelReservas.add(new JLabel(" Hora: "+usuario1.getReservas().get(i).getHora()+" "));
            JPanelReservas.add(new JSeparator(JSeparator.HORIZONTAL));

        }

    }
}
