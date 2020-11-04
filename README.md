# Two-Pass-Assembler

Algorithm - 
Initially we have to give the location of the file as an input. The file is then checked for existence at the location provided as input. If the file is not present, an error message is printed as output, but if its present, the machine language code in the file is converted into assembly language. The input file can have the following formats: a) Opcode, b) Label/ Variable, c) Operand, or d) Comments. Moreover, the code is of a Two Pass Assembler, it is processed two times. The Assembler processes the code line by line in the first pass and looks for any errors that may be present in the assembly code. The Assembler will ignore the error if possible, otherwise it will skip the line of the error. In case no error is found, it will find out the Labels/Literals/Variables in the lines and add them to the Symbol or Literal Table, as the case may be. As the Variables are declared, the Symbol Table is also updated simultaneously. A fragment of the machine code (devoid of literal and variable addresses) is generated in the second pass. Once the STOP statement is discovered, all the addresses are given to the Literals and Variables. Eventually, the machine language code is updated with the addresses and the process comes to an end.

Error Handling - 
In case the file is not found at the given location, the code will give an error message and will
skip the line of error or terminate the code, depending on the extremity of the error. Opcodes
like CLA and STP, there is no need for an operand, but if there exists one, the operand is
ignored and an error message is printed in the output. Furthermore, if a line does not have an
opcode, the line is ignored and an error message is displayed. In DC opcodes, Operand and
Label (both) are needed, but is any one of them or both of them are not given, the line is
ignored and an error message is displayed. Also, if the operand is a label, the Label cannot be
initiated with a value, so, the line is ignored and an error message is printed. In BRZ, BRN, BRP,
the operand needs to be a Label rather than a Variable, but if it is a Variable, the line is ignored
and an error message is printed. In the case of same name declaration of Variable or Label with
the opcodes, the line will be ignored and error message is printed as always. On the existence
of a line after the STOP statement, exception being declarative statements, the line is ignored
and error message is printed. On non-declaration of a Variable, error message is displayed. The
operand needs to be a Variable instead of a Label in most opcodes, or else the line is ignored and error
message is displayed. Operand is also needed in most opcodes, or else the line is ignored and error
message is printed. Error is displayed if the Stop statement is not present in the input file. Lastly, if an
opcode except the ones in the opcode table is found in the input file, the line is ignored and unidentified
opcode error message is displayed.
