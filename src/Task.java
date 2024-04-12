public class Task {
    String name;
    String date;
    Boolean completed;

    public Task(String name, String date, Boolean completed) {
        this.name = name;
        this.date = date;
        this.completed = false;
    }

    // get and set methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
