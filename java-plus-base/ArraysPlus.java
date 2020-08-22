//package setup.javaPlus;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ArraysPlus</code> class is used as an extension to conventional Java arrays, and adds
 * functionality from the {@link List} class into the more common array for convenience. The class
 * also adds a variety of methods to convert between arrays and <code>Lists</code> and complex
 * forms of the more general <code>length()</code> and <code>toString()</code> methods.
 * 
 * @author jonatanfontanez
 *
 */
public final class ArraysPlus {
	private static final String OPEN_ARRAY = "{";
	private static final String CLOSE_ARRAY = "}";
	private static final String INDEX_SEPARATOR = ", ";
	
	/**
	 * Don't let anyone instantiate this class
	 */
	private ArraysPlus() {

	}
	
	/**
	 * Removes the object found at the index <code>index</code> in the array <code>arr</code>.
	 * 
	 * @param arr the array to remove an object from
	 * @param index the index to remove
	 * @return the changed array
	 */
	public static <T>T[] remove(T[] arr, int index) {
		return ArraysPlus.remove(arr, arr[index]);
	}

	/**
	 * Removes the first object that matches <code>obj</code> in the array <code>arr</code>.
	 * All subsequent elements will be shifted one index to the left.
	 * If the array <code>arr</code> does not contain the <code>obj</code>, nothing will happen.
	 * 
	 * @param arr the array to remove an object from
	 * @param obj the object to remove
	 * @return the changed array
	 */
	public static <T>T[] remove(T[] arr, T obj) {
		if(ArraysPlus.contains(arr, obj)) {
			@SuppressWarnings("unchecked")
			T[] newArr = (T[])Array.newInstance(arr.getClass().getComponentType(), arr.length - 1);
			int objIndex = ArraysPlus.indexOf(arr, obj);
			for(int i = 0; i < arr.length; i++) 
				if(newArr.getClass().getComponentType().equals(arr.getClass().getComponentType())) 
					if(i < objIndex) 
						newArr[i] = arr[i];
					else if(i > objIndex) 
						newArr[i-1] = arr[i];
			return newArr;
		}else 
			return arr;
	}
	
	/**
	 * Adds the object <code>obj</code> to the end of the array <code>arr</code>.
	 * 
	 * @param arr the array to remove an object from
	 * @param obj the object to add
	 * @return the changed array
	 */
	public static <T>T[] add(T[] arr, T obj) {
		return ArraysPlus.add(arr, arr.length, obj);
	}
	
	/**
	 * Adds the object <code>obj</code> to the index <code>index</code> in the array <code>arr</code>.
	 * 
	 * @param arr the array to remove an object from
	 * @param index the index to add the object to
	 * @param obj the object to add
	 * @return the changed array
	 */
	public static <T>T[] add(T[] arr, int index, T obj) {
		@SuppressWarnings("unchecked")
		T[] newArr = (T[])Array.newInstance(arr.getClass().getComponentType(), arr.length - 1);
		for(int i = 0; i < index; i++) 
			newArr[i] = arr[i];
		newArr[index] = obj;
		for(int i = index; i < arr.length+1; i++) 
			newArr[i] = arr[i-1];
		return newArr;
	}
	
	/**
	 * Sets the index of <code>index</code> in the array <code>arr</code> as the object <code>obj</code>.
	 * 
	 * @param arr the array to use
	 * @param index the index to set to
	 * @param obj the object to set
	 * @return the changed array
	 */
	public static <T>T[] set(T[] arr, int index, T obj) {
		return (T[])ArraysPlus.remove(ArraysPlus.add(arr, index, obj), index+1);
	}
	
	/**
	 * Returns an array of the same type as <code>arr</code> with every index
	 * reversed. Does not modify or destroy <code>arr</code>.
	 * 
	 * @param arr array to be reversed
	 * @return reversed array
	 */
	public static <T>T[] reverse(T[] arr){
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
		for(int i = arr.length-1; i >= 0; i--) 
			newArr[i] = arr[arr.length-i-1];
		return newArr;
	}
	
