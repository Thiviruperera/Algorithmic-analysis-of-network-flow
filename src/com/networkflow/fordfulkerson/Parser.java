package com.networkflow.fordfulkerson;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

class Parser {
    private String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    private Edge getEdge(String str) {
        try {
            StringTokenizer st = new StringTokenizer(str, " ");
            int[] tokens = {0, 0, 0};
            String token = null;
            int tokenCount = 0;
            while (st.hasMoreTokens()) {
                if (tokenCount > 3) {
                    break;
                }
                token = st.nextToken();
                tokens[tokenCount] = Integer.parseInt(token);
                ++tokenCount;
            }
            return new Edge(tokens);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Parser Error !");
            System.exit(1);
        }
        return null;
    }

    public Graph processAndGetGraph() {
        Graph appGraph = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);    //file to be scanned
            int lineNumber = 0;
            String line = null;
            int numberOfNodes = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                ++lineNumber;
                // The first line contains the number n of nodes.
                if (1 == lineNumber) {
                    numberOfNodes = Integer.parseInt(line);
                    System.out.println(numberOfNodes);
                    appGraph = new Graph(numberOfNodes);
                    continue;
                }
                System.out.println(line);
                appGraph.insertEdge(getEdge(line));
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return appGraph;
    }
}