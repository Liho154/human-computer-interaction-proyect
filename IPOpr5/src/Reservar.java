import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Reservar {
    private JPanel panel1;
    private JTextPane seleccionaAulaTextPane;
    private JComboBox comboBox1;
    private JPanel panel2;
    private JButton reservarButton;
    private JButton limpiarButton;
    private JTextField introduceFechaTextField;
    private JRadioButton a1000RadioButton;
    private JRadioButton a1100RadioButton;
    private JRadioButton a1300RadioButton;
    private JRadioButton a1400RadioButton;
    private JRadioButton a1200RadioButton;
    private JRadioButton a1600RadioButton;
    private JRadioButton a1700RadioButton;
    private JRadioButton a1800RadioButton;
    private JRadioButton a1900RadioButton;
    private JTextPane seleccionaFechaTextPane;
    private ButtonGroup buttonGroup1;

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

        int i =14;
        seleccionaAulaTextPane.setText(v[idioma][i++]);
        comboBox1.removeAllItems();
        comboBox1.addItem(v[idioma][i++]);
        comboBox1.addItem(v[idioma][i++]);
        comboBox1.addItem(v[idioma][i++]);
        seleccionaFechaTextPane.setText(v[idioma][i++]);
        limpiarButton.setText(v[idioma][i++]);
        reservarButton.setText(v[idioma][i]);

    }

    public Reservar(){

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

        buttonGroup1 = new ButtonGroup();
        introduceFechaTextField.setText("");
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonGroup1.clearSelection();
                comboBox1.setSelectedIndex(0);
                introduceFechaTextField.setText("");
            }
        });

        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isValidDate(introduceFechaTextField.getText())) {

                    String[] fecha = new String[3];
                    fecha[0] = "";
                    fecha[1] = "";
                    fecha[2] = "";

                    int j = 0;
                    for (int i = 0; i < introduceFechaTextField.getText().length(); i++) {
                        if (introduceFechaTextField.getText().charAt(i) != '/') {
                            fecha[j] += introduceFechaTextField.getText().charAt(i);
                        } else
                            j++;
                    }
                    for(int i =0; i<3; i++){
                        System.out.println(fecha[i]);
                    }

                    JRadioButton selectedRadioButton = getSelectedRadioButton();

                    if(selectedRadioButton != null){
                        Reservas reserva1 = new Reservas(comboBox1.getSelectedItem().toString(),
                                Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]),
                                selectedRadioButton.getText());

                        String archivo = "src/reservas.txt";
                        try {
                            // Abrir el archivo en modo de escritura al final
                            FileWriter fw = new FileWriter(archivo, true);

                            // Escribir el dato deseado en la última línea
                            String dato = reserva1.getNombreSala() + ";" + reserva1.getAnio() + ";" + reserva1.getMes() + ";" + reserva1.getDia() + ";" + reserva1.getHora();
                            fw.write(dato);
                            fw.write(System.lineSeparator()); // Agregar un salto de línea

                            // Cerrar el archivo
                            fw.close();

                            System.out.println("Dato escrito en la última línea del archivo.");
                            JOptionPane.showMessageDialog(null, "Reserva añadida con exito");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Selecciona una hora");
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Fecha no valida");
            }
        });
    }

    public static boolean isValidDate(String date) {
        try {
            String[] parts = date.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (year < 1000 || year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) {
                return false;
            }

            // Validar febrero (considerando años bisiestos)
            if (month == 2) {
                if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                    return (day <= 29);
                } else {
                    return (day <= 28);
                }
            }

            // Validar meses con 30 días
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                return (day <= 30);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public JRadioButton getSelectedRadioButton() {
        if (a1000RadioButton.isSelected()) {
            return a1000RadioButton;
        } else if (a1100RadioButton.isSelected()) {
            return a1100RadioButton;
        } else if (a1300RadioButton.isSelected()) {
            return a1300RadioButton;
        } else if (a1400RadioButton.isSelected()) {
            return a1400RadioButton;
        } else if (a1200RadioButton.isSelected()) {
            return a1200RadioButton;
        } else if (a1600RadioButton.isSelected()) {
            return a1600RadioButton;
        } else if (a1700RadioButton.isSelected()) {
            return a1700RadioButton;
        } else if (a1800RadioButton.isSelected()) {
            return a1800RadioButton;
        } else if (a1900RadioButton.isSelected()) {
            return a1900RadioButton;
        } else {
            return null;
        }
    }
}
