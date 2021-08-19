package br.ufsc.ine.ppgcc.entity;

import br.ufsc.ine.ppgcc.entity.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SetWeightTest {

    private ServiceHelper helper;
    private final double value = 345.1109D;
    private final double criteria = .1192D;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void setWeightTest_SetWeights(){
        SetWeight setWeight = helper.buildSetWeight(value);

        Assert.assertEquals(1, setWeight.getWeights().length);
        Assert.assertEquals(helper.buildWeight(value), setWeight.getWeights()[0]);
    }

    @Test
    public void setWeightTest_SetCriteria(){
        SetWeight setWeight = helper.buildSetWeight(value);
        setWeight.setCriteria(criteria);

        Assert.assertEquals(criteria, setWeight.getCriteria(), .0001D);
    }

    @Test
    public void setWeightTest_ToString(){
        String stringExpected = value + " - " + criteria;
        SetWeight setWeight = helper.buildSetWeight(value);
        setWeight.setCriteria(criteria);

        Assert.assertEquals(stringExpected, setWeight.toString());
    }

    @Test
    public void setWeightTest_Compare(){
        double valueTwo = 221.0092D;
        SetWeight setWeightOne = helper.buildSetWeight(value);
        setWeightOne.setCriteria(criteria);

        SetWeight setWeightTwo = helper.buildSetWeight(valueTwo);

        Assert.assertNotEquals(setWeightOne, setWeightTwo);

        setWeightTwo.setCriteria(criteria);

        Assert.assertNotEquals(setWeightOne, setWeightTwo);

        setWeightTwo.setWeights(new Weight[]{ helper.buildWeight(value) });

        Assert.assertNotEquals(setWeightOne, null);
        Assert.assertNotEquals(setWeightOne, value);
        Assert.assertEquals(setWeightOne, setWeightOne);
        Assert.assertEquals(setWeightOne, setWeightTwo);
    }
}
