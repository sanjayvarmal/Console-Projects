package repository;

import java.util.*;

import Dto.Flights;
import Dto.Userlogin;
import Dto.users;

public class FlightRepository {
    private static FlightRepository repository;
     private Map<String,List<List<String>>> Users=new HashMap<>();
     private List<Flights> Flight=new ArrayList<>();
      private Userlogin data;
      private String CurrentLogin="";
      private String admin="GodCrazer "+Arrays.toString(("9090").toCharArray());
      private static int ID=100;
    private FlightRepository(){

    }
    public static FlightRepository getInstance(){
        if(repository==null){
            repository=new FlightRepository();
            repository.initialSetUp();
        }
        return repository;
    }
    public void initialSetUp(){
        Flights initial=new Flights();
        initial.SetId(001);
        initial.SetPrice(10000);
        initial.SetFlightName("KeyFlamer");
        initial.SetFrom("India");
        initial.SetTo("America");
        initial.SetDate("20/2/2023");
        initial.SetTime("9.30");
        initial.SetAllotedSeats(30);

        Flight.add(initial);

        Flights initial1=new Flights();
        initial1.SetId(002);
        initial1.SetPrice(20000);
        initial1.SetFlightName("FlamingRoster");
        initial1.SetFrom("Russia");
        initial1.SetTo("NewYork");
        initial1.SetDate("28/2/2023");
        initial1.SetTime("6.30");
        initial1.SetAllotedSeats(50);

        Flight.add(initial1);

        Flights initial2=new Flights();
        initial2.SetId(003);
        initial2.SetPrice(50000);
        initial2.SetFlightName("DevilsTail");
        initial2.SetFrom("England");
        initial2.SetTo("Spain");
        initial2.SetDate("1/3/2023");
        initial2.SetTime("3.30");
        initial2.SetAllotedSeats(80);

        Flight.add(initial2);

        Flights initial3=new Flights();
        initial3.SetId(004);
        initial3.SetPrice(100000);
        initial3.SetFlightName("RedGhosters");
        initial3.SetFrom("NewYork");
        initial3.SetTo("India");
        initial3.SetDate("28/2/2023");
        initial3.SetTime("6.30");
        initial3.SetAllotedSeats(100);
        Flight.add(initial3);
    }
    public List<Flights> checkFlights(String From,String Location,String date,int ticket){
        List<Flights> SortedLocations=new ArrayList<>();
        for(Flights obj:Flight){
            if(obj.GetFrom().equals(From) && obj.GetTo().equals(Location) && obj.GetDate().equals(date) && obj.getAllotedSeats()>=ticket)
                SortedLocations.add(obj);
        }
        return SortedLocations;
    }
    public int cancel(int id){
        List<List<String>> List3=Users.get(CurrentLogin);
        for(int i=0;i<List3.size();i++){
            if(List3.get(i).get(0).equals(id+"")){
                for(int j=0;j<Flight.size();j++){
                    if((Flight.get(j).GetId()+"").equals(List3.get(i).get(1)+"")){
                        Flight.get(j).SetAllotedSeats(Flight.get(j).getAllotedSeats()+1);
                        Flight.get(j).SetAmount(Flight.get(j).GetAmount()-(Flight.get(j).GetPrice()/2));
                        List3.remove(i);
                        Users.put(CurrentLogin,List3);
                        return id;
                    }
                }
            }
        }
        return 0;
    }
    public List<List<String>> viewBooked(){
        return Users.get(CurrentLogin);
    }
    public List<List<String>> addDetails(List<users> bulkdetails,int ticket){
        List<List<String>> list=new ArrayList<>();
        for(int j=0;j<bulkdetails.size();j++) {
            for (int i = 0; i < Flight.size(); i++) {
                if (bulkdetails.get(j).GetId() == Flight.get(i).GetId()) {
                    Flight.get(i).SetAllotedSeats(Flight.get(i).getAllotedSeats() - 1);
                    Flight.get(i).SetAmount(Flight.get(i).GetAmount()+Flight.get(i).GetPrice());
                    List<String> ls=new ArrayList<>();
                    ls.add(Flight.get(i).GetId()+"");
                    ls.add(Flight.get(i).GetFlightName());
                    ls.add(Flight.get(i).GetFrom());
                    ls.add(Flight.get(i).GetTo());
                    ls.add(Flight.get(i).GetTime());
                    ls.add(Flight.get(i).getAllotedSeats()+"");
                    ls.add(Flight.get(i).GetDate());
                    list.add(new ArrayList<>(ls));
                    break;
                }
            }
        }
        for(int i=0;i<bulkdetails.size();i++) {
           List<String> List2=new ArrayList<>();
            list.get(i).add(0,ID+"");
            List2.add(ID+"");
            List2.add(bulkdetails.get(i).GetId()+"");
            List2.add(bulkdetails.get(i).GetName());
            List2.add(list.get(i).get(1));
            List2.add(list.get(i).get(2));
            List2.add(list.get(i).get(3));
            List2.add(list.get(i).get(4));
            List2.add(list.get(i).get(6));
            List2.add(bulkdetails.get(i).GetAge()+"");
            List2.add(bulkdetails.get(i).GetGender());
            List2.add(bulkdetails.get(i).GetPhone()+"");
            List2.add(1+"");
            List<List<String>> List3=Users.get(CurrentLogin);
            List3.add(new ArrayList<>(List2));
            Users.put(CurrentLogin,List3);
            ID++;
        }
        return list;
    }
    public boolean changepassword(char []newPassword){
        List<List<String>> Bookingdata=Users.get(CurrentLogin);
        Users.remove(CurrentLogin);
        CurrentLogin=CurrentLogin.split(" ")[0]+" "+Arrays.toString(newPassword);
        Users.put(CurrentLogin,Bookingdata);
        return true;
    }
    public boolean addFlights(List<Flights> flights){
        Flight.addAll(flights);
        return true;
    }
    public List<Flights> showF(){
        return Flight;
    }
    public boolean modifyF(int id,int ticket,String Location,String landing,String date,String time){
        for(int i=0;i<Flight.size();i++){
            if(Flight.get(i).GetId()==id){
                if(ticket!=0){
                    Flight.get(i).SetPrice(ticket);
                }
                if(!Location.equals(""))
                    Flight.get(i).SetFrom(Location);
                if(!landing.equals(""))
                    Flight.get(i).SetTo(landing);
                if(!date.equals(""))
                    Flight.get(i).SetDate(date);
                if(!time.equals(""))
                    Flight.get(i).SetTime(time);
                return true;
            }
        }
        return false;
    }
    public int check(String username,char []password){
        if((username+" "+Arrays.toString(password)).equals(admin))
            return 0;
        if(Users.size()==0){
            CurrentLogin=username+" "+Arrays.toString(password);
            Users.put(username+" "+Arrays.toString(password),new ArrayList<>());
            return 1;
        }
        else{
            for(Map.Entry<String,List<List<String>>> entry:Users.entrySet()){
                if(entry.getKey().equals(username+" "+Arrays.toString(password)))
                    return 2;
            }
        }
        CurrentLogin=username+" "+Arrays.toString(password);
        Users.put(username+" "+Arrays.toString(password),new ArrayList<>());
        return 1;
    }
}
