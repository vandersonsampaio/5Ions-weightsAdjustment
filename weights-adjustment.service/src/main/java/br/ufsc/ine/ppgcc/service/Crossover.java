package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Parents;
import br.ufsc.ine.ppgcc.entity.SetWeight;
import br.ufsc.ine.ppgcc.entity.Weight;

import java.util.Random;
import java.util.stream.IntStream;

public class Crossover {

    private static final int precision = 9;
    private final Random random = new Random();

    public Crossover() { }

    public Individual crossover(Parents parents) {
        int[] chromosome = new int[precision];
        int cutoff = random.nextInt(precision - 2);
        cutoff = (cutoff == 0) ? 1 : cutoff;

        boolean firstParent = random.nextInt(2) == 0;

        SetWeight son = new SetWeight(parents.getParentOne().getSetWeight().getWeights().length);
        Weight[] weight = son.getWeights();

        for(int j = 0; j < son.getWeights().length; j++) {
            Weight parentOne = selectParent(parents, firstParent).getSetWeight().getWeights()[j];
            Weight parentTwo = selectParent(parents, !firstParent).getSetWeight().getWeights()[j];

            IntStream.range(0, cutoff).forEach(i -> chromosome[i] = parentOne.getChromosome()[i]);
            IntStream.range(cutoff, precision).forEach(i -> chromosome[i] = parentTwo.getChromosome()[i]);

            weight[j] = new Weight(chromosome);
        }

        son.setWeights(weight);

        return new Individual(son);
    }

    private static Individual selectParent(Parents parents, boolean order) {
        return order ? parents.getParentOne() : parents.getParentTwo();
    }
}
