import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {	
	private final String tokens="+-*/";	
	private Calculator(){}
	
	public static Calculator instance = null;
	public static Calculator getInstance(){
		if(instance == null)
			instance = new Calculator();
		return instance;
	}
	
	//중위식 -> 후위식으로 변환 후 계산
	public double calculate(String infixData) throws Exception{		
		String[] postfixData = changeToPostFix(infixData);		
		double result=0;
		Stack<String> stack=new Stack<String>();
		for(int i=0;i<postfixData.length;i++){
			String nowStr=postfixData[i];
			if(tokens.contains(nowStr)){ //연산자면
				double num1=Double.parseDouble(stack.pop());
				double num2=Double.parseDouble(stack.pop());
				double res=0;
				switch(nowStr){				
				case "*":
					res=num2*num1;
					break;
				case "/":
					res=num2/num1;
					break;					
				case "+":
					res=num2+num1;
					break;
				case "-":
					res=num2-num1;
					break;				
				}			
				stack.push(String.valueOf(res));				
			}else{ //숫자이면
				stack.push(nowStr);				
			}
		}
		result=Double.parseDouble(stack.pop());
		return result;
	}
	
	//중위식을 후위식으로 바꿔줌
	private String[] changeToPostFix(String infixData) throws Exception {		
		Stack<String> stack=new Stack<String>();		
		StringTokenizer st=new StringTokenizer(infixData,tokens,true);
		String[] postfixData=new String[st.countTokens()];				
		int idx=0;
		//연산 과정
		while(st.hasMoreTokens()){
			String nowStr=st.nextToken();
			if(tokens.contains(nowStr)){ //연산자 이면
				if(stack.isEmpty()){ //stack이 비어있으면
					stack.push(nowStr);
				}else{ //stack의 데이터가 존재하면
					String compStr=stack.peek();
					if(getOpPrec(nowStr)>getOpPrec(compStr)){ //우선 순위가 높으면
						stack.push(nowStr);
					}else{ //우선 순위가 낮으면
						while(!stack.isEmpty()){
							postfixData[idx++]=stack.pop();
						}
					}
				}				
			}else{ //숫자이면
				postfixData[idx++]=nowStr;
			}
		}		
		while(!stack.isEmpty()){
			postfixData[idx++]=stack.pop();
		}		
		return postfixData;		
	}	
	//연산자 우선순위
	private static int getOpPrec(String op){ 
		switch(op){
		case "*":
		case "/":
			return 3;
		case "+":
		case "-":
			return 1;
		}		
		return -1;		
	}	
}

