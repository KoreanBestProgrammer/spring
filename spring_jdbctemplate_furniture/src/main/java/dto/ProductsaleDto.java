package dto;

public class ProductsaleDto {
	private String d_no,p_no,d_state,c_id,d_email,d_address,purchase_way,price,purchase_date,product_photo,product_name,
				   d_date;

	
	
	
	
	
	//사용자 구매내역 리스트
	public ProductsaleDto(String d_no, String p_no, String c_id, String purchase_date, String product_photo,
			String product_name) {
		super();
		this.d_no = d_no;
		this.p_no = p_no;
		this.c_id = c_id;
		this.purchase_date = purchase_date;
		this.product_photo = product_photo;
		this.product_name = product_name;
	}
	//상태,배송완료날자 변경
	public ProductsaleDto(String d_no, String d_state, String d_date) {
		super();
		this.d_no = d_no;
		this.d_state = d_state;
		this.d_date = d_date;
	}
	//전체 뷰
	public ProductsaleDto(String d_no, String p_no, String d_state, String c_id, String d_email, String d_address,
			String purchase_way, String price, String purchase_date, String product_photo, String product_name,String d_date) {
		super();
		this.d_no = d_no;
		this.p_no = p_no;
		this.d_state = d_state;
		this.c_id = c_id;
		this.d_email = d_email;
		this.d_address = d_address;
		this.purchase_way = purchase_way;
		this.price = price;
		this.purchase_date = purchase_date;
		this.product_photo = product_photo;
		this.product_name = product_name;
		this.d_date = d_date;
	}
	//이전뷰,다음뷰	
	public ProductsaleDto(String d_no, String p_no) {
		super();
		this.d_no = d_no;
		this.p_no = p_no;
	}
	//판매리스트
	public ProductsaleDto(String d_no, String p_no, String d_state, String c_id, String purchase_way,
			String purchase_date, String product_name) {
		super();
		this.d_no = d_no;
		this.p_no = p_no;
		this.d_state = d_state;
		this.c_id = c_id;
		this.purchase_way = purchase_way;
		this.purchase_date = purchase_date;
		this.product_name = product_name;
	}
//등록(상품구매)
	public ProductsaleDto(String d_no, String p_no, String d_state, String c_id, String d_email, String d_address,
			String purchase_way, String price, String purchase_date) {
		super();
		this.d_no = d_no;
		this.p_no = p_no;
		this.d_state = d_state;
		this.c_id = c_id;
		this.d_email = d_email;
		this.d_address = d_address;
		this.purchase_way = purchase_way;
		this.price = price;
		this.purchase_date = purchase_date;
	}

	public String getD_no() {
		return d_no;
	}

	public String getP_no() {
		return p_no;
	}

	public String getD_state() {
		return d_state;
	}

	public String getC_id() {
		return c_id;
	}

	public String getD_email() {
		return d_email;
	}

	public String getD_address() {
		return d_address;
	}

	public String getPurchase_way() {
		return purchase_way;
	}

	public String getPrice() {
		return price;
	}

	public String getPurchase_date() {
		return purchase_date;
	}
	public String getProduct_photo() {
		return product_photo;
	}	
	public String getProduct_name() {
		return product_name;
	}	
	public String getD_date() {
		return d_date;
	}	
}
