import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class infosalas {
    private JPanel panel1;
    public JPanel getPanel1(){return panel1;}
    private JTextPane sala1Edificio1UJATextPane;
    private JTextPane sala2Edificio2UJATextPane;
    private JTextPane sala3Edificio2UJATextPane;
    private JTextPane salaConEspacioParaTextPane3;
    private JTextPane salaConEspacioParaTextPane2;
    private JTextPane salaConEspacioParaTextPane1;
    private JButton button1;
    private JLabel img1;
    private JLabel img2;
    private JLabel img3;
    private JButton button2;
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

        int i =6;
        sala1Edificio1UJATextPane.setText(v[idioma][i++]);
        salaConEspacioParaTextPane1.setText(v[idioma][i++]);
        sala2Edificio2UJATextPane.setText(v[idioma][i++]);
        salaConEspacioParaTextPane2.setText(v[idioma][i++]);
        sala3Edificio2UJATextPane.setText(v[idioma][i++]);
        salaConEspacioParaTextPane3.setText(v[idioma][i]);
        Icon icon = new ImageIcon(v[idioma][30]);
        img1.setIcon(icon);
        img2.setIcon(icon);
        img3.setIcon(icon);
        icon = new ImageIcon(v[idioma][31]);
        button1.setIcon(icon);
        icon = new ImageIcon(v[idioma][32]);
        button2.setIcon(icon);
    }

    public infosalas(){

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
}
