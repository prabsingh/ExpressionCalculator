package eval;

import java.util.LinkedList;
import java.util.Queue;

import node.*;
import node.BaseNode.Type;

public class Parser {

	private String infix;
	private Queue<BaseNode> tokens;
	private Integer parenthCount = 0;

	public Parser(String infix) {
		super();
		this.infix = infix;
		tokenize();
	}

	/*
	 * Main parse method for building an tree structure from the infix expression
	 * input. This method method uses the Precedence Climbing Algorithm
	 */
	public BaseNode parse() throws Exception {
		BaseNode lhs = nextTerm();
		return parse_subroutine(lhs, 0);
	}

	/*
	 * Subroutine used by the Precedence Climbing Algorithm. This attempts to build
	 * the right side of the tree given the left hand side, and operator priority.
	 */
	private BaseNode parse_subroutine(BaseNode lhs, Integer priority) throws Exception {
		BaseNode nextOperator = this.tokens.peek();

		while (!finished() && ((OperatorNode) nextOperator).priority() >= priority) {

			BinaryNode currentOperator = (BinaryNode) nextOperator;
			this.tokens.poll(); // remove the operator
			BaseNode rhs = nextTerm();
			nextOperator = this.tokens.peek();

			while (!finished() && ((OperatorNode) nextOperator).priority() > currentOperator.priority()) {

				rhs = parse_subroutine(rhs, ((OperatorNode) nextOperator).priority());
				nextOperator = this.tokens.peek();
			}
			currentOperator.setL_child(lhs);
			currentOperator.setR_child(rhs);
			lhs = currentOperator;
		}

		return lhs;
	}

	/*
	 * Provides the next term for the existing expression. If the next token is a
	 * left parenthesis. Then recursively build the next term. This term should
	 * terminate with a right parenthesis. Else just return the next token.
	 */
	private BaseNode nextTerm() throws Exception {
		BaseNode next = this.tokens.peek();
		if (next != null && next.getType() == Type.LParenth) {
			this.parenthCount++; 
			this.tokens.poll(); // Remove the Left Parenthesis.
			BaseNode result = this.parse(); // Recursively build the next term
			this.parenthCount--;
			this.tokens.remove(); // Remove the Right Parenthesis
			return result;
		} else {
			return this.tokens.poll();
		}
	}

	/*
	 * Determines if the current expression is finished. Termination conditions are
	 * if there are no more tokens remaining or if the next token is a right
	 * parenthesis.
	 */
	private Boolean finished() throws Exception {
		BaseNode next = this.tokens.peek();
		if (next == null) {
			return true;
		} else if (next.getType() == Type.RParenth) {
			if (this.parenthCount <= 0) {
				throw new Exception("No matching left parenthesis");
			}
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Read the infix string expression. infix string expression should be space
	 * delimited. Create the corresponding node/token and add to the tokens queue.
	 */
	private void tokenize() {
		this.tokens = new LinkedList<>();

		for (String s : infix.split(" ")) {
			switch (s) {
			case "+":
				this.tokens.add(new AddNode());
				break;
			case "-":
				this.tokens.add(new SubNode());
				break;
			case "*":
				this.tokens.add(new MultNode());
				break;
			case "/":
				this.tokens.add(new DivNode());
				break;
			case "(":
				this.tokens.add(new L_ParenthNode());
				break;
			case ")":
				this.tokens.add(new R_ParenthNode());
				break;
			default:
				this.tokens.add(new NumberNode(Double.parseDouble(s)));
			}
		}

	}

}
