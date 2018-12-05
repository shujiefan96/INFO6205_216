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
    long startTime, endTime, totalTime = 0;
    int generationFound = 0;
    public static int maxGenerations;
    private int populationSize = 200;
    private double mutationRate = 0.05;
    private double crossoverRate = 0.9;
    private int elitismSize = 2;
    private int tournamentSize = 10;

    /**
     * Initalize RobotController
     * 
     * @param maxGenerations Maximum generation
     * @param populationSize Genetic Algorithm Population Size
     * @param mutationRate Genetic Algorithm Mutation Rate
     * @param crossoverRate Genetic Algorithm Crossover Rate
     * @param elitismSize Genetic Algorithm Elitism Size
     * @param tournamentSize Genetic Algorithm Tournament Size
     */ 
    public RobotController(int maxGenerations, int populationSize, double mutationRate, double crossoverRate, 
            int elitismSize, int tournamentSize){
        this.maxGenerations = maxGenerations;        
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismSize = elitismSize;
        this.tournamentSize = tournamentSize;
    }
    
    /**
     * Initalize RobotController
     * 
     * @param maxGenerations Maximum generation
     */
    public RobotController(int maxGenerations){
        this.maxGenerations = maxGenerations;
    }


    /**
     * Get best route of each generation
     * 
     * @param m An array that stores maze information
     * @return HashMap<Integer, ArrayList<int[]>> HashMap that stores generations and best route of each generation 
     */
    public HashMap<Integer, ArrayList<int[]>> route(int[][] m){
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>();
        Maze maze = new Maze(m);

//        System.out.println("steps: " + maze.getSteps());
        System.out.println("Start Position: " + "{" + maze.getStartPosition()[0] + "," + maze.getStartPosition()[1] + "}");
        System.out.println("End Position: " + "{" + maze.getEndPosition()[0] + "," + maze.getEndPosition()[1] + "}");

        startTime = System.currentTimeMillis();
        
        // Create genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(this.populationSize, this.mutationRate, this.crossoverRate, this.elitismSize, this.tournamentSize);
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
        totalTime += endTime - startTime;
        
        System.out.println("------------------------------------------------------------------------");
        if(generation < maxGenerations){
            generationFound = generation;
            System.out.println("Stopped after " + generation + " generations:");
        }
        else{
            generationFound = maxGenerations;
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
    
    /**
     * Get string and printable format of route
     * 
     * @param route An ArrayList that stores route information
     * @return String Printable format of route 
     */
    public String printRoute(ArrayList<int[]> route){
        String routeString = "";
        
        for (int[] routeStep : route) {
            routeString += "{" + routeStep[0] + "," + routeStep[1] + "}";
        }        
        return routeString;
    }
    
    public long getTotalTime(){
        return this.totalTime;
    }

    public int getGenerationFound() {
        return this.generationFound;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public int getElitismSize() {
        return elitismSize;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }
    
    

}

