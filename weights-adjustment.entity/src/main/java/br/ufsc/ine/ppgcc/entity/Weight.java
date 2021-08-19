package br.ufsc.ine.ppgcc.entity;

import lombok.AccessLevel;
import lombok.Getter;
import org.junit.Assert;

import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
public class Weight {

    @Getter(value = AccessLevel.NONE)
    private final int precision = 9;
    private double value;
    private int[] chromosome;

    public Weight(double value){
        this.chromosome = new int[precision];
        setValue(value);
    }

    public Weight(int[] chromosome){
        Assert.assertEquals(precision, chromosome.length);
        Assert.assertTrue(chromosome[precision - 1] == 0 || chromosome[precision - 1] == 1);

        setChromosome(chromosome);
    }

    public void setChromosome(int[] chromosome){
        this.chromosome = chromosome;

        int[] elementsInt = Arrays.copyOfRange(chromosome, 0, 3);
        int[] elementsDecimal = Arrays.copyOfRange(chromosome, 3, precision - 1);
        String valueInt = Arrays.stream(elementsInt).mapToObj(Integer::toString).reduce("", String::concat);
        String valueDecimal = Arrays.stream(elementsDecimal).mapToObj(Integer::toString).reduce("", String::concat);

        this.value = Double.parseDouble(valueInt.concat(".").concat(valueDecimal)) * signal(chromosome[precision - 1]);
    }

    private int signal(int value) {
        return value == 0 ? -1 : 1;
    }

    public void setValue(double value){
        this.value = value;
        computeChromosome();
    }

    private void computeChromosome(){
        int partInt = (int) (Math.floor(Math.abs(value) * 100) / 100);
        Double partDecimal = (Math.abs(value) - partInt);

        String sPartInt = String.format("%03d", partInt);
        String sPartDecimal = String.format("%.5f", partDecimal);

        IntStream.range(0, 3).forEach(i -> chromosome[i] = Character.digit(sPartInt.charAt(i), 10));
        IntStream.range(3, 8).forEach(i -> chromosome[i] = Character.digit(sPartDecimal.charAt(i - 1), 10));
        chromosome[8] = value > 0 ? 1 : 0;
    }

    @Override
    public String toString(){
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return deltaCompare(weight.value, value);
    }

    private boolean deltaCompare(double numberOne, double numberTwo) {
        double delta = .00001D;
        return Math.abs(numberOne - numberTwo) <= delta;
    }
}