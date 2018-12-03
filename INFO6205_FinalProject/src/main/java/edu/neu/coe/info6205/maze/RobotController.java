/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.maze;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author yangjing
 */
public class RobotController {
    long startTime, endTime;

    /**
     * Upper bound for the number of generations to run for. 200 generations is
     * sufficient to find the path about 50% of the time, but for demonstration
     * purposes we've set this high by default.
     */
    public static int maxGenerations = 100;

    public RobotController(int maxGenerations){
        this.maxGenerations = maxGenerations;
    }


    public HashMap<Integer, ArrayList<int[]>> route(int[][] m){
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>();
        Maze maze = new Maze(m);

        System.out.println("steps: " + maze.getSteps());
        System.out.println("Start Position: " + "{" + maze.getStartPosition()[0] + "," + maze.getStartPosition()[1] + "}");
        System.out.println("End Position: " + "{" + maze.getEndPosition()[0] + "," + maze.getEndPosition()[1] + "}");

        startTime = System.currentTimeMillis();
        
        // Create genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
        Population population = ga.initPopulation(128);
        ga.evalPopulation(population, maze);

        // Keep track of current generation
        int generation = 1;
        // Start evolution loop
        while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
            // Print fittest individual from population
            Individual fittest = population.getFittest(0);

            String route = "";       
            for (Object routeStep : fittest.getRoute()) {
                int step[] = (int[]) routeStep;
                route += "{" + step[0] + "," + step[1] + "}";
            }
            System.out.println("route: " + route);



            System.out.println(
                            "Generation" + generation + " Best solution (" + fittest.getFitness() + "): " + fittest.toString());


            map.put(generation, fittest.getRoute());

            if((maze.getSteps() == maze.scoreRoute(fittest.getRoute()) + 2) && 
                    fittest.getRoute().contains(maze.getStartPosition()) && 
                    fittest.getRoute().get(fittest.getRoute().size() - 1)[0] == maze.getEndPosition()[0] &&
                    fittest.getRoute().get(fittest.getRoute().size() - 1)[1] == maze.getEndPosition()[1]){
                endTime = System.currentTimeMillis();
                break;
            }


            // Apply crossover
            population = ga.crossoverPopulation(population);

            // Apply mutation
            population = ga.mutatePopulation(population);

            // Evaluate population
            ga.evalPopulation(population, maze);


            // Increment the current generation
            generation++;
        }

		// Create genetic algorithm
		GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
		Population population = ga.initPopulation(128);
		ga.evalPopulation(population, maze);
                
		// Keep track of current generation
		int generation = 1;
		// Start evolution loop
		while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
			// Print fittest individual from population
			Individual fittest = population.getFittest(0);
                          
                        String route = "";       
                        for (Object routeStep : fittest.getRoute()) {
                            int step[] = (int[]) routeStep;
                            route += "{" + step[0] + "," + step[1] + "}";
                        }
                        System.out.println("route: " + route);
                        
