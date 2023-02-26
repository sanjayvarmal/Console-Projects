package Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private static Repository repository;
    private Map<String,String> folder=new HashMap<>();

    public static Repository getInstance(){
        if(repository==null){
            repository=new Repository();
        }
        return repository;
    }
    public String[] add(String name,String value){
        folder.put(name,value);
        return folder.keySet().toArray(new String[0]);
    }
    public String getFile(String index){
        return folder.get(index);
    }
    public String[] delete(String name){
        folder.remove(name);
        return folder.keySet().toArray(new String[0]);
    }
}
