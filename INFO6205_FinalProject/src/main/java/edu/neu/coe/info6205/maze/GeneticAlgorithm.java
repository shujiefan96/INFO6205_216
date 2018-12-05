/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.coe.info6205.maze;

import java.util.Random;

/**
 *
 * @author yangjing
 */
public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismSize;
    private int tournamentSize;
    Random random = new Random();

    /**
     * Initalize GeneticAlgorithm
     * 
     * @param populationSize Genetic Algorithm Population Size
     * @param mutationRate Genetic Algorithm Mutation Rate
     * @param crossoverRate Genetic Algorithm Crossover Rate
     * @param elitismSize Genetic Algorithm Elitism Size
     * @param tournamentSize Genetic Algorithm Tournament Size
     */ 
    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismSize, int tournamentSize) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismSize = elitismSize;
        this.tournamentSize = tournamentSize;
    }

    /**
     * Initialize population
     * 
     * @param chromosomeLength The length of the individual's chromosome
     * @return Population The initial population generated
     */
    public Population initPopulation(int chromosomeLength) {
        // Initialize population
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    /**
     * Calculate individual's fitness.
     * 
     * @param individual The individual to evaluate
     * @return double Individual's fitness
     */
    public double calculateFitness(Individual individual, Maze maze) {
        //get individual's chromosome
        int[] chromosome = individual.getChromosome();

        //get fitness
        int maxMoves = 100;
        Robot robot = new Robot(chromosome, maze, maxMoves);
        robot.simulation();
        int fitness = maze.getRouteScore(robot.getRoute());

        //set fitness to individual
        individual.setFitness(fitness);
        //store route
        individual.setRoute(robot.getRoute());

        return fitness;
    }

    /**
     * Evaluate the whole population
     * 
     * @param population The population to evaluate
     * @param maze The maze to evaluate each individual against.
     */
    public void evaluatePopulation(Population population, Maze maze) {
        double totalFitness = 0;

        for (Individual individual : population.getIndividuals()) {
            totalFitness += this.calculateFitness(individual, maze);
        }

        population.setPopulationFitness(totalFitness);
    }

    /**
     * Check if generation has reached maximum generations
     * 
     * @param generationsCount Number of generations count
     * @param maxGenerations Maximum number of generations
     * @return boolean True if maximum generations reached, otherwise, false
     */
    public boolean isMaxGenerationsReached(int generationsCount, int maxGenerations) {
        return (generationsCount > maxGenerations);
    }

    /**
     * Selects parent for crossover using tournament selection, which is choosing N random individuals, and then
     * choosing the best of those.
     * 
     * @param population
     * @return Individual The individual selected as a parent
     */
    public Individual selectParent(Population population) {
        // Create tournament
        Population tournament = new Population(this.tournamentSize);

        // Add random individuals to the tournament
        Random random = new Random();
        for (int i = 0; i < this.tournamentSize; i++) {
            Individual tournamentIndividual = population.getIndividual(random.nextInt(tournamentSize));
            tournament.setIndividual(i, tournamentIndividual);
        }

        // Return the best
        return tournament.getIndividualByFitness(0);
    }

    /**
     * Apply mutation to population
     * 
     * @param population The population to apply mutation to
     * @return Population The mutated population
     */
    
    public Population mutatePopulation(Population population) {
        // Initialize new population
        Population newPopulation = new Population(this.populationSize);
        
        Individual[] pop = population.getSortedIndividuals();
        for (int popIndex = 0; popIndex < pop.length; popIndex++) {
            Individual individual = pop[popIndex];
            
            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (popIndex >= this.elitismSize) {
                    // Does this gene need mutation?
                    if (this.mutationRate > random.nextDouble()) {
                        if (individual.getGene(geneIndex) == 1) {
                            int newGene = 0;
                            // Mutate gene
                            individual.setGene(geneIndex, newGene);
                        }
                        else{
                            int newGene = 1;
                            // Mutate gene
                            individual.setGene(geneIndex, newGene);
                        }
                    }
                }
            }
            
            // Add individual to population
            newPopulation.setIndividual(popIndex, individual);
        }

        // Return mutated population
        return newPopulation;
    }

    /**
     * Crossover population using single point crossover
     *  
     * @param population Population to crossover
     * @return Population The new population
     */    
    public Population crossoverPopulation(Population population) {
        //create new population
        Population newPopulation = new Population(population.getSize());

        Individual[] pop = population.getSortedIndividuals();
        for (int popIndex = 0; popIndex < pop.length; popIndex++) {
            Individual parent1 = pop[popIndex];
            
            //check if it needs crossover
            if (this.crossoverRate > random.nextDouble() && popIndex >= this.elitismSize) {
                //initialize offspring
                Individual offspring = new Individual(parent1.getChromosomeLength());

                //find second parent
                Individual parent2 = this.selectParent(population);

                //get random swap point
                int swapPoint = (int)(random.nextDouble() * (parent1.getChromosomeLength() + 1));

                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Use part of parent1's genes and part of parent2's genes
                    if (geneIndex < swapPoint) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }

                //add offspring to new population
                newPopulation.setIndividual(popIndex, offspring);
            }         
            else {
                //add individual to new population without applying crossover
                newPopulation.setIndividual(popIndex, parent1);
            }
        }

        return newPopulation;
    }

}
