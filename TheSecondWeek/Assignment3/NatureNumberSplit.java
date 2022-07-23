import java.util.Scanner;
//回溯加深度优先
public class NatureNumberSplit{
    int n = 0, m = 0;//全局变量，控制进程
    int[] a = new int[100];//存储结果
    public void dfs(int pos) {
        for (int i = a[pos - 1]; i <= m; i++) {
            if (i == n) {
                continue;
            }
            a[pos] = i;
            m-=i;
            if (m == 0) {
                prinf(pos);
            } else {
                dfs(pos + 1);
            }
            m+=i;//回溯
        }
    }
    //打印函数
    public void prinf(int b) {
        for (int i = 1; i < b; i++) {
            System.out.print(a[i]+"+");
        }
        System.out.println(a[b]);
    }
    public static void main(String[] args) {
        new NatureNumberSplit().sf();

    }
    public void sf() {
        System.out.println("请输入目标数字：");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = n;
        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }
        dfs(1);//深度优先遍历
    }
}