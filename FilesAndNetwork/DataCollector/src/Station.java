public class Station {
    private String name;
    private String depth;
    private String date;

    public Station(String name, String depth, String date) {
        this.name = name;
        this.depth = depth;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  name + " " + depth + " "+ date;
    }
}
