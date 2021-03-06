package com.algorithms.algorithms.graphs.undirected.exercises;

import com.algorithms.algorithms.BaseAlgorithm;
import com.algorithms.algorithms.graphs.undirected.DFS;
import com.algorithms.algorithms.graphs.undirected.Graph;
import com.algorithms.interfaces.IAlgorithm;

/**
 * Created by Who on 2014/8/22.
 */
public class Exercise1 extends BaseAlgorithm implements IAlgorithm {
    @Override
    public long getID() {
        return 1408702482500l;
    }

    @Override
    public String getName() {
        return "Exercise #1 for Undirected  Graph";
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public void run() {
        Graph graph = new Graph(7);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(6, 4);
        graph.addEdge(6, 0);
        graph.addEdge(1, 0);
        graph.addEdge(2, 0);
        graph.addEdge(0, 5);
        DFS dfs = new DFS(graph, 0);
        print(dfs.marked);
        print(dfs.edgeTo);
    }
}
