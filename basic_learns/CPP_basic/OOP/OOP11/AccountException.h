#ifndef __ACCOUNT_EXCEPTION_H__
#define __ACCOUNT_EXCEPTION_H__

class InputException
{
private:
	int input;
public:
	InputException(int);
	void ShowExceptionReason();
};

class WithdrawException
{
private:
	int balance;
public:
	WithdrawException(int);
	void ShowExceptionReason();
};

#endif
