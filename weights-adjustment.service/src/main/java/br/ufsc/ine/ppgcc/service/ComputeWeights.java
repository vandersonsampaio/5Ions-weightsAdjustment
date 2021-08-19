package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Parents;

public abstract class ComputeWeights {

    private final int generations;
    private final double criteria;
    private final int numberWeights;

    public ComputeWeights(int generations, double criteria, int numberWeights) {
        this.generations = generations;
        this.criteria = criteria;
        this.numberWeights = numberWeights;
    }

    public double[] compute(int amount) throws Exception {
        Crossover crossover = new Crossover();

        Population population = new Population(amount, numberWeights, criteria);
        population.startPopulation();
        calculateCriteria(population);
        population.probability();

        int actualGeneration = 0;
        while (!Engine.isDone(population, criteria) && (actualGeneration++) <= generations) {
            for (int i = 0; i < amount / 2; i++) {
                Parents parents = population.selection();
                population.getChildren().add(population.mutation(crossover.crossover(parents)));
                population.getChildren().add(population.mutation(crossover.crossover(parents)));
            }

            population.nextGeneration();
            calculateCriteria(population);
            population.probability();
        }

        return population.getBestWeights();
    }

    protected abstract void calculateCriteria(Population population);
}
