package dto;

public class FreeDto {
	private String no,title,content,attach,reg_id,reg_name,reg_date,update_date,open;
	private int hit,filehit;
	
	
	
	//수정
	public FreeDto(String no, String title, String content, String attach, String update_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.update_date = update_date;
	}
	//이전 다음뷰
	public FreeDto(String no, String title) {
		super();
		this.no = no;
		this.title = title;
	}
	//뷰
	public FreeDto(String no, String title, String content, String attach, String reg_id, String reg_name,
			String reg_date, String update_date, int hit, int filehit, String open) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.update_date = update_date;
		this.hit = hit;
		this.filehit = filehit;
		this.open = open;
	}
	//리스트
	public FreeDto(String no, String title, String attach, String reg_name, String reg_date, int hit, String open, String reg_id) {
		super();
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.hit = hit;
		this.open = open;
		this.reg_id = reg_id;
	}
	//등록
	public FreeDto(String no, String title, String content, String attach, String reg_id, String reg_name,
			String reg_date,String open) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_name = reg_name;
		this.reg_date = reg_date;
		this.open = open;
	}
	public String getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAttach() {
		return attach;
	}
	public String getReg_id() {
		return reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public int getHit() {
		return hit;
	}
	public int getFilehit() {
		return filehit;
	}
	public String getOpen() {
		return open;
	}
	
	
}
