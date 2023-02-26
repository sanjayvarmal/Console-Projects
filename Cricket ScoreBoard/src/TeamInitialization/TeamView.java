package TeamInitialization;

import dto.Inningsdetails;
import dto.player;

import java.util.*;

public class TeamView extends TeamViewCallBack{
    private Scanner scan=new Scanner(System.in);
    private TeamControllerCallBack teamControllerCallBack;
    private int overball=5;
    private int target;
    public TeamView(){
        teamControllerCallBack=new TeamController(this);
    }
    public static void main(String []args){
        TeamView teamView=new TeamView();
        teamView.Start();
    }
    public void Start(){
        System.out.println("----------------> Welcome to Compact Cricket <----------------");
        System.out.println("1.Change Game");
        System.out.println("2.Play");
        System.out.println("3.Exit");
        switch(scan.nextInt()){
            case 1:change();
            break;
            case 2:play();
            break;
            case 3:System.exit(0);
        }
    }
    private void play()  {
        System.out.println("Enter Team1 Head or Tail (h/t) :");
        char t1=scan.next().charAt(0);
        char t2=t1=='h'?'t':'h';
        Random r=new Random();
        System.out.println("---> Toss is Spinning!!!");
        System.out.println();
        try {
            Thread.sleep(1000);
        }
        catch(Exception e){

        }
        int i=r.nextInt(2);
        System.out.println(i==0?"Head":"Tail");
        String win=i==0?t1=='h'?"t1":"t2":"t2";
        System.out.println("Tose Win By "+win);
        System.out.println("Enter Batting or Bowling(bat/bowl)??");
        String decision=scan.next();
        teamControllerCallBack.setBatting(win,decision);
    }
    private void change() {
        List<player> team1=new ArrayList<>();
        List<player> team2=new ArrayList<>();
        System.out.println("Enter Team1 name :");
        String teamone=scan.next();
        System.out.println("Enter Team2 name :");
        String teamtwo=scan.next();
        System.out.println("Enter Total Overs :");
        int over=scan.nextInt();
        overball=over;
        System.out.println("Enter Team1 details :");
        int sam=0;
        for(int i=0;i<11;i++){
            player play=new player();
            System.out.println("Enter Player"+(i+1)+" details");
            System.out.println("Enter PlayerName");
            play.setPlayer(scan.next());
            System.out.println("Enter Role");
            play.setRole(scan.next());
            play.setTeam(teamone);
            if(sam==0) {
                System.out.println("choose this player as a Caption or not (y/n)");
                if (scan.next().charAt(0) == 'y') {
                    sam=1;
                    play.setCaption(true);
                }
            }
            team1.add(play);
        }
        System.out.println("Enter Team2 details :");
        sam=0;
        for(int i=0;i<11;i++){
            player play=new player();
            System.out.println("Enter Player"+(i+1)+" details");
            System.out.println("Enter PlayerName");
            play.setPlayer(scan.next());
            System.out.println("Enter Role");
            play.setRole(scan.next());
            play.setTeam(teamtwo);
            if(sam==0) {
                System.out.println("choose this player as a Caption or not (y/n)");
                if (scan.next().charAt(0) == 'y') {
                    sam=1;
                    play.setCaption(true);
                }
            }
            team2.add(play);
        }
        teamControllerCallBack.modifyTeam(team1,team2,over);
    }

    @Override
    public void modified() {
        System.out.println();
        System.out.println("<$$$$$*****$$$$$> Enter teams Setted <$$$$$*****$$$$$>");
        System.out.println();
        Start();
    }

    @Override
    public void battingList(List<List<player>> batting) {
        System.out.println("+--------------------------------------------------+");
        System.out.print("|");
        System.out.printf("%-30s"," Batting");
        System.out.printf("%-20s","Bowling");
        System.out.println("|");
        for(int i=0;i<batting.get(0).size();i++){
            System.out.print("|");
            System.out.printf("%-30s"," "+batting.get(0).get(i).getPlayer());
            System.out.printf("%-20s"," "+(batting.get(1).size()>i?batting.get(1).get(i).getPlayer():""));
            System.out.println("|");
        }
        System.out.println("+--------------------------------------------------+");
        System.out.println();
        System.out.println("-----> Choose batsman1 number <-----");
        int man1=scan.nextInt();
        System.out.println("-----> Choose batsman2 number<-----");
        int man2=scan.nextInt();
        System.out.println("-----> Choose bowler number <-----");
        int bowler=scan.nextInt();
        teamControllerCallBack.setgame(man1,man2,bowler);
    }

