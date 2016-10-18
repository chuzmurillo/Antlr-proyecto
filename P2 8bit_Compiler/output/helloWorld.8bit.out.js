.init:
   MOV D, 232
   JMP main
.main_data:
.UNDEF: DB 255

main:
	PUSH .print_string
CALL print_string
HLT