package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Parents;
import br.ufsc.ine.ppgcc.entity.Weight;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Population {

    private final Random random;
    private final int populationLength;
    private List<Individual> population;
    private List<Individual> children;
    private final static Double mutRate = 0.01;
    private final int numberWeight;
    private final double idealCriteria;

    public Population(int populationLength, int numberWeight, double idealCriteria){
        this.populationLength = populationLength;
        this.numberWeight = numberWeight;
        this.idealCriteria = idealCriteria;
        random = new Random();
        children = new ArrayList<>();
        population = new ArrayList<>();
    }

    public List<Individual> getPopulation(){
        return population;
    }

    public List<Individual> getChildren(){
        return children;
    }

    public void nextGeneration(){
        population = children;
        children = new ArrayList<>();
    }

    public void startPopulation() {
        population = new ArrayList<>();
        IntStream.range(0, populationLength).forEach(i -> population.add(Engine.randomIndividual(numberWeight)));
    }

    public void probability() {
        double criteriaTotal = population.stream().mapToDouble(c -> selectCriteria(c.getSetWeight().getCriteria(), idealCriteria)).sum();
        population.forEach(i -> i.setOdd(calculateOdd(i.getSetWeight().getCriteria(), idealCriteria, criteriaTotal)));
    }

    private double calculateOdd(double actual, double ideal, double total){
        return selectCriteria(actual, ideal) / total;
    }

    private double selectCriteria(double actual, double ideal) {
        return Math.min(actual, ideal);
    }

    public Parents selection() throws Exception {
        Individual individualOne = selectIndividual().orElseThrow(Exception::new);

        Individual individualTwo;
        while(true) {
            Optional<Individual> individualOptional = selectIndividual();

            if(!individualOptional.isPresent() || individualOptional.get().equals(individualOne)){
                individualOptional = selectIndividualByMaxOdd(individualOne.getOdd());

                if(!individualOptional.isPresent()) continue;
            }

            individualTwo = individualOptional.get();
            break;
        }

        return new Parents(individualOne, individualTwo);
    }

    private Optional<Individual> selectIndividualByMaxOdd(double odd){
        double delta = .00001D;
        List<Individual> rest = population.stream().filter(i -> i.getOdd() < odd).collect(Collectors.toList());
        if(rest.isEmpty()) return Optional.empty();

        double max = rest.stream().mapToDouble(Individual::getOdd).max().orElse(0);
        return rest.stream().filter(i -> (i.getOdd() >= max - delta) & (i.getOdd() <= max + delta)).findFirst();
    }

    private Optional<Individual> selectIndividual(){
        double cutoff = random.nextDouble();
        double oddOne = convertOdd(cutoff);
        return population.stream().filter(i -> i.getOdd() >= oddOne).findAny();
    }

    private double convertOdd(double odd) {
        double maxOdd = population.stream().mapToDouble(Individual::getOdd).max().orElse(0);
        double minOdd = population.stream().mapToDouble(Individual::getOdd).min().orElse(0);

        return odd * (maxOdd - minOdd) + minOdd;
    }

    public Individual mutation(Individual individual) {
        double probabilityMutation = random.nextDouble();

        if (probabilityMutation <= mutRate) {
            for(Weight weight : individual.getSetWeight().getWeights()) {
                do {
                    int position = random.nextInt(weight.getChromosome().length);
                    int bound = position == weight.getChromosome().length - 1 ? 2 : 10;
                    int value = random.nextInt(bound);
                    int[] chromosome = weight.getChromosome();

                    if (chromosome[position] != value) {
                        chromosome[position] = value;
                        weight.setChromosome(chromosome);
                        break;
                    }
                } while (true);
            }
        }

        return individual;
    }

    public double[] getBestWeights() {
        double[] bestWeights = new double[numberWeight];
        double maxCriteria = population.stream().mapToDouble(i -> i.getSetWeight().getCriteria()).max().orElse(0);
        Optional<Individual> setWeight = population.stream().filter(p -> p.getSetWeight().getCriteria() == maxCriteria).findFirst();

        setWeight.ifPresent(weight ->
                IntStream.range(0, numberWeight).forEach(
                        index -> bestWeights[index] = weight.getSetWeight().getWeights()[index].getValue()
                )
        );

        return bestWeights;
    }
}