package util;

import java.util.List;
import java.util.ArrayList;

public class ListUtils
{
    private ListUtils(){}
    
    public static void printList(ArrayList<Integer> a, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < a.size()-1; ++i)
        {
            System.out.print(a.get(i) + ", ");
        }
        System.out.println(a.get(a.size()-1));
    }
    
    public static <T> void printList(List<T> a, String label)
    {
        System.out.print(label + ":");
        for(int i = 0; i < a.size()-1; ++i)
        {
            System.out.println(a.get(i).toString());
        }
        System.out.println(a.get(a.size()-1).toString());
        System.out.println();
    }
}