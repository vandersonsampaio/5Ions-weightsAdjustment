package br.ufsc.ine.ppgcc.entity.helper;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Parents;
import br.ufsc.ine.ppgcc.entity.SetWeight;
import br.ufsc.ine.ppgcc.entity.Weight;

public class ServiceHelper {

    public Weight buildWeight(double value){
        return new Weight(value);
    }

    public Weight buildWeight(int[] chromosome){
        return new Weight(chromosome);
    }

    public SetWeight buildSetWeight(double value){
        SetWeight setWeight = new SetWeight(1);
        setWeight.setWeights(new Weight[]{buildWeight(value)});
        return setWeight;
    }

    public Individual buildIndividual(double value){
        return new Individual(buildSetWeight(value));
    }

    public Parents buildParents(double parentOneValue, double parentTwoValue) {
        return new Parents(buildIndividual(parentOneValue), buildIndividual(parentTwoValue));
    }
}
