package node;

public class L_ParenthNode extends BaseNode {

	@Override
	public Type getType() {
		return Type.LParenth;
	}

	@Override
	public Double evaluate() throws Exception {
		throw new Exception("Can not evaluate (");
	}

}
