import javax.swing.*;
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.awt.event.*;
import java.lang.String;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.awt.*;

public class homepage{

    private User usuario1 = new User("pepe", "ppp007@red.ujaen.es",new Vector<Reservas>());

    public User getUsuario(){
        return this.usuario1;
    }
    private JMenuBar menuBar;

    public JMenuBar getMenuBar() {
            return menuBar;
        }

    private JMenu menu1;
    private JMenu item1idioma;
    private JMenuItem item2cerrar;
    private JMenuItem ES = new JMenuItem("Español");
    private JMenuItem EN = new JMenuItem("Ingles");
    private int iIdiomas;
    private int jIdiomas;
    String codigoIdioma;
    private String[][] idiomas = new String[10][100];
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

    public void cambiaridiomas(String[][] v, int idioma, JMenu aplic, JMenu idiom, JMenuItem exit){

        int i =1;
        TextArea1.setText(v[idioma][i++]);
        TextArea2.setText(v[idioma][i++]);
        TextArea3.setText(v[idioma][i++]);
        TextArea4.setText(v[idioma][i++]);
        TextArea5.setText(v[idioma][i]);
        ImageIcon icono = new ImageIcon(v[idioma][29]);
        label1.setDisabledIcon(icono);
        homeimg.setDisabledIcon(icono);
        icono = new ImageIcon(v[idioma][36]);
        reservarButton.setIcon(icono);
        icono = new ImageIcon(v[idioma][37]);
        salasButton.setIcon(icono);
        icono = new ImageIcon(v[idioma][38]);
        userButton.setIcon(icono);
        aplic.setText(v[idioma][26]);
        idiom.setText(v[idioma][27]);
        exit.setText(v[idioma][28]);

    }
    private JButton homeButton;
    private JPanel panel1;
    public JPanel getPanel1(){
            return panel1;
        }
    private JButton salasButton;
    private JTextArea TextArea3;
    private JTextArea TextArea4;
    private JTextArea TextArea5;
    private JButton userButton;
    private JButton reservarButton;
    private JPanel imgPanel;
    private JLabel label1;
    private JTextArea TextArea1;
    private JTextPane TextArea2;
    private JLabel homeimg;
    private JPanel JPanelHome;
    private ImageIcon image1;
    private JPanel JPanelReservas;
    private JButton Reservar;
    private JButton Cancelar;
    private CardLayout cardLayout1;

    public homepage() {

        /**
         * @brief: llamada a la funcion para llamar a la carga de los idiomas
         */
        File file = new File("src/codigoIdioma.txt");
        codigoIdioma = "";

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                codigoIdioma = scanner.nextLine();
            }
            scanner.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo.");
            ex.printStackTrace();
        }

        /**
         * @brief: declaracion de la barra de menu
         */
        menuBar = new JMenuBar();
        menu1 = new JMenu("Aplicación");
        item1idioma = new JMenu("Idioma");
        item2cerrar = new JMenuItem("Cerrar");

        cargaridiomas(idiomas, iIdiomas, jIdiomas);
        cambiaridiomas(idiomas, Integer.parseInt(codigoIdioma), menu1, item1idioma, item2cerrar);

        for(int i = 0; i <= iIdiomas; i++){
            final int idiomaSeleccion = i;
            String nombreidioma = idiomas[i][0];
            JMenuItem item = new JMenuItem(nombreidioma);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String archivo = "src/codigoIdioma.txt";
                    String  nuevoDato = Integer.toString(idiomaSeleccion);

                    try {
                        // Abrir el archivo en modo de escritura
                        FileWriter fw = new FileWriter(archivo);

                        // Escribir el nuevo dato en el archivo
                        fw.write(nuevoDato);

                        // Cerrar el archivo
                        fw.close();

                        System.out.println("Dato escrito en el archivo, reemplazando su contenido existente.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    cambiaridiomas(idiomas, idiomaSeleccion, menu1, item1idioma, item2cerrar);
                }
            });
            item1idioma.add(item);
        }
        item2cerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu1.add(item1idioma);
        menu1.add(item2cerrar);
        menuBar.add(menu1);

        /**
         * @brief: declaración de imagenes de botones
         */
        homeButton.setIcon(new ImageIcon("C:\\Users\\Julio GR\\Desktop\\Asignaturas uni\\3º curso\\IPO\\pr5\\IPOpr5\\src\\Iconos\\inicio-removebg-preview.png"));
        salasButton.setIcon(new ImageIcon("C:\\Users\\Julio GR\\Desktop\\Asignaturas uni\\3º curso\\IPO\\pr5\\IPOpr5\\src\\Iconos\\training.png"));
        reservarButton.setIcon(new ImageIcon("C:\\Users\\Julio GR\\Desktop\\Asignaturas uni\\3º curso\\IPO\\pr5\\IPOpr5\\src\\Iconos\\reserva-removebg-preview.png"));
        userButton.setIcon(new ImageIcon("C:\\Users\\Julio GR\\Desktop\\Asignaturas uni\\3º curso\\IPO\\pr5\\IPOpr5\\src\\Iconos\\usuario-removebg-preview.png"));

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanelHome.setVisible(true);
        }
    });

        salasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infosalas salas = new infosalas();
                JFrame frame = new JFrame("UJA Salas Info");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setContentPane(salas.getPanel1());
                frame.pack();
                centerFrame(frame);
                frame.setVisible(true);
            }
        });

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResCanc reservita = new ResCanc();
                JFrame frame = new JFrame("Reservar y cancelar reservas");
                frame.setContentPane(reservita.getPanel1());
                frame.pack();
                frame.setResizable(false);
                centerFrame(frame);
                frame.setVisible(true);
            }
        });
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario user = new usuario();
                JFrame frame = new JFrame("UJA Lobby");
                frame.setContentPane(user.getPanel1());
                frame.pack();
                frame.setResizable(false);
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

    private void createUIComponents() {
        label1 = new JLabel();
        homeimg = new JLabel();
    }
}
