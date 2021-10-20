package methodRef;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MethodReferenceProductMain {

	public static void main(String[] args) {
		Product prd = new Product(1L, "A", new BigDecimal("100"));
		System.out.println(prd);

		ProductCreator prdCreator = Product::new;
		Product prdFunc = prdCreator.create(1L, "A", new BigDecimal("100"));
		System.out.println(prdFunc);
		
		AProduct aPrd = createIProduct(1L, "A", new BigDecimal("100"), "aItem1", AProduct::new);
		BProduct bPrd = createIProduct(2L, "B", new BigDecimal("200"), "bItem2", BProduct::new);
		CProduct cPrd = createIProduct(3L, "C", new BigDecimal("300"), "cItem3", CProduct::new);
		
		System.out.println(aPrd);
		System.out.println(bPrd);
		System.out.println(cPrd);
		
	}

	private static <T extends IProduct> T createIProduct(
			Long id
			, String name
			, BigDecimal price
			, String item
			, IProductCreator<T> iPrdCreator) {
		return iPrdCreator.create(id, name, price, item);
	}
}

@AllArgsConstructor
@Data
class Product {
	private Long id;
	private String name;
	private BigDecimal price;
}

@FunctionalInterface
interface ProductCreator {
	Product create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
abstract class IProduct {
	private Long id;
	private String name;
	private BigDecimal price;
}

class AProduct extends IProduct{
	
	private String aItem;
	
	public AProduct(Long id, String name, BigDecimal price, String aItem) {
		super(id, name, price);
		this.aItem = aItem;
	}

	@Override
	public String toString() {
		return "[Class 'A'Product] " + super.toString() + ", aItem=" + this.aItem;
	}

	public String getaItem() {
		return aItem;
	}

	public void setaItem(String aItem) {
		this.aItem = aItem;
	}
}

class BProduct extends IProduct{
	private String bItem;
	
	public BProduct(Long id, String name, BigDecimal price, String bItem) {
		super(id, name, price);
		this.bItem = bItem;
	}
	
	@Override
	public String toString() {
		return "[Class 'B'Product] " + super.toString() + ", bItem=" + this.bItem;
	}

	public String getbItem() {
		return bItem;
	}

	public void setbItem(String bItem) {
		this.bItem = bItem;
	}
}

class CProduct extends IProduct{
	private String cItem;
	
	public CProduct(Long id, String name, BigDecimal price, String cItem) {
		super(id, name, price);
		this.cItem = cItem;
	}
	
	@Override
	public String toString() {
		return "[Class 'C'Product] " + super.toString() + ", cItem=" + this.cItem;
	}

	public String getcItem() {
		return cItem;
	}

	public void setcItem(String cItem) {
		this.cItem = cItem;
	}
}

@FunctionalInterface
interface IProductCreator<T extends IProduct> {
	T create(Long id, String name, BigDecimal price, String item);
}