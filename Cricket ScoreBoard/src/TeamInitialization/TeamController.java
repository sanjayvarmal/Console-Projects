package TeamInitialization;

import dto.Inningsdetails;
import dto.player;

import java.util.List;

public class TeamController extends TeamControllerCallBack implements TeamModelControllerCallBack{
    private TeamModelCallBack teamModelCallBack;
    private TeamViewCallBack teamViewCallBack;
    public TeamController(TeamView teamView) {
        this.teamViewCallBack=teamView;
        this.teamModelCallBack=new TeamModel(this);
    }

    @Override
    public void modifyTeam(List<player> team1, List<player> team2, int over) {
        teamModelCallBack.modifyTeam(team1,team2,over);
    }

    @Override
    public void setBatting(String win, String decision) {
        teamModelCallBack.setBatting(win,decision);
    }

    @Override
    public void setgame(int man1, int man2, int bowler) {
        teamModelCallBack.setgame(man1,man2,bowler);
    }

    @Override
    public void setscore(String man1,String man2,String bowler,int i) {
        teamModelCallBack.setscore(man1,man2,bowler,i);
    }

    @Override
    public void batchoose(Inningsdetails setscore) {
        teamModelCallBack.batchoose(setscore);
    }

    @Override
    public void setman1(int man1) {
        teamModelCallBack.setman1(man1);
    }

    @Override
    public void bowlchoose(Inningsdetails setscore) {
        teamModelCallBack.bowlchoose(setscore);
    }

    @Override
    public void setBowler(int bowlnum) {
        teamModelCallBack.setBowler(bowlnum);
    }

    @Override
    public void batbowlchoose(Inningsdetails setscore) {
        teamModelCallBack.batbowlchoose(setscore);
    }

    @Override
    public void batbowlset(int man1, int bowlnum) {
        teamModelCallBack.batbowlset(man1,bowlnum);
    }

    @Override
    public void winning(int i) {
        teamModelCallBack.winning(i);
    }

    @Override
    public void reset() {
        teamModelCallBack.reset();
    }

    @Override
    public void modified() {
        teamViewCallBack.modified();
    }

    @Override
    public void batting(List<List<player>> batting) {
        teamViewCallBack.battingList(batting);
    }

    @Override
    public void startgame(String man1, String man2, String bowler) {
        teamViewCallBack.startgame(man1,man2,bowler);
    }

    @Override
    public void scoreBoard(Inningsdetails setscore) {
        teamViewCallBack.scoreBoard(setscore);
    }

    @Override
    public void batmandisplay(List<player> batchoose) {
        teamViewCallBack.batmandisplay(batchoose);
    }

    @Override
    public void man1setted(String setman1) {
        teamViewCallBack.man1setted(setman1);
    }

    @Override
    public void bowldisplay(List<player> bowlchoose, String bowler) {
        teamViewCallBack.bowldisplay(bowlchoose,bowler);
    }

    @Override
    public void bowlSetted(String setbowler) {
        teamViewCallBack.bowlsetted(setbowler);
    }

    @Override
    public void batbowlSetted(List<List<player>> batbowlchoose) {
        teamViewCallBack.batbowl(batbowlchoose);
    }

    @Override
    public void batbowlchoosed(String setbowler) {
        teamViewCallBack.batbowlchoosed(setbowler);
    }

    @Override
    public void winTeam(String winning) {
        teamViewCallBack.winTeam(winning);
    }

    @Override
    public void resetted(List<List<player>> reset) {
        teamViewCallBack.battingList(reset);
    }

}
