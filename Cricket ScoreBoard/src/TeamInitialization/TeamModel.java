package TeamInitialization;

import dto.Inningsdetails;
import dto.player;
import repository.Repository;

import java.util.List;

public class TeamModel extends TeamModelCallBack{
    private TeamModelControllerCallBack teamModelControllerCallBack;
    public TeamModel(TeamController teamController) {
        this.teamModelControllerCallBack=teamController;
    }

    @Override
    public void modifyTeam(List<player> team1, List<player> team2, int over) {
        Repository.getInstance().ModifyDetails(team1,team2,over);
        teamModelControllerCallBack.modified();
    }

    @Override
    public void setBatting(String win, String decision) {
        List<List<player>> batting=Repository.getInstance().setBatting(win,decision);
        teamModelControllerCallBack.batting(batting);
    }

    @Override
    public void setgame(int man1, int man2, int bowler) {
        String name=Repository.getInstance().setplayer(man1,man2,bowler);
        teamModelControllerCallBack.startgame(name.split("@")[0],name.split("@")[1],name.split("@")[2]);
    }

    @Override
    public void setscore(String man1,String man2,String bowler,int i) {
        teamModelControllerCallBack.scoreBoard(Repository.getInstance().setscore(man1,man2,bowler,i));
    }

    @Override
    public void batchoose(Inningsdetails setscore) {
        teamModelControllerCallBack.batmandisplay(Repository.getInstance().batchoose());
    }

    @Override
    public void setman1(int man1) {
        teamModelControllerCallBack.man1setted(Repository.getInstance().setman1(man1));
    }

    @Override
    public void bowlchoose(Inningsdetails setscore) {
        List<player> player=Repository.getInstance().bowlchoose();
        teamModelControllerCallBack.bowldisplay(player,setscore.getBowler());
    }

    @Override
    public void setBowler(int bowlnum) {
        teamModelControllerCallBack.bowlSetted(Repository.getInstance().setbowler(bowlnum));
    }

    @Override
    public void batbowlchoose(Inningsdetails setscore) {
        teamModelControllerCallBack.batbowlSetted(Repository.getInstance().batbowlchoose());
    }

    @Override
    public void batbowlset(int man1, int bowlnum) {
        Repository.getInstance().setman1(man1);
        teamModelControllerCallBack.batbowlchoosed(Repository.getInstance().setbowler(bowlnum));
    }

    @Override
    public void winning(int i) {
        teamModelControllerCallBack.winTeam(Repository.getInstance().winning(i));
    }

    @Override
    public void reset() {
        teamModelControllerCallBack.resetted(Repository.getInstance().reset());
    }
}
