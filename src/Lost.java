class Lost {
    String lostTime; //丢失时间
}
class CardLost extends Lost{
    String cardId; //学号
    String name; //所属一卡通主人姓名
    String institute;//所属学院
    public String toString(){
        return this.name+" "+this.cardId+" "+this.institute+" "+this.lostTime;
    }
}
class BookLost extends Lost{
    String bookName;  //书名
    String author;//作者
    String name;//拥有者姓名，无则填无.
    public String toString(){
        return this.bookName+" "+this.author+" "+this.name+" "+this.lostTime;
    }
}
