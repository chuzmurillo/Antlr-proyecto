.init:
   MOV D, 232
   JMP main

xy.add_data:
	x: DB 0 
	y: DB 0 

.main_data:
	.UNDEF: DB 255
.print_string_data_01:
	add_xy: DB "10+56="
add_main: DB "10+56="

	DB	0.print_number_data_01:
	add_xy: DB 10.0
	DB	56.0

add_main: DB 10.0
	DB	56.0


	DB	0print_string:
	POP C
	POP B
	PUSH C
.print_string_loop_01:
	MOV C, [B]
	CMP C, 0
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
print_number:
	POP C
	POP A
	PUSH C 
.number_to_Stack:
	MOV B,A;
	DIV 10;
	MUL 10;
	SUB B, A;
	PUSH B;
 	CMP A, 0;
	JE .number_to_display;
	DIV 10;
	JMP .number_to_Stack;
.number_to_display:
	POP A;
	CMP A,C;
	JE .exit;
	ADD A, 0x30;
	MOV [D], A;
	INC D;
	JMP .number_to_display;
.exit:	
	PUSH .UNDEF
	PUSH C
	RET
add:
	POP C
	POP B
	POP A
	ADD A,B
	PUSH A
	PUSH C
	RET
main:
		
	PUSH .print_string_data_01
	CALL print_string
	POP A	
	PUSH .print_number_data_01
	CALL print_number
	POP A
	HLT