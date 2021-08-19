package br.ufsc.ine.ppgcc.entity;

import br.ufsc.ine.ppgcc.entity.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeightTest {

    private final double value = 22.7514D;
    private final int[] chromosome = { 1, 5, 2, 6, 6, 9, 0, 1, 0 };
    private ServiceHelper helper;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void weightTest_BuildByPositiveValue(){
        Weight weight = helper.buildWeight(value);
        int[] chromosomeExpected = { 0, 2, 2, 7, 5, 1, 4, 0, 1 };

        Assert.assertArrayEquals(chromosomeExpected, weight.getChromosome());
    }

    @Test
    public void weightTest_BuildByNegativeValue(){
        Weight weight = helper.buildWeight(value * -1);
        int[] chromosomeExpected = { 0, 2, 2, 7, 5, 1, 4, 0, 0 };

        Assert.assertArrayEquals(chromosomeExpected, weight.getChromosome());
    }

    @Test
    public void weightTest_BuildByNegativeChromosome(){
        Weight weight = helper.buildWeight(chromosome);
        double valueExpected = -152.66901D;

        Assert.assertEquals(valueExpected, weight.getValue(), .0001D);
    }

    @Test
    public void weightTest_BuildByPositiveChromosome(){
        int[] chromosome = { 1, 5, 2, 6, 6, 9, 0, 1, 1 };
        Weight weight = helper.buildWeight(chromosome);
        double valueExpected = 152.66901D;

        Assert.assertEquals(valueExpected, weight.getValue(), .0001D);
    }

    @Test
    public void weightTest_ToString(){
        Weight weight = helper.buildWeight(value);

        Assert.assertEquals(Double.toString(value), weight.toString());
    }

    @Test
    public void weightTest_CompareWeights(){
        Weight weightOne = helper.buildWeight(value);
        Weight weightTwo = helper.buildWeight(chromosome);

        Assert.assertNotEquals(weightOne, weightTwo);

        weightTwo.setValue(value);

        Assert.assertNotEquals(weightOne, null);
        Assert.assertNotEquals(weightOne, value);
        Assert.assertEquals(weightOne, weightOne);
        Assert.assertEquals(weightOne, weightTwo);
    }

    @Test(expected = AssertionError.class)
    public void weightTest_ChromosomeLengthLess9(){
        int[] chromosome = { 0, 0, 1, 3, 3 };
        helper.buildWeight(chromosome);
    }

    @Test(expected = AssertionError.class)
    public void weightTest_SignalChromosomeError(){
        int[] chromosome = { 0, 0, 1, 3, 3, 0, 0, 1, 2 };
        helper.buildWeight(chromosome);
    }
}
