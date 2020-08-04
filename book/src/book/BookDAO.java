package book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.UUID;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import oracle.jdbc.OracleTypes;

public class BookDAO implements ActionListener{
   
   JTable table;
   JTextField txtName, txtWriter, txtPublisher;
   JTextField txtSearch;
   BookDAO(){}
   
   BookDAO(JTable table){
      this.table = table;
   }
   BookDAO(JTable table, JTextField txtSearch){
      this.table= table;
      this.txtSearch = txtSearch;
   }
   BookDAO(JTable table, JTextField txtName, JTextField txtWriter, JTextField txtPublisher){
      this.table = table;
      this.txtName = txtName;
      this.txtWriter = txtWriter;
      this.txtPublisher = txtPublisher;
   }
   
   @Override
      public void actionPerformed(ActionEvent e) {
         // 대여버튼 처리 패널 이름이 대여이면 작동
         if (e.getActionCommand().equals("대여")) { //완료
            rentaled ();
         }
         // 반납버튼 처리   패널 이름이 반납이면 작동
         else if (e.getActionCommand().equals("반납")) {//완료
            handined();
         }
         // 추가버튼 처리      패널 이름이 추가이면 작동
         else if (e.getActionCommand().equals("추가")) { //완료
            BookGUI.clayout.show(BookGUI.tab, "add");
         }
         // 수정버튼 처리      패널 이름이 수정이면 작동
         else if (e.getActionCommand().equals("수정")) {
          BookGUI.clayout.show(BookGUI.tab, "update");
         }
         else if (e.getActionCommand().equals("추가확인")) {//수정부분일때확인해야함
            insert();
            BookGUI.clayout.show(BookGUI.tab,"default");
         // 오류 시 오류 창 뜨기 
                  
         }else if(e.getActionCommand().equals("수정확인")) {
            update();
            BookGUI.clayout.show(BookGUI.tab,"default");
         }
         else if(e.getActionCommand().equals("수정취소")) {
            BookGUI.clayout.show(BookGUI.tab,"default");
         }else if(e.getActionCommand().equals("취소")) {//완료
             BookGUI.clayout.show(BookGUI.tab,"default");
          }else if(e.getActionCommand().equals("삭제")) {//완료 
             delete();
          }else if(e.getActionCommand().equals("조회")) {//조회
             search();
          }
          else if(e.getActionCommand().equals("전체조회")) {//전체조회
             totalsearch();
          }
         
         
      }
   
   
   void insert() {
      BookDTO obj = new BookDTO();
         int i, rowNum, flag = 0;
     
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         
         obj.name = txtName.getText().trim();
         rowNum = model.getRowCount();
         Object arr[] = new Object[6];
         
         for(i = 0; i<rowNum; i++) {
            String table_name = model.getValueAt(i, 1).toString().trim();
            String table_publisher = model.getValueAt(i, 3).toString().trim();
            if(table_name.equals(obj.name)||table_publisher.equals(obj.publisher)) {
               flag = 1;
               break;
            }
         }
         if(txtName.getText().toString().equals("")||txtWriter.getText().toString().equals("")||txtPublisher.getText().toString().equals("")) {
            flag = 1;
         }
         
         if(flag ==0) {
            obj.name = txtName.getText().trim();
            obj.writer = txtWriter.getText().trim();
            obj.publisher = txtPublisher.getText().trim();
            obj.code = UUID.randomUUID().toString().split("-")[0];
          
            arr[0] = obj.code;
            arr[1] = obj.name;
            arr[2] = obj.writer;
            arr[3] = obj.publisher;
            arr[4] = null;
            arr[5] = null;
            System.out.println("책 :" + obj.name +"추가 성공 ");
            }else {
               JOptionPane.showMessageDialog(null, "책 :" +txtName.getText().toString()+"의 출판사" + txtPublisher.getText().toString()+" 중복 or 빈칸이 있음","실패",1);
               
               System.out.println("책 :" +obj.name+"의 출판사" + obj.publisher+" 중복 or 빈칸이 있음");
         }
         Connection con = null;
         CallableStatement cstmt = null;
         
        int rowNumber;
        
        try {
             
           con = connectDB();
           
           cstmt = con.prepareCall("{call book_insert(?, ?, ?, ?, ?, ?)}");
           
           cstmt.setString(1, obj.code);
           cstmt.setString(2, obj.name);
           cstmt.setString(3, obj.writer);
           cstmt.setString(4, obj.publisher);
           cstmt.setString(5, null);
           cstmt.setString(6, null);
           
           rowNumber = cstmt.executeUpdate();
           
           if(rowNumber == 0)
              System.out.println("데이터 추가 실패!");
           else {
              model.addRow(arr);
              System.out.println("데이터 추가 성공!");
              txtName.setText("");
               txtWriter.setText("");
               txtPublisher.setText("");
           }
        }
         catch (Exception e){
            System.out.println("데이터 추가 실패! :"+ e.getMessage() );
         }
        finally {
            try {cstmt.close();} catch (Exception ignored) {}
            try {con.close();} catch (Exception ignored) {}
         }
   }
   
