package bussinessLayer;

import java.io.IOException;
import java.util.List;

public interface IDeliveryServiceProcessing {

	/**
	 * Precondition: none. Postcondition: none.
	 */
	void importProducts() throws IOException;

	/**
	 * Precondition: none. Postcondition: baseProduct is not null.
	 */
	void addProduct(BaseProduct baseProduct);

	/**
	 * Precondition: title is not null. 
	 * Postcondition: none.
	 */
	void deleteProduct(String title);

	/**
	 * Precondition: oldname and name is not null and rating and calories and sodium and fat and proteins are greater than zero .
	 * Postcondition: none.
	 */
	void modifyProduct(String oldname, float rating, String name, int calories, int sodium, int fat, int proteins,
			int price);

	/**
	 * Precondition: start and end are greater than zero and smaller than 24 and end is greater than start. 
	 * Postcondition: none.
	 * @param start the start value
	 * @param end the end value
	 * @return a list of orders
	 */
	List<Order> Report1(int start, int end);
}
