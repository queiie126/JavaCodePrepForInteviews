package interview;

/*
 This class implements TODO
 @HW
 */
class SimpleLL_sorted{
    public static void main(String[] args){
        SimpleLL l = new SimpleLL(null, true);
        l.insert(1);
        l.p();
        l.insert(-2);
        l.p();
        l.insert(-4);
        l.p();
        l.insert(10);
        l.p();
        l.deleteFirstV(-4);
        l.deleteFirstV(1);
        l.p();
    }
}
