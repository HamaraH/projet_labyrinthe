package com.project.labyrinth.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Class calculating the shortest path between two points using the A* algorithm
 */
public class AStar {
    private int [][] map;

    /**
     * Creates the pathfinder and saves a map of the current level
     * @param map : the map of the current level
     */
    public AStar(int[][] map){
        this.map = map;
    }

    /**
     * Gets the next move an entity should go from a start to a goal
     * @param start the starting point
     * @param target the goal
     * @return the coordinates [x;y] of the next placement to get to the goal
     */
    public int[] getNextMove(int[] start, int[] target){
        Node startNode = new Node(0, start[0], start[1]);
        Node targetNode = new Node(0, target[0], target[1]);
        startNode.setWeightToGetHere(0);
        startNode.setTotalPathWeight(0);
        targetNode.setHeuristic(startNode.calculateAndSetHeuristic(targetNode));
        //call ASTAR
        Node pathEnd = aStar(startNode, targetNode);
        //System.out.println("pathEnd = " + pathEnd);
        Node next = nextStep(pathEnd);
        //System.out.println("Next Step = " + next);
        int[] res = new int[]{-1, -1};
        if (next != null)
            res = new int[]{next.getX(), next.getY()};
        return res;
    }

    /**
     * Gets the length of the shortest path to a from a start to a goal
     * @param start the starting point
     * @param target the goal
     * @return the length of the shortest path to get from the starting point to the goal
     */
    public int getPathLength(int[] start, int[] target){
        int res = -1;
        Node startNode = new Node(0, start[0], start[1]);
        Node targetNode = new Node(0, target[0], target[1]);
        startNode.setWeightToGetHere(0);
        startNode.setTotalPathWeight(0);
        targetNode.setHeuristic(startNode.calculateAndSetHeuristic(targetNode));
        Node pathEnd = aStar(startNode, targetNode);
        if (pathEnd != null) {
            res = 0;
            while(pathEnd.getParent() != null) {
                res++;
                pathEnd = pathEnd.getParent();
            }
        }
        return res;
    }

    /**
     * Calculates the next node to take from a complete path (first node)
     * @param target the ending node of the path
     * @return the first step of the path
     */
    private Node nextStep(Node target){
        if(target != null && target.getParent() != null)
            while(target.getParent().getParent() != null){
                target = target.getParent();
            }
        return target;
    }

    /**
     * Calculates the shortest path between two nodes, and returns the last node of the path
     * @param start the starting point from which to calculate the path
     * @param target the ending node of the path
     * @return the last node on the path
     */
    private Node aStar(Node start, Node target){
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(start);
        start.setWeightToGetHere(0);
        start.setTotalPathWeight(start.getWeightToGetHere() + start.calculateAndSetHeuristic(target));
        while(!openList.isEmpty()){
            Node nodeTreated = openList.peek();
            //System.out.println("Node treated = " + nodeTreated);
            if(nodeTreated.equals(target)){
                return nodeTreated;
            }
            for (Node succ : getSuccessors(nodeTreated)){
                //System.out.println("Succ = " + succ);
                if (!openList.contains(succ) && !closedList.contains(succ)){
                    succ.setParent(nodeTreated);
                    succ.setWeightToGetHere(nodeTreated.getWeightToGetHere() + 1);
                    succ.setTotalPathWeight(succ.getWeightToGetHere() + succ.calculateAndSetHeuristic(target));
                    openList.add(succ);
                }
                else{
                    if(openList.contains(succ))
                        for(Node trueSucc : openList)
                            if (trueSucc.equals(succ)) {
                                succ = trueSucc;
                                break;
                            }
                    if(closedList.contains(succ))
                        for(Node trueSucc : closedList)
                            if (trueSucc.equals(succ)) {
                                succ = trueSucc;
                                break;
                            }
                    if (nodeTreated.getWeightToGetHere() + 1 < succ.getWeightToGetHere()){
                        succ.setParent(nodeTreated);
                        succ.setWeightToGetHere(nodeTreated.getWeightToGetHere() + 1);
                        succ.setTotalPathWeight(succ.getWeightToGetHere() + succ.calculateAndSetHeuristic(target));

                        if(closedList.contains(succ)){
                            closedList.remove(succ);
                            openList.add(succ);
                        }
                    }
                }
            }

            openList.remove(nodeTreated);
            closedList.add(nodeTreated);
        }
        return null;
    }

    /**
     * Returns the successors (surroundings) of a specified node
     * @param n the node from which we want to get the successors
     * @return the successors of the specified node
     */
    private ArrayList<Node> getSuccessors(Node n){
        ArrayList<Node> res = new ArrayList<>();
        if (map[n.getX()][n.getY() + 1] != 1) { //up is free
            res.add(new Node(0, n.getX(), n.getY() + 1));
        }
        if (map[n.getX()][n.getY() - 1] != 1) { //down is free
            res.add(new Node(0, n.getX(), n.getY() - 1));
        }
        if (map[n.getX() - 1][n.getY()] != 1) { //left is free
            res.add(new Node(0, n.getX() - 1, n.getY()));
        }
        if (map[n.getX() + 1][n.getY()] != 1) { //right is free
            res.add(new Node(0, n.getX() + 1, n.getY()));
        }
        return res;
    }
}
