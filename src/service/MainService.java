package service;


import datastr.MyBST;
import model.Patient;

public class MainService {

	public static void main(String[] args) {
		MyBST<Integer> bstForIntegers = new MyBST<Integer>();
		
		try {
			
		
		bstForIntegers.add(10);
		bstForIntegers.add(6);
		bstForIntegers.add(23);
		bstForIntegers.add(8);
		bstForIntegers.add(7);
		bstForIntegers.print();
		
		System.out.println(bstForIntegers.search(7));
		System.out.println(bstForIntegers.search(20));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		MyBST<Patient> bstForPatients= new MyBST<Patient>();
		
		try {
			bstForPatients.add(new Patient("Janis", "Berzins", 4));
			bstForPatients.add(new Patient("Liga", "Jauka", 2));
			bstForPatients.add(new Patient("Baiba", "Kalnina", 3));
			bstForPatients.add(new Patient("Juris", "Nagis", 5));
			bstForPatients.print();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	


}
