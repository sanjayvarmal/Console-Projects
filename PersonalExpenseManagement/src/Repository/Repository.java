package Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository repository;
    private final String Driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost/expense";
    private static final String user="root";
    private static final String pass="Hackerboy2002";
    private static Connection connection=null;
    private static Statement st=null;
    private String current="";
    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection(url,user,pass);
                st=connection.createStatement();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return repository;
    }
    public void login(String name){
        current=name;
    }
    public void insert(String []data){
        for(int i=0;i<data.length;i++) {
            try {
                String insert = "INSERT INTO management VALUES(\"" + current+"\",\""+data[i].split("@")[0] + "\",\""+data[i].split("@")[1]+"\","+Integer.parseInt(data[i].split("@")[2])+")";
                st.execute(insert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<String> search(String date){
        List<String> result=new ArrayList<>();
        try {
            String search = "select Date,Reason,Amount from management where Name=\""+current+"\" and Date=\""+date+"\"";
            ResultSet r=st.executeQuery(search);
            while(r.next()){
                String s="";
                s=r.getString("Date")+"@";
                s+=r.getString("Reason")+"@";
                s+=r.getInt("Amount");
                result.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void exit(){
        try{
            if(st!=null)
                connection.close();
            if(connection!=null)
                connection.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
