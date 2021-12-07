package com.project.labyrinth.pathfinding;


/**
 * A class representing a node in a graph
 */
public class Node implements Comparable<Node>{
    private Node parent = null;
    private int totalPathWeight = Integer.MAX_VALUE, weightToGetHere = Integer.MAX_VALUE, heuristic, x, y;

    public Node(int heuristic, int x, int y){
        this.heuristic = heuristic;
        this.x = x;
        this.y = y;
    }

    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getTotalPathWeight() {
        return totalPathWeight;
    }

    public int getWeightToGetHere() {
        return weightToGetHere;
    }

    /**
     * Calculates the heuristic between this node and a target node, saving and returning it
     * @param target the node to which we want the heuristic
     * @return the heuristic between this node and the target
     */
    public int calculateAndSetHeuristic(Node target){
        this.heuristic = Math.abs(this.x - target.getX()) + Math.abs(this.y - target.getY());
        return this.heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTotalPathWeight(int totalPathWeight) {
        this.totalPathWeight = totalPathWeight;
    }

    public void setWeightToGetHere(int weightToGetHere) {
        this.weightToGetHere = weightToGetHere;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    /**
     * Compares this node to another using their weight
     * @param n the node to which we compare this node
     * @return an indicator of the best node
     */
    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.totalPathWeight, n.getTotalPathWeight());
    }

    @Override
    public boolean equals(Object o) {
        boolean res = false;
        if (o instanceof Node)
            res = ((Node)o).getX() == this.x && ((Node)o).getY() == this.y;
        return res;
    }

    @Override
    public String toString() {
        return "X = " + x + " ; Y = " + y;
    }
}
