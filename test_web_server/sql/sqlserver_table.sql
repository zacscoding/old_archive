CREATE TABLE test_table (
	varchar2_col text null,
	boolean_col tinyint,
	short_col int,
	integer_col int ,
	long_col bigint,
	float_col float,
	double_col decimal,
	bigdecimal_col decimal,
	timestamp_col datetime,
	id int default 1
);



# 이전에 사용한 것
CREATE TABLE test_table (
	id int default 1,
	bigint_col bigint,
	binary_col binary(50),
	char_col char,
	datetime_col datetime,
	decimal_col decimal,
	float_col float , 
	int_col int,
	real_col real,
	text_col text,
	tinyint_col tinyint,
	bit_col bit,
);