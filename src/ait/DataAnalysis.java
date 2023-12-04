package ait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataAnalysis {
    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("train.csv"))) {
            String line = br.readLine();

            double totalFares = 0;
            int[] classCounts = new int[3];
            int totalSurvived = 0;
            int totalNotSurvived = 0;
            int totalMenSurvived = 0;
            int totalWomenSurvived = 0;
            int totalChildrenSurvived = 0;

            while ((line = br.readLine()) != null) {
                String[] cells = line.split(",");

                String fareString = cells[10].trim();
                if (!fareString.isEmpty() && fareString.matches("[0-9.]+")) {
                    totalFares += Double.parseDouble(fareString);
                }


                if (!cells[2].isEmpty()) {
                    int pclass = Integer.parseInt(cells[2]);
                    if (pclass >= 1 && pclass <= 3) {
                        classCounts[pclass - 1]++;
                    }
                }

                if (!cells[1].isEmpty()) {
                    int survived = Integer.parseInt(cells[1]);
                    if (survived == 1) {
                        totalSurvived++;
                    } else {
                        totalNotSurvived++;
                    }
                }

                String sex = cells[5].toLowerCase();
                if (!cells[6].isEmpty()) {
                    double age = Double.parseDouble(cells[6]);
                    if (age > 0) {
                        int survived = Integer.parseInt(cells[1]);
                        if (survived == 1) {
                            if (sex.equals("male")) {
                                totalMenSurvived++;
                            } else if (sex.equals("female")) {
                                totalWomenSurvived++;
                            }
                            if (age < 18) {
                                totalChildrenSurvived++;
                            }
                        }
                    }
                }
            }

            System.out.println("Task 1: Total Fares: " + totalFares);
            System.out.println("Task 2: Average Fare for Class 1: " + totalFares / classCounts[0]);
            System.out.println("        Average Fare for Class 2: " + totalFares / classCounts[1]);
            System.out.println("        Average Fare for Class 3: " + totalFares / classCounts[2]);
            System.out.println("Task 3: Total Survived Passengers: " + totalSurvived);
            System.out.println("        Total Non-Survived Passengers: " + totalNotSurvived);
            System.out.println("Task 4: Total Survived Men: " + totalMenSurvived);
            System.out.println("        Total Survived Women: " + totalWomenSurvived);
            System.out.println("        Total Survived Children: " + totalChildrenSurvived);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
