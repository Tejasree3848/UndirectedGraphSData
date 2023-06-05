package org.graph.com;

import java.util.*;
public class Graph {

    private Map<String, Node> nodes = new HashMap<String, Node>();

    public Graph() {

    }

    class Node {
        String name;
        List<Node> neighbors = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }

        public String toString() {
            return this.name;
        }
    }
    public void addEdge(String nodeName1, String nodeName2) {
        Node node1 = nodes.get(nodeName1);
        if (node1 == null) {
            node1 = new Node(nodeName1);
        }

        Node node2 = nodes.get(nodeName2);
        if (node2 == null) {
            node2 = new Node(nodeName2);
        }

        node1.addNeighbor(node2);
        node2.addNeighbor(node1);

        nodes.put(nodeName1, node1);
        nodes.put(nodeName2, node2);
    }

    List<List<String>> output = new ArrayList<>();
    public List<List<String>> printAllPaths(String startNodeName, String endNodeName) {

        BackTracking(startNodeName, endNodeName, new LinkedList<String>(), new HashMap<String, String>());

        return output;
    }

    public void BackTracking(String startNodeName, String endNodeName, List<String> localPathList,Map<String, String> parents) {

        if (startNodeName.equals(endNodeName)) {
            System.out.println(localPathList);
            output.add(localPathList);
            return;
        }
        Node node1 = nodes.get(startNodeName);
        parents.put(startNodeName, null);


        for (Node i : node1.neighbors) {
            String nodeName = i.getName();
            boolean visited = parents.containsKey(nodeName);
            if(!localPathList.contains(startNodeName)) {
                localPathList.add(startNodeName);
            }

            if (visited) {
                continue;
            } else {
                localPathList.add(nodeName);
                BackTracking(nodeName,endNodeName, localPathList,parents);
                localPathList.remove(localPathList.size() - 1);
            }
        }
    }

    public List<String> shortestPath(String startNodeName, String endNodeName) {
        Map<String, String> parents = new HashMap<String, String>();
        List<Node> temp = new ArrayList<Node>();

        Node start = nodes.get(startNodeName);
        temp.add(start);
        parents.put(startNodeName, null);

        while (temp.size() > 0) {
            Node currentNode = temp.get(0);
            List<Node> neighbors = currentNode.getNeighbors();

            for (int i = 0; i < neighbors.size(); i++) {
                Node neighbor = neighbors.get(i);
                String nodeName = neighbor.getName();

                boolean visited = parents.containsKey(nodeName);
                if (visited) {
                    continue;
                } else {
                    temp.add(neighbor);

                    parents.put(nodeName, currentNode.getName());

                    if (nodeName.equals(endNodeName)) {
                        System.out.println(parents);
                        return getPath(parents, endNodeName);
                    }
                }
            }

            temp.remove(0);
        }

        return null;
    }






    private List<String> getPath(Map<String, String> parents, String endNodeName) {
        List<String> path = new ArrayList<>();
        String node = endNodeName;
        while (node != null) {
            path.add(0, node);
            String parent = parents.get(node);
            node = parent;
        }
        return path;
    }
}
