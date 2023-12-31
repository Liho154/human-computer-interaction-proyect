import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.*;
import java.io.*;


public class Cancelar extends JFrame{
    private JButton aceptarButton;
    private JButton limpiarButton;
    private JTextPane tusReservasTextPane;
    private JPanel panel1;
    private JPanel panel2;
    private JButton selectAllButton;
    Vector<JCheckBox> checkCancelar;
    public JPanel getPanel1(){ return panel1; }
    private homepage todo;
    private User usuario1;
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

        int i =21;
        tusReservasTextPane.setText(v[idioma][i++]);
        selectAllButton.setText(v[idioma][i++]);
        limpiarButton.setText(v[idioma][i++]);
        aceptarButton.setText(v[idioma][i]);

    }
    public Cancelar() {

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


        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean cancelar = false;
                for (int i = 0; i < checkCancelar.size(); i++) {

                    if (checkCancelar.get(i).isSelected()) {
                        String nombreArchivo = "src/reservas.txt";
                        String lineaABorrar = usuario1.getReservas().get(i).getNombreSala() + ";" + usuario1.getReservas().get(i).getAnio() + ";" + usuario1.getReservas().get(i).getMes() + ";" + usuario1.getReservas().get(i).getDia() + ";" + usuario1.getReservas().get(i).getHora();
                        String nombreTemp = "src/temp.txt";
                        try {
                            // Abrir el archivo original en modo lectura
                            BufferedReader archivoLectura = new BufferedReader(new FileReader(nombreArchivo));

                            // Abrir el archivo temporal en modo escritura
                            BufferedWriter archivoEscritura = new BufferedWriter(new FileWriter(nombreTemp));

                            String linea;
                            // Leer cada línea del archivo original
                            while ((linea = archivoLectura.readLine()) != null) {
                                // Si no es la línea que deseas borrar, escribir esa línea en el archivo temporal
                                if (!linea.equals(lineaABorrar)) {
                                    archivoEscritura.write(linea);
                                    archivoEscritura.newLine();
                                }
                            }

                            // Cerrar ambos archivos
                            archivoLectura.close();
                            archivoEscritura.close();

                            // Eliminar el archivo original
                            File archivoOriginal = new File(nombreArchivo);
                            archivoOriginal.delete();

                            // Renombrar el archivo temporal con el nombre del archivo original
                            File archivoTemp = new File(nombreTemp);
                            archivoTemp.renameTo(archivoOriginal);

                            System.out.println("Línea borrada del archivo.");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        cancelar = true;
                    }
                }
                if (cancelar) {
                    JOptionPane.showMessageDialog(null, "Reservas canceladas con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha cancelado ninguna reserva");
                }
                if(cancelar) {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                    frame.dispose();
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < checkCancelar.size(); i++) {
                    checkCancelar.get(i).setSelected(false);
                }
            }
        });

        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < checkCancelar.size(); i++) {
                    checkCancelar.get(i).setSelected(true);
                }
            }
        });
    }

    private void createUIComponents() {

        File file2 = new File("src/codigoIdioma.txt");
        String codigoIdioma = "";

        try {
            Scanner scanner = new Scanner(file2);

            while (scanner.hasNextLine()) {
                codigoIdioma = scanner.nextLine();
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo.");
            e.printStackTrace();
        }

        usuario1 = new User("pepe", "ppp007@red.ujaen.es",new Vector<Reservas>());

        /**
         * carcar las reservas del usuario
         */
        try {
            File file = new File("src/reservas.txt");
            Scanner scanner = new Scanner(file);
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
        checkCancelar = new Vector<>();

        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        if(usuario1.getReservas().size()==0){
            if(codigoIdioma.equals("0")) {
                panel2.add(new JLabel("No tienes reservas"));
            }
            else{
                panel2.add(new JLabel("No Bookings"));
            }
        }
        else {
            for (int i = 0; i < usuario1.getReservas().size(); i++) {
                panel2.add(new JLabel(" Nombre sala: "));
                panel2.add(new JLabel(" " + usuario1.getReservas().get(i).getNombreSala()));

                panel2.add(new JLabel(" Fecha: "));
                panel2.add(new JLabel(" " + usuario1.getReservas().get(i).getAnio() + "/" + usuario1.getReservas().get(i).getMes() + "/" + usuario1.getReservas().get(i).getDia()));

                panel2.add(new JLabel(" Hora: "));
                panel2.add(new JLabel(" " + usuario1.getReservas().get(i).getHora()));
                checkCancelar.add(new JCheckBox("Borrar", false));

                panel2.add(checkCancelar.get(i));
                panel2.add(new JSeparator(JSeparator.HORIZONTAL));

            }
        }
    }
}
