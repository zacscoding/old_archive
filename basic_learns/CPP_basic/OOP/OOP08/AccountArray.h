#ifndef __AccountArray_H__
#define __AccountArray_H__
#include "Account.h"

typedef Account* ACC_PTR;

class BoundCheckAccountPtrArray
{
private:
	ACC_PTR * arr;
	int arrlen;
	BoundCheckAccountPtrArray(const BoundCheckAccountPtrArray& arr) {}
	BoundCheckAccountPtrArray& operator=(const BoundCheckAccountPtrArray&ref) {}
public:
	BoundCheckAccountPtrArray(int len=0);
	ACC_PTR& operator[] (int);
	ACC_PTR operator[](int) const;
	int GetArrLen()const;
	~BoundCheckAccountPtrArray();
};
#endif
