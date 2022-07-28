public class Test {
    public static void main(String args[]){
        DoubleList list=new DoubleList();
        list.nAddNode("赵云");
        list.pAddNode("马超");
        list.nAddNode("黄忠");
        list.nAddNode("诸葛亮");
        list.pAddNode("关羽");
        list.nAddNode("张飞");
        list.pAddNode("孙尚香");
        list.pAddNode("花木兰");
        list.pAddNode("武则天");
        System.out.println("未删除前打印：");
        list.print();
        System.out.println("\n"+"----------------------------------------------------");
        System.out.println("查找");
        boolean flag1=list.contains("赵云");
        boolean flag2=list.contains("狄仁杰");
        System.out.println(flag1);
        System.out.println(flag2);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("删除后情况:");
        list.delete("孙尚香");
        list.delete("张飞");
        list.delete("李白");
        list.print();
        System.out.println("\n----------------------------------------------------------");
        System.out.println("删除后查找：");
        boolean flag3=list.contains("张飞");
        boolean flag4=list.contains("孙尚香");
        boolean flag5=list.contains("花木兰");
        System.out.println(flag3);
        System.out.println(flag4);
        System.out.println(flag5);

    }
}
