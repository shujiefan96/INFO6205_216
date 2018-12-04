/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.maze;


import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author yangjing
 */
public class RobotController {
    long startTime, endTime;
    public static int maxGenerations;

    public RobotController(int maxGenerations){
        this.maxGenerations = maxGenerations;
    }


    public HashMap<Integer, ArrayList<int[]>> route(int[][] m){
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>();
        Maze maze = new Maze(m);

//        System.out.println("steps: " + maze.getSteps());
        System.out.println("Start Position: " + "{" + maze.getStartPosition()[0] + "," + maze.getStartPosition()[1] + "}");
        System.out.println("End Position: " + "{" + maze.getEndPosition()[0] + "," + maze.getEndPosition()[1] + "}");

        startTime = System.currentTimeMillis();
        
        // Create genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
        Population population = ga.initPopulation(128);
        ga.evaluatePopulation(population, maze);

        // Keep track of current generation
        int generation = 1;
        // Start evolution loop
        while (ga.isMaxGenerationsReached(generation, maxGenerations) == false) {
            // Print fittest individual from population
            Individual fittest = population.getIndividualByFitness(0);
                        
            System.out.println("Generation " + generation + ", Best solution (" + fittest.getFitness() + "): " + fittest.toString());
            System.out.println("Route: " + this.printRoute(fittest.getRoute()));

            map.put(generation, fittest.getRoute());

//            //if route found, stop
//            if((maze.getSteps() == maze.scoreRoute(fittest.getRoute()) + 2) && 
//                    fittest.getRoute().contains(maze.getStartPosition()) && 
//                    fittest.getRoute().get(fittest.getRoute().size() - 1)[0] == maze.getEndPosition()[0] &&
//                    fittest.getRoute().get(fittest.getRoute().size() - 1)[1] == maze.getEndPosition()[1] &&
//                    maze.getSteps() == fittest.getRoute().size()){
//                break;
//            }

            //if route found, stop
            if(fittest.getRoute().contains(maze.getStartPosition()) && 
                    fittest.getRoute().get(fittest.getRoute().size() - 1)[0] == maze.getEndPosition()[0] &&
                    fittest.getRoute().get(fittest.getRoute().size() - 1)[1] == maze.getEndPosition()[1] &&
                    maze.getSteps() == fittest.getRoute().size()){
                break;
            }


            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);

            // Evaluate population
            ga.evaluatePopulation(population, maze);

            // Increment the current generation
            generation++;
        }
        endTime = System.currentTimeMillis();
        
        System.out.println("------------------------------------------------------------------------");
        if(generation < maxGenerations){
            System.out.println("Stopped after " + generation + " generations:");
        }
        else{
            System.out.println("Stopped after " + maxGenerations + " generations:");
        }

        Individual fittest = population.getIndividualByFitness(0);

        System.out.println("Best Solution (" + fittest.getFitness() + "): " + fittest.toString());
        System.out.println("Best Route: " + this.printRoute(fittest.getRoute()));
        System.out.println("Actual Best Route: " + maze.getActualRoute());
        System.out.println("Running Time: " + (endTime - startTime) + "ms");
        System.out.println("------------------------------------------------------------------------");

        return map;
    }
    
    public String printRoute(ArrayList<int[]> route){
        String routeString = "";
        
        for (int[] routeStep : route) {
            routeString += "{" + routeStep[0] + "," + routeStep[1] + "}";
        }        
        return routeString;
    }
 
}

