#include "BankingCommonDecl.h"
#include "AccountException.h"

InputException::InputException(int money)
	:input(money) {}

void InputException::ShowExceptionReason()
{
	cout << "[예외발생 : " << input << "불가능]" << endl;
}

WithdrawException::WithdrawException(int money)
	:balance(money) {}

void WithdrawException::ShowExceptionReason()
{
	cout << "[예외발생 : 잔액(" << balance << ")부족]" << endl;
}