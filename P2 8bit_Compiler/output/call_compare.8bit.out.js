.init:
	MOV D, 232
	JMP main
.main_data:
.UNDEF: DB 255

.compare_numberes_01:
DB "true"
DB 0;
.compare_numberes_02:
DB "false"
DB 0;
compare:
POP A
POP C
POP B
CMP B, C
JA .print_bool_loop_01
JB .print_bool_loop_02



.print_string_data:
	DB "10>5="
	DB 0
print_string:
	POP C
	POP B
	PUSH C
.print_string_loop_01:
	MOV C, [B]
	CMP C, 0
	CMP C, 0
	JE .print_string_exit
	JE .print_string_exit
	MOV [D], C
	INC D
	INC B
	JMP .print_string_loop_01
.print_string_exit:
	POP C 
	PUSH .UNDEF
	PUSH C
	RET
.print_boolean_data:
	DB compare(10.05.0)
	DB 0
print_string:
	POP C
	POP B
	PUSH C
.print_string_loop_01:
	MOV C, [B]
	CMP C, 0
	CMP C, 0
	JE .print_string_exit
	JE .print_string_exit
	MOV [D], C
	INC D
	INC B
	JMP .print_string_loop_01
.print_string_exit:
	POP C 
	PUSH .UNDEF
	PUSH C
	RET
.print_string_data:
	DB " 5>10="
	DB 0
print_string:
	POP C
	POP B
	PUSH C
.print_string_loop_01:
	MOV C, [B]
	CMP C, 0
	CMP C, 0
	JE .print_string_exit
	JE .print_string_exit
	MOV [D], C
	INC D
	INC B
	JMP .print_string_loop_01
.print_string_exit:
	POP C 
	PUSH .UNDEF
	PUSH C
	RET
.print_boolean_data:
	DB compare(5.010.0)
	DB 0
print_string:
	POP C
	POP B
	PUSH C
.print_string_loop_01:
	MOV C, [B]
	CMP C, 0
	CMP C, 0
	JE .print_string_exit
	JE .print_string_exit
	MOV [D], C
	INC D
	INC B
	JMP .print_string_loop_01
.print_string_exit:
	POP C 
	PUSH .UNDEF
	PUSH C
	RET



