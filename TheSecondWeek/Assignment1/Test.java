public class Test {
    public static void main(String args[]){
        Computing compute=new Computing();
        System.out.println("开始运算");
        try {
            Number result=compute.computingAll();
            System.out.println("运算结果是"+result);
        }catch (MyException e){
            System.out.println("MyException:"+"表达式错误:"+e.getMessage());
        }
    }
}
