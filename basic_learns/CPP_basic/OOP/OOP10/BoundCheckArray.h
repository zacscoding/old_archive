#ifndef __BOUND_CHECK_ARRAY_H__
#define __BOUND_CHECK_ARRAY_H__
#include <iostream>
#include <cstdlib>
using namespace std;

template <typename T>
 
class BoundCheckArray
{
private:
	T* arr;
	int arrlen;
	BoundCheckArray(const BoundCheckArray& ref) {}
	BoundCheckArray& operator=(const BoundCheckArray&ref) {}
public:
	BoundCheckArray(int len=100)
		:arrlen(len)
	{
		arr = new T[arrlen];
	}
	int GetArrLen() const
	{
		return arrlen;
	}
	~BoundCheckArray()
	{
		delete[]arr;
	}
	T& operator[](int idx)
	{
		if (idx < 0 || idx >= arrlen) {
			cout << "Array index out of index exception" << endl;
			exit(1);
		}
		return arr[idx];
	}
	T operator[](int idx) const
	{
		if (idx < 0 || idx >= arrlen) {
			cout << "Array index out of index exception" << endl;
			exit(1);
		}
		return arr[idx];
	}
};

#endif
