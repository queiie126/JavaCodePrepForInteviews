package interview;
import java.util.Arrays;
public class test{
    public static void main(String[] args){
        int[] ints = new int[]{1,2,3};
        func(ints);
        System.out.println(Arrays.toString(ints));
    }
    private static void func(int[] ints){
        ints[2] = 1;
    }
}
