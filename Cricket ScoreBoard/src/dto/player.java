package dto;

public class player {
    private String player;
    private String Role;
    private String team;
    private boolean caption;
    private int score;
    private int in;
    private int wicket;
    private int out;
    public void setIn(int in){
        this.in=in;
    }
    public void setOut(int out){
        this.out=out;
    }
    public int getOut(){
        return out;
    }
    public int getIn(){
        return this.in;
    }
    public void setPlayer(String player){
        this.player=player;
    }
    public void setRole(String role){
        this.Role=role;
    }
    public void setTeam(String team){
        this.team=team;
    }
    public void setCaption(boolean b){
        this.caption=b;
    }
    public void setScore(int score){
        this.score=score;
    }
    public void setWicket(int wicket){
        this.wicket=wicket;
    }


    public String getPlayer(){
        return this.player;
    }
    public String getRole(){
        return this.Role;
    }
    public String getTeam(){
        return this.team;
    }
    public boolean getCaption(){
        return this.caption;
    }
    public int getScore(){
        return this.score;
    }
    public int getWicket(){
        return this.wicket;
    }
}