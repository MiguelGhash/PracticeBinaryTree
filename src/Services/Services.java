package Services;
import Infraestructure.*;
import Models.*;
import javax.swing.*;

public class Services {
    private BinaryTree DiscoC = new BinaryTree();
    private BinaryTree DiscoD = new BinaryTree();
    private List ListaDocumentos = new List();
    
    public void AgregarADiscoC(Documento Doc) {
        DiscoC.Add(Doc);
    }

    public void AgregarADiscoD(Documento Doc) {
        DiscoD.Add(Doc);
    }

    public String PreOrdenDiscoC() {
        return DiscoC.PreOrder();
    }

    public String PreOrdenDiscoD() {
        return DiscoD.PreOrder();
    }

    public int TotalDocSize() {
        String Discos[] = {"Disco C", "Disco D"};
        String Disco =(String)JOptionPane.showInputDialog(null, "Select", "Selecciona el disco", 1, null, Discos, Discos[0]);
        
        if (Disco.equalsIgnoreCase("Disco C")) {
            return DiscoC.TotalDocSize();
        } else if (Disco.equalsIgnoreCase("Disco D")) {
            return DiscoD.TotalDocSize();
        } else {
            return 0;
        }
    }
    public String CrearListaDocumentos() {
        ListaDocumentos = new List(); 
        EnlistarDocumentos(DiscoC.GetRoot());
        EnlistarDocumentos(DiscoD.GetRoot());
    
        return ListaDocumentos.ToString(); 
    }
    private void EnlistarDocumentos(BinaryNode Aux) {
        if (Aux != null) {
            Documento Doc = (Documento) Aux.getData();
           if (!DocumentoYaEnLista(Doc)) {
                ListaDocumentos.Enlist(Doc); 
            }

            EnlistarDocumentos(Aux.getLeft());
            EnlistarDocumentos(Aux.getRight());
        }
    }
 
    private boolean DocumentoYaEnLista(Documento Doc) {
        Node Aux = ListaDocumentos.GetFirst(); 
        while (Aux != null) {
            Documento Existente= (Documento) Aux.getData();
            if (Existente.GetName().equals(Doc.GetName()) && Existente.GetSize() == Doc.GetSize()) {
                return true; 
            } 
            Aux = Aux.getLink(); 
        }
        return false; 
    }
    public void CopiarDocumento() {
        String Discos[] = {"Disco C", "Disco D"};
        String DiscoOrigen = (String) JOptionPane.showInputDialog(null, "Selecciona el disco de origen", "Menú disco de origen", 1, null, Discos, Discos[0]);

        if (DiscoOrigen.equals("Disco C") && DiscoC.TotalDocSize() == 0) {
            JOptionPane.showMessageDialog(null, "No hay documentos en Disco C :c");
            return; 
        } else if (DiscoOrigen.equals("Disco D") && DiscoD.TotalDocSize() == 0) {
            JOptionPane.showMessageDialog(null, "No hay documentos en Disco D :c");
            return; 
        }
        CrearListaDocumentos();
        Node Aux = ListaDocumentos.GetFirst(); 
        String[] MenuDocumentos = new String[ListaDocumentos.Size()]; 

        int Index = 0;
        while (Aux != null) {
            Documento Doc = (Documento) Aux.getData();
            MenuDocumentos[Index++] = Doc.GetName(); 
        }
        String DiscoDestino = (String) JOptionPane.showInputDialog(null, "Selecciona el disco de destino", "Menú disco de origen", 1, null, Discos, Discos[0]);
        String DocumentoSeleccionado = (String) JOptionPane.showInputDialog(null, "Selecciona el documento a copiar", "Seleccionar Documento",1, null, MenuDocumentos, MenuDocumentos[0]);

        if (DocumentoSeleccionado != null) {
            Node NodoDocumento = ListaDocumentos.GetFirst();
            while (NodoDocumento != null) {
                Documento doc = (Documento) NodoDocumento.getData();
                if (doc.GetName().equals(DocumentoSeleccionado)) {
                    if (DiscoDestino.equals("Disco C")) {
                        AgregarADiscoC(doc); 
                    } else {
                        AgregarADiscoD(doc); 
                    }
                    if (DiscoOrigen.equals("Disco C")) {
                        DiscoC.Delete(doc); 
                    } else {
                        DiscoD.Delete(doc);
                    }

                    JOptionPane.showMessageDialog(null, "Documento copiado exitosamente.");
                    break;
                }
                NodoDocumento = NodoDocumento.getLink(); // Mover al siguiente nodo
            }
        }
    }
    public BinaryTree CrearArbolBalanceado() {
        BinaryTree Balance = new BinaryTree();
        CrearListaDocumentos();
        int TotalDocumentos = ListaDocumentos.Size();
        AdicionarElementosBalanceado(Balance, ListaDocumentos, 0, TotalDocumentos - 1);
        
        return Balance;
    }

    private void AdicionarElementosBalanceado(BinaryTree AuxTree, List Lista, int Inicio, int Fin) {
        if (Inicio > Fin) {
            return;
        }
        int Medio = (Inicio + Fin) / 2;
        Node Aux = Lista.GetFirst();
        for (int i = 0; i < Medio; i++) {
            Aux = Aux.getLink();
        }
        Documento Doc = (Documento) Aux.getData();
        AuxTree.NewAdd(Doc); 
        AdicionarElementosBalanceado(AuxTree, Lista, Inicio, Medio - 1);
        AdicionarElementosBalanceado(AuxTree, Lista, Medio + 1, Fin);
    }

}  

