import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class ResCanc {
    private JButton reservarButton;
    private JPanel panel1;
    private JButton cancelarButton;
    public JPanel getPanel1(){ return panel1; }
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

        int i =12;
        reservarButton.setText(v[idioma][i++]);
        cancelarButton.setText(v[idioma][i]);
        Icon icono = new ImageIcon(v[idioma][33]);
        cancelarButton.setIcon(icono);
        icono = new ImageIcon(v[idioma][34]);
        reservarButton.setIcon(icono);

    }

    public ResCanc(){

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

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reservar reservita = new Reservar();
                JFrame frame = new JFrame("Reservar");
                frame.setContentPane(reservita.getPanel1());
                frame.pack();
                frame.pack();
                centerFrame(frame);
                frame.setVisible(true);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cancelar reservita = new Cancelar();
                JFrame frame = new JFrame("Cancelar");
                frame.setContentPane(reservita.getPanel1());
                frame.pack();
                frame.pack();
                centerFrame(frame);
                frame.setVisible(true);
            }
        });
    }
    private static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setLocation(x, y);
    }
}