    @Override
    public void startgame(String man1, String man2, String bowler) {
        System.out.println("+--------------------------------------+");
        System.out.print("|");
        System.out.printf("%-38s"," Striker : "+man1);
        System.out.println("|");
        System.out.print("|");
        System.out.printf("%-38s"," Non-Striker : "+man2);
        System.out.println("|");
        System.out.print("|");
        System.out.printf("%-38s"," Bowler : "+bowler);
        System.out.println("|");
        System.out.println("+--------------------------------------+");
        System.out.println();
        System.out.println("---> Choose Entries <---");
        System.out.println("1.Run");
        System.out.println("2.Run");
        System.out.println("3.Run");
        System.out.println("4.Run");
        System.out.println("6.Run");
        System.out.println("7.Noball or Dot");
        System.out.println("8.Wicket");
        switch(scan.nextInt()){
            case 1:teamControllerCallBack.setscore(man1,man2,bowler,1);
                break;
            case 2:teamControllerCallBack.setscore(man1,man2,bowler,2);
                break;
            case 3:teamControllerCallBack.setscore(man1,man2,bowler,3);
                break;
            case 4:teamControllerCallBack.setscore(man1,man2,bowler,4);
                break;
            case 5:teamControllerCallBack.setscore(man1,man2,bowler,5);
                break;
            case 6:teamControllerCallBack.setscore(man1,man2,bowler,6);
                break;
            case 7:
                System.out.println("Enter no ball Run or Dot ball");
                teamControllerCallBack.setscore(man1,man2,bowler,scan.nextInt()*10);
                break;
            case 8:
                System.out.println("Enter Wicket Run  :");
                teamControllerCallBack.setscore(man1,man2,bowler,-scan.nextInt());
                break;
        }
    }

    @Override
    public void scoreBoard(Inningsdetails setscore) {
        System.out.println("+---------------------------------------------+");
        System.out.print("|");
        System.out.printf("%-20s"," "+setscore.getPlayer1()+" :");
        System.out.printf("%-10d",setscore.getp1score());
        System.out.printf("%-6s","Total :");
        System.out.printf("%-4s",setscore.getscore()+"/");
        System.out.printf("%-4d",setscore.getwicket());
        System.out.println("|");
        System.out.print("|");
        System.out.printf("%-19s"," "+setscore.getPlayer2()+" :");
        System.out.printf("%-10d",setscore.getp2score());
        System.out.printf("%-5s","");
        System.out.printf("%-10s",setscore.getBowler());
        System.out.println("|");
        if(target!=0) {
            System.out.print("|");
            System.out.printf("%-45s", " " + target);
            System.out.println("|");
        }
        System.out.print("|");
        System.out.printf("%-13s","");
        System.out.printf("%-23s",Arrays.toString(setscore.getover()));
        System.out.printf("%-10s","");
        System.out.println("|");
        System.out.println("+---------------------------------------------+");
        if(target!=0 && target<=setscore.getscore()){
            teamControllerCallBack.winning(1);
        }
        else if((int) (setscore.getRemainingover()*10)%100==0){
            System.out.println("----> Over Ended <----");
            if(target!=0 ){
                teamControllerCallBack.winning(0);
            }
            else {
                System.out.println("TARGET ====> " + setscore.getscore());
                target = setscore.getscore();
                teamControllerCallBack.reset();
            }
        }
        else if(setscore.getin()!=0){
            if(setscore.getin()==1){
                System.out.println("<**** Striker Out ****>");
                teamControllerCallBack.batchoose(setscore);
            }
            else if(setscore.getin()==3){
                System.out.println("<**** Over Ended ****>");
                System.out.println("<`````` bowler changed ``````>");
                teamControllerCallBack.bowlchoose(setscore);
            }
            else{
                teamControllerCallBack.batbowlchoose(setscore);
            }
        }
        else{
            startgame(setscore.getPlayer1(),setscore.getPlayer2(),setscore.getBowler());
        }
    }

