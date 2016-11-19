package interview;

/*
 This class only contains a quickselect and a quicksort.
 @HW
 */
import java.util.*;
public class qss{
    public static void main(String[] args){
        int arr[] = {12, 3, 5, 7, 4, 19, 26, 8};
        partition(arr, 0, arr.length-1);
        System.out.println(quickselect(arr, 4, 0, arr.length-1));
        System.out.println(quickselect(arr, 8, 0, arr.length-1));
        System.out.println(Arrays.toString(quicksort(arr)));
    }
    //select kth minimum in an array with all distinct elements
    //the smallest is 1
    public static int quickselect(int[] arr, int k, int l, int r){
        if(k>r-l+1&&k<=0) return -1;//exception
        int curr = partition(arr, l, r);
        if(curr-l == k-1) return arr[curr];
        if(curr-l>k-1){//
            return quickselect(arr, k, l, curr-1);
        } else {
            return quickselect(arr, k-curr+l-1, curr+1, r);
        }
    }
    public static int[] quicksort(int[] arr){
       // quicksort(arr, 0, arr.length-1);
        quicksort(arr, 0, arr.length-1);
        return arr;
    }
    private static void quicksort(int[] arr, int l, int r){
        if(l<r){
            int pi = partition(arr, l, r);
            quicksort(arr, l, pi-1);
            quicksort(arr, pi+1, r);
        }
    }
    //********the key method to both select and sort
    private static int partition(int[] arr, int left, int right){
        int pivot = arr[right];//8
        int lo = left;//0
        for(int i = lo;i<right;i++){//i=0 =1
            if(arr[i]<=pivot){//arr[0]<=8? no, arr[1]<=3?
                swap(arr, i, lo);//yes arr[lo==0] = 3 arr[1] = 12
                lo++;//lo = 1
            }
        }
        swap(arr, lo, right);
      //  System.out.println(Arrays.toString(arr));
        return lo;
        
    }
    private static void swap(int[] arr, int i, int j){
        int save = arr[i];
        arr[i] = arr[j];
        arr[j] = save;
    }
}
