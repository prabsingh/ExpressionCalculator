package node;

public class R_ParenthNode extends BaseNode {

	@Override
	public Type getType() {
		return Type.RParenth;
	}

	@Override
	public Double evaluate() throws Exception {
		throw new Exception("Can not evaluate )");
	}

}
