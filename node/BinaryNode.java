package node;

public abstract class BinaryNode extends OperatorNode {

	protected BaseNode l_child;
	protected BaseNode r_child;
	
	// Setters and Getters
	public BaseNode getL_child() {
		return l_child;
	}

	public void setL_child(BaseNode l_child) {
		this.l_child = l_child;
	}

	public BaseNode getR_child() {
		return r_child;
	}

	public void setR_child(BaseNode r_child) {
		this.r_child = r_child;
	}
	
	

}
