package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Weight;
import br.ufsc.ine.ppgcc.service.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class EngineTest {

    private ServiceHelper helper;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void engineTest_Constructor(){
        Engine engine = new Engine();
        Assert.assertNotNull(engine);
    }

    @Test
    public void engineTest_RandomWeight(){
        int lengthElements = 3;
        Individual individual = Engine.randomIndividual(lengthElements);

        for(int i = 0; i < lengthElements; i++){
            double value = individual.getSetWeight().getWeights()[i].getValue();
            Weight expectedWeight = helper.buildWeight(value);

            Assert.assertEquals(expectedWeight, individual.getSetWeight().getWeights()[i]);
        }
    }

    @Test
    public void engineTest_BuildWeight(){
        int repeatLength = 100;
        for(int i = 0; i < repeatLength; i++) {
            Weight weight = Engine.buildWeight();

            double expectedValue = weight.getValue();
            Weight expectedWeight = helper.buildWeight(expectedValue);

            Assert.assertEquals(expectedValue, weight.getValue(), .0001D);
            Assert.assertEquals(expectedWeight, weight);
        }
    }

    @Test
    public void engineTest_IsDone() {
        double criteria = .8;
        List<Individual> individuals = helper.buildListIndividuals(
                new double[] {-0.12991, 9.199821, 82.99819},
                new double[] {.428, .712, .891});
        Population populationMock = Mockito.mock(Population.class);
        Mockito.when(populationMock.getPopulation()).thenReturn(individuals);

        boolean done = Engine.isDone(populationMock, criteria);

        Assert.assertTrue(done);
    }

    @Test
    public void engineTest_IsNotDone() {
        double criteria = .9;
        List<Individual> individuals = helper.buildListIndividuals(
                new double[] {-0.12991, 9.199821, 82.99819},
                new double[] {.428, .712, .891});
        Population populationMock = Mockito.mock(Population.class);
        Mockito.when(populationMock.getPopulation()).thenReturn(individuals);

        boolean done = Engine.isDone(populationMock, criteria);

        Assert.assertFalse(done);
    }
}
