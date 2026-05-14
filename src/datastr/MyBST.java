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
		
		//ja koks tuks ieliekam pirmo ka root
		if(isEmpty()) {
			MyNode<Ttype> newNode = new MyNode<Ttype>(element);
			rootNode = newNode;
		}
		else {
			addHelper(rootNode, element);
		}
		howManyElements++;
		
	}
	
	private void addHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp!=null) {
			//parbauda uz kuru pusi elements japarvieto
			//ja lielaks, novietosies pa labi
			if( ((Comparable)element).compareTo(nodeTemp.getElement()) > 0 ) {
				
				if(nodeTemp.getRightChNode()==null) {
					 MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					 newNode.setParentNode(nodeTemp);
					 nodeTemp.setRightChNode(newNode);
				}
				else {
				addHelper(nodeTemp.getRightChNode(), element);
				}
				
			}
			else {//ja mazaks, movietosies pa kreisi
				
				//ja kreisa puse nav node
				if(nodeTemp.getLeftChNode()==null) {
					MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					newNode.setParentNode(nodeTemp);
					nodeTemp.setLeftChNode(newNode);
				}
				else {
					addHelper(nodeTemp.getLeftChNode(), element);

				}
			}
		}
	}
}
