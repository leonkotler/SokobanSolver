package test;


import loader.MyObjectLevelSaver;
import loader.MyTextLevelLoader;
import loader.MyXMLLevelSaver;
import loader.level.Level;
import planner.plannable.PlanAction;
import planner.planner.Strips;
import searcher.searchable.Position;
import searcher.searcher.BFS;
import searcher.searcher.Dijkstra;
import sokoban.SokobanHeuristic;
import sokoban.SokobanPlannable;
import sokoban.SokobanSolver;

import java.io.*;
import java.util.List;

public class Run {

    public static void main(String[] args){

        if(args.length<2){
            System.out.println("Please provide a level file and a file for solution. ");
            System.exit(1);
        }

        // In case you want to create an Obj and XML level from text level
        //convertTxtLevel(args[0]);

        // Solving the level
        SokobanPlannable sp = new SokobanPlannable(args[0],new BFS<Position>(),new BFS<Position>());
        //SokobanPlannable sp = new SokobanPlannable(args[0],new Dijkstra<Position>(),new Dijkstra<Position>());
        SokobanSolver solver = new SokobanSolver(sp,new Strips());
        solver.setHeuristic(new SokobanHeuristic()); // Setting heuristic method to the solver
        List<PlanAction> plan = solver.solve(); // Getting the solution

        // Writing the solution to a file
        try {
            PrintWriter out = new PrintWriter(new File(args[1]));
            for(PlanAction a : plan)
                out.println(a);
            out.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}

        System.out.println("Solved! Please check " + args[1]);

    }

    public static void convertTxtLevel(String filePath){
        MyTextLevelLoader txtLoader = new MyTextLevelLoader();
        MyObjectLevelSaver objSaver = new MyObjectLevelSaver();
        MyXMLLevelSaver xmlSaver = new MyXMLLevelSaver();
        Level level = null;
        try {
            level = txtLoader.loadLevel(new FileInputStream(filePath));
            objSaver.saveLevel(level, new FileOutputStream(filePath.replaceFirst("txt","obj")));
            xmlSaver.saveLevel(level, new FileOutputStream(filePath.replaceFirst("txt","xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
