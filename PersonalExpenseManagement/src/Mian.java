import java.sql.*;
import java.time.LocalDate;

public class Mian {
    static final String Driver="com.mysql.jdbc.Driver";
    static final String url="jdbc:mysql://localhost/expense";
    static final String user="root";
    static final String pass="Hackerboy2002";
    public static void main(String []args){
        Connection connection=null;
        Statement st=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting_____");
            connection= DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database");
            st=connection.createStatement();
            String insert="INSERT INTO expensesmanage VALUES(\""+ String.valueOf(LocalDate.now())+"\",\"Food expense\",1000)";
            st.execute(insert);
            System.out.println("Inserted");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                if(st!=null){
                    connection.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        try{
            if(connection!=null)
                connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
