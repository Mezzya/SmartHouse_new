/**
 * Created by An on 18.02.2016.
 */
public class Keys {
//    Входы
    int id;
    int key;
    boolean status = false;
    Coils coils = null;

    void key(int id, int key, Coils coils)
    {
        this.id = id;
        this.key = key;
        this.coils = coils;
        //coment
    }

    boolean getStatus()
    {
        return status;
    }


}
