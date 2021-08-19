package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.SetWeight;
import br.ufsc.ine.ppgcc.entity.Weight;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class Engine {

    private final static Random random = new Random();

    public Engine() { }

    public static Individual randomIndividual(int numberWeights) {
        SetWeight setWeight = new SetWeight(numberWeights);
        Weight[] weight = new Weight[numberWeights];

        IntStream.range(0, numberWeights).forEach(index -> weight[index] = buildWeight());

        setWeight.setWeights(weight);
        return new Individual(setWeight);
    }

    public static Weight buildWeight() {
        double number = random.nextFloat();
        int decimalPart = random.nextInt(4);
        int signal = random.nextInt(2);

        double newValue = (number * Math.pow(10, decimalPart)) * (signal == 0 ? -1 : 1);

        return new Weight(newValue);
    }

    public static boolean isDone(Population population, double criteria) {
        Optional<Individual> findIndividual = population.getPopulation()
                .stream().filter(w -> w.getSetWeight().getCriteria() >= criteria).findAny();

        return findIndividual.isPresent();
    }
}
