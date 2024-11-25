package amazonsystem;

import amazonsystem.AmazonProduct.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AmazonProductList {
	// define array length from CSV columns:
	private static final int NUMCOLS = 10;
	private final ArrayList<String> DEFAULT_TITLE = new ArrayList<>(Arrays.asList("id","name","category","sub_category","image_url","link","rating","no_ratings","discount_price","actual_price"));
	private static ArrayList<AmazonProduct> myArrayList = new ArrayList<AmazonProduct>();

	public static void createList(String CSVFileName) throws AmazonException {
		int productQuantity = 0;

		File file = new File(CSVFileName);
		if (!file.isFile()) { // check da file real
			throw new AmazonException("File not found...");
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader(CSVFileName))){
			while (reader.readLine() != null) productQuantity++; // count CSV lines
			
			for (int i = 1 ; i < productQuantity ; i++) {
				myArrayList.add(i-1, (new AmazonProduct(AmazonUtil.lineReader(CSVFileName, i)))); // starts at one to avoid csv header, but starts at index 0 for arraylist
			}
		} catch (IOException e) {
			throw new AmazonException("Product list creation error!"); // disaster strikes
		}
		
	}

	public void saveList(String newFileName) throws AmazonException { // save product list to file
		File savedList = new File(newFileName);
		
		try {
			if(savedList.createNewFile()) {
				FileWriter fileWriter = new FileWriter(newFileName);
				String csvHeader = "";
				
				for(int i = 0; i < DEFAULT_TITLE.size(); i++) {
					csvHeader += DEFAULT_TITLE.get(i);
					if(i < DEFAULT_TITLE.size()-1) {//adding commas until the last column
						csvHeader += ",";
					}
				}
				
				fileWriter.write(csvHeader+'\n');
				
				for(int i = 0; i < myArrayList.size(); i++) {
					System.out.println(myArrayList.get(i));
					fileWriter.append(myArrayList.get(i).toString()+'\n');
				}//only prints the id of the last 
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void edit(int index, AmazonProduct product) throws AmazonException{
		if(index>myArrayList.size() || index < 0) {
			throw new AmazonException("Index out of range!");
		}
		
		myArrayList.set(index, product);
	}

	public AmazonProduct findProductByIndex(int index) throws AmazonException{
		if(index>myArrayList.size() || index < 0) {
			throw new AmazonException("Index out of range!");
		}
		
		return myArrayList.get(index);
	}

	public int getSize() {
		return myArrayList.size();
	}

	public void add(AmazonProduct productToAdd) {
		myArrayList.add(productToAdd);
	}

	public void delete(int index) throws AmazonException{
		if (index>myArrayList.size() || index < 0) {
			throw new AmazonException("Index out of range!");
		}
		
		myArrayList.remove(index);
	}

	public static void search(String stringQuery) throws AmazonException{ // stringQuery = data being searched for in product data
		
		for (AmazonProduct i : myArrayList) {
			if (i.toString().contains(stringQuery)) {
				System.out.println(i);
			}
		}
	}
}
