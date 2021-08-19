package br.ufsc.ine.ppgcc.entity;

import br.ufsc.ine.ppgcc.entity.helper.ServiceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParentsTest {

    private ServiceHelper helper;

    @Before
    public void setUp(){
        helper = new ServiceHelper();
    }

    @Test
    public void parentsTest_Build(){
        double parentOneValue = 3.9D;
        double parentTwoValue = 103.9810D;
        Parents parents = helper.buildParents(parentOneValue, parentTwoValue);

        Assert.assertEquals(helper.buildIndividual(parentOneValue), parents.getParentOne());
        Assert.assertEquals(helper.buildIndividual(parentTwoValue), parents.getParentTwo());
    }
}
