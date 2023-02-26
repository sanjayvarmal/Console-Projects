package NotesApplication;

import Repository.Repository;

import java.util.Arrays;

public class NotesModel extends NotesModelCallBack {
    private NotesModelControllerCallBack notesModelControllerCallBack;
    public NotesModel(NotesController notesController) {
        this.notesModelControllerCallBack=notesController;
    }

    @Override
    public void save(String str, String value) {
        String []add=Repository.getInstance().add(str,value);
        notesModelControllerCallBack.setCombo(add);
    }

    @Override
    public void getFile(String selectedIndex) {
        notesModelControllerCallBack.DisplayFile(Repository.getInstance().getFile(selectedIndex));
    }

    @Override
    public void deleteFile(String valueOf) {
        notesModelControllerCallBack.setdelete(Repository.getInstance().delete(valueOf));
    }
}
