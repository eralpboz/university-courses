package lab5;

public class Path  {
    private Node node1;
    private Node node2;
    private int distance;
    private boolean isShortest;

    public Path(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
        isShortest=false;

        double dx = node1.getX() - node2.getX();
        double dy = node1.getY() - node2.getY();
        distance = (int) Math.round(Math.sqrt(dx * dx + dy * dy));
        
    }

    public int getDistance() {
        return distance;
    }
    public void setDistance(int n){
        distance=n;
    }
    public boolean getIsShortes(){
        return isShortest;
    }
    public void setIsShortes(boolean x){
        isShortest=x;
    }
    public Node getNode1(){
        return node1;
    }
    public Node getNode2(){
        return node2;
    }
    
}
