package node;

public class NumberNode extends BaseNode {

	private Double value;

	public NumberNode(Double value) {
		super();
		this.value = value;
	}

	@Override
	public Type getType() {
		return Type.Operand;
	}

	@Override
	public Double evaluate() {
		return this.value;
	}

}
