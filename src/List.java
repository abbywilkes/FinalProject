import java.util.ArrayList;
public class List {
    ArrayList<Task> list;

    public List(ArrayList list) {
        this.list = list;
    }

    // get and set methods
    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
}
