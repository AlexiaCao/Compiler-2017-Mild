package FrontEnd.AbstractSyntaxTree.Expression.VariableExpression;

import BackEnd.ControlFlowGraph.Instruction.Instruction;
import Environment.SymbolTable.Symbol;
import FrontEnd.AbstractSyntaxTree.Expression.Expression;
import FrontEnd.AbstractSyntaxTree.Function;
import FrontEnd.AbstractSyntaxTree.Type.ClassType.ClassType;
import FrontEnd.AbstractSyntaxTree.Type.Type;
import Environment.Environment;
import Utility.Error.CompilationError;
import Utility.Utility;

import java.util.List;

public class IdentifierExpression extends Expression {
	public Symbol symbol;

	private IdentifierExpression(Type resultType, boolean resultIsLeftValue, Symbol symbol) {
		super(resultType, resultIsLeftValue);
		this.symbol = symbol;
	}

	public static Expression getExpression(String symbolName) {
		if (!Environment.symbolTable.contains(symbolName)) {
			throw new CompilationError("\"" + symbolName + "\" is not a symbol name");
		}
		Symbol symbol = Environment.symbolTable.get(symbolName);
		if (symbol.scope instanceof ClassType) {
			return FieldExpression.getExpression(IdentifierExpression.getExpression("this"), symbolName);
		} else {
			if (symbol.type instanceof Function) {
				return new IdentifierExpression(symbol.type, false, symbol);
			} else {
				return new IdentifierExpression(symbol.type, true, symbol);
			}
		}
	}

	@Override
	public String toString() {
		return "[expression: identifier, name = " + symbol.name + ", type = " + type + "]";
	}

	@Override
	public String toString(int indents) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Utility.getIndent(indents));
		stringBuilder.append(toString()).append("\n");
		return stringBuilder.toString();
	}

	@Override
	public void emit(List<Instruction> instructions) {
		operand = symbol.register;
	}
}
