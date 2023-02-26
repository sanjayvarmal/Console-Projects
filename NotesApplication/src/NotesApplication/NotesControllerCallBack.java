package NotesApplication;

public abstract class NotesControllerCallBack {

    public abstract void save(String str, String value);

    public abstract void getFile(String selectedIndex);

    public abstract void deleteFile(String valueOf);
}
