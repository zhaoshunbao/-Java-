public class Node {
    private String data;
    private Node next;//下一个节点
    private Node previous;   //上一个节点

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    //设置下一个节点
    public void setNode(Node node) {
        this.next = node;
    }

    //取得下一个节点
    public Node getNext() {
        return this.next;
    }

    //设置上一个节点
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    //取得上一个节点
    public Node getPrevious() {
        return this.previous;
    }

    //插入
    //往后插
    public void nAdd(Node newNode) {
        if (this.next == null) {
            setNode(newNode);
            newNode.setPrevious(this);
        } else {
            this.next.nAdd(newNode);
        }
    }

    //往前插
    public void pAdd(Node newNode) {
        if (this.previous == null) {
            setPrevious(newNode);
            newNode.setNode(this);
        } else {
            this.previous.pAdd(newNode);
        }
    }

    //查找
    //往一个方向的查找
    //往前
    public boolean pSearch(String searchData) {
        if (searchData.equals(this.data)) {
            return true;
        } else {
            if (this.previous != null) {
                return this.previous.pSearch(searchData);
            } else {
                return false;
            }
        }
    }

    //往后
    public boolean nSearch(String searchData) {
        if (searchData.equals(this.data)) {
            return true;
        } else {
            if (this.next != null) {
                return this.next.nSearch(searchData);
            } else {
                return false;
            }
        }
    }

    //双向
    public boolean doubleSearch(String searchData) {
        Boolean flag = false;
        if (searchData.equals(this.data)) {
            flag = true;
        } else {
            if (this.next != null && this.previous == null) {
                flag = this.next.nSearch(searchData);
            } else if (this.previous != null && this.next == null) {
                flag = this.previous.pSearch(searchData);
            } else if (this.next != null && this.previous != null) {
                Boolean flag1 = this.next.nSearch(searchData);
                Boolean flag2 = this.previous.pSearch(searchData);
                if (flag1 == false && flag2 == false) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else {
                System.out.println("链表中不存在这个元素！");
            }
        }
        return flag;
    }

    //删除
    //往后查找删除
    public void nDelete(String deleteData) {
            if (this.data.equals(deleteData)) {
                System.out.println("已到该删除的节点");
                this.previous.next=this.next;
            } else {
                if(this.next!=null) {
                    this.next.nDelete(deleteData);
                }
            }

    }
    //往前查找删除
    public void pDelete(String deleteData) {
        if (this.data.equals(deleteData)) {
            System.out.println("已到该删除的节点");
            this.next.previous = this.previous;
        }
        else {
            if(this.previous!=null) {
                this.previous.pDelete(deleteData);
            }
        }
    }
    //双向查找删除
    public void doubleDelete (String deleteData) throws MyException1 {
            if (this.doubleSearch(deleteData) == false) {
                throw new MyException1(this, deleteData);
            } else {
                if (this.next != null) {
                    this.nDelete(deleteData);
                }
                if (this.previous != null) {
                    this.pDelete(deleteData);
                }
                System.out.println("删除完成");
            }
        }

    //遍历
    //往后
    public void nPrint(){
        System.out.print(this.data+"\t");
        if(this.next!=null){
            this.next.nPrint();
        }
    }
    //往前
    public void pPrint(){
        System.out.print(this.data+"\t");
        if(this.previous!=null){
            this.previous.pPrint();
        }
    }
    //双向
    public void doublePrint(){
        if(this.previous!=null){
            System.out.print("插入在前的：");
            this.previous.pPrint();
        }
        if(this!=null){
            System.out.print("\n"+this.data+"\n");
        }
        if(this.next!=null){
            System.out.print("插入在后的：");
            this.next.nPrint();
        }
    }
}