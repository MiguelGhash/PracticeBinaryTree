/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestructure;

import Models.*;

public class List {
    private Node first;
    private InfraestructureNode InfraNode;

    public List() {
        this.InfraNode = new InfraestructureNode(first);
    }
    public Node GetFirst()
    {
        return first;
    }

    // Comprobar si la lista está vacía
    public boolean IsEmpty() {
        return first == null;
    }

    // Agregar al inicio de la lista 
    public void AddFirst(Object Obj) {
        Node aux = first;
        if (IsEmpty())
            first = new Node(Obj);
        else {
            Node n = new Node(Obj);
            n.setLink(first);
            first = n;
        }

        InfraNode = new InfraestructureNode(first);
    }


    public void AddLast(Object Obj) {
        if (IsEmpty()) 
        {
            AddFirst(Obj);
        } else 
        {
            Node Nuevo = new Node(Obj);
            Node Final = InfraNode.Last();
            if (Final != null)
            {
                Final.setLink(Nuevo);
            }
        }
    }


    public void Enlist(Object Obj) {
        AddLast(Obj);
    }
    public boolean DeleteFirst() {
        if (!IsEmpty()) {
            Node aux = first;
            first = first.getLink();
            aux = null;
            return true;
        }
        return false;
    }


    public boolean Delete(Object Obj) {
        Node aux = first;
        if (!IsEmpty()) {
            while (aux != null && !aux.getData().equals(Obj)) {
                aux = aux.getLink();
            }
            if (aux == null) {
                return false;
            } else {
                if (aux == first)
                    DeleteFirst();
                else {
                    Node Previo = InfraNode.Previous(aux);
                    Previo.setLink(aux.getLink());
                    aux = null;
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean DeleteLast() {
        if (!IsEmpty()) {
            Node Final = InfraNode.Last();
            Node Previo = InfraNode.Previous(Final);
            if (Previo != null) {
                Previo.setLink(null);   
            } else {
                first = null;
            }
            return true;
        }
        return false;
    }
    public String ToString() {
        String text = "";
        Node aux = first;

        while (aux != null) {
            text = text + aux.getData() + "\n";
            aux = aux.getLink();
        }
        return text;
    }
    public int Size() {
        int Count = 0;
        Node aux = first;
        while (aux != null) {
            Count = Count + 1;
            aux = aux.getLink();
        }
        return Count;
    }
    public void InsertarEnPosicion(int Posicion, Object Obj) {
        if (Posicion == 1) {
            AddFirst(Obj);
        } else {
            if (Posicion == Size() + 1) {
                AddLast(Obj);  
            } else {
                int Count = 1;
                Node aux = first;
                while (aux != null && Count < Posicion) {
                    aux = aux.getLink();
                    Count++;
                }
                Node Nuevo = new Node(Obj);
                Nuevo.setLink(aux);
                Node Previo = InfraNode.Previous(aux);
                Previo.setLink(Nuevo);
            }
        }         
    }
}
