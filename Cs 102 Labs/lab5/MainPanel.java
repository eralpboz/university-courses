package lab5;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MainPanel extends JPanel {
    private ArrayList<Node> nodes;
    private ArrayList<Path> paths;
    private MouseMode currentMode;
    private Node selectedA;
    private Node selectedB;
    private ArrayList<Node> bestWay=new ArrayList<>();
    private int bestDistance =10000000;


    private Node draggedNode;
    private int dragOffsetX;
    private int dragOffsetY;

    public enum MouseMode {
        NONE,
        ADD_NODE,
        ADD_PATH,
        REMOVE_NODE,
        REMOVE_PATH,
        SET_START,
        SET_FINISH,
        MOVE_NODE,
        ADD_REVISION_NODE

    }

    public MainPanel() {
        setPreferredSize(new Dimension(500, 500));
        nodes = new ArrayList<>();
        paths = new ArrayList<>();
        currentMode = MouseMode.NONE;
        setBackground(Color.GRAY);
        selectedA = null;
        selectedB = null;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xPoint = e.getX();
                int yPoint = e.getY();

                if (currentMode == MouseMode.ADD_NODE) {
                    handleAddNode(xPoint, yPoint);
                } else if (currentMode == MouseMode.ADD_PATH) {
                    handleAddPath(xPoint, yPoint);
                } else if (currentMode == MouseMode.REMOVE_NODE) {
                    handleRemoveNode(xPoint, yPoint);
                } else if (currentMode == MouseMode.REMOVE_PATH) {
                    handleRemovePath(xPoint, yPoint);
                } else if (currentMode == MouseMode.SET_START) {
                    handleSetStart(xPoint, yPoint);
                } else if (currentMode == MouseMode.SET_FINISH) {
                    handleSetFinish(xPoint, yPoint);
                }else if (currentMode == MouseMode.ADD_REVISION_NODE) {
                    handleAddRevisionNode(xPoint,yPoint);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (currentMode != MouseMode.MOVE_NODE) {
                    return;
                }
                int xPoint = e.getX();
                int yPoint = e.getY();

                draggedNode = null;

                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                        draggedNode = nodes.get(i);

                        dragOffsetX = xPoint - draggedNode.getX();
                        dragOffsetY = yPoint - draggedNode.getY();
                    }
                }

            }

            public void mouseReleased(MouseEvent e) {
                if (currentMode != MouseMode.MOVE_NODE) {
                    return;
                }
                draggedNode = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }

        });

    }
    public Node findStartNode(){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getIsStart()){
                return nodes.get(i);
            }
        }
        return null;
    }
    public Node findFinishNode(){
        for(int i=0;i<nodes.size();i++){
            if(nodes.get(i).getIsFinish()){
                return nodes.get(i);
            }
        }
        return null;
    }
    public void reset(){
        for (int i = 0; i < paths.size(); i++) {
            paths.get(i).setIsShortes(false);
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setDistance(10000000);
            nodes.get(i).setPrevious(null);
            nodes.get(i).setVisited(false);
            nodes.get(i).setShortest(false);
            bestWay.clear();
            bestDistance=10000000;
        }
    }

    

        public void findShortestPath(Node node1, Node node2){
            if (node1 == null || node2 == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "You must select Start and Finish",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            reset();   
        
            
            finder(node1, node2, new ArrayList<>(), 0);
        
            if (bestWay.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No path found.");
                repaint();
                return;
            }
        
            
            for (int i = 0; i < bestWay.size(); i++) {
                bestWay.get(i).setShortest(true);
            }
        
            
            for (int i = 0; i < bestWay.size() - 1; i++) {
                Node a = bestWay.get(i);
                Node b = bestWay.get(i + 1);
        
                for (Path p : paths) {
                    if ((p.getNode1() == a && p.getNode2() == b) ||
                        (p.getNode2() == a && p.getNode1() == b)) {
                        p.setIsShortes(true);
                        break;
                    }
                }
            }
        
            repaint();
        }
        
        private void finder(Node first, Node last, ArrayList<Node> way, int distance) {

            if (distance >= bestDistance) {return;}
        
            way.add(first);
            first.setVisited(true);
        
            if (first == last) {
                bestDistance = distance;
                bestWay = new ArrayList<>(way);
        
                first.setVisited(false);
                way.remove(way.size() - 1);
                return;
            }
            
            for(int i=0;i<first.getPaths().size();i++){
                Node neighbor;
                if (first.getPaths().get(i).getNode1() == first) {
                    neighbor = first.getPaths().get(i).getNode2();
                } else {
                    neighbor = first.getPaths().get(i).getNode1();
                }
        
                if (!neighbor.isVisited()) {
                    int extra=0;
                    if(neighbor.getIsBus()){
                        extra=42;
                    }
                    finder(neighbor, last, way, distance + first.getPaths().get(i).getDistance()+extra);
                }
            }
            
        
            first.setVisited(false);
            way.remove(way.size() - 1);
        }
        
    


    public void setMode(MouseMode mode) {
        currentMode = mode;

        selectedA = null;
        selectedB = null;
        draggedNode = null;
        repaint();

    }

    private void handleAddRevisionNode(int xPoint, int yPoint){
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                return;
            }
        }

        Node newNode = new Node(xPoint, yPoint,true);
        nodes.add(newNode);

        repaint();
    }
   
    

    private void handleAddNode(int xPoint, int yPoint) {

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                return;
            }
        }

        Node newNode = new Node(xPoint, yPoint,false);
        nodes.add(newNode);

        repaint();
    }

    private void handleRemoveNode(int xPoint, int yPoint) {

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {

                Node removeNode = nodes.get(i);

                paths.removeAll(removeNode.getPaths());

                for (int j = 0; j < nodes.size(); j++) {
                    Node other = nodes.get(j);
                    for (int k = 0; k < other.getPaths().size(); k++) {
                        Path p = other.getPaths().get(k);
                        if (p.getNode1() == removeNode || p.getNode2() == removeNode) {
                            other.getPaths().remove(k);
                            k--;
                        }
                    }
                }

                nodes.remove(removeNode);

            }
        }

        repaint();
    }

    private void handleSetStart(int xPoint, int yPoint) {
        Node clickedNode = null;
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setStart(false);
        }

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                nodes.get(i).setFinish(false);
                nodes.get(i).setStart(true);

            }
        }

        repaint();
    }

    private void handleSetFinish(int xPoint, int yPoint) {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setFinish(false);
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                nodes.get(i).setStart(false);
                nodes.get(i).setFinish(true);
            }
        }

        repaint();
    }

    private void handleAddPath(int xPoint, int yPoint) {
        Node clickedNode = null;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).containsPoint(xPoint, yPoint)) {
                clickedNode = nodes.get(i);
            }
        }
        if (clickedNode == null) {
            selectedA = null;
            selectedB = null;
            return;
        }
        if (selectedA == null) {
            selectedA = clickedNode;
            selectedA.setClicked(true);
            repaint();
            return;
        }
        if (selectedB == null && clickedNode != selectedA) {
            selectedB = clickedNode;
        } else {
            selectedA.setClicked(false);
            selectedA = null;
            selectedB = null;
            return;
        }
        for (int i = 0; i < paths.size(); i++) {
            if ((paths.get(i).getNode1() == selectedA && paths.get(i).getNode2() == selectedB) ||
                    (paths.get(i).getNode2() == selectedA && paths.get(i).getNode1() == selectedB)) {

                selectedA = null;
                selectedB = null;
                selectedA.setClicked(false);
                repaint();
                return;
            }
        }
        Path path = new Path(selectedA, selectedB);
        paths.add(path);
        selectedA.addPath(path);
        selectedB.addPath(path);
        selectedA.setClicked(false);
        selectedA = null;
        selectedB = null;

        repaint();
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setClicked(false);
        }
    }

    private void handleRemovePath(int xPoint, int yPoint) {

        Node clickedNode = null;
        for (Node n : nodes) {
            if (n.containsPoint(xPoint, yPoint)) {
                clickedNode = n;
                break;
            }
        }

        if (clickedNode == null) {
            if (selectedA != null)
                selectedA.setClicked(false);
            selectedA = null;
            selectedB = null;
            repaint();
            return;
        }

        if (selectedA == null) {
            selectedA = clickedNode;
            selectedA.setClicked(true);
            repaint();
            return;
        }

        if (clickedNode == selectedA) {
            selectedA.setClicked(false);
            selectedA = null;
            selectedB = null;
            repaint();
            return;
        }

        if (selectedB == null) {
            selectedB = clickedNode;
        }

        Path toRemove = null;
        for (Path p : paths) {
            if ((p.getNode1() == selectedA && p.getNode2() == selectedB) ||
                    (p.getNode2() == selectedA && p.getNode1() == selectedB)) {
                toRemove = p;
                break;
            }
        }

        if (toRemove == null) {
            selectedA.setClicked(false);
            selectedA = null;
            selectedB = null;
            repaint();
            return;
        }

        selectedA.getPaths().remove(toRemove);
        selectedB.getPaths().remove(toRemove);
        paths.remove(toRemove);

        selectedA.setClicked(false);
        selectedA = null;
        selectedB = null;

        for (int i = 0; i < paths.size(); i++) {
            paths.get(i).setIsShortes(false);
        }

        repaint();
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).setClicked(false);
        }
    }

    private void handleMouseDrag(MouseEvent e) {
        if (currentMode != MouseMode.MOVE_NODE) {
            return;
        }
        if (draggedNode == null) {
            return;
        }

        int xPoint = e.getX();
        int yPoint = e.getY();

        int newX = xPoint - dragOffsetX;
        int newY = yPoint - dragOffsetY;

        draggedNode.setX(newX);
        draggedNode.setY(newY);

        for (Path p : draggedNode.getPaths()) {
            Node node1 = p.getNode1();
            Node node2 = p.getNode2();
            double dx = node1.getX() - node2.getX();
            double dy = node1.getY() - node2.getY();
            p.setDistance((int) Math.round(Math.sqrt(dx * dx + dy * dy)));
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            Node node1 = path.getNode1();
            Node node2 = path.getNode2();
            if (path.getIsShortes()) {
                g.setColor(Color.RED);
                g.drawLine(node1.getX() + 10, node1.getY() + 10, node2.getX() + 10, node2.getY() + 10);
            } else {
                g.setColor(Color.BLACK);

                g.drawLine(node1.getX() + 10, node1.getY() + 10, (node2.getX() + 10),
                        (node2.getY() + 10));

            }
        }
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            Node node1 = path.getNode1();
            Node node2 = path.getNode2();
            if (path.getIsShortes()) {

                g.setColor(Color.GRAY);
                g.fillOval((node2.getX() + 10 + node1.getX() + 10) / 2 - 17,
                        (node2.getY() + 10 + node1.getY() + 10) / 2 - 17, 27, 27);
                g.setColor(Color.RED);
                g.drawString(String.valueOf(path.getDistance()), (node2.getX() + 10 + node1.getX() + 10) / 2 - 12,
                        (node2.getY() + 10 + node1.getY() + 10) / 2 + 4);
            } else {
                g.setColor(Color.GRAY);
                g.fillOval((node2.getX() + 10 + node1.getX() + 10) / 2 - 14,
                        (node2.getY() + 10 + node1.getY() + 10) / 2 - 14, 22, 22);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(path.getDistance()), (node2.getX() + 10 + node1.getX() + 10) / 2 - 12,
                        (node2.getY() + 10 + node1.getY() + 10) / 2 + 4);

            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if((!node.getIsBus())){
            if (node.getIsClicked()) {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLUE);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsStart()) {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawString("S", node.getX() + 7, node.getY() + 12);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsFinish()) {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawString("F", node.getX() + 7, node.getY() + 12);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsShortes()) {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsClicked()) {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLUE);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            } else  {
                g.setColor(Color.GRAY);
                g.fillRect(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(node.getX(), node.getY(), 20, 20);
            }
        }
        if((node.getIsBus())){
            if (node.getIsClicked()) {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLUE);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsStart()) {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawString("S", node.getX() + 7, node.getY() + 12);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsFinish()) {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawString("F", node.getX() + 7, node.getY() + 12);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsShortes()) {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.RED);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            } else if (node.getIsClicked()) {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLUE);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            } else  {
                g.setColor(Color.GRAY);
                g.fillOval(node.getX(), node.getY(), 20, 20);
                g.setColor(Color.BLACK);
                g.drawOval(node.getX(), node.getY(), 20, 20);
            }
        }
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (node.getIsStart()) {

                g.setColor(Color.RED);
                g.drawString("S", node.getX() + 7, node.getY() + 12);

            } else if (node.getIsFinish()) {

                g.setColor(Color.RED);
                g.drawString("F", node.getX() + 7, node.getY() + 12);

            }
        }
    }

}