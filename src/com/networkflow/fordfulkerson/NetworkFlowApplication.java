package com.networkflow.fordfulkerson;


import edu.princeton.cs.introcs.Stopwatch;


public class NetworkFlowApplication {
    public static void main(String args[]) {
        if(args.length < 1) {
            System.out.println("Error, usage: java ClassName inputfile");
            System.exit(1);
        }


//        Parser parser = new Parser(".\\res\\files\\demo.txt");
        Parser parser = new Parser(args[0]);
        Graph graph = parser.processAndGetGraph();
        int n = graph.getNodeCount();
        int source = 0;
        int target = n - 1;
        NetworkFlowSolver solver = new NetworkFlowSolver(graph, source, target);

        Stopwatch stopwatch = new Stopwatch ();
        
        System.out.printf("Maximum Flow is: %d\n", solver.calculateMaxFlow());
        System.out.printf("Time Elapsed in Seconds: ");
        System.out.println(stopwatch.elapsedTime());
        System.out.println("~Done~");

    }



}
