package datastr;

public class MyBST<Ttype> {
	private MyNode<Ttype> rootNode = null;
	private int howManyElements = 0;
	
	public int getHowManyElements() {
		return howManyElements;
	}
	
	public boolean isEmpty() {
		return (howManyElements == 0);
	}

	public boolean isFull() {
		try {
			new MyNode<Character>('A');
			return false;
		} catch (OutOfMemoryError e) {
			return true;
		}
	}
	
	public void add(Ttype element) throws Exception{
		if(isFull()) {
			throw new Exception("Koks ir pilns un nevar vairs pievienot jaunus elementus");
		}
		
		addHelper(rootNode, element);
		howManyElements++;
		
	}
	
	private void addHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp!=null) {
			if( ((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {
				addHelper(nodeTemp.getRightChNode(), element);;
			}
			else {
				addHelper(nodeTemp.getLeftChNode(), element);
			}
		}
	}
}
