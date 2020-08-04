package book;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BookGUI {
   static JButton searchButton; //조회 
   static JButton deleteButton; // 삭제
   static JButton searchAllButton; // 전체조회 
   static JButton rentalButton; // 대여
   static JButton turninButton; // 반납
   static JButton inputButton; // 추가
   static JButton updateButton; // 수정
   static JButton checkButton; // 추가확인
   static JButton upcheckButton; // 수정확인
   static JButton cancelButton; // 취소
   static JButton cancelButton2; // 취소
   static JPanel buttonPanel1;   //버튼4개패널
   static JPanel buttonpanel0; //버튼 4개패널
   static JPanel buttonPanel2;   //추가,수정하면 나오는 패널
   static JPanel buttonPanel3;   //패널2 아래부분에 넣을 확인,취소 패널
   static JPanel buttonPanel4;   // 패널2+패널3합친거
   static JTextField name; // 도서명
   static JTextField publisher; // 출판사
   static JTextField writer; // 저자
   static JTextField name2; // 도서명
   static JTextField publisher2; // 출판사
   static JTextField writer2; // 저자
   static JTextField search; // 검색창
   static JLabel namelabel; // 도서명라벨
   static JLabel publisherlabel; // 출판사라벨
   static JLabel writerlabel; // 저자라벨
   static JLabel namelabel2; // 도서명라벨
   static JLabel publisherlabel2; // 출판사라벨
   static JLabel writerlabel2; // 저자라벨
   static JPanel buttonPanel5;
   static JPanel updatebuttonPanel;
   static JPanel updatebuttonPanel_1;
   static JPanel updatebuttonPanel_2;
   // 카드 레이아웃 관련
   static Container tab;
   static CardLayout clayout;

   public static void main(String args[]) {
      JFrame frame = new JFrame("도서 관리 프로그램 ");
      frame.setPreferredSize(new Dimension(900, 600));
      frame.setLocation(600, 400);
      Container contentPane = frame.getContentPane();
      //컴포넌트 생성 및 크기 와 실행시켰을 때에 위치선정
      
      String colNames[] = { "도 서  코 드", "도 서 명", "저 자", "출 판 사", "대 여 일", "반 납 일" };   
       DefaultTableModel model = new DefaultTableModel(colNames, 0);   
       JTable table = new JTable(model);
       int width[] = { 150, 130, 90, 90, 130, 130 };
         for (int i = 0; i < width.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(width[i]);
         }
         //라벨에 필요한 이름들 추가 | 테이블 생성 | 이름들 간격 조정 | 
   //      
         
         JPanel panel1 = new JPanel(); //NORTH에 올 검색패널
         panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        
         JTextField search = new JTextField(30);
         JButton searchButton = new JButton("조회");
         searchButton.setPreferredSize(new Dimension(120,35));
         JButton deleteButton = new JButton("삭제");
         deleteButton.setPreferredSize(new Dimension(120,35));
         JButton searchAllButton = new JButton("전체조회");
         searchAllButton.setPreferredSize(new Dimension(120,35));
         
         panel1.add(new JLabel("검색"));
         panel1.add(search);
         panel1.add(searchButton);   //조회
         panel1.add(deleteButton);   // 삭제
         panel1.add(searchAllButton);   // 전체조회
      

         
//         JPanel panel2 = new JPanel(new BorderLayout());
//         
//         panel2.add(panel1, BorderLayout.SOUTH);
//         contentPane.add(panel2, BorderLayout.CENTER);
//        // contentPane.add(buttonPanel1, BorderLayout.)
//
//      
      rentalButton = new JButton("대여");
      turninButton = new JButton("반납");
      inputButton = new JButton("추가");
      updateButton = new JButton("수정");
      JPanel buttonPanel1_1 = new JPanel();
      JPanel buttonPanel1_2 = new JPanel();
      JPanel buttonPanel1_3 = new JPanel();
      JPanel buttonPanel1_4 = new JPanel();
      rentalButton.setPreferredSize(new Dimension(80,30));
      turninButton.setPreferredSize(new Dimension(80,30));
      inputButton.setPreferredSize(new Dimension(80,30));
      updateButton.setPreferredSize(new Dimension(80,30));
      buttonPanel1_1.add(rentalButton, BorderLayout.CENTER);
      buttonPanel1_2.add(turninButton, BorderLayout.CENTER);
      buttonPanel1_3.add(inputButton, BorderLayout.CENTER);
      buttonPanel1_4.add(updateButton, BorderLayout.CENTER);
      //각 버튼에 해당 버튼에 대한 라벨 기입 그리고 사이즈 조정과 세팅 
         
      
      // 패널 1   =    버튼4개있는 패널
      buttonPanel1 = new JPanel();
      buttonPanel1.setLayout(new GridLayout(1, 4));
      buttonPanel1.add(buttonPanel1_1);
      buttonPanel1.add(buttonPanel1_2);
      buttonPanel1.add(buttonPanel1_3);
      buttonPanel1.add(buttonPanel1_4);
      buttonPanel1.setPreferredSize(new Dimension(200,100));
//      rentalButton.addActionListener(/*대여생성자*/);
//      turninButton.addActionListener(/*반납생성자*/);
//      inputButton.addActionListener(tab.add(buttonPanel2));
//      updateButton.addActionListener(tab.add(buttonPanel2);
      
      
   
        
      
      
      
      // 패널 2    =   추가,수정 있는 버튼패널을 누르면 나오는 패널
      buttonPanel2 = new JPanel();
      buttonPanel2.setLayout(new GridLayout(3, 2));
      namelabel = new JLabel("도서명");
      writerlabel = new JLabel("저자");
      publisherlabel = new JLabel("출판사");
      checkButton = new JButton("추가확인");
      cancelButton = new JButton("취소");
      name = new JTextField(12);
      publisher = new JTextField(12);
      writer = new JTextField(12);
      
      buttonPanel2.add(namelabel);
      buttonPanel2.add(name);   
      buttonPanel2.add(writerlabel);
      buttonPanel2.add(writer);
      buttonPanel2.add(publisherlabel);
      buttonPanel2.add(publisher);
     
      buttonPanel3 = new JPanel();
      buttonPanel3.add(checkButton, BorderLayout.SOUTH);
      buttonPanel3.add(cancelButton, BorderLayout.SOUTH);
      //         패널 3     =         추가확인 및 취소 버튼 
      buttonPanel4 = new JPanel();
      buttonPanel4.add(buttonPanel2, BorderLayout.CENTER);
      buttonPanel4.add(buttonPanel3, BorderLayout.SOUTH);
      // 패널 4  =       패널 2 + 패널 3 (추가 수정 + 추가확인 취소) 
      contentPane.add(buttonPanel4, BorderLayout.SOUTH);
      // 패널 4 를 팬에 넣음 
      contentPane.add(panel1, BorderLayout.NORTH);
      contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
      

      buttonPanel5 = new JPanel();
      buttonPanel5.add(new JScrollPane(table),BorderLayout.NORTH);
      buttonPanel5.add(buttonPanel4, BorderLayout.SOUTH);
      
      // 버튼을 눌렀을 때에 카드가 바뀌는 것을 만들어야 함 
      updatebuttonPanel = new JPanel();
      updatebuttonPanel.setLayout(new GridLayout(3, 2));
      namelabel2 = new JLabel("도서명");
      writerlabel2 = new JLabel("저자");
      publisherlabel2 = new JLabel("출판사");
      upcheckButton = new JButton("수정확인");
      cancelButton2 = new JButton("수정취소");
      name2 = new JTextField(12);
      writer2  = new JTextField(12);
      publisher2  = new JTextField(12);  
      updatebuttonPanel.add(namelabel2);
      updatebuttonPanel.add(name2);   
      updatebuttonPanel.add(writerlabel2);
      updatebuttonPanel.add(writer2);
      updatebuttonPanel.add(publisherlabel2);
      updatebuttonPanel.add(publisher2);
     
      
      updatebuttonPanel_1 = new JPanel();
      updatebuttonPanel_1.add(upcheckButton, BorderLayout.SOUTH);
      updatebuttonPanel_1.add(cancelButton2, BorderLayout.SOUTH);
      
      updatebuttonPanel_2 = new JPanel();
      updatebuttonPanel_2.add(updatebuttonPanel, BorderLayout.CENTER);
      updatebuttonPanel_2.add(updatebuttonPanel_1, BorderLayout.SOUTH);
      
      upcheckButton.addActionListener(new BookDAO(table,name2, writer2, publisher2)); //수정확인
      cancelButton2.addActionListener(new BookDAO()); //수정취소
      
      searchAllButton.addActionListener(new BookDAO(table,search));
      searchButton.addActionListener(new BookDAO(table, search));
      rentalButton.addActionListener(new BookDAO(table)); //대여
      inputButton.addActionListener(new BookDAO());//추가 들어가기
      updateButton.addActionListener(new BookDAO(table));//수정들어가기
      checkButton.addActionListener(new BookDAO(table,name, writer, publisher));//추가확인
      cancelButton.addActionListener(new BookDAO(table));//추가취소
      turninButton.addActionListener(new BookDAO(table,name, writer,publisher));
      deleteButton.addActionListener(new BookDAO(table)); //삭제

      
      // 버튼을 눌렀을 때 기능들  렌탈 = 대여 || 턴인 = 반납  || 인풋 = 책 추가 || 업데이트 = 수정사항 
      
      
       

      
//      checkButton.addActionListener(/*추가or수정*/);
//      cancelButton.addActionListener(tab.add(buttonPanel1);
         
      // 대여,반납,추가,삭제 버튼 누르면 나올 패널
      tab = new JPanel();
      clayout = new CardLayout();
      tab.setLayout(clayout);
      tab.add(buttonPanel1,"default");
      tab.add(buttonPanel4,"add");
      tab.add(updatebuttonPanel_2,"update");
      
      contentPane.add(panel1,BorderLayout.NORTH);
      contentPane.add(buttonPanel5, BorderLayout.CENTER);
      contentPane.add(tab, BorderLayout.SOUTH);
      
      
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setVisible(true);
   }


}