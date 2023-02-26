package dto;

public class Inningsdetails {
    private String player1;
    private String player2;
    private String bowler;
    private int p1score;
    private int p2score;
    private int score;
    private int wicket;
    private float remainingover;
    private int in;
    private int target;
    private int over[];
    public void setTarget(int target){
        this.target=target;
    }
    public int getTarget(){
        return target;
    }
    public void setRemainingover(float remainingover){
        this.remainingover=remainingover;
    }
    public float getRemainingover(){
        return remainingover;
    }
    public void setIn(int in){
        this.in=in;
    }
    public int getin(){
        return in;
    }
    public void setPlayer1(String player1){
        this.player1=player1;
    }
    public void setPlayer2(String player2){
        this.player2=player2;
    }
    public void setBowler(String bowler){
        this.bowler=bowler;
    }
    public void setp1score(int score){
        this.p1score=score;
    }
    public void setp2score(int score){
        this.p2score=score;
    }
    public void setscore(int score){
        this.score=score;
    }
    public void setwicket(int wicket){
        this.wicket=wicket;
    }
    public void setover(int []over){
        this.over=over.clone();
    }

    public String getPlayer1(){
        return this.player1;
    }
    public String getPlayer2(){
        return this.player2;
    }
    public String getBowler(){
        return this.bowler;
    }
    public int getp1score(){
        return this.p1score;
    }
    public int getp2score(){
        return this.p2score;
    }
    public int getscore(){
        return this.score;
    }
    public int getwicket(){
        return this.wicket;
    }
    public int[] getover(){
        return this.over;
    }
}
