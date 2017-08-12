#ifndef __ACCOUNT_H__
#define __ACCOUNT_H__

#include "String.h"
#include "AccountException.h"

class Account
{
private:
	int accID;
	int balance;
	String cusName;
public:
	Account(int ID, int money, String name) throw(InputException);
	int GetAccID() const;
	virtual void Deposit(int money) throw(InputException, WithdrawException);
	int Withdraw(int money);
	void ShowAccInfo() const;
};
#endif