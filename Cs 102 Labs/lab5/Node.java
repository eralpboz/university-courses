package lab5;


import java.util.ArrayList;

public class Node {
    private int x;
    private int y;
    private final int SIZE = 20;
    private boolean isStart;
    private boolean isFinish;
    private boolean isShortest;
    private ArrayList<Path> paths = new ArrayList<>();
    private int distance;
    private Node previous;
    private boolean visited;
    private boolean isClicked;
    private boolean isBus;

    public Node(int x, int y, boolean isBus) {
        this.x = x;
        this.y = y;
        this.isBus=isBus;
        
        isStart = false;
        isFinish = false;
        isShortest = false;
        distance = 10000000;
        previous = null;
        visited = false;
        isClicked=false;
    }

    public int getX() {
        return x;
    }
    public boolean getIsBus(){
        return isBus;
    }
    public void setX(int a){
        x=a;
    }

    public int getY() {
        return y;
    }
    public void setY(int a){
        y=a;
    }

    public boolean getIsShortes() {
        return isShortest;
    }

    public void setShortest(boolean x) {
        isShortest = x;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public boolean getIsFinish() {
        return isFinish;
    }

    public void setStart(boolean x) {
        isStart = x;
    }

    public void setFinish(boolean x) {
        isFinish = x;
    }

    public void addPath(Path path) {
        paths.add(path);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean getIsClicked(){
        return isClicked;
    }
    public void setClicked(boolean x){
        isClicked=x;
    }

    public boolean containsPoint(int mx, int my) {
        return (mx >= x && mx <= x + SIZE &&
                my >= y && my <= y + SIZE);
    }

}
