//溢出错误
public class MyException2 extends Exception {
    private Number message;
    public MyException2(Number message){
        this.message=message;
    }
    public Number getMMessage(){
        return message;
    }
    public void overFlow(){
        if(getMMessage().floatValue()>Float.MAX_VALUE){
            System.out.println("结果上溢出！");
        }
        if(getMMessage().floatValue()<Float.MIN_VALUE){
            System.out.println("结果下溢出！");
        }
    }
}
