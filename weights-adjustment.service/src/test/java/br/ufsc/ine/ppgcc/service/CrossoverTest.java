package br.ufsc.ine.ppgcc.service;

import br.ufsc.ine.ppgcc.entity.Individual;
import br.ufsc.ine.ppgcc.entity.Parents;
import br.ufsc.ine.ppgcc.service.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrossoverTest {

    private ServiceHelper helper;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void CrossoverTest_Crossover(){
        Crossover crossover = new Crossover();
        int lengthRepeat = 100;
        double valueOne = 149.11593D;
        double valueTwo = -328.19281D;
        Parents parents = helper.buildParents(valueOne, valueTwo);

        for(int i = 0; i < lengthRepeat; i++) {
            Individual son = crossover.crossover(parents);

            Assert.assertNotEquals(valueOne, son.getSetWeight().getWeights()[0].getValue());
            Assert.assertNotEquals(valueTwo, son.getSetWeight().getWeights()[0].getValue());
        }
    }
}
