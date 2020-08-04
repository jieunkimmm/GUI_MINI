package book;

import static java.util.Calendar.AM_PM;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BookDTO {
   String code;      //책의 고유 코드
   String name;    //책의 제목
   String writer;   //책의 저자
   String publisher;   //책의 출판사
   /*
    * String rental = year+month+date+"q"; //책 대여날짜 String handin =
    * year+month+(date+3)+"q"; //책 반납날짜
    */   
 
   
   GregorianCalendar calendar = new GregorianCalendar();
   int year = calendar.get(YEAR);
   int month = calendar.get(MONTH);
   int date = calendar.get(DATE);
   int amPm = calendar.get(AM_PM);
   String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
   //System.out.printf("%d년 %d월 %d일",year,month,date,amPm);
   
   String rental = year+"년"+(month+1)+"월"+date+"일";
   String handin = year+"년"+(month+1)+"월"+(date+3)+"일";
   
   public BookDTO() {} //생성용
   
   @Override
   public String toString() {
      return code +"\t" + name+"\t" + writer+"\t" + publisher
            +"\t" + rental;
   }
   public BookDTO(String code, String name, String writer, String publisher) {
      this.code = code;
      this.name = name;
      this.writer = writer;
      this.publisher = publisher;
   }//추가수정용
   
   
   public BookDTO(String code, String name, String writer, String publisher, String rental, String handin) {
      this.code = code;
      this.name = name;
      this.writer = writer;
      this.publisher = publisher;
      this.rental = rental;
      this.handin = handin;
   }//전체출력 및 기능용
   
   
   /*
    * public boolean equals(Object obj) { //테이블에 있는 가져오는 name 이랑 해야함
    * if(name.equals()) return true; return false; }
    */
   
   void input() {
      @SuppressWarnings("resource")
   Scanner sc = new Scanner(System.in);
      System.out.println("코드을 입력해주세요.");
      code = sc.next();
      System.out.println("제목을 입력해주세요.");
      name = sc.next();
      System.out.println("저자을 입력해주세요.");
      writer = sc.next();
      System.out.println("출판사를 입력해주세요.");
      publisher = sc.next();
   }
   
}