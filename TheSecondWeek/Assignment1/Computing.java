import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;
public class Computing {
    Scanner in=new Scanner(System.in);
    private String expression; //表达式
    public Computing(){
        System.out.println("请输入表达式");
        expression=in.nextLine();     //输入表达式
    }
    public String getExpression(){
        return expression;
    }
    /*
    computingAll用于计算表达式
    judge用于判断表达式是否正确
    strSplit用于将运算式拆分
    comparePriority判断运算符优先级函数 大返回1，小返回0
    priority//运算符优先级
    compute简单的计算
    */
    public Number computingAll() throws MyException {
        Number result=0;
        if(!judge(expression)){
            throw new  MyException(expression);
        }
        else{
            String pattern="+-*/()#";
            Stack<String>numberStack=new Stack<>();//存放数字的栈
            Stack<String>option=new Stack<>();  //存放操作符的栈
            String expression1[]=strSplit(expression);//得到表达式的拆分
           /* for(String m:expression1){
                System.out.println(m);
            }*/
            for(int i=0;i<expression1.length;i++){
                //System.out.println("expression[i]="+expression1[i]);
                if(pattern.indexOf(expression1[i])!=-1){ //运算符
                   if(expression1[i].indexOf("#")!=-1){
                       if(option.size()==1) {
                           String temp1 = numberStack.pop();
                           String temp2 = numberStack.pop();
                           String op = option.pop();
                           try {
                               result = compute(temp2, temp1, op);
                           }catch (MyException2 e) {
                               e.overFlow();
                               System.exit(0);
                           }
                           //System.out.println("1."+temp2+op+temp1+"="+result);
                           break;
                       }
                        else{
                             while(!option.empty()){
                               String temp1 = numberStack.pop();
                               String temp2 = numberStack.pop();
                               String op = option.pop();
                                 try {
                                     result = compute(temp2, temp1, op);
                                 }catch (MyException2 e){
                                     e.overFlow();
                                     System.exit(0);
                                 }
                               numberStack.push(result.toString());
                              // System.out.println(temp2+op+temp1+"="+result);
                           }
                           break;
                        }
                    }
                    //直接压入运算符情况
                    if(option.empty()||(option.peek().equals("(")&&!expression1[i].equals(")"))||(comparePriority(option.peek(),expression1[i])==0)){
                       // System.out.println("直接压入："+expression1[i]);
                        option.push(expression1[i]);
                        continue;
                    }
                    //直接弹出运算符情况
                    if(option.peek().equals("(")&&expression1[i].equals(")")){
                       // System.out.println("直接弹出："+option.peek());
                        option.pop();
                        continue;
                    }
                    //计算的情况
                    if((expression1[i].equals(")")&&!option.peek().equals("("))||(comparePriority(option.peek(),expression1[i])==1)){
                        String temp1=numberStack.pop();
                        String temp2=numberStack.pop();
                        String op=option.pop();
                       // System.out.println("op="+op);
                       // System.out.println("temp2="+temp2+","+"temp1="+temp1);
                        try {
                            result = compute(temp2, temp1, op);
                        }catch (MyException2 e){
                            e.overFlow();
                            System.exit(0);
                        }
                        numberStack.push(result.toString());
                       // System.out.println(temp2+op+temp1+"="+result);
                        i=i-1;   //如果在当前运算的之前还有运算符，则对于现在的运算符还应与option里面的剩余运算符最顶上进行优先级比较，所有i--
                        continue;
                    }
                }
                else{  // 数字
                    numberStack.push(expression1[i]);
                    }
                }
        }
        return result;
    }
    //judge用于判断表达式是否正确
    public boolean judge(String str){
        boolean flag=true;
        char st[]=str.toCharArray();
        String pattern1="0123456789+-*/().";
        String pattern2="+-*/";
        String pattern3="0123456789";
        String pattern4="()";
        int leftKuohao=0;
        int rightKuohao=0;
        if(pattern2.indexOf(st[0])!=-1||st[0]==')'||st[0]=='.'){
            return false;
        }
        for(int i=1;i<st.length;i++){
            if(pattern1.indexOf(st[i])==-1){
                return false;
            }
            //运算符号的要求
            if(pattern2.indexOf(st[i])!=-1){
                if(st[i-1]=='('||(pattern2.indexOf(st[i-1])!=-1)||st[i-1]=='.'){  //运算符前面字符要求
                    return false;
                }
                else{
                    if(st[i+1]==')'||(pattern2.indexOf(st[i+1])!=-1)||st[i+1]=='.'){
                        return false;
                    }
                }
            }
            //括号的要求
            if(st[i]=='('){
                if(st[i-1]=='.'||st[i+1]=='.'||st[i-1]==')'||st[i+1]==')'){
                    return false;
                }
                leftKuohao++;
            }
            if(st[i]==')'){
                if(st[i-1]=='.'||st[i+1]=='.'||st[i+1]=='('||st[i-1]=='('){
                    return false;
                }
                if(leftKuohao==0){
                    return false;
                }
                rightKuohao++;
            }
            //小数点要求
            if(st[i]=='.'){
                int number=0;
                for(int j=i+1;j<st.length;j++){
                    if(pattern2.indexOf(st[j])!=-1){
                        number++;
                    }
                    if(st[j]=='.'){
                        if(number==0){
                            return false;
                        }
                    }
                }
            }
        }
        if(leftKuohao!=rightKuohao){
            return false;
        }
        return flag;
    }
    //用于将表达式拆分为数组的函数-->方便后续的浮点运算和多位数(9.9*999这种)的运算，最后在式子的最后加“#”作为最后一位的判别符
    public static String[] strSplit(String message){
        System.out.println("开始划分");
        String str[]=new String[message.length()+1];
        for(int i=0;i< str.length;i++){
            str[i]="";
        }
        char st[]=message.toCharArray();
        String pattern1="+-*/()";
        int j=0;
        for(int i=0;i<st.length;i++){
            if(pattern1.indexOf(st[i])!=-1){
               // System.out.println("1.yu dao yun suan fu shi:");
                str[++j]=st[i]+"";
                if(pattern1.indexOf(st[i+1])==-1) {//当未遇到连续的符号时（+-*/()）
                    j++;
                }
                continue;
            }
            else{
               // System.out.println("yu dao shu zi shi:");
                str[j]=str[j]+st[i];
            }
        }
        str[j+1]="#";
        return str;
    }
    //判断运算符优先级函数 大返回1，小返回0
    public int comparePriority(String a,String b){
        int pro;
        if(priority(a)>=priority(b)){
            pro=1;
        }
        else {
            pro=0;
        }
        return pro;
    }
    //运算符优先级
    public int  priority(String a){
        int p;
        switch (a){
            case "+"+"":
                p=1;
                break;
            case "-"+"":
                p=1;
                break;
            case "*"+"":
                p=2;
                break;
            case "/"+"":
                p=2;
                break;
            case "("+"":
                p=3;
                break;
            case "#"+"":
                p=4;
                break;
            default:
                p=0;
                break;
        }
        return p;
    }
    //计算值,c为运算符,使用BigDecimal在进行浮点运算是保证精度和准确性
    public Number compute(String a,String b,String c) throws MyException2{
        Number result=0;
        BigDecimal num1=new BigDecimal(a);
        BigDecimal num2=new BigDecimal(b);
        switch (c){
            case "+"+"":
                if(num1.add(num2).floatValue()>Float.MAX_VALUE||num1.add(num2).floatValue()<Float.MIN_VALUE){
                    throw new MyException2(num1.add(num2));
                }
                result=num1.add(num2);
                break;
            case "-"+"":
                if(num1.subtract(num2).floatValue()>Float.MAX_VALUE||num1.subtract(num2).floatValue()<Float.MIN_VALUE){
                    throw new MyException2(num1.subtract(num2));
                }
                result=num1.subtract(num2);
                break;
            case "*"+"":
                if(num1.multiply(num2).floatValue()>Float.MAX_VALUE||num1.multiply(num2).floatValue()<Float.MIN_VALUE){
                    throw new MyException2(num1.multiply(num2));
                }
                result=num1.multiply(num2);
                break;
            case "/"+"":
                if(!b.equals("0")) {
                    if(num1.divide(num2).floatValue()>Float.MAX_VALUE||num1.divide(num2).floatValue()<Float.MIN_VALUE){
                        throw new MyException2(num1.divide(num2));
                    }
                    result = num1.divide(num2);
                }
                else {
                    System.out.println("除数不能为0");
                }
                break;
        }
        return result;
    }
}
