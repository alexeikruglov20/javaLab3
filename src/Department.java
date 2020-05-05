import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Department {
    private String name;
    private Node head;
    private Node tail;
    private int size;

    public Department() {
        this("");
    }

    public Department(String name) {
        setName(name);
        head = tail = null;
        size = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void add(Equipment value){
        if(head==null){
            head = tail = new Node(null,value);
        }else{
            tail.next = new Node(tail,value);
            tail = tail.next;
        }
        size++;
    }

    public void add(Equipment value, int index){
        if(index<0) throw new IndexOutOfBoundsException();
        if(index>=size) add(value);
        Node buf = head;
        for(int i = 0; i<index-1;i++){
            buf=buf.next;
        }
        //В итоге получаем узел передцелевым
        Node nextBuf = buf.next;
        buf.next = new Node(buf,value);
        buf = buf.next;
        buf.next = nextBuf;
        nextBuf.prev = buf;
        size++;
    }

    public Equipment get(int index){
        if(index<0 || index>size) throw new IndexOutOfBoundsException();
        if(head == tail) return head.value;
        Node buf = head;
        for(int i = 0; i<index;i++){
            buf=buf.next;
        }
        return buf.value;
    }

    public Equipment get(long number){
        Node buf = head;
        if(buf.value.getNumber() == number) return buf.value;
        while(buf.next!=null){
            buf=buf.next;
            if(buf.value.getNumber()==number){
                return buf.value;
            }
        }
        return null;
    }

    public Equipment remove(int index){
        if(index<0 || index>size) throw new IndexOutOfBoundsException();
        if(head == tail) {
            Node buf = head;
            head = tail = null;
            size--;
            return buf.value;
        }
        if(index==0){
            Node buf = head;
            head.next=head;
            head.prev = null;
            size--;
            return buf.value;
        }
        if(index==size-1){
            Node buf = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
            return buf.value;
        }
        Node buf = head;
        for(int i = 0; i<index;i++){
            buf=buf.next;
        }
        Node prev = buf.prev;
        Node next = buf.next;
        prev.next = next;
        next.prev = prev;
        size--;
        return buf.value;
    }

    //Не ну это пздц конечно, учти ебатб каждый вариант события напиши отдельный иф для этого
    //Госпади, а в задании ещё до кучи методы возвращающие НОДУ (О_О) памагити(( HELP BL!!!
    public Equipment remove(long number){
        if(size==1){
            if(head.value.getNumber() == number){
                Equipment buf = head.value;
                head = tail = null;
                return buf;
            } else{ return null; }

        }
        if(size>1){
            Node buf = head;
            if(buf.value.getNumber() == number){
                Equipment tmp = head.value;
                head = head.next;
                head.prev = null;
                size--;
                return tmp;
            }
            while(buf.next!=null){
                buf = buf.next;
                if(buf.value.getNumber() == number){
                    Equipment tmp = buf.value;
                    if(buf.next!=null){
                        Node prev = buf.prev;
                        Node next = buf.next;
                        prev.next = next;
                        next.prev = prev;

                    }else{
                        tail.prev = tail;
                        tail.next = null;
                    }
                    size--;
                    return tmp;
                }
            }
        }
        return null;
    }

    public Equipment set(Equipment value, int index){
        if(index<0 || index>size) throw new IndexOutOfBoundsException();
        Node buf = head;
        for(int i = 0; i<index;i++){
            buf=buf.next;
        }
        Equipment tmp = buf.value;
        buf.value = value;

        return tmp;
    }

    public boolean contains(long number){
        Node buf = head;
        if(buf.value.getNumber() == number) return true;
        while(buf.next!=null){
            buf=buf.next;
            if(buf.value.getNumber()==number){
                return true;
            }
        }
        return false;
    }

    public boolean contains(String name){
        Node buf = head;
        if(buf.value.getName().equals(name)) return true;
        while(buf.next!=null){
            buf=buf.next;
            if(buf.value.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return size;
    }

    public Equipment[] toArray(){
        Equipment[] equipments = new Equipment[size];
        Node buf = head;
        int index = 0;
        equipments[index] = buf.value;
        while (buf.next!=null){
            index++;
            buf=buf.next;
            equipments[index] = buf.value;
        }
        return equipments;
    }

    //Просто на зло всем богам *чёСртКочОка РнаО всСё слТовоИ*
    public Equipment[] getNamedArray(String name){
        int count = 0;
        for(int i = 0; i<size;i++){
            if(get(i).getName().equals(name)) count++;
        }
        Equipment[] equipments = new Equipment[count];
        count = 0;
        for(int i = 0; i<size;i++){
            if(get(i).getName().equals(name)){
                equipments[count++] = get(i);
            }
        }
        return equipments;
    }

    public Equipment[] getTypedEquipments(EquipmentTypes type){
        Node buf = head;
        int count = 0;
        if(buf.value.getType() == type) count++;
        while(buf.next!=null){
            buf=buf.next;
            if(buf.value.getType() == type){
                count++;
            }
        }
        if(count==0) return null;
        else{
            Equipment[] tmp = new Equipment[count];
            buf = head;
            count = 0;
            if(buf.value.getType() == type) tmp[count] = buf.value;
            while(buf.next!=null){
                buf=buf.next;
                if(buf.value.getType() == type){
                    tmp[count] = buf.value;
                }
            }
            return tmp;
        }
    }

}

class Node{
    public Equipment value;
    public Node next;
    public Node prev;

    public Node(){

    }

    public Node(Node prev,Equipment equipment){
        this.prev = prev;
        prev.next = this;
    }
}
