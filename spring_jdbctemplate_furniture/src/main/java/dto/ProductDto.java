package dto;

public class ProductDto {
	private String no,product_name,product_explain,product_size,update_date,product_price,product_photo,ranking,reg_date,reg_id;
	private int hit;
	

	//인덱스 사진
	public ProductDto(String no, String product_name, String product_price, String product_photo) {
		super();
		this.no = no;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_photo = product_photo;
	}

	public ProductDto(String no, String product_name, String product_explain, String product_size, String update_date,
			String product_price, String product_photo, String ranking) {
		super();
		this.no = no;
		this.product_name = product_name;
		this.product_explain = product_explain;
		this.product_size = product_size;
		this.update_date = update_date;
		this.product_price = product_price;
		this.product_photo = product_photo;
		this.ranking = ranking;
	}

	public ProductDto(String no, String product_name) {
		super();
		this.no = no;
		this.product_name = product_name;
	}
	
	public ProductDto(String no, String product_name, String product_explain, String product_size, String product_price,
			String product_photo, String ranking, String reg_date, String reg_id, int hit, String update_date) {
		super();
		this.no = no;
		this.product_name = product_name;
		this.product_explain = product_explain;
		this.product_size = product_size;
		this.product_price = product_price;
		this.product_photo = product_photo;
		this.ranking = ranking;
		this.reg_date = reg_date;
		this.reg_id = reg_id;
		this.hit = hit;
		this.update_date = update_date;
	}
	
	public ProductDto(String no, String product_name, String product_photo, String reg_date, int hit) {
		super();
		this.no = no;
		this.product_name = product_name;
		this.product_photo = product_photo;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	
	public ProductDto(String no, String product_name, String product_explain, String product_size,
			String product_price, String product_photo, String ranking, String reg_date, String reg_id) {
		super();
		this.no = no;
		this.product_name = product_name;
		this.product_explain = product_explain;
		this.product_size = product_size;
		this.product_price = product_price;
		this.product_photo = product_photo;
		this.ranking = ranking;
		this.reg_date = reg_date;
		this.reg_id = reg_id;
	}
	
	
	
	
	public String getNo() {
		return no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_explain() {
		return product_explain;
	}
	public String getProduct_size() {
		return product_size;
	}
	public String getProduct_price() {
		return product_price;
	}
	public String getProduct_photo() {
		return product_photo;
	}
	public String getRanking() {
		return ranking;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getReg_id() {
		return reg_id;
	}
	public int getHit() {
		return hit;
	}
	public String getUpdate_date() {
		return update_date;
	}
	
	
}
