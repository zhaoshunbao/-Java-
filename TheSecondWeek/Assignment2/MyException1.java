//检测链表为空或者待删除元素不在链表中的错误
public class MyException1 extends Exception{
    private Node node;
    private  String data;
    public MyException1(Node node1,String data1){
        node=node1;
        data=data1;
    }
    public void errorNode(){
        if(node==null){
            System.out.println("该链表为空，无法删除！");
        }
        if(node.doubleSearch(data)==false){
            System.out.println("该元素"+data+"在链表中不存在，操作有误！");
        }
    }
}
