import java.util.regex.Pattern;
public class Solution {
    /*
    sortLost用于排序，swap函数用于交换
    selectByKeyword函数用于查找，
    isNumber函数用于判断输入的keyword字符串是否是纯数字
    */
    /* 失物排序方法
     * @param lostArray 待排序的失物数组
     */
    //采用快速排序算法
    //low最左边的指针，high最右边的指针
    public void sortLost(Lost[] lostArray,int low,int high){
        if(lostArray==null||low>=high){
            return;
        }
        int left=low;
        int right=high;
        Lost pivote=lostArray[left];
        while(left<right){
            while (left<right&&Integer.valueOf(lostArray[left].lostTime)>=Integer.valueOf(pivote.lostTime)){
                left++;
            }
            while (left<right&&Integer.valueOf(lostArray[right].lostTime)<=Integer.valueOf(pivote.lostTime)){
                right--;
            }
            swap(lostArray,left,right);
        }
        swap(lostArray,low,left);
        sortLost(lostArray,low,left-1);//左边部分
        sortLost(lostArray,left+1,high);//右边部分
    }
    public void swap(Lost lostArray[],int left,int right){
        Lost lost=lostArray[left];
        lostArray[left]=lostArray[right];
        lostArray[right]=lost;
    }
    /**
     * 按关键字搜索失物的方法，这里假设按照失物的领取地点进行搜索
     * @param lostArray 失物数组
     * @param keyword 用户输入的关键字
     * @return 返回查找到的失物
     */
    //查找丢失的卡可用学号查询，书籍则输入书名查询
    public Lost[] selectByKeyword(Lost[] lostArray,String keyword){
        Lost [] lostList=new Lost[lostArray.length];//创建查询结果数组
        //ArrayList<Lost> lostList=new ArrayList<Lost>();
        int i;
        int j=0;
        if(isNumber(keyword)){
            System.out.println("以学号为关键字进行丢失的卡的查找！");
            for(i=0;i<lostArray.length;i++){
                if(CardLost.class.isInstance(lostArray[i])){
                    CardLost cardlost= (CardLost) lostArray[i]; //下转型
                   if(cardlost.cardId.equals(keyword)){
                      lostList[j]=lostArray[i];
                      j++;
                   }
                }
            }
        }
        else{
            System.out.println("以书名为关键字查找丢书的书籍！");
            for(i=0;i<lostArray.length;i++){
                if(BookLost.class.isInstance(lostArray[i])){
                    BookLost booklost= (BookLost) lostArray[i]; //下转型
                    if(booklost.bookName.equals(keyword)){
                        lostList[j]=lostArray[i];
                        j++;
                    }
                }
            }
        }
        return lostList;
    }
    //判断一个字符串是否是由纯数字组成
    //使用正则表达式
    private boolean isNumber(String str){
        boolean flag=false;
        if(Pattern.compile("[0-9]+").matcher(str).matches()){
            flag=true;
        }
        return flag;
    }

}
