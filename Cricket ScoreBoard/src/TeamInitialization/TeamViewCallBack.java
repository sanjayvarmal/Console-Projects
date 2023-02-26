package TeamInitialization;

import dto.Inningsdetails;
import dto.player;

import java.util.List;

public abstract class TeamViewCallBack {
    public abstract void modified() ;

    public abstract void battingList(List<List<player>> batting) ;

    public abstract void startgame(String man1, String man2, String bowler);

    public abstract void scoreBoard(Inningsdetails setscore);

    public abstract void batmandisplay(List<player> batchoose);

    public abstract void man1setted(String setman1);

    public abstract void bowldisplay(List<player> bowlchoose, String bowler);

    public abstract void bowlsetted(String setbowler);

    public abstract void batbowl(List<List<player>> batbowlchoose);

    public abstract void batbowlchoosed(String setbowler);

    public abstract void winTeam(String winning);

}
