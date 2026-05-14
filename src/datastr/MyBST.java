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
	
	public void remove(Ttype element) throws Exception{
		if (isEmpty()) {
			throw new Exception("BST ir tukšs un tajā nevar dzēst elementus");
		}
		
		removeHelper(rootNode, element);
	}
	
	private void removeHelper(MyNode<Ttype> nodeTemp, Ttype element) {
		if(nodeTemp!=null) {
			if(nodeTemp.getElement().equals(element))
			{
				//apstrdāt visus četrus dzēšanas gadījumus
				
				//ja elements ir lapa jeb nav neviena bērna
				if(nodeTemp.getLeftChNode()==null 
						&& nodeTemp.getRightChNode()==null) {
					
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					
					//jānoskaidro, kurā puse ir šis berns
					//ja dzēšamais elments ir lielāks par savu vecāku, tad tas ir labais bērns
					if(((Comparable)(nodeTemp.getElement())).compareTo(parentNode.getElement()) > 0) {
						parentNode.setRightChNode(null);
					}
					else//ja dzēšamais elements ir mazaks par savuvecāku, tad viņš ir kā kreisais bērns bijis
					{
						parentNode.setLeftChNode(null);
					}
						
				}//ja ir tikai kreisais bērns
				else if (nodeTemp.getLeftChNode()!=null 
						&& nodeTemp.getRightChNode()==null) {
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					MyNode<Ttype> leftChNode = nodeTemp.getLeftChNode();
					//vai dzēšamais elements ir lielāks par savu vecāku
					//kā labais bērns
					if(((Comparable)(nodeTemp.getElement())).compareTo(parentNode.getElement()) > 0) {
						parentNode.setLeftChNode(leftChNode);
						leftChNode.setParentNode(parentNode);
					}
					else
					{
						parentNode.setRightChNode(leftChNode);
						leftChNode.setParentNode(parentNode);
					}
				}
				//ja ir tikai labais bērns
				else if(nodeTemp.getLeftChNode()==null 
						&& nodeTemp.getRightChNode()!=null) {
					
					MyNode<Ttype> parentNode = nodeTemp.getParentNode();
					MyNode<Ttype> rightChNode = nodeTemp.getRightChNode();
					//vai dzēšamais elements ir lielāks par savu vecāku
					//kā labais bērns
					if(((Comparable)(nodeTemp.getElement())).compareTo(parentNode.getElement()) > 0) {
						parentNode.setRightChNode(rightChNode);
						rightChNode.setParentNode(parentNode);
					}
					else
					{
						parentNode.setLeftChNode(rightChNode);
						rightChNode.setParentNode(parentNode);
					}
				}
				else //ir abi bērni
				{
					//TODO uztaisīt tuvakā elementa atrašanas algoritmu, lai to ievietu dzēšajamā elementā
					//TODO notestēt dzēšanu arī MainService
				}
				
			}
			else
			{
				//pa kursu pusi turpināt meklēšanu
				//pa labo pusi
				if(((Comparable)element).compareTo(nodeTemp.getElement()) > 0) {
					if(nodeTemp.getRightChNode()!=null) {
						removeHelper(nodeTemp.getRightChNode(), element);
					}
				}
				else//meklēšanu jāturpina pa kreiso pusi
				{
					if(nodeTemp.getLeftChNode()!=null) {
						removeHelper(nodeTemp.getLeftChNode(), element);
					}
				}
			}
		}
	}
}
