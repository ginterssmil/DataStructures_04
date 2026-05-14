package service;

import datastr.MyBST;
import datastr.MyNode;

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
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
