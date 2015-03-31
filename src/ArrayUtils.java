public class ArrayUtils
{
    private ArrayUtils(){}
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
    
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(T[] arr, int d)
    {
        Class<T> tClass = (Class<T>)arr[0].getClass();
        return (T[])java.lang.reflect.Array.newInstance(
            tClass, d);
    }
    
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
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] changeSize(
        T[] arr, int delta, int justification)
    {
        Class<T> tClass = (Class<T>)arr[0].getClass();
        if(delta >= arr.length || delta == 0)
        {
            T[] newArr = 
                (T[])java.lang.reflect.Array.newInstance(
                tClass, arr.length);
            for(int i = 0; i < newArr.length; ++i)
            {
                newArr[i] = arr[i];
            }
            return newArr;
        }
        
        T[] newArr = 
                (T[])java.lang.reflect.Array.newInstance(
                tClass, arr.length+delta);
        
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
                    newArr[i] = null;
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
     * Method that zeros out an array of type byte
     *
     * @param array The byte array
     */
    public static <T> void zero(T[] array)
    {
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = null;
        }
    }
    
    /**
     * Prints an array of objects
     *
     * @param array The array of objects
     */
    public static <T> void printArrayInline(T[] array)
    {
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
    
    public static void print2DArrayF(String[][] array)
    {
        int[] maxLens = new int[array.length];
        zero(maxLens);
        for(int i = 0; i < array[0].length; ++i)
        {
            for(int j = 0; j < array.length; ++j)
            {
                if(array[j][i].length() > maxLens[j])
                {
                    maxLens[j] = array[j][i].length();
                }
            }
        }
        
        String spacer = "";
        
        for(int i = 0; i < array[0].length; ++i)
        {
            for(int j = 0; j < array.length; ++j)
            {
                for(int k = 0; k < (maxLens[j]-array[j][i].length()+1); ++k)
                {
                    spacer += " ";
                }
                System.out.print(array[j][i] + spacer);
                spacer = "";
            }
            System.out.println();
        }
    }
    
    
    
    
    
    /*
     * Primitive versions of methods above, as well as some
     * methods dealing with numbers and wrapper classes
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
