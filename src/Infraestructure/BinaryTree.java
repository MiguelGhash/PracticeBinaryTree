package Infraestructure;
import Models.*;
import javax.swing.JOptionPane;
public class BinaryTree {
    BinaryNode Root;

    public BinaryTree()
    {
        Root = null;
    }
    public boolean IsTreeEmpty()
    {
        return Root == null;
    }
    public BinaryNode GetRoot()
    {
        return Root;
    }
    private void Add(Object Data, BinaryNode Aux)
    {
        if(IsTreeEmpty())
        {
            Root = new BinaryNode(Data);
        }
        else
        {
            String SideMenu[] = {"Right","Left"};
            String Side =(String)JOptionPane.showInputDialog(null, "Select", "Main menu", 1, null, SideMenu, SideMenu[0]);
            if(Side.equals("Left"))
            {
                if (Aux.getLeft() == null) 
                {
                Aux.setLeft(new BinaryNode(Data));
                }
                else
                {
                Add(Data, Aux.getLeft());
                }
            }
            else
            {
                if(Side.equals("Right"))
                {
                    if (Aux.getRight() == null) 
                    {
                    Aux.setRight(new BinaryNode(Data));
                    }
                    else
                    {
                    Add(Data, Aux.getRight());
                    }
                }   
            }

        }          
    }
    
    public void NewAdd(Object Data) {
        Root = NewAdd(Data, Root);
    }

    private BinaryNode NewAdd(Object Data, BinaryNode Aux) {
        if (Aux == null) {
            return new BinaryNode(Data);
        }
        Documento newDoc = (Documento) Data;
        Documento currentDoc = (Documento) Aux.getData();

        if (newDoc.GetSize() < currentDoc.GetSize()) {
            Aux.setLeft(NewAdd(Data, Aux.getLeft()));
        } else {
            Aux.setRight(NewAdd(Data, Aux.getRight()));
        }

        return Aux;
    }
    public void Add(Object Data)
    {
 
        Add(Data, GetRoot());
    }
    public String PreOrder()
    {
       return PreOrder(GetRoot());
    }
    private String PreOrder(BinaryNode Aux)
    {
        if(Aux!= null)
        {
            return  Aux.getData()+ " " + PreOrder(Aux.getLeft())+" "+PreOrder(Aux.getRight());
        }
        return "";
    } 
    public String InOrder()
    {
       return PostOrder(GetRoot());
    }
    private String InOrder(BinaryNode Aux)
    {
        if(Aux!= null)
        {
            return  PreOrder(Aux.getLeft())+" "+Aux.getData() + " "+PreOrder(Aux.getRight()) ;
        }
        return "";
    } 
    public String PostOrder()
    {
       return PostOrder(GetRoot());
    }
    private String PostOrder(BinaryNode Aux)
    {
        if(Aux!= null)
        {
            return  PostOrder(Aux.getLeft())+" "+PostOrder(Aux.getRight())+" "+ Aux.getData();
        }
        return "";
    }
    public int Size()
    {
        return Size(Root);
    }
    private int Size(BinaryNode Aux)
    {
        int Cont = 0;
       
        if(Aux!= null) 
        {
            return + 1 + (Size(Aux.getLeft())) + (Size(Aux.getRight())); 
        }
         return 0;         
    }
    private int Height(BinaryNode Aux)
    {
        int Cont = 0;
        if(Aux!=null)
            return  1 + Math.max(Height(Aux.getLeft()), Height(Aux.getRight()));
        return 0;
         
    }
    public int Height()
    {
        return Height(Root);
    }
    
    private boolean Search(BinaryNode Aux, Object Data) 
    {
        if (Aux == null) 
        {
            return false;
        }
        if (Aux.getData().equals(Data))
        {
            return true;
        }
        return Search(Aux.getLeft(), Data) || Search(Aux.getRight(), Data);
    }
    public boolean Search(Object Data)
    {
        return Search(Root,Data);
    }
    private BinaryNode SearchNode(BinaryNode Aux, Object Data)
    {
        BinaryNode Result = null;
        if(Aux!=null)
        {
            if(Aux.getData().equals(Data))
            {
                Result = Aux;
            }
            else
            {
               Result = SearchNode(Aux.getLeft(),Data);
               if(Result == null)
               {
                   Result = SearchNode(Aux.getRight(),Data);
               }
            }
        } 
        return Result;
    }
    
