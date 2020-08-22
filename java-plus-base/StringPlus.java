//package setup.javaPlus;

public final class StringPlus {
	
	/**
	 * Don't let anyone instantiate this class
	 */
	private StringPlus() {

	}

	/**
	 * Returns how many times the String <code>find</code> appears in the String
	 * <code>s</code>.
	 * 
	 * @param find string to search for
	 * @param s    string to search in
	 * @return number of times <code>find</code> appears in <code>s</code>
	 */
	public static int occurencesOf(String find, String s) {
		int amount = 0;
		if (find.length() > s.length())
			return 0;
		else if (find.equals(s))
			return 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == find.charAt(0))
				if (find.length() == 1)
					amount++;
				else
					for (int j = 1; j < find.length(); j++) {
						if (i + j > s.length() - 1)
							break;
						else if (s.charAt(i + j) != find.charAt(j))
							break;
						else
							amount++;
					}
		}
		return amount;
	}

	/**
	 * Returns a String equivalent to the input String in reverse
	 * 
	 * @param s input String to be reversed
	 * @return reversed String
	 */
	public static String reverse(String s) {
		String reverseS = "";
		for (int i = 0; i < s.length(); i++)
			reverseS += s.charAt(s.length() - i - 1);
		return reverseS;
	}

	/**
	 * Returns true if the String <code>s</code> contains any of the Strings found
	 * within the array <code>arr</code>; useful for checking File types
	 * 
	 * @param s   String to be checked
	 * @param arr Strings to check for
	 * @return if <code>s</code> contains any Strings in <code>arr</code>
	 */
	public static boolean contains(String s, String[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (s.contains(arr[i]))
				return true;
		return false;
	}

	/**
	 * Returns true if the String <code>s</code> contains any of the Strings found
	 * within the array <code>arr</code>, no matter the case; useful for checking
	 * File types
	 * 
	 * @param s   String to be checked
	 * @param arr Strings to check for
	 * @return if <code>s</code> contains any Strings in <code>arr</code>
	 */
	public static boolean containsIgnoreCase(String s, String[] arr) {
		for (int i = 0; i < arr.length; i++)
			if (s.toLowerCase().contains(arr[i].toLowerCase()))
				return true;
		return false;
	}

	/**
	 * Converts all characters occurring after a space to capital letters while
	 * leaving the rest alone. For example, "to capital case" will be converted to
	 * "To Capital Case"
	 * 
	 * @param s input String to be converted
	 * @return String that is capital cased
	 */
	public static String toCapitalCase(String s) {
		if (!s.startsWith(" "))
			s = s.substring(0, 1).toUpperCase() + s.substring(1);
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == ' ' && i + 1 < s.length())
				s = s.substring(0, i + 1) + s.substring(i + 1, i + 2).toUpperCase() + s.substring(i + 2);
		return s;
	}

	/**
	 * Turns the input String into a palindrome with the first half the String
	 * 
	 * @param s String to form a palindrome out of the first half
	 * @return palindrome made out of the first half of the input String
	 */
	public static String makeFirstHalfPalindrome(String s) {
		String half = s.substring(0, s.length() / 2 + 1);
		return half + StringPlus.reverse(half).substring(0, StringPlus.reverse(half).length() - 1);
	}

	/**
	 * Turns the input String into a palindrome with the second half the String
	 * 
	 * @param s String to form a palindrome out of the second half
	 * @return palindrome made out of the second half of the input String
	 */
	public static String makeSecondHalfPalindrome(String s) {
		String half = s.substring(s.length() / 2);
		return StringPlus.reverse(half).substring(0, StringPlus.reverse(half).length() - 1) + half;
	}
	
	/**
	 * Returns if the input String is a palindrome
	 * 
	 * @param s the String to be checked
	 * @return true if input is a palindrome; false otherwise
	 */
	public static boolean isPalindrome(String s) {
		return s.equals(StringPlus.reverse(s));
	}

}
