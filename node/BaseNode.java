package node;

public abstract class BaseNode {
	public enum Type {
		Operator, 
		Operand, 
		LParenth, 
		RParenth
	}

	public abstract Double evaluate() throws Exception;

	public abstract Type getType();
}