			System.out.println(
					"G" + generation + " Best solution (" + fittest.getFitness() + "): " + fittest.toString());

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population);

			// Evaluate population
			ga.evalPopulation(population, maze);
                        
                        map.put(generation, fittest.getRoute());

			// Increment the current generation
			generation++;
		}

		System.out.println("Stopped after " + maxGenerations + " generations.");
		Individual fittest = population.getFittest(0);
		System.out.println("Best solution (" + fittest.getFitness() + "): " + fittest.toString());
	
            return map;
        if(generation < maxGenerations){
            System.out.println("Stopped after " + generation + " generations.");
        }
        else{
            System.out.println("Stopped after " + maxGenerations + " generations.");
        }

        Individual fittest = population.getFittest(0);
        System.out.println("Best solution (" + fittest.getFitness() + "): " + fittest.toString());
        System.out.println("Running time: " + (endTime - startTime) + "milliseconds");

        return map;
    }


    public static void main(String[] args) {

            /**
             * Initialize a maze. We'll write this by hand, because, y'know, this
             * book isn't called "maze generation algorithms".
             * 
             * The 3s represent the correct route through the maze. 1s are walls
             * that can't be navigated through, and 0s are valid positions, but not
             * the correct route. You can follow the 3s visually to find the correct
             * path through the maze.
             * 
             * If you've read the docblock for
             * GeneticAlgorithm::isTerminationConditionMet, I mention that we don't
             * know what a perfect solution looks like, so the only constraint we
             * can give the algorithm is the number of generations. That's both true
             * and untrue. In this case, because we made the maze by hand, we
             * actually DO know the winning fitness: it's 29, or the number of 3s
             * below! However, we can't use that as a termination condition; if this
             * maze were procedurally generated we would not necessarily know that
             * the magic number is 29.
             * 
             * As a reminder: 
             * 0 = Empty 
             * 1 = Wall 
             * 2 = Starting position 
             * 3 = Route 
             * 4 = Goal position
             */



//                int[][] m = new int[][] { 
//                    { 0, 0, 0, 0, 1, 0, 1, 3, 2 }, 
//                    { 1, 0, 1, 1, 1, 0, 1, 3, 1 },
//                    { 1, 0, 0, 1, 3, 3, 3, 3, 1 }, 
//                    { 3, 3, 3, 1, 3, 1, 1, 0, 1 }, 
//                    { 3, 1, 3, 3, 3, 1, 1, 0, 0 },
//                    { 3, 3, 1, 1, 1, 1, 0, 1, 1 }, 
//                    { 1, 3, 0, 1, 3, 3, 3, 3, 3 }, 
//                    { 0, 3, 1, 1, 3, 1, 0, 1, 3 },
//                    { 1, 3, 3, 3, 3, 1, 1, 1, 4 } 
//                };

            int[][] m = new int[][] { 
                    { 0, 3, 3, 3, 2 }, 
                    { 1, 3, 1, 1, 1 },
                    { 1, 3, 0, 1, 0 }, 
                    { 0, 3, 1, 1, 1 }, 
                    { 1, 3, 3, 3, 4 } 
            };


            Maze maze = new Maze(m);

            System.out.println("steps: " + maze.getSteps());
            System.out.println("Start Position: " + "{" + maze.getStartPosition()[0] + "," + maze.getStartPosition()[1] + "}");
            System.out.println("End Position: " + "{" + maze.getEndPosition()[0] + "," + maze.getEndPosition()[1] + "}");

            // Create genetic algorithm
            GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 2, 10);
            Population population = ga.initPopulation(128);
            ga.evalPopulation(population, maze);

            // Keep track of current generation
            int generation = 1;
            // Start evolution loop
            while (ga.isTerminationConditionMet(generation, maxGenerations) == false) {
                    // Print fittest individual from population
                    Individual fittest = population.getFittest(0);

                    String route = "";       
                    for (Object routeStep : fittest.getRoute()) {
                        int step[] = (int[]) routeStep;
                        route += "{" + step[0] + "," + step[1] + "}";
                    }
                    System.out.println("route: " + route);



                    System.out.println(
                                    "G" + generation + " Best solution (" + fittest.getFitness() + "): " + fittest.toString());


                    if(maze.getSteps() == maze.scoreRoute(fittest.getRoute()) + 2){
                        break;
                    }


                    // Apply crossover
                    population = ga.crossoverPopulation(population);

                    // Apply mutation
                    population = ga.mutatePopulation(population);

                    // Evaluate population
                    ga.evalPopulation(population, maze);

                    // Increment the current generation
                    generation++;

            }

            if(generation < maxGenerations){
                System.out.println("Stopped after " + generation + " generations.");
            }
            else{
                System.out.println("Stopped after " + maxGenerations + " generations.");
            }

            Individual fittest = population.getFittest(0);
            System.out.println("Best solution (" + fittest.getFitness() + "): " + fittest.toString());

    }
}

