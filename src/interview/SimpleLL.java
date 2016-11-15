package interview;

/*
 This class implements
 TODO
 @HW
 */
import java.util.*;
class SimpleLL{
    boolean isSorted = false;
    LLNode head  = null;
    SimpleLL(LLNode h, boolean sorted){
        head = h;
        isSorted = sorted;
    }
    void insert(int v){
        insert(new LLNode(v));
    }
    void insert(char c){
        addToFront(new LLNode(c));
    }
    void insert(LLNode n){//assuming n is a node and not a list.
        if(n==null) return;
        int v = n.val;

        if(isSorted){
            if(head==null) {
                head = n;

            }
            else if(head.val>=v){
                addToFront(n);

            } else{
                LLNode ptr = head;
                LLNode nptr = head.next;
                while(nptr!=null){
                    if(ptr.val<=v&&nptr.val>=v){
                        ptr.next = n;
                        ptr.next.next = nptr;

                        break;
                    }
                    ptr = ptr.next;
                    nptr = nptr.next;

                }
                if(nptr==null){
                    ptr.next = n;
                }
            }
        } else {
            addToFront(n);
        }
    }
    void addToFront(LLNode n){
    //    LLNode newhead = new LLNode(v);
        n.next = head;
        head = n;
    }
    void reverseInGroup(int k){
        head = reverseInGroup(k, head);
        System.out.println("---------- reverse in group complete ----------");
    }
    private LLNode reverseInGroup(int k, LLNode currhead){
        LLNode newhead = null;
        LLNode ptr = currhead;
        int origk = k;
        while(ptr!=null&&k>0){
            LLNode save = ptr.next;
            ptr.next = newhead;
            newhead = ptr;
            ptr = save;
            k--;
        }
        if(ptr!=null) currhead.next = reverseInGroup(origk, ptr);
        return newhead;
    }
    void reverse(){
        LLNode newhead = null;
        LLNode ptr = head;
        while(ptr!=null){
            LLNode save = ptr.next;
            ptr.next = newhead;
            newhead = ptr;
            ptr = save;
        }
        head = newhead;
        System.out.println("---------- reverse complete ----------");
    }
    void deleteFirstV(int v){
        if(head.val==v) head = head.next;
        else {
            LLNode ptr = head;
            while(ptr.next!=null){
                if(ptr.next.val==v) {
                    ptr.next = ptr.next.next;
                    return;
                }
            }
        }
    }
    void p(){
        LLNode ptr = head;
        while(ptr!=null){
            System.out.print(ptr.val+" ");
            ptr = ptr.next;
        }
        System.out.println("\n---------- print val complete ----------");
    }
    void pc(){
        LLNode ptr = head;
        while(ptr!=null){
            System.out.print(ptr.c+" ");
            ptr = ptr.next;
        }
        System.out.println("\n---------- print char complete ----------");
    }
    
    boolean compare(SimpleLL ll2, boolean checkC){
        LLNode ptr = head;
        LLNode ptr2 = ll2.head;
        while(ptr!=null&&ptr2!=null){
            if(checkC&&(ptr.c==ptr2.c)){
                ptr = ptr.next;
                ptr2 = ptr2.next;
            } else if(!checkC&&(ptr.val==ptr2.val)){
                ptr = ptr.next;
                ptr2 = ptr2.next;
            } else return false;
        }
        if(ptr==null&&ptr2==null) return true;
        return false;
    }
    
    SimpleLL addINT(SimpleLL ll2){
        LLNode ptr = head;
        LLNode ptr2 = ll2.head;
        int len = count();
        int len2 = ll2.count();
        //allign
        Stack<Integer> stack = new Stack<>();
        while(len<len2){
            len2--;
            stack.push(ptr2.val);
            ptr2 = ptr2.next;
        }
        while(len2<len){
            len--;
            stack.push(ptr.val);
            ptr = ptr.next;
        }
        while(ptr!=null){
            stack.push(ptr.val+ptr2.val);
           // len--;
            //len2--; no longer needed
            ptr = ptr.next;
            ptr2 = ptr2.next;
        }
        SimpleLL ll = new SimpleLL(null, false);
        while(!stack.isEmpty()){
            int curri = stack.pop();
            ll.addToFront(new LLNode(curri%10));
            if(curri>9){
                if(!stack.isEmpty()){
                    int nexti = stack.pop();
                    nexti++;
                    stack.push(nexti);
                } else {
                    stack.push(1);
                }
            }
        }
        ll.p();
        return ll;
    }
    
    int count(){
        int res = 0;
        LLNode ptr = head;
        while(ptr!=null){
            ptr = ptr.next;
            res++;
        }
        return res;
    }
}
