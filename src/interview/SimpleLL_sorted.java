package interview;

/*
 This class implements TODO
 @HW
 */
class SimpleLL_sorted{
    public static void main(String[] args){
        SimpleLL l = new SimpleLL(null, true);
        for(int i = 0;i<10;i++){
            l.insert(i*2);
            if(i%3==0) l.insert(-i*2);
        }
       
        //l.deleteFirstV(-4);
        //l.deleteFirstV(1);
        l.p();
        l.reverse();
        l.p();
        l.reverseInGroup(3);
        l.p();
    }
}
