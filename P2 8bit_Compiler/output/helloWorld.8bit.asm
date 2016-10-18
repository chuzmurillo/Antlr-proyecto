.init:
   MOV D, 232
   JMP main
.main_data:
.UNDEF: DB 255

.print_string_data_01:
	DB	"Hello World!"
	DB	0
main:
	PUSH .print_string_data_01
	CALL print_string
	HLT