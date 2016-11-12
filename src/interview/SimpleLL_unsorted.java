package interview;

/*
 This class implements TODO
 @HW
 */
class SimpleLL_unsorted{
    public static void main(String[] args){
        //insert();
       // compareTwoString("geeksa","geeksb");
        //compareTwoString("geeksa","geeks");
        //compareTwoString("geeks","geeks");
        addIntger(9563, 99848);
        addIntger(9563, 29842);
    }
    public static SimpleLL addIntger(int a, int b){
        SimpleLL la = convertToLL(a);
        SimpleLL lb = convertToLL(b);
        SimpleLL sum = convertToLL(a+b);
        System.out.println("should be:");
        sum.p();
        return la.addINT(lb);
        //lb.addINT(la); this should be same as above line
        //and should return true if result.compare(converToLL(a+b), false);
        //return null;
    }
    public void insert(){
        SimpleLL l = new SimpleLL(new LLNode(90), false);
        l.insert(1);
        l.p();
        l.insert(-2);
        l.p();
        l.head.next = new LLNode(30);//unsafe, but allowed in our case
        l.p();
    }
    public static boolean compareTwoString(String str1, String str2){
        
        SimpleLL l1 = convertToLL(str1);
        SimpleLL l2 = convertToLL(str2);
        return l1.compare(l2, true);
    }
    public static SimpleLL convertToLL(int i){
        SimpleLL l = new SimpleLL(null, false);
        while(i>0){
            l.addToFront(new LLNode(i%10));
            i/=10;
        }
        return l;
    }
    public static SimpleLL convertToLL(String str){
        SimpleLL l = new SimpleLL(null, false);
        for(int i = str.length()-1;i>=0;i--){
            char c= str.charAt(i);
            l.insert(c);
        }
        return l;
    }
}
