package datastr;

public class MyBST<Ttype> {
	private MyNode<Ttype> rootNode = null;
	private int howManyElements = 0;

	public int getHowManyElements() {
		return howManyElements;
	}

	//bezargumenta konstruktors būs no Object klases

	public boolean isEmpty() {
		return (howManyElements == 0);
	}

	public boolean isFull() {
		try
		{
			new MyNode<Character>('A');
			return false;
		}
		catch (OutOfMemoryError error) {
			return true;
		}
	}


	public void add(Ttype element) throws Exception {
		if(isFull()) {
			throw new Exception("Koks ir pilns un nevar vairs pievienot jaunus elementus");
		}

		//koks ir tukšs, tad ieliekam pirmo kā root
		if(isEmpty()) {
			MyNode<Ttype> newNode = new MyNode<Ttype>(element);
			rootNode = newNode;
		}
		else
		{
			addHelper(rootNode, element);
		}
		howManyElements++;
	}

	private void addHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp != null) {
			//parbaudam, uz kuru pusi elements jaāpārvieto
			//ja lielāks, tad pa labi
			if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0) {
				//ja laba puse nekā nav, tad var ievieot jauno bloku
				if(nodeTemp.getRightChNode() == null) {
					MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					newNode.setParentNode(nodeTemp);
					nodeTemp.setRightChNode(newNode);
				}
				else
				{
					addHelper(nodeTemp.getRightChNode(), element);
				}
			}
			else//ja mazaks, tad pa kreisi
			{
				//ja kreisā pusē nekā nav
				if(nodeTemp.getLeftChNode() == null) {
					MyNode<Ttype> newNode = new MyNode<Ttype>(element);
					newNode.setParentNode(nodeTemp);
					nodeTemp.setLeftChNode(newNode);
				}
				else//kreisajā puse jau ir kāds bloks un tāpēc jāizsauc uz kreiso pusi sī pati funkcija
				{
					addHelper(nodeTemp.getLeftChNode(), element);
				}
			}

		}
	}

	public void print() throws Exception {
		if (isEmpty()) {
			throw new Exception("BST ir tukšs un to nevar izprintēt");
		}

		printHelper(rootNode);
	}

	private void printHelper(MyNode<Ttype> nodeTemp) {
		if (nodeTemp != null) {
			System.out.println("P: " + nodeTemp.getElement());
			// noskaidrojam, vai eksiste kreisais bērns
			if (nodeTemp.getLeftChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Left child: " + nodeTemp.getLeftChNode().getElement());
				// izpildi so pasu funkciju uz kreiso bērnu
				printHelper(nodeTemp.getLeftChNode());
			}
			// noskaidrojam, vai eksistē labais bērns
			if (nodeTemp.getRightChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Right child: " + nodeTemp.getRightChNode().getElement());
				printHelper(nodeTemp.getRightChNode());
			}
		}
	}

	public boolean search(Ttype element) throws Exception{
		if (isEmpty()) {
			throw new Exception("BST ir tukšs un tajā nevar meklēt elementus");
		}

		return searchHelper(rootNode, element);
	}

	private boolean searchHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp!=null) {
			//Ja sakrīt, tad atgriežam, ka ir atrasts
			if(nodeTemp.getElement().equals(element)) {
				return true;
			}
			else //ja nesakrīt, tad turpinam meklēt
			{
				//meklēšana notiks pa labo pusi
				if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0) {
					//labais berns nemaz neeksistē
					if(nodeTemp.getRightChNode()==null)
					{
						//tads elements nav atrodams un atgriežam false
						return false;
					}
					else
					{		
						return searchHelper(nodeTemp.getRightChNode(), element);
					}
				}
				else//meklēšanu jāmeklē pa kreiso pusi
				{
					//ja kreisais berns eneeksistē, tad elements tur arī nebūs un būs false
					if(nodeTemp.getLeftChNode() == null) {
						return false;
					}
					else
					{
						return searchHelper(nodeTemp.getLeftChNode(), element);
					}
				}
			}


		}

		return false;
	}

}
