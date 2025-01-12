import java.util.ArrayList;

public class SlimNode {
    //Instance Variables
    private boolean lastLetter;
    private int nodeChar;
    private SlimNode[] next;

    // Constructor
    public SlimNode(int nodeChar, boolean lastLetter, SlimNode parentNode, int nextIndex) {
        this.lastLetter = lastLetter;
        this.next = new SlimNode[3];
        for(int i = 0; i < next.length; i++){
            next[i] = null;
        }
        this.nodeChar = nodeChar;
        if(parentNode != null && nextIndex >=0 && nextIndex <=2){
            parentNode.setNext(nextIndex, this);
        }
    }

    // Getters and setters
    public int getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public boolean isLastLetter(){
        return lastLetter;
    }
    public void setLastLetter(boolean endWord){
        lastLetter = endWord;
    }

    public SlimNode getNext(int index) {
        if(index >= 0 && index < next.length){
            return next[index];
        }
        return null;
    }

    public void setNext(int index, SlimNode newNode) {
        if((index >= 0) && (index < next.length)){
            //System.out.println("Parent "+this.getNodeChar()+" Set next "+newNode.getNodeChar()+ " index "+ index);
            next[index] = newNode;
        }
    }

    // ToString for debugging.
    @Override
    public String toString() {
        return "Node{" +
                "lastLetter="+ lastLetter+
                ", nodeChar=" + nodeChar +
                '}';
    }
}