	/**
	 * Returns an array which contains all indices of all input arrays in the original
	 * order added into a single array. Comparable to the concatenation of Strings.
	 * 
	 * @param arrs arrays to be concatenated
	 * @return fully concatenated array
	 */
	@SafeVarargs
	public static <T>T[] concatenate(T[]... arrs){
		int fullLength = 0;
		for(int i = 0; i < arrs.length; i++) 
			for(int j = 0; j < arrs[i].length; j++) 
				fullLength++;
		@SuppressWarnings("unchecked")
		T[] fullArr = (T[]) Array.newInstance(arrs[0].getClass().getComponentType(), fullLength);
		int index = 0;
		for(int i = 0; i < arrs.length; i++) 
			for(int j = 0; j < arrs[i].length; j++) 
				fullArr[index++] = arrs[i][j];
		return fullArr;
	}
	
	/**
	 * Returns the index of the first occurrence of the object <code>obj</code> 
	 * if it is within the array <code>arr</code>; if not, returns -1.
	 * 
	 * @param arr
	 * @param obj
	 * @return the index of the found object; returns -1 if not found
	 */
	public static int indexOf(Object[] arr, Object obj) {
		if(ArraysPlus.contains(obj, arr))
			for(int i = 0; i < arr.length; i++) 
				if(arr[i].equals(obj))
					return i;
		return -1;
	}
	
	/**
	 * Returns the index of the last occurrence of the object <code>obj</code> if
	 * it is within the array <code>arr</code>; if not, returns -1.
	 * 
	 * @param arr
	 * @param obj
	 * @return the index of the found object; returns -1 if not found
	 */
	public static int lastIndexOf(Object[] arr, Object obj) {
		if(ArraysPlus.contains(arr, obj))
			for(int i = arr.length-1; i >= 0; i--) 
				if(arr[i].equals(obj))
					return i;
		return -1;
	}
	
	/**
	 * Returns the amount of times <code>obj</code> appears in the array <code>arr</code>.
	 * If it does not appear, returns 0. <br>
	 * Works with multidimensional arrays.
	 * 
	 * @param obj
	 * @param arr
	 * @return
	 */
	public static int occurencesOf(Object obj, Object arr) {
		int count = 0;
		if(arr.getClass().isArray() && Array.get(arr, 0).getClass().isArray()) 
			for(int i = 0; i < Array.getLength(arr); i++) 
				count += occurencesOf(obj, Array.get(arr, i));
		else if(arr.getClass().isArray()) 
			for(int i = 0; i < Array.getLength(arr); i++)
				if(Array.get(arr, i).equals(obj))
					count++;
		return count;
	}
	
	/**
	 * Checks if the <code>obj</code> is contained within the array <code>arr</code>. <br>
	 * Works with multidimensional arrays.
	 * 
	 * @param arr the array to search through
	 * @param obj the object to be checked for
	 * @return if <code>arr</code> contains the object <code>obj</code>
	 */
	public static boolean contains(Object obj, Object arr) {
		return ArraysPlus.occurencesOf(obj, arr) > 0;
	}
	
	/**
	 * Converts an array into a String using the toString method. Returns a list of
	 * all indices separated by commas and surrounded by curly braces. Does not destroy
	 * or modify input array. If the array is of type String, quotations will be placed 
	 * around each index.
	 * 
	 * @param arr array to be turned into a String
	 * @return array in String form
	 */
	public static String toString(Object[] arr) {
		return ArraysPlus.toString(arr, OPEN_ARRAY, CLOSE_ARRAY, INDEX_SEPARATOR);
	}
	
