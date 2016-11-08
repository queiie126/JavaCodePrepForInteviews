import java.util.*;

public class tryJava8 {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        list.forEach(e -> System.out.println(e) );
        list.forEach(System.out::println);
    }
}
