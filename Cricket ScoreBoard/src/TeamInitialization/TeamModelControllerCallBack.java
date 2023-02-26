package TeamInitialization;

import dto.Inningsdetails;
import dto.player;

import java.util.List;

public interface TeamModelControllerCallBack {
    void modified();

    void batting(List<List<player>> batting);

    void startgame(String man1, String man2, String bowler);

    void scoreBoard(Inningsdetails setscore);

    void batmandisplay(List<player> batchoose);

    void man1setted(String setman1);

    void bowldisplay(List<player> bowlchoose, String bowler);

    void bowlSetted(String setbowler);

    void batbowlSetted(List<List<player>> batbowlchoose);

    void batbowlchoosed(String setbowler);

    void winTeam(String winning);

    void resetted(List<List<player>> reset);
}