    public BinaryNode SearchNode(Object Data)
    {
        return SearchNode(Root, Data);
    }
    private BinaryNode GetFather(BinaryNode Aux, Object Data)
    {
        BinaryNode Father = null;
        if(Aux!= null)
        {
            if(Aux.getLeft()!=null && (Aux.getLeft()).getData().equals(Data) ||Aux.getRight()!=null && (Aux.getRight()).getData().equals(Data)  )
            {
                Father = Aux;
            }
            else
            {
              Father = GetFather(Aux.getLeft(),Data);
              if(Father == null)
              {
                  Father = GetFather(Aux.getRight(),Data);
              }
            }
        }
     return Father;
    }
    
    public BinaryNode GetFather(Object Data)
    {
        if(!IsTreeEmpty())
        {
            if((GetRoot()).getData().equals(Data))
            {
                return null;
            }
            else
            {
                return GetFather(GetRoot(), Data);
            }
        }
     return null;
    }
    private List Sucessor(Object Data, BinaryNode Aux, List List)
    {
        if(Aux!=null)
        {
         if(Aux.getData().equals(Data))
         {
             List.AddLast(Aux.getData());
             Sucessor(Data,Aux.getLeft(),List);
             Sucessor(Data,Aux.getRight(),List);
         }
        }
        return List;
    }
    public List Sucessor(Object Data)
    {
        BinaryNode Init = SearchNode(Data);
        if(Init!=null)
        {
            return Sucessor(Data, Init, new List());
        }else
        {
            return null;
        }
            
    }
    public boolean Delete(Object Data)
    {
        BinaryNode Delete = SearchNode(Data);
        if(Delete!=null)
        {
            if(Delete.getLeft()==null && Delete.getRight()==null)
            {
                DeleteZeroChild(Delete);
            }
            else
            {
                if(Delete.getLeft()==null || Delete.getRight()==null)
                {
                    DeleteOne(Delete);
                }
                else
                {
                    DeleteTwo(Delete);
                }
               
            }
            return true;
        }
        return false;
    }
    private void DeleteZeroChild(BinaryNode Delete) 
    {
            BinaryNode Father = GetFather(Delete.getData());
            if (Father == null)
            {
                Root = null; 
            } 
            else 
            {
                if (Father.getLeft() == Delete) 
                {
                    Father.setLeft(null);
                }
                else 
                {
                    Father.setRight(null); 
                }
        }
    }
    

    private void DeleteOne(BinaryNode Delete)
    {
        BinaryNode Father = GetFather(Delete.getData());
        if(Father == null)
        {
            if(Delete.getLeft()!=null)
            {
                Root = Delete.getLeft();
            }
            else
            {
               Root = Delete.getRight();
            }
        }
        else
        {
            if(Father.getLeft()==Delete)
            {
                if(Delete.getLeft()!=null)
                {
                    Father.setLeft(Delete.getLeft());
                }
                else
                {
                    Father.setLeft(Delete.getRight());
                }
            }
            else
            {
                if(Delete.getRight()!=null)
                {
                    Father.setRight(Delete.getLeft());
                }
                else
                {
                    Father.setRight(Delete.getLeft());
                } 
            }
        }
    }

    private void DeleteTwo(BinaryNode Delete)
    { 
           String Value = (String)(MostLeft(Delete)).getData();
           Delete(Value);
           Delete.setData(Value);
    }
    private BinaryNode MostLeft(BinaryNode Aux)
    {
        if(Aux.getLeft()!=null)
        {
            return MostLeft(Aux.getLeft());
        }
        return Aux;
    }
        public int TotalDocSize() {
        return TotalDocSize (Root);
    }

    private int TotalDocSize(BinaryNode Aux) {
        if (Aux == null) {
            return 0;
        }
        Documento Doc = (Documento) Aux.getData();
        return Doc.GetSize() + TotalDocSize(Aux.getLeft()) + TotalDocSize(Aux.getRight());
    }
    
    
}