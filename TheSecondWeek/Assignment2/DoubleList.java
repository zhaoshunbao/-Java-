public class DoubleList {
    private Node head;
    //往前添加元素
    public void pAddNode(String name){
        Node newNode=new Node(name);
        if(this.head==null){
            this.head=newNode;
        }
        else{
            this.head.pAdd(newNode);
        }
    }
    //往后添加元素
    public void nAddNode(String name){
        Node newNode=new Node(name);
        if(this.head==null){
            this.head=newNode;
        }
        else{
            this.head.nAdd(newNode);
        }
    }
    //打印元素
    public void print(){
        if(this.head!=null){
            this.head.doublePrint();
        }
    }
    //查找
    public boolean contains(String name){
        return this.head.doubleSearch(name);
    }
    //删除
    public void delete(String name){
            try {
                this.head.doubleDelete(name);
            }catch (MyException1 e){
                e.errorNode();
            }
    }
}
