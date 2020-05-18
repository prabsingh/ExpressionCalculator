package node;

public class SubNode extends BinaryNode {

	@Override
	public int priority() {
		return 1;
	}

	@Override
	public Double evaluate() throws Exception {
		Double lhs = this.l_child.evaluate();
		Double rhs = this.r_child.evaluate();
		return lhs - rhs;
	}

}
