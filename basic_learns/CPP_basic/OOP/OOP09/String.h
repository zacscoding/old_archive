#ifndef __String_H__
#define __String_H__
#include "BankingCommonDecl.h"

class String
{
private:
	int len;
	char * str;
public:
	String();
	String(const char *);
	String(const String&);
	~String();
	String& operator= (const String&);
	String& operator+= (const String&);
	bool operator== (const String&);
	String operator+ (const String&);

	friend ostream& operator<< (ostream&, const String&);
	friend istream& operator>> (istream&, String&);
};
#endif
