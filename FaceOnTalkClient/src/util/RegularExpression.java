package util;

/*
 * 정규식 체크해주는 클래스
 */
public class RegularExpression {	
	private static final String idRegex = "\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alnum}+";
	private static final String phoneRegex = "(010|011|016)-\\d{3,4}-\\d{4}";
	private static final String searchPhoneRegex = "(010|011|016)\\d{7,8}";
	private static final String firstBirthRegex = "\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])";
	private static final String secondBirthRegex = "(m|f)";
	private static final String passwordRegex = "[a-zA-Z0-9].{7,12}";
	
	public static boolean isEmailExpression(String value) {
		return value.matches(idRegex);
	}
	public static boolean isPhoneExpression(String value) {
		return value.matches(phoneRegex);
	}
	public static boolean isFirstBirthExpression(String value) {
		return value.matches(firstBirthRegex);
	}
	public static boolean isSecondBirthExpression(String value) {
		return value.matches(secondBirthRegex);
	}
	public static boolean isPasswordExpression(String value) {
		return value.matches(passwordRegex);
	}
	public static boolean isSearchPhoneExpression(String value) {
		return value.replaceAll("-","").matches(searchPhoneRegex);
	}
	
	//private constructor
	private RegularExpression(){}
}