    @Override
    public void batmandisplay(List<player> batchoose) {
        System.out.println("Choose Not Out batsman");
        String s="";
        System.out.println("+-----------------------------------+");
        for(int i=0;i<batchoose.size();i++){
            System.out.print("|");
            System.out.printf("%-24s"," "+batchoose.get(i).getPlayer());
            s+=batchoose.get(i).getOut()==0?"@"+(i+1)+"@":"";
            System.out.printf("%-10s",batchoose.get(i).getOut()==1?"Out":batchoose.get(i).getOut()==2?"Playing":"Not Out");
            System.out.println("|");
        }
        System.out.println("+-----------------------------------+");
        System.out.println();
        System.out.println("Choose Batsman number : ");
        int man1=scan.nextInt();
        while(!s.contains("@"+man1+"@")){
            System.out.println("Enter Not Out Batsman ??");
            man1=scan.nextInt();
        }
        teamControllerCallBack.setman1(man1);
    }

    @Override
    public void man1setted(String setman1) {
        startgame(setman1.split("@")[0],setman1.split("@")[1],setman1.split("@")[2]);
    }

    @Override
    public void bowldisplay(List<player> bowlchoose, String bowler) {
        System.out.println("+-----------------------------------+");
        String bowler1="";
        int count=0;
        for(int i=0;i<bowlchoose.size();i++){
            if(bowlchoose.get(i).getRole().equals("bowl")) {
                count++;
                if(bowlchoose.get(i).getIn()<overball/5 && !bowlchoose.get(i).getPlayer().equals(bowler))
                    bowler1+=count;
                    System.out.print("|");
                    System.out.printf("%-24s", " " + bowlchoose.get(i).getPlayer());
                    System.out.printf("%-10s", bowlchoose.get(i).getIn());
                    System.out.println("|");
            }
        }
        System.out.println("+-----------------------------------+");
        System.out.println();
        System.out.println("Choose Bowler Number");
        int bowlnum=scan.nextInt();
        while(!bowler1.contains(bowlnum+"")){
            System.out.println("<~~~~~~ Entered Bowler Has Not Enough Chance Choose another ~~~~~~>");
            bowlnum=scan.nextInt();
        }
        teamControllerCallBack.setBowler(bowlnum);
    }

    @Override
    public void bowlsetted(String setbowler) {
        startgame(setbowler.split("@")[0],setbowler.split("@")[1],setbowler.split("@")[2]);
    }

    @Override
    public void batbowl(List<List<player>> batbowlchoose) {
        System.out.println("+-----------------------------------+");
        String bowler1="";
        int count=0;
        for(int i=0;i<batbowlchoose.get(1).size();i++){
            if(batbowlchoose.get(1).get(i).getRole().equals("bowl")) {
                count++;
                if(batbowlchoose.get(1).get(i).getIn()<overball/5)
                    bowler1+=count;
                System.out.print("|");
                System.out.printf("%-24s", " " + batbowlchoose.get(1).get(i).getPlayer());
                System.out.printf("%-10s", batbowlchoose.get(1).get(i).getIn());
                System.out.println("|");
            }
        }
        System.out.println("+-----------------------------------+");
        System.out.println();
        System.out.println("Choose Bowler Number");
        int bowlnum=scan.nextInt();
        while(!bowler1.contains(bowlnum+"")){
            System.out.println("<~~~~~~ Entered Bowler Has Not Enough Chance Choose another ~~~~~~>");
            bowlnum=scan.nextInt();
        }
        // bat display
        String s="";
        System.out.println("+-----------------------------------+");
        for(int i=0;i<batbowlchoose.get(0).size();i++){
            System.out.print("|");
            System.out.printf("%-24s"," "+batbowlchoose.get(0).get(i).getPlayer());
            s+=batbowlchoose.get(0).get(i).getOut()==0?"@"+(i+1)+"@":"";
            System.out.printf("%-10s",batbowlchoose.get(0).get(i).getOut()==1?"Out":batbowlchoose.get(0).get(i).getOut()==2?"Playing":"Not Out");
            System.out.println("|");
        }
        System.out.println("+-----------------------------------+");
        System.out.println();
        System.out.println("Choose Batsman number : ");
        int man1=scan.nextInt();
        while(!s.contains("@"+man1+"@")){
            System.out.println("Enter Not Out Batsman ??");
            man1=scan.nextInt();
        }
        teamControllerCallBack.batbowlset(man1,bowlnum);
    }

    @Override
    public void batbowlchoosed(String setbowler) {
        startgame(setbowler.split("@")[0],setbowler.split("@")[1],setbowler.split("@")[2]);
    }

    @Override
    public void winTeam(String winning) {
        System.out.println("<~~~~~~> "+winning+" Wins <~~~~~~>");
    }
}
