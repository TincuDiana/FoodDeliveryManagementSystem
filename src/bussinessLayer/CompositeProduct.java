package bussinessLayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeProduct extends MenuItem{
	private String name;
	List<BaseProduct> compPr= new ArrayList<BaseProduct>();
	
	public List<BaseProduct> getCompPr() {
		return compPr;
	}


	public void setCompPr(List<BaseProduct> compPr) {
		this.compPr = compPr;
	}


	public void addToCompProduct(BaseProduct item) {
		assert item!=null;
		compPr.add(item);
	}
	

    public CompositeProduct() {
        super();
    }
	
    public CompositeProduct(String title, List<BaseProduct> productsList) {
        this.name=title;
        this.compPr=productsList;
    }
    
    public void addProduct(BaseProduct baseproduct) {
		this.compPr.add(baseproduct);
	}
	public void deleteProduct(BaseProduct baseproduct) {
		this.compPr.remove(baseproduct);
	}
	public void setItems(List<BaseProduct> list) {
		this.compPr=list;
	}

	public List<BaseProduct> getItems() {
		return compPr;
	}
    
    private int computePrice() {
    	int total=0;
    	for(MenuItem i: compPr) {
    		total+= i.getPrice();
    	}
    	return total;
    }


}
