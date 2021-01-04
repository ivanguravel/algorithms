package org.ivzh.sort


import java.util.*;

public class AverageSalaryExcludingTheMinimumAndMaximumSalary {
  
  public double average(int[] salary) {
        
        Arrays.sort(salary);
        int sum = 0;
        for (int i = 1; i <= salary.length -2; i++) {
            sum = sum + salary[i];
        }
        return (double) sum / (salary.length - 2);
    }
}
