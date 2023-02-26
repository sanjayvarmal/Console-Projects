package repository;

import dto.player;
import dto.Inningsdetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {
    private static repository.Repository Repository;
    private static List<player> team1=new ArrayList<>();
    private String bat="";
    private static List<player> team2=new ArrayList<>();
    private static float over;
    private static float overcopy=over;
    private String b1="";
    private String b2="";
    private String bowl="";
    private int scores[]=new int[6];
    private int wicket=0;
    private int score=0;
    public Repository(){

    }
    public static repository.Repository getInstance(){
        if(Repository==null){
            Repository=new Repository();
            intialize();
        }
        return Repository;
    }

    private static void intialize() {
        for(int i=0;i<11;i++){
            player team=new player();
            team.setPlayer("Oneplayer "+(i+1));
            team.setTeam("One");
            team.setRole(i%2==0?"bowl":"bat");
            team1.add(team);
        }
        for(int i=0;i<11;i++){
            player team=new player();
            team.setPlayer("Twoplayer "+(i+1));
            team.setTeam("Two");
            team.setRole(i%2==0?"bowl":"bat");
            team2.add(team);
        }
        over=5;
        overcopy=over;
    }

    public List<List<player>> setBatting(String team,String decision){
        List<List<player>> bowling=new ArrayList<>();
        if(team.charAt(1)=='1'){
            if(decision.equals("bat")) {
                bat="t1";
                bowling.add(new ArrayList<>(team1));
                List<player> bowl=new ArrayList<>();
                for(int i=0;i<team2.size();i++){
                    if(team2.get(i).getRole().equals("bowl")){
                        bowl.add(team2.get(i));
                    }
                }
                bowling.add(new ArrayList<>(bowl));
                return bowling;
            }
            else {
                bat="t2";
                bowling.add(new ArrayList<>(team2));
                List<player> bowl=new ArrayList<>();
                for(int i=0;i<team1.size();i++){
                    if(team1.get(i).getRole().equals("bowl")){
                        bowl.add(team1.get(i));
                    }
                }
                bowling.add(new ArrayList<>(bowl));
                return bowling;
            }
        }
        else{
            if(decision.equals("bat")) {
                bat="t2";
                bowling.add(new ArrayList<>(team2));
                List<player> bowl=new ArrayList<>();
                for(int i=0;i<team1.size();i++){
                    if(team1.get(i).getRole().equals("bowl")){
                        bowl.add(team1.get(i));
                    }
                }
                bowling.add(new ArrayList<>(bowl));
                return bowling;
            }
            else {
                bat="t1";
                bowling.add(new ArrayList<>(team1));
                List<player> bowl=new ArrayList<>();
                for(int i=0;i<team2.size();i++){
                    if(team2.get(i).getRole().equals("bowl")){
                        bowl.add(team2.get(i));
                    }
                }
                bowling.add(new ArrayList<>(bowl));
                return bowling;
            }
        }
    }
    public String setplayer(int man1,int man2,int bowler){
        String name;
        if(bat.equals("t1")) {
            team1.get(man1-1).setIn(1);
            b1=team1.get(man1-1).getPlayer();
            team1.get(man2-1).setIn(1);
            b2=team1.get(man2-1).getPlayer();
            name=team1.get(man1-1).getPlayer()+"@"+team1.get(man2-1).getPlayer();
            int count=0;
            for(int i=0;i<team2.size();i++){
                if (team2.get(i).getRole().equals("bowl")) {
                    count++;
                    if(count==bowler) {
                        team2.get(i).setIn(team2.get(i).getIn() + 1);
                        bowl=team2.get(i).getPlayer();
                        name+="@"+team2.get(i).getPlayer();
                        break;
                    }
                }
            }
        }
        else{
            team2.get(man1-1).setIn(1);
            b1=team2.get(man1-1).getPlayer();
            team2.get(man2-1).setIn(1);
            b2=team2.get(man2-1).getPlayer();
            name=team2.get(man1-1).getPlayer()+"@"+team2.get(man2-1).getPlayer();
            int count=0;
            for(int i=0;i<team1.size();i++){
                if (team1.get(i).getRole().equals("bowl")) {
                    count++;
                    if(count==bowler) {
                        team1.get(i).setIn(team1.get(i).getIn() + 1);
                        name+="@"+team1.get(i).getPlayer();
                        bowl=team1.get(i).getPlayer();
                        break;
                    }
                }
            }
        }
        return name;
    }
    public Inningsdetails setscore(String man1, String man2, String bowler, int i){
        Inningsdetails play=new Inningsdetails();
        if(i==1 || i==3){
            if(bat.equals("t1")) {
                for (int j=0;j<team1.size();j++){
                    if(team1.get(j).getPlayer().contains(man1)){
                        team1.get(j).setScore(team1.get(j).getScore()+i);
                        score+=i;
                        team1.get(j).setOut(2);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        scores[5-index]=i;
                        int finalover=0;
                        if((int) (over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                            over-=0.4;
                            finalover=1;
                            for(int l=0;l<team2.size();l++){
                                if(team2.get(l).getRole().equals("bowl") && team2.get(l).getPlayer().equals(bowler))
                                    team2.get(l).setIn(team2.get(l).getIn()+1);
                            }
                            play.setIn(3);
                        }
                        for(int k=0;k<team1.size();k++) {
                            if(team1.get(k).getPlayer().contains(man2)){
                                team1.get(k).setOut(2);
                                play.setPlayer1(finalover==1?man1:man2);
                                play.setPlayer2(finalover==1?man2:man1);
                                play.setp1score(team1.get(finalover==1?j:k).getScore());
                                play.setp2score(team1.get(finalover==1?k:j).getScore());
                                play.setBowler(bowler);
                                play.setRemainingover(over);
                                play.setscore(score);
                                play.setwicket(wicket);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
            else{
                for (int j=0;j<team2.size();j++){
                    if(team2.get(j).getPlayer().contains(man1)){
                        team2.get(j).setScore(team2.get(j).getScore()+i);
                        score+=i;
                        team2.get(j).setOut(2);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        scores[5-index]=i;
                        int finalover=0;
                        if((int) (over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                            over-=0.4f;
                            finalover=1;
                            for(int l=0;l<team1.size();l++){
                                if(team1.get(l).getRole().equals("bowl") && team1.get(l).getPlayer().equals(bowler))
                                    team1.get(l).setIn(team1.get(l).getIn()+1);
                            }
                            play.setIn(3);
                        }
                        for(int k=0;k<team2.size();k++) {
                            if(team2.get(k).getPlayer().contains(man2)){
                                team2.get(k).setOut(2);
                                play.setPlayer1(finalover==1?man1:man2);
                                play.setPlayer2(finalover==1?man2:man1);
                                play.setp1score(team2.get(finalover==1?j:k).getScore());
                                play.setp2score(team2.get(finalover==1?k:j).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setRemainingover(over);
                                play.setwicket(wicket);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
        }
        else if(i==2 || i==4 || i==6){
            if(bat.equals("t1")) {
                for (int j=0;j<team1.size();j++){
                    if(team1.get(j).getPlayer().contains(man1)){
                        team1.get(j).setScore(team1.get(j).getScore()+i);
                        score+=i;
                        team1.get(j).setOut(2);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        scores[5-index]=i;
                        int finalover=0;
                        if((int) (over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                            over-=0.4f;
                            finalover=1;
                            for(int l=0;l<team2.size();l++){
                                if(team2.get(l).getRole().equals("bowl") && team2.get(l).getPlayer().equals(bowler))
                                    team2.get(l).setIn(team2.get(l).getIn()+1);
                            }
                            play.setIn(3);
                        }
                        for(int k=0;k<team1.size();k++) {
                            if(team1.get(k).getPlayer().contains(man2)){
                                team1.get(k).setOut(2);
                                play.setPlayer1(finalover==1?man2:man1);
                                play.setPlayer2(finalover==1?man1:man2);
                                play.setp1score(team1.get(finalover==1?k:j).getScore());
                                play.setp2score(team1.get(finalover==1?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setRemainingover(over);
                                play.setwicket(wicket);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
            else{
                for (int j=0;j<team2.size();j++){
                    if(team2.get(j).getPlayer().contains(man1)){
                        team2.get(j).setScore(team2.get(j).getScore()+i);
                        score+=i;
                        team2.get(j).setOut(2);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        scores[5-index]=i;
                        int finalover=0;
                        if((int) (over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                            over-=0.4f;
                            finalover=1;
                            for(int l=0;l<team1.size();l++){
                                if(team1.get(l).getRole().equals("bowl") && team1.get(l).getPlayer().equals(bowler))
                                    team1.get(l).setIn(team1.get(l).getIn()+1);
                            }
                            play.setIn(3);
                        }
                        for(int k=0;k<team2.size();k++) {
                            if(team2.get(k).getPlayer().contains(man2)){
                                team2.get(k).setOut(2);
                                play.setPlayer1(finalover==1?man2:man1);
                                play.setPlayer2(finalover==1?man1:man2);
                                play.setp1score(team2.get(finalover==1?k:j).getScore());
                                play.setp2score(team2.get(finalover==1?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setRemainingover(over);
                                play.setwicket(wicket);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
        }
        else if(i%10==0){
            if(bat.equals("t1")) {
                for (int j=0;j<team1.size();j++){
                    if(team1.get(j).getPlayer().contains(man1)){
                        team1.get(j).setScore(team1.get(j).getScore()+(i/10));
                        score+=(i/10);
                        team1.get(j).setOut(2);
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        scores[5-(index>5?0:index)]+=(i/10);
                        for(int k=0;k<team1.size();k++) {
                            if(team1.get(k).getPlayer().contains(man2)){
                                team1.get(k).setOut(2);
                                play.setPlayer1((i/10)%2!=0?man2:man1);
                                play.setPlayer2((i/10)%2!=0?man1:man2);
                                play.setp1score(team1.get((i/10)%2!=0?k:j).getScore());
                                play.setp2score(team1.get((i/10)%2!=0?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setRemainingover(over);
                                play.setwicket(wicket);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
            else{
                for (int j=0;j<team2.size();j++){
                    if(team2.get(j).getPlayer().contains(man1)){
                        team2.get(j).setScore(team2.get(j).getScore()+(i/10));
                        score+=(i/10);
                        team2.get(j).setOut(2);
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        scores[5-(index>5?0:index)]+=(i/10);
                        for(int k=0;k<team2.size();k++) {
                            if(team2.get(k).getPlayer().contains(man2)){
                                team2.get(k).setOut(2);
                                play.setPlayer1((i/10)%2!=0?man2:man1);
                                play.setPlayer2((i/10)%2!=0?man1:man2);
                                play.setp1score(team2.get((i/10)%2!=0?k:j).getScore());
                                play.setp2score(team2.get((i/10)%2!=0?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setwicket(wicket);
                                play.setRemainingover(over);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
        }
        else if(i<=0){
            if(bat.equals("t1")) {
                for (int j=0;j<team1.size();j++){
                    if(team1.get(j).getPlayer().contains(man1)){
                        team1.get(j).setScore(team1.get(j).getScore()+Math.abs(i));
                        team1.get(j).setOut(1);
                        wicket++;
                        int bowindex=-1;
                        for(int l=0;l<team2.size();l++){
                            if(team2.get(l).getRole().equals("bowl") && team2.get(l).getPlayer().equals(bowler))
                            {
                                bowindex=l;
                                team2.get(l).setWicket(team2.get(l).getWicket()+1);
                            }
                        }
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        play.setIn(1);
                        score+=Math.abs(i);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        scores[5-index]=Math.abs(i);
                        if((int) (over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                                    team2.get(bowindex).setIn(team2.get(bowindex).getIn()+1);
                            over-=0.4;
                            play.setIn(4);
                        }
                        for(int k=0;k<team1.size();k++) {
                            if(team1.get(k).getPlayer().contains(man2)){
                                team1.get(k).setOut(2);
                                play.setPlayer1(i%2!=0?man2:man1);
                                play.setPlayer2(i%2!=0?man1:man2);
                                play.setp1score(team1.get((i/10)%2!=0?k:j).getScore());
                                play.setp2score(team1.get((i/10)%2!=0?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setwicket(wicket);
                                play.setRemainingover(over);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
            else{
                for (int j=0;j<team2.size();j++){
                    if(team2.get(j).getPlayer().contains(man1)){
                        team2.get(j).setScore(team2.get(j).getScore()+Math.abs(i));
                        team2.get(j).setOut(1);
                        wicket++;
                        if(scores[5]!=-1){
                            scores=new int[6];
                            Arrays.fill(scores,-1);
                        }
                        int bowindex=-1;
                        play.setIn(1);
                        for(int l=0;l<team1.size();l++){
                            if(team1.get(l).getRole().equals("bowl") && team1.get(l).getPlayer().equals(bowler)) {
                                bowindex=l;
                                team1.get(l).setWicket(team1.get(l).getWicket()+1);
                            }
                        }
                        score+=Math.abs(i);
                        over=Float.parseFloat(String.format("%.1f",(over-0.1f)));
                        int index=(int)(Float.parseFloat(String.format("%.1f",(over-0.4f)))*10)%10;
                        scores[5-index]+=Math.abs(i);
                        if((over*10)%10==4){
                            String ch=b1;
                            b1=b2;
                            b2=ch;
                            team1.get(bowindex).setIn(team1.get(bowindex).getIn() + 1);
                            over-=0.4;
                            play.setIn(4);
                        }
                        for(int k=0;k<team2.size();k++) {
                            if(team2.get(k).getPlayer().contains(man2)){
                                team2.get(k).setOut(2);
                                play.setPlayer1(i%2!=0?man2:man1);
                                play.setPlayer2(i%2!=0?man1:man2);
                                play.setp1score(team2.get((i/10)%2!=0?k:j).getScore());
                                play.setp2score(team2.get((i/10)%2!=0?j:k).getScore());
                                play.setBowler(bowler);
                                play.setscore(score);
                                play.setwicket(wicket);
                                play.setRemainingover(over);
                                play.setover(scores);
                                return play;
                            }
                        }
                    }
                }
            }
        }
        return play;
    }
    public List<player> batchoose(){
        if(bat.equals("t1"))
        return team1;
        else
            return team2;
    }
    public List<player> bowlchoose(){
        if(bat.equals("t1"))
            return team2;
        else
            return team1;
    }
    public String setman1(int man1){
        if(bat.equals("t1"))
            b1=team1.get(man1-1).getPlayer();
        else
            b1=team2.get(man1-1).getPlayer();
        return b1+"@"+b2+"@"+bowl;
    }
    public String setbowler(int bowler){
        int count=0;
        if(bat.equals("t1")) {
            for (int i = 0; i < team2.size(); i++) {
                if(team2.get(i).getRole().equals("bowl")){
                    count++;
                    if(bowler==count){
                       bowl=team2.get(i).getPlayer();
                        break;
                    }
                }
            }
        }
        else{
            for (int i = 0; i < team1.size(); i++) {
                if(team1.get(i).getRole().equals("bowl")){
                    count++;
                    if(bowler==count){
                        bowl=team1.get(i).getPlayer();
                        break;
                    }
                }
            }
        }
        return b1+"@"+b2+"@"+bowl;
    }
    public List<List<player>> batbowlchoose(){
        List<List<player>> player=new ArrayList<>();
        player.add(new ArrayList<>(batchoose()));
     player.add(new ArrayList<>(bowlchoose()));
     return player;
    }
    public List<List<player>> reset(){
        over=overcopy;
        score=0;
        scores=new int[6];
        wicket=0;
        for(int i=0;i<(bat.equals("t1")?team1:team2).size();i++){
                (bat.equals("t1")?team1:team2).get(i).setIn(0);
        }
        bat=bat.equals("t1")?"t2":"t1";
        return setBatting(bat,"bat");
    }
    public String winning(int i){
        if(i==0)
        return bat.equals("t1")?team1.get(0).getTeam():team2.get(0).getTeam();
        else
            return bat.equals("t1")?team2.get(0).getTeam():team1.get(0).getTeam();
    }
    public void ModifyDetails(List<player> team11,List<player> team22,int over){
        team1=new ArrayList<>(team11);
        team2=new ArrayList<>(team22);
        this.over=over;
        overcopy=over;
    }
}