   //추가완료
   void delete() {

         DefaultTableModel model = (DefaultTableModel) table.getModel();
         int row = table.getSelectedRow();
         String name = (String) model.getValueAt(row, 1);

         System.out.println("책 제목이" + name + "인 책을 지우겠습니다.");

         Connection con = null;

         CallableStatement cstmt = null;

         try {

            con = connectDB();
            cstmt = con.prepareCall("{call book_delete(?)}");
            cstmt.setString(1, name);

            row = cstmt.executeUpdate();

            if (row == 0)
               System.out.println("데이터베이스 삭제 실패!");

            System.out.println(row);
            model.removeRow(row);
         } catch (Exception e) {
            System.out.println("데이터베이스 삭제 실패! " + e.getMessage());
         }

         finally {

            try {
               cstmt.close();
            } catch (Exception ignored) {
            }
            try {
               con.close();
            } catch (Exception ignored) {
            }
         }
         
         totalsearch();
         JOptionPane.showMessageDialog(null, name + " 인 책을 삭제했습니다.", "삭제안내", 1);
      }
      //삭제완료
   
   
   void update() {//수정// 저자와 출판사만 바뀐다.    
         int i, rowNum;
         String table_name = null;
         int row = table.getSelectedRow();
         if (row == -1) {return;}
               
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         BookDTO obj = new BookDTO();
         
         Connection con = null;
         CallableStatement cstmt = null;
         obj.writer = txtWriter.getText().trim();
         obj.publisher = txtPublisher.getText().trim();   
         obj.name = txtName.getText().trim();
         try 
         {
            con = connectDB();
            cstmt = con.prepareCall("{call book_update(?,?,?)}");
            cstmt.setString(2, obj.writer);
            cstmt.setString(3, obj.publisher);
            cstmt.setString(1, obj.name);
            cstmt.executeUpdate();
               
            rowNum = model.getRowCount();  
               for (i = 0; i < rowNum; i++) {
                  table_name = model.getValueAt(i, 1).toString().trim(); 
                  if (table_name.equals(obj.name)) {
                     model.setValueAt(obj.writer, i, 2);
                     model.setValueAt(obj.publisher, i, 3);                  
                     break;
                  }
               }
               System.out.println("데이터 베이스 연결 + 수정 성공!! ");
               this.txtName.setText("");
               this.txtWriter.setText("");
               this.txtPublisher.setText("");
               
            

         }catch (Exception e1) {
            System.out.println("연결 실패" + e1.getMessage());

         } finally {
            try {
               cstmt.close();
            } catch (Exception ignored) {
            }
            try {
               con.close();
            } catch (Exception ignored) {
            }
            
         }
      }
   //수정 
   
   /*
    * cstmt = con.prepareCall("{call book_callsearch(?)}"); cstmt.setString(0,
    * code); cstmt.registerOutParameter(1, Types.VARCHAR);
    * cstmt.registerOutParameter(2, Types.VARCHAR); cstmt.registerOutParameter(3,
    * Types.VARCHAR); cstmt.registerOutParameter(4, Types.VARCHAR);
    * cstmt.registerOutParameter(5, Types.DATE); cstmt.registerOutParameter(6,
    * Types.DATE); cstmt.executeQuery();
    * 
    * model.setValueAt(cstmt.getString(5).toString(), i, 4);
    * model.setValueAt(cstmt.getString(6).toString(), i, 5);
    */               
   
   
   void rentaled () {
      int i, rowNum;
      
         String table_name = null;
         int row = table.getSelectedRow();
         if (row == -1) {return;}
               
         DefaultTableModel model = (DefaultTableModel) table.getModel();
         BookDTO obj = new BookDTO();
         
         Connection con = null;
         CallableStatement cstmt = null;
         String name = model.getValueAt(row, 1).toString().trim(); 
         String code = model.getValueAt(row, 0).toString().trim(); 
         try 
         {
            con = connectDB();
            cstmt = con.prepareCall("{call book_callrental(?)}");

            cstmt.setString(1, name);
            cstmt.executeUpdate();
            
            rowNum = model.getRowCount();  
               for (i = 0; i < rowNum; i++) {
                  table_name = model.getValueAt(i, 1).toString().trim(); 
                  if (table_name.equals(name)) {
                     model.setValueAt(obj.rental, row, 4);
                     model.setValueAt(obj.handin, row, 5);
                     break;
                  }
               }
               System.out.println("데이터 베이스 연결 + 수정 성공!! ");
            

         }catch (Exception e1) {
            System.out.println("연결 실패" + e1.getMessage());

         } finally {
            try {
               cstmt.close();
            } catch (Exception ignored) {
            }
            try {
               con.close();
            } catch (Exception ignored) {
            }
            
         }
          }
      
      
   //대여 완료
   void handined() {
         int i, rowNum;
         
            String table_name = null;
            int row = table.getSelectedRow();
            if (row == -1) {return;}
                  
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            BookDTO obj = new BookDTO();
            
            Connection con = null;
            CallableStatement cstmt = null;
            String name = model.getValueAt(row, 1).toString().trim(); 
            String code = model.getValueAt(row, 0).toString().trim(); 
            try 
            {
               con = connectDB();
               cstmt = con.prepareCall("{call book_callhandin(?)}");

               cstmt.setString(1, name);
               cstmt.executeUpdate();
                  
               rowNum = model.getRowCount();  
                  for (i = 0; i < rowNum; i++) {
                     table_name = model.getValueAt(i, 1).toString().trim(); 
                     if (table_name.equals(name)) {
                        model.setValueAt(null, i, 4);
                       model.setValueAt(null, i, 5);
                        break;
                     }
                  }
                  System.out.println("데이터 베이스 연결 + 수정 성공!! ");
               

            }catch (Exception e1) {
               System.out.println("연결 실패" + e1.getMessage());

            } finally {
               try {
                  cstmt.close();
               } catch (Exception ignored) {
               }
               try {
                  con.close();
               } catch (Exception ignored) {
               }
               
            }
            }
   
