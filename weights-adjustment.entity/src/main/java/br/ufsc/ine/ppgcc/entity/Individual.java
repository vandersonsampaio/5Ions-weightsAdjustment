package br.ufsc.ine.ppgcc.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Individual {

    private final SetWeight setWeight;
    private double odd;

    public Individual(SetWeight setWeight) {
        this.setWeight = setWeight;
        setOdd(0);
    }

    public void setOdd(double odd){
        this.odd = odd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return setWeight.equals(that.setWeight);
    }
}
