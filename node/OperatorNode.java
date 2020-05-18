package node;

public abstract class OperatorNode extends BaseNode {

	/*
	 * priority of operators based on order of operations
	 */
	public abstract int priority();

	public Type getType() {
		return Type.Operator;
	}

}
