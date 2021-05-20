package bookshop.example;

public class Book {
	private int bookNo;
	private String title;
	private String author;
	private int stateCode;
	
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Book(int bookNo, String title, String author) {
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.stateCode = 1;
	}
	
	public void rent() {
		this.stateCode = 0;
		System.out.println(this.title + "이(가) 대여 됐습니다.");
	}
	
	public void print() {
		if (this.stateCode == 0) {
			System.out.println("책 제목:" + this.title + ", 작가 : " + this.author + ", 대여 유무 : 대여중");
		} else {
			System.out.println("책 제목:" + this.title + ", 작가 : " + this.author + ", 대여 유무 : 재고있음");
		}
	}
	
}
