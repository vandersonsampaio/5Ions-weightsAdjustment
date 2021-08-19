package br.ufsc.ine.ppgcc.entity;

import br.ufsc.ine.ppgcc.entity.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IndividualTest {

    private static final double delta = .0001D;
    private final double individualValue = 3.4;
    private ServiceHelper helper;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void individualTest_Build() {
        Individual individual = helper.buildIndividual(individualValue);

        Assert.assertEquals(helper.buildSetWeight(individualValue), individual.getSetWeight());
        Assert.assertEquals(0, individual.getOdd(), delta);
    }

    @Test
    public void individualTest_SetOdd() {
        double odd = 8.3D;
        Individual individual = helper.buildIndividual(individualValue);
        individual.setOdd(odd);

        Assert.assertEquals(helper.buildSetWeight(individualValue), individual.getSetWeight());
        Assert.assertEquals(odd, individual.getOdd(), delta);
    }

    @Test
    public void individualTest_Compare() {
        double odd = 8.3D;
        Individual individual = helper.buildIndividual(individualValue);
        individual.setOdd(odd);

        Assert.assertNotEquals(individual, null);
        Assert.assertNotEquals(individual, odd);
        Assert.assertEquals(individual, individual);
        Assert.assertEquals(helper.buildIndividual(individualValue), individual);
    }
}
