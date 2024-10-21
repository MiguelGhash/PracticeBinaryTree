package Main;

import Services.*;
import Models.*;
import javax.swing.JOptionPane;

/**
 *
 * @author BaalG
 */
public class Main {

    public static void main(String[] args) {
        Services Servicio = new Services();
        String Menu[] = {"Agregar a Disco C","Agregar a Disco D","PreOrden Disco C","PreOrden Disco D","Calcular Tamaño Total","Mostrar lista de documentos","Copiar Documento","Crear nuevo árbol","Salir"};
        String Opcion, Nombre;
        int Tamaño;
        do {
            Opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Menú principal", 1, null, Menu, Menu[0]);
            switch (Opcion) {
                case "Agregar a Disco C":
                    Nombre = JOptionPane.showInputDialog("Ingrese el nombre del documento para el Disco C:");
                    Tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del documento para el Disco C:"));
                    Documento documentoC = new Documento(Nombre, Tamaño);
                    Servicio.AgregarADiscoC(documentoC);
                    break;
                case "Agregar a Disco D":
                    Nombre = JOptionPane.showInputDialog("Ingrese el nombre del documento para el Disco D:");
                    Tamaño = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño del documento para el Disco D:"));
                    Documento documentoD = new Documento(Nombre, Tamaño);
                    Servicio.AgregarADiscoD(documentoD);
                    break;
                case "PreOrden Disco C":
                    JOptionPane.showMessageDialog(null, Servicio.PreOrdenDiscoC());
                    break;
                case "PreOrden Disco D":
                    JOptionPane.showMessageDialog(null, Servicio.PreOrdenDiscoD());
                    break;
                case "Calcular Tamaño Total":
                    int TotalSize = Servicio.TotalDocSize();
                    JOptionPane.showMessageDialog(null, "El tamaño total de los documentos en el disco seleccionado es de: " + TotalSize + "MB");
                    break;
                case "Mostrar lista de documentos":
                    JOptionPane.showMessageDialog(null, Servicio.CrearListaDocumentos());
                    System.out.println(Servicio.CrearListaDocumentos());
                    break;
                case "Copiar Documento":
                    Servicio.CopiarDocumento();
                    break;
                case "Crear nuevo árbol":
                    String[] OpcionesOrden = {"InOrder", "PreOrder", "PostOrder"};
                    String OrdenSeleccionado = (String) JOptionPane.showInputDialog(null, "¿En qué orden desea ver el nuevo árbol", "Menú de órden", 1, null, OpcionesOrden, OpcionesOrden[0]);
                    switch (OrdenSeleccionado) {
                        case "InOrder":
                        JOptionPane.showMessageDialog(null,Servicio.CrearArbolBalanceado().InOrder());
                        break;
                        case "PreOrder":
                        JOptionPane.showMessageDialog(null,Servicio.CrearArbolBalanceado().PreOrder());
                        break;
                        case "PostOrder":
                        JOptionPane.showMessageDialog(null,Servicio.CrearArbolBalanceado().PostOrder());
                        break;
               
                        }
                    break;
            }
        } while (!Opcion.equals("Salir"));
    }
}
