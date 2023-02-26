package NotesApplication;

import java.util.Arrays;

public class NotesController extends NotesControllerCallBack implements NotesModelControllerCallBack {
    private NotesViewCallBack notesViewCallBack;
    private NotesModelCallBack notesModelCallBack;

    public NotesController(NotesView notesView) {
        this.notesViewCallBack=notesView;
        this.notesModelCallBack=new NotesModel(this);
    }

    @Override
    public void save(String str, String value) {
        notesModelCallBack.save(str,value);
    }

    @Override
    public void getFile(String selectedIndex) {
     notesModelCallBack.getFile(selectedIndex);
    }

    @Override
    public void deleteFile(String valueOf) {
        notesModelCallBack.deleteFile(valueOf);
    }

    @Override
    public void setCombo(String[] add) {
        notesViewCallBack.setCombo(add);
    }

    @Override
    public void DisplayFile(String file) {
        notesViewCallBack.DisplayFile(file);
    }

    @Override
    public void setdelete(String[] delete) {
        notesViewCallBack.setdelete(delete);
    }
}
