package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import org.junit.Assert;
import org.junit.Test;

public class ComputeWeightsTest {

    @Test
    public void computeWeights_ComputeIsNotDone() throws Exception {
        final double idealValue = 1000.0D;
        ComputeWeights computeWeights = new ComputeWeights(4, 1, 1){
            @Override
            protected void calculateCriteria(Population population) {
                for (Individual individual : population.getPopulation()) {
                    double value = Math.abs(individual.getSetWeight().getWeights()[0].getValue());
                    double criteria = value >= idealValue ? 1 : value / idealValue;
                    individual.getSetWeight().setCriteria(criteria);
                }
            }
        };

        double[] bestWeights = computeWeights.compute(200);

        Assert.assertTrue(bestWeights.length > 0);
    }

    @Test
    public void computeWeights_ComputeIsDone() throws Exception {
        final double idealValue = 960.0D;
        ComputeWeights computeWeights = new ComputeWeights(4, .98, 1){
            @Override
            protected void calculateCriteria(Population population) {
                for (Individual individual : population.getPopulation()) {
                    double value = Math.abs(individual.getSetWeight().getWeights()[0].getValue());
                    double criteria = value >= idealValue ? 1 : value / idealValue;
                    individual.getSetWeight().setCriteria(criteria);
                }
            }
        };

        double[] bestWeights = computeWeights.compute(200);

        Assert.assertTrue(bestWeights.length > 0);
        Assert.assertTrue(Math.abs(bestWeights[0]) >= idealValue);
    }
}
