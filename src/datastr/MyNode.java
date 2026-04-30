package datastr;

public class MyNode<Ttype> {
	//1.mainīgie
	private Ttype element;
	private MyNode<Ttype> parentNode = null;
	private MyNode<Ttype> leftChNode = null;
	private MyNode<Ttype> rightChNode = null;
	
	//2.getter
	public Ttype getElement() {
		return element;
	}
	public MyNode<Ttype> getParentNode() {
		return parentNode;
	}
	public MyNode<Ttype> getLeftChNode() {
		return leftChNode;
	}
	public MyNode<Ttype> getRightChNode() {
		return rightChNode;
	}
	
	//3.setter
	public void setElement(Ttype inputElement) {
		if(inputElement!=null)
		{
			element = inputElement;
		}
		else
		{
			element = (Ttype)new Object();
		}
	}
	public void setParentNode(MyNode<Ttype> parentNode) {
		this.parentNode = parentNode;
	}
	public void setLeftChNode(MyNode<Ttype> leftChNode) {
		this.leftChNode = leftChNode;
	}
	public void setRightChNode(MyNode<Ttype> rightChNode) {
		this.rightChNode = rightChNode;
	}
	
	
	//4.konstruktors/i
	public MyNode(Ttype inputElement){
		setElement(inputElement);
	}
	
	//4.toString
	public String toString() {
		return "" + element;
	}
}