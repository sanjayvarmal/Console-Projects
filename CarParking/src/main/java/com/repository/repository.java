package com.repository;

import com.dto.*;
import java.text.Normalizer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class repository {

	    private static repository repository;

	    private static final String Driver="com.mysql.jdbc.Driver";
	    private static final String url="jdbc:mysql://localhost/expense";
	    private static final String user="root";
	    private static final String pass="Hackerboy2002";
	    private static Connection connection=null;
	    private static Statement st=null;

	    private List<Parkerdetails> History=new ArrayList<>();
	    private List<subadmin> client=new ArrayList<>();
	    private Map<String,List<Parkerdetails>> users=new HashMap<String,List<Parkerdetails>>();
	    private String current="";
	    private repository(){

	    }
	    public static repository getInstance(){
	        if(repository==null){
	            repository=new repository();
	            try{
	                Class.forName(Driver);
	                connection= DriverManager.getConnection(url,user,pass);
	                st=connection.createStatement();
	                System.out.println("connection established");
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
	    public static String convertExtendedAsciiToAscii(String input) {
	        // Remove diacritics from extended ASCII characters
	        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
	        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	        String asciiString = pattern.matcher(normalizedString).replaceAll("");

	        // Remove remaining non-ASCII characters
	        asciiString = asciiString.replaceAll("[^\\x00-\\x7F]", "");

	        return asciiString;
	    }
	    public List<String> checkLocation(String loca){
	    	String sam[]=loca.split("@");
	    	for(int i=0;i<sam.length;i++) {
	    		sam[i]=convertExtendedAsciiToAscii(sam[i]);
	    		
	    	}
	    	String sear="";
	    	for(int i=0;i<sam.length-1;i++) {
	    		sear+="location like '%"+sam[i].toLowerCase()+"%' or ";
	    	}
	    	sear+="location like '%"+sam[sam.length-1].toLowerCase()+"%'";
	        List<String> sortedLoc=new ArrayList<>();
	        String search="select * from users where "+sear,result="";
	        try {
	            ResultSet set=st.executeQuery(search);
	            while(set.next()){
	                String loc =set.getString("location");
	                int remaining=Integer.parseInt(set.getString("nooflots"));
	                String price=set.getString("price");
	                if(remaining>0){
	                    result=loc+"@"+remaining+"@"+price;
	                    sortedLoc.add(result);
	                    result="";
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return sortedLoc;
	    }
	    public void quickBook(Parkerdetails parkerdetails){
	    	System.out.println(parkerdetails.getLocation());
	        String search="select * from users where location=\""+parkerdetails.getLocation()+"\"",username="";
	        int lots=0,slot=0;
	        try {
	            ResultSet set=st.executeQuery(search);
	            while(set.next()){
	                username=set.getString("username");
	                lots=Integer.parseInt(set.getString("slots"));
	                slot= Integer.parseInt(set.getString("nooflots"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        String details[][][]=new String[lots][5][5];
	        String searchparks="select * from "+username+" where cost is null";
	        try {
	            ResultSet resultofusers=st.executeQuery(searchparks);
	            while(resultofusers.next()){
	                String index=resultofusers.getString("slotno");
	                String carnum=resultofusers.getString("carNumber");
	                if(carnum.equals(parkerdetails.getCar())){
	                    return;
	                }
	                int i= Integer.parseInt(index.split("@")[0]);
	                int j= Integer.parseInt(index.split("@")[1]);
	                int k= Integer.parseInt(index.split("@")[2]);
	                details[i][j][k]=carnum;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        String history="insert into history(name,location,phonenum,carNumber,hours,parktime,date) values(\""+parkerdetails.getName()+"\",\""+parkerdetails.getLocation()+"\"," +
	                "\""+parkerdetails
	                .getNumber()+"\",\""+parkerdetails.getCar()+"\",\""+parkerdetails.getHours()+"\",\""+parkerdetails.getParkTime()+"\",\""+parkerdetails.getDate()+"\")";

	        try {
	            st.execute(history);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        slot--;
	        String alter="update users set nooflots="+slot+" where username='"+username+"'";
	        try {
	            st.execute(alter);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        int i1=-1,j1=-1,k1=-1;
	        label:
	        for(int i=0;i<lots;i++) {
	            for (int j = 0; j < 5; j++) {
	                for (int k = 0; k < 5; k++) {
	                    if (details[i][j][k] == null) {
	                        i1 = i;
	                        j1 = j;
	                        k1 = k;
	                        break label;
	                    }
	                }
	            }
	        }
	        details[i1][j1][k1]=parkerdetails.getCar();
	        String ownerdet="insert into "+username+"(name,location,phonenum,carNumber,hours,parktime,slotno) values(\""+
	                parkerdetails.getName()+"\",\""+parkerdetails.getLocation()+"\",\""+parkerdetails.getNumber()+"\",\""+
	                parkerdetails.getCar()+"\",\""+parkerdetails.getHours()+"\",\""+parkerdetails.getParkTime()+"\",\""+
	                (i1+"@"+j1+"@"+k1)+"\")";
	        try {
	            st.execute(ownerdet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return;
	    }
	    public int checkuser(String name,String sampassword) {
	        
	        String password="";
	        try {
				MessageDigest ms=MessageDigest.getInstance("MD5");
				byte []byt=ms.digest(sampassword.getBytes());
				BigInteger big=new BigInteger(1,byt);
				password=big.toString(16);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String searchadmin = "select username,password from admin";
	        ResultSet rad= null;
	        try {
	            rad = st.executeQuery(searchadmin);
	            while(rad.next()){
	                String adminname=rad.getString("username");
	                String adminpassword=rad.getString("password");
	                if(adminname.equals(name) && adminpassword.equals(password)){
	                    return 1;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        String searchuser = "select username,password from users";
	        ResultSet rus= null;
	        try {
	            rus = st.executeQuery(searchuser);
	            while(rus.next()){
	                String username=rus.getString("username");
	                String userpassword=rus.getString("password");
	                if(username.equals(name) && userpassword.equals(password)){
	                    current=name+" "+password;
	                    return 0;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 2;
	    }
	    public List<Parkerdetails> search(String num){
	        String str="select * from history where carNumber=\""+num+"\"";
	        List<Parkerdetails> details=new ArrayList<>();
	        try {
	            ResultSet set=st.executeQuery(str);
	            while(set.next()){
	                Parkerdetails pd=new Parkerdetails();
	                pd.nameSet(set.getString("name"));
	                pd.setLocation(set.getString("location"));
	                pd.setDate(set.getString("date"));
	                pd.numberSet(set.getString("phonenum"));
	                pd.carSet(set.getString("carNumber"));
	                details.add(pd);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return details;
	    }
	    public int deleteLot(String lot){
	        String strmain="select * from users where username=\""+lot+"\"";
	        String str="delete from users where username=\""+lot+"\"";
	        String s="drop table "+lot;
	        try {
	            ResultSet set=st.executeQuery(strmain);
	            if(set.next()) {
	                st.execute(s);
	                st.execute(str);
	                return 0;
	            }
	                return 1;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 1;
	    }
	    public List<Parkerdetails> viewDetails(String date,String user){
	        List<Parkerdetails> details=new ArrayList<>();
	        String str="select h.* from history h inner join users u on h.location=u.location && u.username =\""+user+"\" && h.date=\""+date+"\"";
	        try {
	            ResultSet set=st.executeQuery(str);
	            while(set.next()){
	                Parkerdetails obj=new Parkerdetails();
	                obj.nameSet(set.getString("name"));
	                obj.setLocation(set.getString("location"));
	                obj.numberSet(set.getString("phonenum"));
	                obj.carSet(set.getString("carNumber"));
	                obj.setDate(set.getString("date"));
	                Timestamp time= Timestamp.valueOf(set.getString("parktime"));
	                obj.setParkTime(time.toLocalDateTime());
	                if(set.getString("endtime")!=null){
	                    Timestamp end= Timestamp.valueOf(set.getString("endtime"));
	                    obj.setendTime(end.toLocalDateTime());
	                }
	                details.add(obj);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return details;
	    }
	    public List<String> view(){
	    	List<String> view=new ArrayList<>();
	    	String select="select * from users";
	    	try {
				ResultSet set=st.executeQuery(select);
				while(set.next()) {
					String s=set.getString("username");
					s+="-"+set.getString("location");
					view.add(s);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return view;
	    }
	    public int payment(String number,String name){
	        int price=-1,cost=0,lots=0,amount=0;
	        String str="select * from users where username=\""+name+"\"";
	        try {
	            ResultSet set=st.executeQuery(str);
	            while(set.next()){
	                amount= Integer.parseInt(set.getString("amount"));
	                lots= Integer.parseInt(set.getString("nooflots"));
	                cost=Integer.parseInt(set.getString("price"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        str="select * from "+name+" where carNumber=\""+number+"\"";
	        String end= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	        int sam=0;
	        try {
	            ResultSet set=st.executeQuery(str);
	            while(set.next()){
	                sam=1;
	                Timestamp time= Timestamp.valueOf(set.getString("parktime"));
	                cost*=(LocalDateTime.now().getHour()-time.toLocalDateTime().getHour()+1);
	                price=cost;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if(sam==1){
	            lots++;
	            amount+=cost;
	            str="update "+name+" set endtime=\""+end+"\" , cost="+cost+" where carNumber=\""+number+"\"";
	            String history="update history set endtime=\""+end+"\" , cost="+cost+" where carNumber=\""+number+"\"";
	            String payment="update users set nooflots="+lots+" , amount="+amount+" where username=\""+name+"\"";
	            try {
	                st.execute(str);
	                st.execute(history);
	                st.execute(payment);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return price;
	    }
	    public String[][][] viewParks(String lot){
	        String str="select * from users where location=\""+lot+"\"";
	        String username="";
	        int lots=0;
	        try {
	            ResultSet set=st.executeQuery(str);
	            while(set.next()){
	                username=set.getString("username");
	                lots= Integer.parseInt(set.getString("slots"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        String s[][][]=new String[lots][5][5];
	        if(!username.equals("")) {
	            str = "select * from " + username + " where cost is null";
	            try {
	                ResultSet set = st.executeQuery(str);
	                while (set.next()) {
	                    String number = set.getString("carNumber");
	                    String index = set.getString("slotno");
	                    int i = Integer.parseInt(index.split("@")[0]);
	                    int j = Integer.parseInt(index.split("@")[1]);
	                    int k = Integer.parseInt(index.split("@")[2]);
	                    s[i][j][k] = number;
	                }
	                return s;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return new String[0][0][0];
	    }
	    public String[][][] viewlots(String user){
	        String query="select * from users where username=\""+user+"\"";
	        int lots=0;
	        try {
	            ResultSet set=st.executeQuery(query);
	            while(set.next()){
	                lots=Integer.parseInt(set.getString("slots"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        String str[][][]=new String[lots][5][5];
	        query="select * from "+user+" where cost is null";
	        try {
	            ResultSet set=st.executeQuery(query);
	            while(set.next()){
	                String num=set.getString("slotno");
	                String carnum=set.getString("carNumber");
	                int i=Integer.parseInt(num.split("@")[0]);
	                int j=Integer.parseInt(num.split("@")[1]);
	                int k=Integer.parseInt(num.split("@")[2]);
	                str[i][j][k]=carnum;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return str;
	    }
	    public void modify(String name,String password,int lots,String user,String orgpass){

	        String hashedPassord="";
	        try {
				MessageDigest ms=MessageDigest.getInstance("MD5");
				byte []byt=ms.digest(orgpass.getBytes());
				BigInteger big=new BigInteger(1,byt);
				hashedPassord=big.toString(16);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        String hashedPassord1="";
	        try {
				MessageDigest ms=MessageDigest.getInstance("MD5");
				byte []byt=ms.digest(password.getBytes());
				BigInteger big=new BigInteger(1,byt);
				hashedPassord1=big.toString(16);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        if(!name.equals("")){
	            String str="update users set username=\""+name+"\" where username=\""+user+"\" and password=\""+hashedPassord+"\"";
	            try {
	                st.execute(str);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(!password.equals("")){
	            String str="update users set password=\""+hashedPassord1+"\" where username=\""+user+"\" and password=\""+hashedPassord+"\"";
	            try {
	                st.execute(str);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(lots!=0){
	            String str="update users set slots="+lots+" where username=\""+user+"\" and password=\""+hashedPassord+"\"";
	            try {
	                st.execute(str);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public boolean addUser(subadmin admin){
	        String users="select username,password from users";
	        try {
	            ResultSet result=st.executeQuery(users);
	            while(result.next()){
	                if(result.getString("username").equals(admin.getUserid())){
	                    return false;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        String hashedPassord="";
	        try {
				MessageDigest ms=MessageDigest.getInstance("MD5");
				byte []byt=ms.digest(admin.getPassword().getBytes());
				BigInteger big=new BigInteger(1,byt);
				hashedPassord=big.toString(16);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        String insert="insert into users(username,password,location,slots,nooflots,price,amount) values(\"" +
	                admin.getUserid()+"\",\""+hashedPassord+"\",\""+admin.getLocation()+"\",\""+
	                admin.getSlots()+"\",\""+(admin.getSlots()*25)+"\",\""+admin.getPrice()+"\",\""+0+"\")";
	        String table="create table "+admin.getUserid()+"(name varchar(20),location varchar(20)," +
	                "phonenum varchar(20),carNumber varchar(20),hours int,parktime timestamp,endtime timestamp,cost int,slotno varchar(20))";
	        try {
	            st.execute(insert);
	            st.execute(table);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return true;
	    }
	}
	