package com.project.labyrinth.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private int [][] map = null;

    public AStar(int[][] map){
        this.map = map;
    }

    public int getNextMove(int[] start, int[] target){
        Node startNode = new Node(0, start[0], start[1]);
        Node targetNode = new Node(0, target[0], target[1]);
        startNode.setWeightToGetHere(0);
        startNode.setTotalPathWeight(0);
        targetNode.setHeuristic(startNode.calculateAndSetHeuristic(targetNode));
        //call ASTAR
        Node pathEnd = aStar(startNode, targetNode);
        //System.out.println("pathEnd = " + pathEnd);
        Node next = nextStep(pathEnd);
        System.out.println("Next Step = " + next);
        int res = -1;
        if(next != null) {
            if (start[1] < next.getY()) // up
                res = 0;
            else if (start[1] > next.getY()) // down
                res = 1;
            else if (start[0] > next.getX()) // left
                res = 2;
            else if (start[0] < next.getX()) // right
                res = 3;
        }
        return res;
    }

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

    private Node nextStep(Node target){
        if(target != null)
            while(target.getParent().getParent() != null){
                target = target.getParent();
            }
        return target;
    }

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
            for (Node succ : getSuccessors(nodeTreated, target)){
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

    private ArrayList<Node> getSuccessors(Node n, Node target){
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
