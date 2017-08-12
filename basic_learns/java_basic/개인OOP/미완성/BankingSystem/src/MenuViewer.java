import java.util.Scanner;

interface INPUT_SELECT {
	int NORMAL=1,CREDIT=2;
//	int HIGH=7,MIDDLE=5,LOW=3;
}

interface INIT_MENU {
	int MAKE=1,DEPOSIT=2,WITHDRAW=3,SEARCH=4,SHOWALL=5,EXIT=6;
}

public class MenuViewer {
	
	public static Scanner keyboard=new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("1.계좌 개설");
		System.out.println("2.계좌 입금");
		System.out.println("3.계좌 출금");
		System.out.println("4.계좌 검색");
		System.out.println("5.계좌 정보 출력");
		System.out.println("6.프로그램 종료");
		System.out.print("선택 : ");
	}
	
	public static void makeAccountMenu()  {
		System.out.println("----계좌 개설----");
		System.out.println("1.일반 계좌  2.신용계좌");
		System.out.print("선택 : ");
		
	}
}
