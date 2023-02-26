package TeamInitialization;

import dto.Inningsdetails;
import dto.player;

import java.util.List;

public abstract class TeamControllerCallBack {
    public abstract void modifyTeam(List<player> team1, List<player> team2, int over);

    public abstract void setBatting(String win, String decision) ;

    public abstract void setgame(int man1, int man2, int bowler) ;

    public abstract void setscore(String man1,String man2,String bowler,int i);

    public abstract void batchoose(Inningsdetails setscore) ;

    public abstract void setman1(int man1);

    public abstract void bowlchoose(Inningsdetails setscore);

    public abstract void setBowler(int bowlnum);

    public abstract void batbowlchoose(Inningsdetails setscore);

    public abstract void batbowlset(int man1, int bowlnum);

    public abstract void winning(int i) ;

    public abstract void reset();
}
