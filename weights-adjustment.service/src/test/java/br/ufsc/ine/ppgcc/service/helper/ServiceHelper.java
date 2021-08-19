package br.ufsc.ine.ppgcc.service.helper;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Parents;
import br.ufsc.ine.ppgcc.entity.SetWeight;
import br.ufsc.ine.ppgcc.entity.Weight;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class ServiceHelper {

    public Weight buildWeight(double value){
        return new Weight(value);
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

    public List<Individual> buildListIndividuals(double[] values, double[] criteria){
        Assert.assertTrue(values.length >= 3);
        Assert.assertTrue(criteria.length >= 3);

        return Arrays.asList(buildIndividualWithCriteria(values[0], criteria[0]),
                buildIndividualWithCriteria(values[1], criteria[1]),
                buildIndividualWithCriteria(values[2], criteria[2]));
    }

    private Individual buildIndividualWithCriteria(double value, double criteria){
        Individual individual = buildIndividual(value);
        individual.getSetWeight().setCriteria(criteria);

        return individual;
    }
}
