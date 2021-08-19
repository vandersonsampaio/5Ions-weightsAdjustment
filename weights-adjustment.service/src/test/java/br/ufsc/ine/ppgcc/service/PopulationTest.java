package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.service.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Engine.class)
public class PopulationTest {

    private final int populationLength = 4;
    private Population population;
    private ServiceHelper helper;

    @Before
    public void setUp(){
        population = new Population(populationLength, 1, .5);
        helper = new ServiceHelper();
    }

    @Test
    public void population_StartPopulation(){
        Individual individualOne = helper.buildIndividual(1);
        Individual individualTwo = helper.buildIndividual(2);
        Individual individualThree = helper.buildIndividual(3);
        Individual individualFour = helper.buildIndividual(4);

        PowerMockito.mockStatic(Engine.class);
        PowerMockito.when(Engine.randomIndividual(1)).thenReturn(individualOne)
                .thenReturn(individualTwo).thenReturn(individualThree).thenReturn(individualFour);
        population.startPopulation();

        Assert.assertEquals(populationLength, population.getPopulation().size());
        Assert.assertTrue(population.getPopulation().stream().anyMatch(i -> i.equals(individualOne)));
        Assert.assertTrue(population.getPopulation().stream().anyMatch(i -> i.equals(individualTwo)));
        Assert.assertTrue(population.getPopulation().stream().anyMatch(i -> i.equals(individualThree)));
        Assert.assertTrue(population.getPopulation().stream().anyMatch(i -> i.equals(individualFour)));
    }
}
