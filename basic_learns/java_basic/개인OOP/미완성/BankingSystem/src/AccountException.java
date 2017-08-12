public class AccountException extends Exception {	
	AccountException() {
		super("계좌 관련 오류 발생");
	}	
}

class MinusException extends AccountException {
	
	private int inputMoney;
	
	MinusException(int money) {
		super();
		inputMoney=money;
	}
	
	public void showReason() {
		System.out.println(super.getMessage());
		System.out.println(inputMoney+"는 입금할 수 없습니다.");		
	}	
}

class InsuffException extends AccountException {
	private int balance;
	
	InsuffException(int balance) {
		super();
		this.balance=balance;
	}
	
	public void showReason() {
		System.out.println(super.getMessage());
		System.out.println("잔액 : "+balance+" 이 부족합니다.");		
	}	
}

class MenuChoiceException extends Exception {
	private int inputNum;
	
	MenuChoiceException(int inputNum) {
		super("메뉴 선택 오류입니다.");
		this.inputNum=inputNum;
	}
	
	public void showReason() {
		System.out.println(super.getMessage());
		System.out.println(inputNum+"번은 선택할 수 없습니다.");
	}
}