	/**
	 * Converts an array into a String using the toString method. Returns a list of
	 * all indices separated by commas with each array starting with <code>opnArr</code>
	 * and ending with <code>clsArr</code>. Does not destroy or modify input array. If
	 * the array is of type String, quotations will be placed around each index.
	 * 
	 * @param arr array to be turned into a String
	 * @param opnArr String to start array with
	 * @param clsArr String to end array with
	 * @return array in custom String form
	 */
	public static String toString(Object[] arr, String opnArr, String clsArr) {
		return ArraysPlus.toString(arr, opnArr, clsArr, INDEX_SEPARATOR);
	}
	
	/**
	 * Converts an array into a String using the toString method. Returns a list of
	 * all indices separated by the string <code>sep</code> with each array starting with
	 * <code>opnArr</code> and ending with <code>clsArr</code>. Does not destroy or modify 
	 * input array. If the array is of type String, quotations will be placed around each
	 * index.
	 * 
	 * @param arr array to be turned into a String
	 * @param opnArr String to start array with
	 * @param clsArr String to end array with
	 * @param sep String to separate indices with
	 * @return array in custom String form
	 */
	public static String toString(Object[] arr, String opnArr, String clsArr, String sep) {
		String fullArr = opnArr;
		String stringAdd = "\"";
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] instanceof String) 
				stringAdd = "\"";
			else 
				stringAdd = "";
			if(i != arr.length-1)
				fullArr += stringAdd + arr[i].toString() + stringAdd + sep;
			else
				fullArr += stringAdd + arr[i].toString() + stringAdd;
		}
		fullArr += clsArr;
		return fullArr;
	}
	
	/**
	 * Converts any kind of array into the equivalent object array. Object arrays
	 * are left alone, but primitives are converted into the object equivalent wrapping.
	 * 
	 * @param arr array to be converted
	 * @return object converted array
	 */
	public static Object[] toObjectArray(Object arr) {
		if(arr.getClass().isArray()) 
			if(arr instanceof boolean[]) {
				Boolean[] arr1 = new Boolean[((boolean[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((boolean[])arr)[i];
				return arr1;
			}else if(arr instanceof byte[]) {
				Byte[] arr1 = new Byte[((byte[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((byte[])arr)[i];
				return arr1;
			}else if(arr instanceof char[]) {
				Character[] arr1 = new Character[((char[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((char[])arr)[i];
				return arr1;
			}else if(arr instanceof short[]) {
				Short[] arr1 = new Short[((short[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((short[])arr)[i];
				return arr1;
			}else if(arr instanceof int[]) {
				Integer[] arr1 = new Integer[((int[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((int[])arr)[i];
				return arr1;
			}else if(arr instanceof long[]) {
				Long[] arr1 = new Long[((long[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((long[])arr)[i];
				return arr1;
			}else if(arr instanceof float[]) {
				Float[] arr1 = new Float[((float[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((float[])arr)[i];
				return arr1;
			}else if(arr instanceof double[]) {
				Double[] arr1 = new Double[((double[])arr).length];
				for(int i = 0; i < arr1.length; i++)
					arr1[i] = ((double[])arr)[i];
				return arr1;
			}else
				return (Object[])arr;
		else
			throw new IllegalArgumentException("Not an array!");
	}
	
	public static double[] toDoubleArray(int[] arr) {
		double[] arr2 = new double[arr.length];
		for(int i = 0; i < arr.length; i++)
			arr2[i] = arr[i];
		return arr2;
	}
	
	/**
	 * Converts an array into an equivalent {@link List} with every index in the same order.
	 * Does not destroy or modify array. 
	 * 
	 * @param arr array to be converted
	 * @return List equivalent to the array
	 */
	public static <T>List<T> toList(T[] arr){
		List<T> arrList = new ArrayList<T>();
		for(T obj:arr) 
			arrList.add(obj);
		return arrList;
	}
	
	/**
	 * Converts a {@link List} into an equivalent array with every index in the same order. Does
	 * not destroy or modify the {@link List}.
	 * 
	 * @param list list to be converted
	 * @return array equivalent to the list
	 */
	public static <T>T[] fromList(List<T> list){
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(list.get(0).getClass(), list.size());
		for(int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}
	
	/**
	 * Converts an array of any amounts of dimensions into a readable string
	 * format. Separates indices with commas and arrays with curly braces. Does
	 * not destroy or modify input array. If the array is of type String, quotations 
	 * will be placed around each index.
	 * 
	 * @param arr array to be converted
	 * @return readable string format of the array
	 */
	public static String multidimensionalArrayToString(Object arr) {
		return ArraysPlus.multidimensionalArrayToString(arr, OPEN_ARRAY, CLOSE_ARRAY, INDEX_SEPARATOR);
	}
	
	/**
	 * Converts an array of any amounts of dimensions into a readable string
	 * format. Separates indices with commas and with each array starting with the 
	 * String <code>opnArr</code> and ending with the String <code>clsArr</code>. Does
	 * not destroy or modify input array. If the array is of type String, quotations 
	 * will be placed around each index.
	 * 
	 * @param arr array to be converted to a custom String format
	 * @param opnArr String to start an array with
	 * @param clsArr String to end an array with
	 * @return custom readable String format of the array
	 */
	public static String multidimensionalArrayToString(Object arr, String opnArr, String clsArr) {
		return ArraysPlus.multidimensionalArrayToString(arr, opnArr, clsArr, INDEX_SEPARATOR);
	}
	
	/**
	 * Converts an array of any amounts of dimensions into a custom readable String
	 * format. Separates indices with the String <code>sep</code>, with each array
	 * starting with the String <code>opnArr</code> and ending with the String 
	 * <code>clsArr</code>. Does not destroy or modify input array. If the array is
	 * of type String, quotations will be placed around each index.
	 * 
	 * @param arr array to be converted to a custom String format
	 * @param opnArr String to start an array with
	 * @param clsArr String to end an array with
	 * @param sep String to separate indices with
	 * @return custom readable String format of the array
	 */
	public static String multidimensionalArrayToString(Object arr, String opnArr, String clsArr, String sep){
		String s = "";
		if (arr.getClass().isArray() && Array.get(arr, 0).getClass().isArray()) 
			for (int i = 0; i < Array.getLength(arr); i++)
				if(i == Array.getLength(arr)-1)
					s += opnArr + multidimensionalArrayToString(Array.get(arr, i), opnArr, clsArr, sep) + clsArr;
				else
					s += opnArr + multidimensionalArrayToString(Array.get(arr, i), opnArr, clsArr, sep) + clsArr + sep;
		else if(arr.getClass().isArray())
			s += ArraysPlus.toString(ArraysPlus.toObjectArray(arr), opnArr, clsArr, sep).substring(opnArr.length(), 
					ArraysPlus.toString(ArraysPlus.toObjectArray(arr), opnArr, clsArr, sep).length()-clsArr.length()); 
		return s;
	} 
	
	/**
	 * Returns how many indices the given array has, or the sum of all lengths of every
	 * internal array.
	 * 
	 * @param arr array whose indices will be counted
	 * @return amount of indices in array
	 */
	public static int fullLength(Object arr) {
		int count = 0;
		if (arr.getClass().isArray() && Array.get(arr, 0).getClass().isArray()) 
			for (int i = 0; i < Array.getLength(arr); i++)
				count += fullLength(Array.get(arr, i));
		else if(arr.getClass().isArray())
			count += Array.getLength(arr);
		return count;
	}
	
	/**
	 * Checks if every index in both arrays are equal using the equal method implemented
	 * within each object.
	 * 
	 * @param arr1 first array to compare
	 * @param arr2 second array to compare
	 * @return if both arrays are equal in respect to order of objects
	 */
	public static boolean equals(Object[] arr1, Object[] arr2) {
		if(arr1.length != arr2.length)
			return false;
		for(int i = 0; i < arr1.length; i++) 
			if(!arr1[i].equals(arr2[i]))
				return false;
		return true;
	}

}
