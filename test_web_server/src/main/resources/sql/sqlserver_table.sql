# https://docs.microsoft.com/en-us/sql/connect/jdbc/using-basic-data-types

CREATE TABLE test_table (
	string_col varchar(100),
	boolean_col bit,
	short_col smallint,
	integer_col int,
	long_col bigint,
	float_col real ,
	double_col float,
	bigdecimal_col money,
	timestamp_col datetime,
	id int default 1
);
