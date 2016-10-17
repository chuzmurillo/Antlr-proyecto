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
.add_data:
	xy tamaño de formals 1

add:
	return x+y;


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

main:
	.print_string:
	DB"10+56="
	DB 0	CALL .print_string.print_number:
	DBadd(10.056.0)
	DB 0	CALL .print_number


