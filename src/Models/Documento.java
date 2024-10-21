package Models;

public class Documento {
    private String Name;  
    private int Size;      

    public Documento(String Name, int Size) {
        this.Name = Name;
        this.Size = Size; 
    }

    public String GetName() {
        return Name;
    }

    public void SetName(String Name) {
        this.Name = Name;
    }

    public int GetSize() {
        return Size; 
    }

    public void SetSize(int Size) {
        this.Size = Size;
    }
    @Override
    public String toString() {
        return "Documento{" + "Name=" + Name + ", Size=" + Size + "MB"+'}';
    }

}
