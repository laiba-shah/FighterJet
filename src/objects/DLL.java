package Objects;

import java.awt.*;

public class DLL {
    public Node head;
    public Node tail;
    private int counter = 0;
    private boolean removeFirstBulletOnCollision;
    public DLL(){
        this.head = null;
    }
    public void add(Vehicle val){
        Node temp = new Node(val);
        temp.ID = counter;
        counter++;
        temp.next = head;
        temp.prev = null;
        if(head != null){
            head.prev = temp;
        }else{
            tail =temp;
        }
        head = temp;
    }
    public void show(Graphics2D g){
        Node temp = tail ;
        while(temp!=null){
            if (temp.ID == 0 && !temp.data.isBullet() ){
                temp.data.makeBackground(g);
            }
            else if (temp.ID ==0 && temp.data.isBullet() && !temp.data.isVisible()){
                temp.data.makeBackground(g);
            }else{
                temp.data.paintComponent(g);
            }
            temp = temp.prev;
        }

    }
//    public void move(){
//        Node temp = this.tail;
//        while (temp!=null){
//            temp.data.move();
//            temp = temp.prev;
//        }
//    }



    public int length(){
        int length = 0;
        Node temp = this.tail;
        while (temp!=null){
            length++;
            temp = temp.prev;
        }
        return length;
    }
    public Node nodeAtPosition(int i){
        if (i>length() && i<length()){
            return null;
        }
        Node temp = this.tail;
        int counter = 0;
        while (counter != i){
            if (temp == null ){
                break;
            }
            temp = temp.prev;
            counter++;
        }
        return temp;
    }


    public  void removeAt(int n){
        Node temp = head;
//        temp.data.setVisible(false);
        if (head == tail){
        }else{

        while (temp.ID != n)
        {
            temp = temp.next;
        }
        if(temp == tail){
            removeFirstIn();
        }else if (temp == head) {
            removeLastIn();
        }else{

            temp.prev.next = temp.next;
            if (temp.next != null)
            {
                temp.next.prev = temp.prev;
            }
            temp = null;
        }

        }

    }

    public void removeLastIn(){

        head = head.next;
        head.prev = null;
    }
    public void removeFirstIn(){
        tail = tail.prev;
        tail.next = null;
    }
}
