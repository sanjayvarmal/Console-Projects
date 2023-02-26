package repository;

import dto.Parkerdetails;
import dto.subadmin;

import java.awt.*;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class Repository {
    private static Repository repository;
    private List<Parkerdetails> History=new ArrayList<>();
    private List<subadmin> client=new ArrayList<>();
    private Map<String,List<Parkerdetails>> users=new HashMap<String,List<Parkerdetails>>();
    private String admin="demogod savitor";
    private String current="";
    private Repository(){

    }
    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    public List<String> checkLocation(String location){
        List<String> sortedLoc=new ArrayList<>();
        for(int i=0;i<client.size();i++){
            if(client.get(i).getLocation().indexOf(location)!=-1){
                if(client.get(i).getNooflots()>0)
                sortedLoc.add(client.get(i).getLocation()+"@"+client.get(i).getNooflots()+"@"+client.get(i).getPrice());
            }
        }
        return sortedLoc;
    }
    public String[][][] quickBook(Parkerdetails parkerdetails){
        int index=0;
        for(int i=0;i<client.size();i++){
            if(client.get(i).getLocation().equals(parkerdetails.getLocation())){
                index=i;
            }
        }
        String details[][][]=client.get(index).getFloor();
        int i1=-1,j1=-1,k1=-1;
        label:
        for(int i=0;i<details.length;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(details[i][j][k]!=null && details[i][j][k].equals(parkerdetails.getCar())){
                        return new String[0][0][0];
                    }
                    if(i1==-1 && details[i][j][k]==null){
                        i1=i;
                        j1=j;
                        k1=k;
                    }
                }
            }
        }
        History.add(parkerdetails);
        client.get(index).setNooflots(client.get(index).getNooflots()-1);
        if(i1!=-1){
            details[i1][j1][k1]=parkerdetails.getCar();
        }
        client.get(index).setFloors(details);
        List<Parkerdetails> parkerdetails1=new ArrayList<>();
        parkerdetails1.addAll(users.get(client.get(index).getUserid()+" "+client.get(index).getPassword()));
        parkerdetails1.add(parkerdetails);
        users.put(client.get(index).getUserid()+" "+client.get(index).getPassword(),parkerdetails1);
        return details;
    }
    public int checkuser(String name,String password){
        if(users.containsKey(name+" "+password)){
            current=name+" "+password;
            return 0;
        }
        if(admin.equals(name+" "+password))
            return 1;
        return 2;
    }
    public List<Parkerdetails> search(String num){
        List<Parkerdetails> details=new ArrayList<>();
        for(int i=0;i<History.size();i++){
            if(History.get(i).getCar().equals(num)){
                details.add(History.get(i));
            }
        }
        return details;
    }
    public int deleteLot(String lot){
        if(users.containsKey(lot)){
            users.remove(lot);
            for(int i=0;i<client.size();i++){
                if((client.get(i).getUserid()+" "+client.get(i).getPassword()).equals(lot)){
                   client.remove(i);
                    break;
                }
            }
            return 0;
        }
        else
            return 1;
    }
    public List<Parkerdetails> viewDetails(String date){
        String location="";
        List<Parkerdetails> details=new ArrayList<>();
        for(int i=0;i<client.size();i++){
            if((client.get(i).getUserid()+" "+client.get(i).getPassword()).equals(current)){
                location=client.get(i).getLocation();
                break;
            }
        }
        for(int i=0;i<History.size();i++){
            if(History.get(i).getLocation().equals(location) && History.get(i).getDate().equals(date)){
                details.add(History.get(i));
            }
        }
        return details;
    }
    public void number(String number,String details[][][],int k1){
        label:
        for(int i=0;i<details.length;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(details[i][j][k].equals(number)){
                        details[i][j][k]=null;
                        client.get(k1).setFloors(details);
                        break label;
                    }
                }
            }
        }
    }
    public int payment(String number){
        List<Parkerdetails> list = new ArrayList<>(users.get(current));
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCar().equals(number)){
                for(int j=0;j<History.size();j++){
                    if(History.get(j).getLocation().equals(list.get(i).getLocation())){
                        History.get(j).setendTime(LocalTime.now());
                        int amount=History.get(j).getParkTime().getHour()-History.get(j).getEndTime().getHour();
                        History.get(j).hourSet(-amount);
                        list.remove(i);
                        users.put(current,list);
                        for(int k=0;k<client.size();k++){
                            if(client.get(k).getUserid().equals(current.split(" ")[0])){
                                number(number,client.get(k).getFloor(),k);
                                int price = Math.abs((1+amount)*client.get(k).getPrice());
                                return price;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
    public String[][][] viewParks(String lot){
        for(int i=0;i<client.size();i++){
            if(client.get(i).getLocation().equals(lot)){
                return client.get(i).getFloor();
            }
        }
        return new String[0][0][0];
    }
    public String[][][] viewlots(){
        for(int i=0;i<client.size();i++){
            if(client.get(i).getUserid().equals(current.split(" ")[0])){
                return client.get(i).getFloor();
            }
        }
        return new String[0][0][0];
    }
    public void modify(String name,String password,int lots){
        for(int i=0;i<client.size();i++){
            if((client.get(i).getUserid()+" "+client.get(i).getPassword()).equals(current)){
                if(!name.equals("") && !password.equals("")){
                    client.get(i).setUserid(name);
                    client.get(i).setPassword(password);
                    List<Parkerdetails> list=new ArrayList<>();
                    list.addAll(users.get(current));
                    users.remove(current);
                    current=name+" "+password;
                    users.put(name+" "+password,list);
                }
                else if(!name.equals("")){
                    client.get(i).setUserid(name);
                    List<Parkerdetails> list=new ArrayList<>();
                    list.addAll(users.get(current));
                    users.remove(current);
                    users.put(name+" "+current.split(" ")[1],list);
                    current=name+" "+current.split(" ")[1];
                }
                else if(!password.equals("")){
                    client.get(i).setPassword(password);
                    List<Parkerdetails> list=new ArrayList<>();
                    list.addAll(users.get(current));
                    users.remove(current);
                    users.put(current.split(" ")[0]+" "+password,list);
                    current=current.split(" ")[0]+" "+password;
                }
                if(lots!=0){
                    client.get(i).setSlots(lots);
                }
                break;
            }
        }
    }
    public boolean addUser(subadmin admin){
        for(int i=0;i<client.size();i++){
            if(client.get(i).getUserid().equals(admin.getUserid())) {
                return false;
            }
        }
        client.add(admin);
        users.put(admin.getUserid()+" "+admin.getPassword(),new ArrayList<>());
        return true;
    }
}