   //반납 완료
   
   void search() {	//조회
      DefaultTableModel model = (DefaultTableModel) table.getModel();
            
         Connection con = null;
         CallableStatement cstmt = null;
         ResultSet rs = null;
        String name = txtSearch.getText().toString();
        System.out.println(name);
        
         
         String arr[] = new String[6];

         try {
            
            con = connectDB();
               cstmt = con.prepareCall("{call total_select(?)}");
               cstmt.registerOutParameter(1, OracleTypes.CURSOR);
               cstmt.executeQuery();
               rs = (ResultSet) cstmt.getObject(1);

               model.setNumRows(0);

               while (rs.next()) {
                 if(rs.getString("name").equals(name)){
                  arr[0] = rs.getString("code");
                  arr[1] = rs.getString("name");
                  arr[2] = rs.getString("writer");
                  arr[3] = rs.getString("publisher");
                  arr[4] = rs.getString("rental");
                  arr[5] = rs.getString("handin");

                  model.addRow(arr);
                 }
               }
               
            
            
            
         /*
          * con = connectDB(); cstmt = con.prepareCall("{call book_callsearch(?)}");
          * 
          * cstmt.setString(1, name);
          * 
          * rs = cstmt.executeQuery();
          * 
          * arr[0] = rs.getString(1); arr[1] = rs.getString(2); arr[2] = rs.getString(3);
          * arr[3] = rs.getString(4); arr[4] = rs.getString(5); arr[5] = rs.getString(6);
          * 
          * 
          * model.addRow(arr);
          */

         } catch (Exception e) {
            System.out.println("데이터베이스 오류!" + e.getMessage());
         } finally {
            try {
               cstmt.close();
            } catch (Exception ignored) {
            }
            try {
               con.close();
            } catch (Exception ignored) {
            }
         }

      
   
   }
    
   
   void totalsearch() {// 전체조회

         DefaultTableModel model = (DefaultTableModel) table.getModel();

         Connection con = null;
         CallableStatement cstmt = null;
         ResultSet rs = null;

         int rowNum = model.getRowCount();
         String arr[] = new String[6];

         try {
            con = connectDB();
            cstmt = con.prepareCall("{call total_select(?)}");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.executeQuery();
            rs = (ResultSet) cstmt.getObject(1);

            model.setNumRows(0);

            while (rs.next()) {

               arr[0] = rs.getString("code");
               arr[1] = rs.getString("name");
               arr[2] = rs.getString("writer");
               arr[3] = rs.getString("publisher");
               arr[4] = rs.getString("rental");
               arr[5] = rs.getString("handin");

               model.addRow(arr);
            }

         } catch (Exception e) {
            System.out.println("데이터베이스 오류!" + e.getMessage());
         } finally {
            try {
               cstmt.close();
            } catch (Exception ignored) {
            }
            try {
               con.close();
            } catch (Exception ignored) {
            }
         }


      }

   //전체조회

private Connection connectDB() {
    Connection con = null;
    
    try {
       String driver =
             "oracle.jdbc.driver.OracleDriver";
       String url = 
             "jdbc:oracle:thin:@localhost:1521:orcl";
       
       Class.forName(driver);
       
       //연결객체 생성
       con = DriverManager.getConnection(url,
             "scott", "123456");
    }
    catch (Exception e) {
       System.out.println("데이터베이스 연결 실패!");
       e.printStackTrace();
    }
    return con;
 }
}