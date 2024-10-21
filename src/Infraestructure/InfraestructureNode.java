package Infraestructure;
import Models.*;

public class InfraestructureNode {
    private Node first;

    public InfraestructureNode(Node first) {
        this.first = first;
    }

    public Node Last() {
        Node aux = first;
        while (aux != null && aux.getLink() != null) {
            aux = aux.getLink();
        }
        return aux;
    }
    public Node Previous(Node Search) {
        Node aux = first, Previo = null;
        while (aux != null && aux!= Search) {
            Previo = aux;
            aux = aux.getLink();
        }
        return Previo;
    }
}
