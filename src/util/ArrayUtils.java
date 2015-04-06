package util;
/**
 * Class ArrayUtils provides static methods for printing and
 * manipulating arrays of any type.
 *
 * The way of implementing the methods for primitive types is
 * still in development. A way to automatically generate equivalent
 * methods for primitive types is being developed.
 *
 * Methods for lists will be considered, but for now, use
 * listName.toArray() to go from a list to an array, then, if the
 * method has a return value, put it in Arrays.toList(...)
 * to convert the array back to the list of the desired type
 * (does not work for primitives).
 *
 * @author Ryan Conrad
 */
public class ArrayUtils
{
    private ArrayUtils(){} // Prevent instantiation
    
    /**
     * Method that returns a subarray based on the start and end indices.
     * 0 to array.length-1 are the standard indices of the array, and
     * -1 to -array.length represent the number of elements away from
     * the first out-of-bounds position beyond the last element.
     *
     * This method returns the elements forward if end > start, or returns
     * the elements backwards if start > end.
     *
     * Start is inclusive: end is not.
     *
     * ex. subarray(arr, 1, -2) will have elements 1, 0,
     * and arr.length-1 in an array of length 4, in that order.
     *
     * @param array The array
     * @param start The start
     * @param end The end
     * @return A subarray of array (null if bad values)
     */
    public static <T> T[] subarray(T[] array, int start, int end)
    {
        // invalid input
        if(start < -array.length || end < -array.length
            || start >= array.length || end >= array.length)
        {
            return null;
        }
        
        int dir = 0;        // Direction to scan array
        int numElems = 0;   // Number of elements in new array
        
        // If start = end, there's nothing to scan.
        if(start == end)
        {
            return newArray(array, 0);
        }
        else if(start < end)
        {
            numElems = end-start;
            dir = 1;
        }
        else
        {
            numElems = start-end;
            dir = -1;
        }
        
        T[] newArr = newArray(array, numElems);
        
        if(dir == 1)
        {
            for(int i = start; i < end; ++i)
            {
                if(i < 0)
                {
                    newArr[i-start] = array[i+array.length];
                }
                else newArr[i-start] = array[i];
            }
        }
        else //if(dir == -1)
        {
            int j = 0;
            for(int i = start; i > end; --i)
            {
                if(i < 0)
                {
                    newArr[j] = array[i+array.length];
                }
                else newArr[j] = array[i];
                ++j;
            }
        }
        return newArr;
    }
    
    /**
     * Method to transpose a 2D array of any non-primitive type.
     * The method does not overwrite the passed in array, but instead
     * returns a transposed array.
     *
     * @param array The array to transpose
     * @return The transposed array
     */
    public static <T> T[][] transpose(T[][] array)
    {
        T[][] transpose = 
            newArray(array,array[0].length,array.length);
        for(int i = 0; i < array.length; ++i)
        {
            for(int j = 0; j < array[0].length; ++j)
            {
                transpose[j][i] = array[i][j];
            }
        }
        return transpose;
    }
    
