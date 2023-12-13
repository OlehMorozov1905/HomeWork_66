package ait.numbers.model;



import ait.numbers.task.OneGroupSum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum{
   public ExecutorGroupSum(int [][] numberGroups){
       super(numberGroups);
   }
   @Override
   public int computeSum() {
      // TODO Homework: reduce sum numbers of numberGroups,
      //  use ExecutorService
      OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
      ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

      for (int i = 0; i < numberGroups.length; i++) {
         tasks[i] = new OneGroupSum(numberGroups[i]);
         executorService.execute(tasks[i]);
      }

      executorService.shutdown();

      try {
         executorService.awaitTermination(1, TimeUnit.MINUTES);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      int result = 0;
      for (OneGroupSum task : tasks) {
         result += task.getSum();
      }

      return result;
   }
}
