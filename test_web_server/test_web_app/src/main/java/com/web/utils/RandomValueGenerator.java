package com.web.utils;

/**
 * java 기본 자료형의 Wrapper 클래스로 랜덤값을 구하는 클래스
 * 
 * @author zaccoding
 * @date 2017. 8. 25.
 */
public class RandomValueGenerator {
	private RandomValueGenerator(){}
	
    private static final String DEFAULT_RATIONAL_FORMAT="00.00";
    private static final int DEFAULT_INTEGER_RANGE = 100;
    private static final int DEFAULT_INTEGER_START = 0;
    
    /**
     * 랜덤값을 반환하는 메소드
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @param clazz 원하는 랜덤값의 클래스타입(기본자료형의 Wrapper or String)
     * @return 랜덤 값(Wrapper or String) or 기본 자료형이 아니면 null
     */
    @SuppressWarnings("unchecked")
    public static<T> T generate(Class<T> clazz) {
        if(clazz == Boolean.class || clazz == boolean.class) {
            return (T)generateBoolean();
        }
        else if(clazz == Character.class || clazz == char.class) {
            return (T)generateCharacter();
        }
        else if(clazz == Byte.class || clazz == byte.class) {
            return (T)generateByte();
        }
        else if(clazz == Short.class || clazz == short.class) {
            return (T)generateShort();
        }
        else if(clazz == Integer.class || clazz == int.class) {
            return (T)generateInteger(DEFAULT_INTEGER_RANGE,DEFAULT_INTEGER_START);
        }
        else if(clazz == Long.class || clazz == long.class) {
            return (T)generateLong(DEFAULT_INTEGER_RANGE,DEFAULT_INTEGER_START);
        }
        else if(clazz == Float.class || clazz == float.class) {
            return (T)generateFloat();
        }
        else if(clazz == Double.class || clazz == double.class) {
            return (T)generateDouble();
        }
        else if(clazz == String.class) {
            return (T)generateString();
        }
        else{
            return null;
        }
    }
    
    /**
     * Boolean 형 랜덤 값을 구하는 메소드
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @return Boolean 형 랜덤 값
     */
    public static Boolean generateBoolean() {
        return (generateInteger(100,0) % 2) == 1;        
    }
    
    /**
     * Character 형 랜덤 값을 구하는 메소드 <br>
     * 
     * [a-zA-Z0-9]에 포함되는 값을 반환
     * @author zaccoding
     * @date 2017. 8. 25.
     * @return [a-zA-Z0-9]에 포함되는 값
     */
    public static Character generateCharacter() {
        int[] chars = new int[] {
                generateInteger(('z'-'a'),'a')
                ,generateInteger(('Z'-'A'),'A')
                ,generateInteger(('9'-'0'),'0')
        };
        return (char)chars[(generateInteger(100,0)%3)];           
    }
    public static Character generateCharacter(boolean containsDigit) {
        int[] chars = new int[] {
                generateInteger(('z'-'a'),'a')
                ,generateInteger(('Z'-'A'),'A')
                ,generateInteger(('9'-'0'),'0')
        };
        
        int mod = containsDigit ? 3 : 2;
        
        return (char)chars[(generateInteger(100,0)%mod)];        
    }
    
    public static Byte generateByte() {
        int randomValue = generateInteger(256, 0); 
        return (byte)randomValue;
    }
        
    public static Short generateShort() {
        int randomValue = generateInteger(65536,0);
        return (short)randomValue;
    }
    
    public static Integer generateInteger() {
        return generateInteger(DEFAULT_INTEGER_RANGE, DEFAULT_INTEGER_START);
    }
    
    /**
     * Integer 형 랜덤 값을 구하는 메소드
     * @author zaccoding
     * @date 2017. 8. 25.
     * @param range 랜덤 정수형의 범위
     * @param start 가장 작은 값 
     * @return start+0 <= random value < start + range
     */
    public static Integer generateInteger(int range, int start) {
        return (int)(randomValue()*range) + start;
    }
    
    public static Long generateLong() {
        return Long.valueOf(generateInteger(DEFAULT_INTEGER_RANGE,DEFAULT_INTEGER_START));
    }
    
    public static Long generateLong(int range, int start) {
        return Long.valueOf(generateInteger(range,start));
    }
    
    
    /**
     * Double 형 랜덤 값을 구하는 메소드
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @param format 소수점 포맷 e.g) 00.00
     * @return 소수점 포맷에 맞는 랜덤 double 값
     */
    public static Double generateDouble(String format) {    
        return Double.parseDouble(generateRational(format));
    }
    
    /**
     * 랜덤 Double 값을 구하는 메소드 <br>
     * 
     * (default foramt == "00.00") 
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @return
     */
    public static Double generateDouble() {
        return generateDouble(DEFAULT_RATIONAL_FORMAT);
    }
    
    /**
     * Float 형 랜덤 값을 구하는 메소드
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @param format 소수점 포맷 e.g) 00.00
     * @return 소수점 포맷에 맞는 랜덤 float 값
     */
    public static Float generateFloat(String format) {
        return Float.parseFloat(generateRational(format));
    }
    
    /**
     * Float 형 랜덤 값을 구하는 메소드
     * 
     * (default foramt == "00.00")
     * @author zaccoding
     * @date 2017. 8. 25.
     * @return 기본 포맷("00.00")에 맞는 Float 형 랜덤 값
     */
    public static Float generateFloat() {
        return Float.parseFloat(generateRational(DEFAULT_RATIONAL_FORMAT));
    }
    
    public static String generateString() {
        int length = generateInteger(50, 5);
        return generateString(length);
    }
    
    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<length; i++) {
            sb.append(generateCharacter());
        }
        
        return sb.toString();
    }
    
    
    /**
     * format에 맞는 랜덤 유리수를 생성하는 메소드
     * 
     * @author zaccoding
     * @date 2017. 8. 25.
     * @param format 00.00과 같은 포맷
     * @return format에 맞는 랜덤 값
     */
    private static String generateRational(String format) {
        int dotIdx = format.indexOf('.');
        
        if(dotIdx == -1) {
            throw new IllegalArgumentException("format must contains \'.\'");
        }
        
        
        String integerDigit = String.valueOf(generateInteger((int)Math.pow(10, dotIdx), 0));
        String fractionDigit = String.valueOf(generateInteger((int)Math.pow(10,format.length() - dotIdx -1),0));
        
        return integerDigit + "." + fractionDigit;
    }
    
    
    private static double randomValue() {
        return Math.random();
    }    
}