    /**
     * Generates a new 1D array of any non-primitve type.
     *
     * @param arr The array of the desired type for the new array
     * @param d The number of elements in the new array.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(T[] arr, int d)
    {
        Class<T> tClass = (Class<T>)arr[0].getClass();
        return (T[])java.lang.reflect.Array.newInstance(
            tClass, d);
    }
    
    /**
     * Generates a new 2D array of any non-primitve type.
     *
     * @param arr The array of the desired type for the new array
     * @param d1 1st dimension size.
     * @param d2 2nd dimension size.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] newArray(T[][] arr, int d1, int d2)
    {
        Class<T> tClass = (Class<T>)arr[0].getClass();
        T[][] arr2D = (T[][])java.lang.reflect.Array.newInstance(
            tClass, d1);
        for(int i = 0; i < d1; ++i)
        {
            arr2D[i] = newArray(arr[0], d2);
        }
        return arr2D;
    }
    
    /**
     * Change the size of an array.
     * The method does not overwrite the passed in array, but instead
     * returns an array of modified size.
     *
     * @param arr The array of the desired type for the new array
     * @param delta The number of elements to add or subtract from
     *          the old array
     * @param justification
     *          <0: The new array starts with the first element of
     *              the old array (left justified)
     *          =0: There is an equal distance (rounded to the left)
     *              between the beginning of the old and new array
     *              and the end of the old and new array (respectively)
     *              (center justified)
     *          >0: The new array ends with the last element of
     *              the old array (right justified)
     * @return A new array of modified size
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] changeSize(
        T[] arr, int delta, int justification)
    {
        if(delta >= arr.length || delta == 0)
        {
            T[] newArr = newArray(arr, arr.length);
            for(int i = 0; i < newArr.length; ++i)
            {
                newArr[i] = arr[i];
            }
            return newArr;
        }
        
        T[] newArr = newArray(arr, arr.length+delta);
        
        // push old elements to the left
        if(justification < 0)
        {
            // new array is smaller
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i];
                }
            }
            // new array is larger
            else
            {
                for(int i = 0; i < arr.length; ++i)
                {
                    newArr[i] = arr[i];
                }
                for(int i = arr.length; i < newArr.length; ++i)
                {
                    newArr[i] = null;
                }
            }
        }
        
        // position old elements in the center
        else if(justification == 0)
        {
            // center (elements are left by 1 on a tiebreaker)
            int center = (newArr.length-arr.length)/2;
            // new array is smaller
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-center];
                }
            }
            // new array is larger
            else
            {
                for(int i = 0; i < center; ++i)
                {
                    newArr[i] = null;
                }
                for(int i = center; i < arr.length+center; ++i)
                {
                    newArr[i] = arr[i-center];
                }
                for(int i = arr.length+center; i < newArr.length; ++i)
                {
                    newArr[i] = null;
                }
            }
        }
        
        // push old elements to the right
        else // if(justification > 0)
        {
            // new array is smaller
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-delta];
                }
            }
            // new array is larger
            else
            {
                for(int i = 0; i < delta; ++i)
                {
                    newArr[i] = null;
                }
                for(int i = delta; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-delta];
                }
            }
        }
        return newArr;
    }

    /**
     * Method that swaps two objects.
     *
     * @param array The object array
     * @param i1 The index of the first object
     * @param i2 The index of the second object
     */
    public static <T> void swap(T[] array, int i1, int i2)
    {
        T tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
    
    /**
     * Method that zeros out an array of any non-primitive type
     *
     * @param array The array
     */
    public static <T> void zero(T[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = null;
        }
    }
    
    /**
     * Prints an array of objects in one line
     *
     * @param array The array of objects
     */
    public static <T> void printArrayInline(T[] array)
    {
        if(array == null)
        {
            System.out.println("null");
        }
        else if(array.length == 0)
        {
            System.out.println();
        }
        else
        {
            for(int i = 0; i < array.length-1; ++i)
            {
                System.out.print(array[i] + ", ");
            }
            System.out.println(array[array.length-1]);
        }
    }
    
    /**
     * Prints an array of objects in one line
     *
     * @param array The array of objects
     * @param label The label
     */
    public static <T> void printArrayInline(T[] array, String label)
    {
        System.out.print(label);
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of objects
     *
     * @param array The array of objects
     */
    public static <T> void printArray(T[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            System.out.println(array[i]);
        }
    }
    
    /**
     * Prints an array of objects
     *
     * @param array The array of objects
     * @param label The name of the array
     */
    public static <T> void printArray(T[] array, String label)
    {
        System.out.println(label);
        for(int i = 0; i < array.length; ++i)
        {
            System.out.println(array[i]);
        }
    }
    
    /** 
     * Prints a 2D array of objects
     *
     * @param array The array
     */
    public static <T> void print2DArrayF(T[][] array)
    {
        int[] maxLens = new int[array.length];
        zero(maxLens);
        for(int i = 0; i < array[0].length; ++i)
        {
            for(int j = 0; j < array.length; ++j)
            {
                if(array[j][i].toString().length() > maxLens[j])
                {
                    maxLens[j] = array[j][i].toString().length();
                }
            }
        }
        
        String spacer = "";
        
        for(int i = 0; i < array[0].length; ++i)
        {
            for(int j = 0; j < array.length; ++j)
            {
                for(int k = 0; 
                    k < (maxLens[j]-array[j][i].toString().length()+1); ++k)
                {
                    spacer += " ";
                }
                System.out.print(array[j][i] + spacer);
                spacer = "";
            }
            System.out.println();
        }
    }
    
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////

    /*
     * Primitive versions of methods above, as well as some
     * methods dealing with numbers and wrapper classes, are here.
     * Much of this has been automatically generated.
     */
    
    public static int[] changeSize(int[] arr, int delta, int justification)
    {
        if(delta >= arr.length || delta == 0)
        {
            int[] newArr = new int[arr.length];
            for(int i = 0; i < newArr.length; ++i)
            {
                newArr[i] = arr[i];
            }
            return newArr;
        }
        
        int[] newArr = new int[arr.length+delta];
        
        if(justification < 0)
        {
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i];
                }
            }
            else
            {
                for(int i = 0; i < arr.length; ++i)
                {
                    newArr[i] = arr[i];
                }
                for(int i = arr.length; i < newArr.length; ++i)
                {
                    newArr[i] = Integer.MIN_VALUE;
                }
            }
        }
        else if(justification == 0)
        {
            int center = (newArr.length-arr.length)/2;
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-center];
                }
            }
            else
            {
                for(int i = 0; i < center; ++i)
                {
                    newArr[i] = Integer.MIN_VALUE;
                }
                for(int i = center; i < arr.length+center; ++i)
                {
                    newArr[i] = arr[i-center];
                }
                for(int i = arr.length+center; i < newArr.length; ++i)
                {
                    newArr[i] = Integer.MIN_VALUE;
                }
            }
        }
        else // if(justification > 0)
        {
            if(delta < 0)
            {
                for(int i = 0; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-delta];
                }
            }
            else
            {
                for(int i = 0; i < delta; ++i)
                {
                    newArr[i] = Integer.MIN_VALUE;
                }
                for(int i = delta; i < newArr.length; ++i)
                {
                    newArr[i] = arr[i-delta];
                }
            }
        }
        return newArr;
    }
    
    /**
     * Method that swaps two byte values.
     *
     * @param array The byte array
     * @param i1 The index of the first byte
     * @param i2 The index of the second byte
     */
    public static void swap(byte[] array, int i1, int i2)
    {
        byte tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two short values.
     *
     * @param array The short array
     * @param i1 The index of the first short
     * @param i2 The index of the second short
     */
    public static void swap(short[] array, int i1, int i2)
    {
        short tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two int values.
     *
     * @param array The int array
     * @param i1 The index of the first int
     * @param i2 The index of the second int
     */
    public static void swap(int[] array, int i1, int i2)
    {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two long values.
     *
     * @param array The long array
     * @param i1 The index of the first long
     * @param i2 The index of the second long
     */
    public static void swap(long[] array, int i1, int i2)
    {
        long tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two float values.
     *
     * @param array The float array
     * @param i1 The index of the first float
     * @param i2 The index of the second float
     */
    public static void swap(float[] array, int i1, int i2)
    {
        float tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two double values.
     *
     * @param array The double array
     * @param i1 The index of the first double
     * @param i2 The index of the second double
     */
    public static void swap(double[] array, int i1, int i2)
    {
        double tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two boolean values.
     *
     * @param array The boolean array
     * @param i1 The index of the first boolean
     * @param i2 The index of the second boolean
     */
    public static void swap(boolean[] array, int i1, int i2)
    {
        boolean tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * Method that swaps two char values.
     *
     * @param array The char array
     * @param i1 The index of the first char
     * @param i2 The index of the second char
     */
    public static void swap(char[] array, int i1, int i2)
    {
        char tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
    
    /**
     * Method that zeros out an array of type byte
     *
     * @param array The byte array
     */
    public static void zero(byte[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type short
     *
     * @param array The short array
     */
    public static void zero(short[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type int
     *
     * @param array The int array
     */
    public static void zero(int[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type long
     *
     * @param array The long array
     */
    public static void zero(long[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type float
     *
     * @param array The float array
     */
    public static void zero(float[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type double
     *
     * @param array The double array
     */
    public static void zero(double[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    /**
     * Method that zeros out an array of type boolean (zero = false)
     *
     * @param array The boolean array
     */
    public static void zero(boolean[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = false;
        }
    }
    
    /**
     * Method that zeros out an array of type char
     *
     * @param array The char array
     */
    public static void zero(char[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = 0;
        }
    }
    
    public static void initIncr(Integer[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = i;
        }
    }
    
    public static void initIncr(Integer[][] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            for(int j = 0; j < array[0].length; ++j)
            {
                array[i][j] = i*array[0].length+j;
            }
        }
    }
    
    public static void init(int[] array, int start)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = start;
        }
    }
    
    /**
     * Prints an array of type byte
     *
     * @param array The byte array
     */
    public static void printArray(byte[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type short
     *
     * @param array The short array
     */
    public static void printArray(short[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type int
     *
     * @param array The int array
     */
    public static void printArray(int[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type long
     *
     * @param array The long array
     */
    public static void printArray(long[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type float
     *
     * @param array The float array
     */
    public static void printArray(float[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type double
     *
     * @param array The double array
     */
    public static void printArray(double[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type boolean
     *
     * @param array The boolean array
     */
    public static void printArray(boolean[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type char
     *
     * @param array The char array
     */
    public static void printArray(char[] array)
    {
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type byte
     *
     * @param array The byte array
     * @param label The name of the array
     */
    public static void printArray(byte[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type short
     *
     * @param array The short array
     * @param label The name of the array
     */
    public static void printArray(short[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type int
     *
     * @param array The int array
     * @param label The name of the array
     */
    public static void printArray(int[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type long
     *
     * @param array The long array
     * @param label The name of the array
     */
    public static void printArray(long[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type float
     *
     * @param array The float array
     * @param label The name of the array
     */
    public static void printArray(float[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type double
     *
     * @param array The double array
     * @param label The name of the array
     */
    public static void printArray(double[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type boolean
     *
     * @param array The boolean array
     * @param label The name of the array
     */
    public static void printArray(boolean[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
    
    /**
     * Prints an array of type char
     *
     * @param array The char array
     * @param label The name of the array
     */
    public static void printArray(char[] array, String label)
    {
        System.out.print(label + ": ");
        for(int i = 0; i < array.length-1; ++i)
        {
            System.out.print(array[i] + ", ");
        }
        System.out.println(array[array.length-1]);
    }
}
