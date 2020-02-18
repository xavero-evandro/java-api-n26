package com.n26.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Statistics {

    private String sum;

    private String avg;

    private String max;

    private String min;

    private Long count;

    public Statistics() {
    }

    public Statistics(List<Transaction> transactions) {
        List<BigDecimal> amountsLastMinute = transactions.stream()
                .map(t -> new BigDecimal(t.getAmount()).setScale(2, RoundingMode.HALF_UP))
                .collect(toList());
        Long count = amountsLastMinute.stream().count();
        this.setCount(count);
        if (count > 0) {
            BigDecimal sum = amountsLastMinute.stream()
                    .filter(Objects::nonNull)
                    .map(Objects::requireNonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            this.setSum(sum.toString());
            this.setAvg(sum.divide(new BigDecimal(amountsLastMinute.size()),2,RoundingMode.HALF_UP).toString());
            this.setMax(amountsLastMinute.stream().max(BigDecimal::compareTo).get().toString());
            this.setMin(amountsLastMinute.stream().min(BigDecimal::compareTo).get().toString());
        }
        this.setSum(this.getSum() == null ? "0.00" : this.getSum());
        this.setAvg(this.getAvg() == null ? "0.00" : this.getAvg());
        this.setMax(this.getMax() == null ? "0.00" : this.getMax());
        this.setMin(this.getMin() == null ? "0.00" : this.getMin());

    }
}
