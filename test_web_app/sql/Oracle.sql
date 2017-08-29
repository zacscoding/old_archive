--https://docs.oracle.com/cd/E11882_01/java.112/e16548/apxref.htm#JJDBC28908

-------------------------------
-- Oracle 11g exp
-------------------------------

create table test_table (
	varchar2_col varchar2(150) null,
	boolean_col number(1) null,
	short_col number(10) null,
	integer_col number(10) null,
	long_col number(10) null,
	float_col number(10) null,
	double_col number(10) null,
	bigdecimal_col number(10) null,
	timestamp_col timestamp null,
	id number default 1
);