package br.ufsc.ine.ppgcc.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class SetWeight {

    private Weight[] weights;
    private double criteria;

    public SetWeight(int numberWeight){
        this.weights = new Weight[numberWeight];
        this.criteria = 0;
    }

    public void setWeights(Weight[] weights) {
        this.weights = weights;
    }

    public void setCriteria(double criteria){
        this.criteria = criteria;
    }

    @Override
    public String toString(){
        String stringWeights = Arrays.stream(weights).map(Weight::toString).reduce(" ", String::concat);
        return stringWeights.trim().concat(" - ").concat(Double.toString(criteria));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetWeight setWeight = (SetWeight) o;
        return Double.compare(setWeight.criteria, criteria) == 0 && Arrays.equals(weights, setWeight.weights);
    }
}
