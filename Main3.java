import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This main class is for storing report methods
 */

public class Main3 {

    // reports

    /**
     * Generate and displays customer demographic report
     *
     * @param customerList An array of {@code Customer} objects
     */
    public static void custDemoReport(ArrayList<Customer> customerList) {
        // 0-North, 1-NE, 2-East, 3-SE, 4-south, 5-sw, 6-w, 7-nw, 8-others
        String[] regionName = {"North","North East","East","South East","South","South West","West","North West","Other States"};
        int[] regionCount = {0,0,0,0,0,0,0,0,0};
        int totalCustCount = 0;
        double[] regionTotalAmount = {0,0,0,0,0,0,0,0,0};
        LocalDate startDate;
        LocalDate endDate;

        //get min date
        startDate = reportInputStartDate("\n\n  Enter start date");
        //get max date
        endDate = reportInputEndDate("\n  Enter end date", startDate);

        for (Customer customer : customerList) {
            switch (customer.getAddress().getRegion()) {
                case "North" -> {
                    regionCount[0] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[0] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "North East" -> {
                    regionCount[1] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[1] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "East" -> {
                    regionCount[2] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[2] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South East" -> {
                    regionCount[3] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[3] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South" -> {
                    regionCount[4] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[4] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South West" -> {
                    regionCount[5] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[5] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "West" -> {
                    regionCount[6] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[6] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "North West" -> {
                    regionCount[7] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[7] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "Other states" -> {
                    regionCount[8] += checkPaymentPresent(customer, startDate, endDate);
                    regionTotalAmount[8] += calcRegionTotalAmount(customer, startDate, endDate);
                }
            }
        }

        for (int i = 0; i < 9; i++){
            totalCustCount += regionCount[i];
        }

        //sort descending order
        double temp1;
        int temp2;
        String temp3;
        for (int i = 0; i < 9; i++)
        {
            for (int j = i + 1; j < 9; j++)
            {
                if (regionTotalAmount[i] < regionTotalAmount[j])
                {
                    temp1 = regionTotalAmount[i];
                    regionTotalAmount[i] = regionTotalAmount[j];
                    regionTotalAmount[j] = temp1;

                    temp2 = regionCount[i];
                    regionCount[i] = regionCount[j];
                    regionCount[j] = temp2;

                    temp3 = regionName[i];
                    regionName[i] = regionName[j];
                    regionName[j] = temp3;
                }
            }
        }

        System.out.println("\n\n                        CUSTOMER DEMOGRAPHIC REPORT                      ");
        System.out.println("\n\n  From: " + startDate + "   To: " + endDate);
        System.out.print("\n  ------------------------------------------------------------------------");
        System.out.print("\n        Region         Customer        Weight(%%)        Total Amount(RM) ");
        System.out.print("\n  ------------------------------------------------------------------------\n");
        for(int i = 0; i < 9; i++){
            double weight =  100.0*(double)regionCount[i]/(double)totalCustCount;
            System.out.printf("    %-14s |   %4d      |      %5.2f%%       |    %10.2f\n", regionName[i], regionCount[i],Double.isNaN(weight) ? 0 : weight, regionTotalAmount[i]);
        }
        System.out.print("  ------------------------------------------------------------------------\n");
        Main.pressAnyKeyToContinue();
    }

    /**
     * To calculate region count for customer demographic report
     *
     * @param c {@code Customer} to obtain bill history
     * @param start Start date in {@code LocalDate}
     * @param end End date in {@code LocalDate}
     * @return 1 if customer have payment in date range, else 0
     */
    public static int checkPaymentPresent(Customer c, LocalDate start, LocalDate end){
        for(int i = 0; i < c.getBillHistory().size(); i++){
            if(c.getBillHistory().get(i).getPaymentDate().compareTo(start) >= 0 && c.getBillHistory().get(i).getPaymentDate().compareTo(end) <= 0)
                return 1;
        }
        return 0;
    }

    /**
     * To calculate the total amount of billing history of a specific customer within a specific range of dates
     *
     * @param c {@code Customer} to obtain bill history
     * @param start Start date in {@code LocalDate}
     * @param end End date in {@code LocalDate}
     * @return Total amount of customer billing history in date range
     */
    public static double calcRegionTotalAmount(Customer c, LocalDate start, LocalDate end){
        double regionTotalAmount = 0;
        for(int i = 0; i < c.getBillHistory().size(); i++){
            if(c.getBillHistory().get(i).getPaymentDate().compareTo(start) >= 0 && c.getBillHistory().get(i).getPaymentDate().compareTo(end) <= 0)
                regionTotalAmount+= c.getBillHistory().get(i).getTotalAmount();
        }
        return regionTotalAmount;
    }

    /**
     * Generate and displays source of promo code report
     *
     * @param customer An array of {@code Customer} objects
     */
    public static void sourcePromoReport(ArrayList<Customer> customer) {
        ArrayList<Billing> billHistory;
        LocalDate startdate = reportInputStartDate("\n\n  Enter start date");
        LocalDate enddate = reportInputEndDate("\n  Enter end date", startdate);

        String[] promoSrc = {"Youtube","Instagram","Facebook","Twitter","Friends"};
        int[] promoSrcCnt = {0,0,0,0,0};
        int[][] promoSrcAge = {{0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}
                ,{0,0,0,0,0,0,0,0,0,0}};
        String origin;

        for(Customer cust: customer) {
            billHistory = cust.getBillHistory();
            for (Billing billing : billHistory) {
                try {
                    origin = billing.getPromoOrigin();
                }
                catch (NullPointerException e) {
                    origin = "";
                }
                if (startdate.isBefore(billing.getPaymentDate()) && enddate.isAfter(billing.getPaymentDate())) {
                    for (int j = 0; j < promoSrc.length; j++) {
                        if (promoSrc[j].equals(origin)) {
                            promoSrcCnt[j]++;
                            promoSrcAge[j][(cust.age) / 10]++;
                        }
                    }
                }
            }
        }

        int j=0;
        String[] sortPromoSrc = new String[promoSrc.length];
        int[] sortSrcCnt= promoSrcCnt.clone();
        Arrays.sort(sortSrcCnt);
        for(int cnt:sortSrcCnt) {
            for(int i=0 ; i < promoSrcCnt.length ; i++) {
                if(promoSrcCnt[i]==cnt && promoSrcCnt[i]!=-1) {
                    sortPromoSrc[j]=promoSrc[i];
                    j++;
                    promoSrcCnt[i]=-1;
                }
            }
        }
        int cnt=sortSrcCnt.length-1;
        System.out.println("\n\n             FREQUENCY OF PROMOTION CODE SOURCE           ");
        System.out.println("\n\n  From: " + startdate + "   To: " + enddate);
        for(int c=sortPromoSrc.length-1 ; c >= 0 ; c--,cnt--) {
            System.out.printf("\n\n  %-10s                  Total : %2d           Weight(%%)\n", sortPromoSrc[c] ,sortSrcCnt[cnt]);
            System.out.println("  ----------------------------------------------------------");
            int num=-1;
            for(int i=0; i <promoSrc.length ;i++) {
                if(sortPromoSrc[c].equals(promoSrc[i])) {
                    num=i;
                }
            }
            if(sortSrcCnt[cnt]==0) {
                System.out.println("  No record.");
            }
            else {
                int count;
                System.out.print("  Age Range: ");
                for(count=0 ; count < 3 ; count++) {
                    int maxNum=0, max=0;
                    for(int i=0 ; i < promoSrcAge[num].length ; i++) {
                        if(promoSrcAge[num][i] > maxNum && promoSrcAge[num][i]!=-1) {
                            max = i;
                            maxNum=promoSrcAge[num][i];
                        }
                    }
                    if(maxNum!=0) {
                        System.out.printf(" %d0 - %d9    |            %2d",max, max, maxNum);
                        System.out.printf("       |      %.2f%%",( (double) maxNum/sortSrcCnt[cnt] * 100));
                        System.out.printf("\n  %-10s "," ");
                        promoSrcAge[num][max]=-1;
                    }
                }
            }
        }
        System.out.println("\n");
        Main.pressAnyKeyToContinue();
    }

    /**
     * Generates and displays pet demographic report
     *
     * @param customerList An array of {@code Customer} objects
     */
    public static void petDemoReport(ArrayList<Customer> customerList) {
        int totalBirdAge = 0;
        int totalCatAge = 0;
        int totalDogAge = 0;
        int totalRabbitAge = 0;

        int totalBirdCount = 0;
        int totalCatCount = 0;
        int totalDogCount = 0;
        int totalRabbitCount = 0;

        int[] birdAggr = new int[3];
        String[] birdAggrName = {"Low", "Medium", "High"};
        int[] catAggr = new int[3];
        String[] catAggrName = {"Low", "Medium", "High"};
        int[] dogAggr = new int[3];
        String[] dogAggrName = {"Low", "Medium", "High"};
        int[] rabbitAggr = new int[3];
        String[] rabbitAggrName = {"Low", "Medium", "High"};

        int[] birdSize = new int[5];
        String[] birdSizeName = {"Extra Small", "Small", "Medium", "Large", "Extra Large"};
        int[] catSize = new int[5];
        String[] catSizeName = {"Extra Small", "Small", "Medium", "Large", "Extra Large"};
        int[] dogSize = new int[5];
        String[] dogSizeName = {"Extra Small", "Small", "Medium", "Large", "Extra Large"};
        int[] rabbitSize = new int[5];
        String[] rabbitSizeName = {"Extra Small", "Small", "Medium", "Large", "Extra Large"};

        LocalDate startDate;
        LocalDate endDate;


        //get min date
        startDate = reportInputStartDate("\n\n  Enter start date");
        //get max date
        endDate = reportInputEndDate("\n  Enter end date", startDate);

        for (Customer customer : customerList) {
            @SuppressWarnings("unchecked") ArrayList<Billing> cbh = (ArrayList<Billing>) customer.getBillHistory().clone();
            for (Billing billing : cbh) {
                for (int j = 0; j < billing.getBillDetails().size(); j++) {
                    if ((billing.getPaymentDate().isAfter(startDate) || billing.getPaymentDate().equals(startDate)) && (billing.getPaymentDate().equals(endDate) || billing.getPaymentDate().isBefore(endDate))) {
                        if (billing.getBillDetails().get(j).getPet() instanceof Bird) {
                            totalBirdCount++;
                            totalBirdAge += billing.getBillDetails().get(j).getPet().getAge();
                            switch (billing.getBillDetails().get(j).getPet().getAggressive()) {
                                case LOW -> birdAggr[0]++;
                                case MEDIUM -> birdAggr[1]++;
                                case HIGH -> birdAggr[2]++;
                            }
                            switch (billing.getBillDetails().get(j).getPet().getSize()) {
                                case XSMALL -> birdSize[0]++;
                                case SMALL -> birdSize[1]++;
                                case MEDIUM -> birdSize[2]++;
                                case LARGE -> birdSize[3]++;
                                case XLARGE -> birdSize[4]++;
                            }
                        } else if (billing.getBillDetails().get(j).getPet() instanceof Cat) {
                            totalCatCount++;
                            totalCatAge += billing.getBillDetails().get(j).getPet().getAge();
                            switch (billing.getBillDetails().get(j).getPet().getAggressive()) {
                                case LOW -> catAggr[0]++;
                                case MEDIUM -> catAggr[1]++;
                                case HIGH -> catAggr[2]++;
                            }
                            switch (billing.getBillDetails().get(j).getPet().getSize()) {
                                case XSMALL -> catSize[0]++;
                                case SMALL -> catSize[1]++;
                                case MEDIUM -> catSize[2]++;
                                case LARGE -> catSize[3]++;
                                case XLARGE -> catSize[4]++;
                            }
                        } else if (billing.getBillDetails().get(j).getPet() instanceof Dog) {
                            totalDogCount++;
                            totalDogAge += billing.getBillDetails().get(j).getPet().getAge();
                            switch (billing.getBillDetails().get(j).getPet().getAggressive()) {
                                case LOW -> dogAggr[0]++;
                                case MEDIUM -> dogAggr[1]++;
                                case HIGH -> dogAggr[2]++;
                            }
                            switch (billing.getBillDetails().get(j).getPet().getSize()) {
                                case XSMALL -> dogSize[0]++;
                                case SMALL -> dogSize[1]++;
                                case MEDIUM -> dogSize[2]++;
                                case LARGE -> dogSize[3]++;
                                case XLARGE -> dogSize[4]++;
                            }
                        } else if (billing.getBillDetails().get(j).getPet() instanceof Rabbit) {
                            totalRabbitCount++;
                            totalRabbitAge += billing.getBillDetails().get(j).getPet().getAge();
                            switch (billing.getBillDetails().get(j).getPet().getAggressive()) {
                                case LOW -> rabbitAggr[0]++;
                                case MEDIUM -> rabbitAggr[1]++;
                                case HIGH -> rabbitAggr[2]++;
                            }
                            switch (billing.getBillDetails().get(j).getPet().getSize()) {
                                case XSMALL -> rabbitSize[0]++;
                                case SMALL -> rabbitSize[1]++;
                                case MEDIUM -> rabbitSize[2]++;
                                case LARGE -> rabbitSize[3]++;
                                case XLARGE -> rabbitSize[4]++;
                            }
                        } else {
                            System.out.println("  Error when sorting type of pet.");
                            break;
                        }
                    }

                }

            }
            cbh.clear();
        }

        double birdAverageAge = (double)totalBirdAge / (double)totalBirdCount;
        double catAverageAge = (double)totalCatAge / (double)totalCatCount;
        double dogAverageAge = (double)totalDogAge / (double)totalDogCount;
        double rabbitAverageAge = (double)totalRabbitAge / (double)totalRabbitCount;

        petDemoSortFigures(birdAggrName, birdAggr);
        petDemoSortFigures(catAggrName, catAggr);
        petDemoSortFigures(dogAggrName, dogAggr);
        petDemoSortFigures(rabbitAggrName, rabbitAggr);

        petDemoSortFigures(birdSizeName, birdSize);
        petDemoSortFigures(catSizeName, catSize);
        petDemoSortFigures(dogSizeName, dogSize);
        petDemoSortFigures(rabbitSizeName, rabbitSize);

        System.out.println("\n\n                                  PET DEMOGRAPHIC REPORT                  ");
        System.out.println("\n\n  From: " + startDate + "   To: " + endDate);
        System.out.println("\n  --------------------------------------------------------------------------------------------");
        System.out.println("    Pet            Average Age             Aggressiveness                  Size             ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        System.out.println("             |                     |                           |                           ");
        System.out.printf("   %-8s  |", "Bird");
        if(Double.isNaN(birdAverageAge)){
            System.out.printf("   %10.2f        |", 0.0);
        }
        else{
            System.out.printf("   %10.2f        |", birdAverageAge);
        }
        for(int i = 0; i < 3; i++){
            if(i > 0){ System.out.print("             |                     |");  }
            double birdA1 = 100.00 * (double)birdAggr[i] / (double)(birdAggr[0] + birdAggr[1] + birdAggr[2]);
            System.out.printf("     %-8s > %6.2f%%", birdAggrName[i], Double.isNaN(birdA1) ? 0 : birdA1);
            double birdS1 = 100.00 * (double)birdSize[i] / (double)(birdSize[0] + birdSize[1] + birdSize[2] + birdSize[3] + birdSize[4]);
            System.out.printf("    |    %-12s > %6.2f%%\n", birdSizeName[i], Double.isNaN(birdS1) ? 0 : birdS1);
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            double birdS2 = 100.00 * (double)birdSize[i] / (double)(birdSize[0] + birdSize[1] + birdSize[2] + birdSize[3] + birdSize[4]);
            System.out.printf("                           |    %-12s > %6.2f%%\n", birdSizeName[i], Double.isNaN(birdS2) ? 0 : birdS2);
        }

        System.out.println("             |                     |                           |                           ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        System.out.println("             |                     |                           |                           ");
        System.out.printf("   %-8s  |", "Cat");
        if(Double.isNaN(catAverageAge)){
            System.out.printf("   %10.2f        |", 0.0);
        }
        else{
            System.out.printf("   %10.2f        |", catAverageAge);
        }
        for(int i = 0; i < 3; i++){
            if(i > 0){ System.out.print("             |                     |");  }
            double catA1 = 100.00 * (double)catAggr[i] / (double)(catAggr[0] + catAggr[1] + catAggr[2]);
            System.out.printf("     %-8s > %6.2f%%", catAggrName[i], Double.isNaN(catA1) ? 0 : catA1);
            double catS1 = 100.00 * (double)catSize[i] / (double)(catSize[0] + catSize[1] + catSize[2] + catSize[3] + catSize[4]);
            System.out.printf("    |    %-12s > %6.2f%%\n", catSizeName[i], Double.isNaN(catS1) ? 0 : catS1);
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            double catS2 =  100.00 * (double)catSize[i] / (double)(catSize[0] + catSize[1] + catSize[2] + catSize[3] + catSize[4]);
            System.out.printf("                           |    %-12s > %6.2f%%\n",catSizeName[i], Double.isNaN(catS2) ? 0 :catS2);
        }

        System.out.println("             |                     |                           |                           ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        System.out.println("             |                     |                           |                           ");
        System.out.printf("   %-8s  |", "Dog");
        if(Double.isNaN(dogAverageAge)){
            System.out.printf("   %10.2f        |", 0.0);
        }
        else{
            System.out.printf("   %10.2f        |", dogAverageAge);
        }
        for(int i = 0; i < 3; i++){
            if(i > 0){ System.out.print("             |                     |");  }
            double dogA1 = 100.00 * (double)dogAggr[i] / (double)(dogAggr[0] + dogAggr[1] + dogAggr[2]);
            System.out.printf("     %-8s > %6.2f%%", dogAggrName[i], Double.isNaN(dogA1) ? 0 : dogA1);
            double dogS1 = 100.00 * (double)dogSize[i] / (double)(dogSize[0] + dogSize[1] + dogSize[2] + dogSize[3] + dogSize[4]);
            System.out.printf("    |    %-12s > %6.2f%%\n", dogSizeName[i], Double.isNaN(dogS1) ? 0 : dogS1);
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            double dogS2 = 100.00 * (double)dogSize[i] / (double)(dogSize[0] + dogSize[1] + dogSize[2] + dogSize[3] + dogSize[4]);
            System.out.printf("                           |    %-12s > %6.2f%%\n", dogSizeName[i], Double.isNaN(dogS2) ? 0 : dogS2);
        }

        System.out.println("             |                     |                           |                           ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        System.out.println("             |                     |                           |                           ");
        System.out.printf("   %-8s  |", "Rabbit");
        if(Double.isNaN(rabbitAverageAge)){
            System.out.printf("   %10.2f        |", 0.00);
        }
        else{
            System.out.printf("   %10.2f        |", rabbitAverageAge);
        }
        for(int i = 0; i < 3; i++){
            if(i > 0){ System.out.print("             |                     |");  }
            double rabbitS1 = 100.00 * (double)rabbitAggr[i] / (double)(rabbitAggr[0] + rabbitAggr[1] + rabbitAggr[2]);
            System.out.printf("     %-8s > %6.2f%%", rabbitAggrName[i], Double.isNaN(rabbitS1) ? 0 :rabbitS1);
            double rabbitA1 = 100.00 * (double)rabbitSize[i] / (double)(rabbitSize[0] + rabbitSize[1] + rabbitSize[2] + rabbitSize[3] + rabbitSize[4]);
            System.out.printf("    |    %-12s > %6.2f%%\n", rabbitSizeName[i], Double.isNaN(rabbitA1) ? 0 : rabbitA1);
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            double rabbitS2 = 100.00 * (double)rabbitSize[i] / (double)(rabbitSize[0] + rabbitSize[1] + rabbitSize[2] + rabbitSize[3] + rabbitSize[4]);
            System.out.printf("                           |    %-12s > %6.2f%%\n", rabbitSizeName[i], Double.isNaN(rabbitS2) ? 0 : rabbitS2);
        }

        System.out.println("             |                     |                           |                           ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        Main.pressAnyKeyToContinue();
    }

    /**
     * Sort figures for pet demographic report
     *
     * @param naming Sorted based on the arrangement of {@code figures}
     * @param figures The values to be sorted
     */
    public static void petDemoSortFigures(String[] naming, int[] figures){
        int temp1;
        String temp2;
        for (int i = 0; i < figures.length; i++)
        {
            for (int j = i + 1; j < figures.length; j++)
            {
                if (figures[i] < figures[j])
                {
                    temp1 = figures[i];
                    figures[i] = figures[j];
                    figures[j] = temp1;

                    temp2 = naming[i];
                    naming[i] = naming[j];
                    naming[j] = temp2;
                }
            }
        }
    }

    /**
     * To prompt user for start date input and validate it
     *
     * @param text Text to prompt user for date input
     * @return Start date in {@code LocalDate}
     */
    public static LocalDate reportInputStartDate(String text){
        LocalDate startDate;
        do {
            try {
                System.out.println(text);
                startDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));

                if (startDate.isBefore(LocalDate.of(2018,1,1))) {
                    System.out.println("  Date entered must be after year 2018...");
                    continue;
                }
                else if (startDate.isAfter(LocalDate.now())) {
                    System.out.println("  Date entered must be before today's date");
                }
                return startDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        } while (true);
    }

    /**
     * To prompt user for end date input and validate it
     *
     * @param text Text to prompt user for date input
     * @param startDate Start date to validate the end date
     * @return End date in {@code LocalDate}
     */
    public static LocalDate reportInputEndDate(String text, LocalDate startDate) {
        LocalDate endDate;
        do {
            try {
                System.out.println(text);
                endDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));
                if (endDate.isBefore(startDate)) {
                    System.out.println("  End date should not be earlier than start date...");
                    continue;
                }
                if (endDate.isBefore(LocalDate.now().minusYears(4))) {
                    System.out.println("  Date entered must be after year 2018...");
                    continue;
                }
                if(endDate.isAfter(LocalDate.now())){
                    System.out.println("  End date should not after today's date...");
                    continue;
                }
                return endDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }

        }  while (true);
    }

    /**
     * Generate and displays top 10 most spending customer report
     *
     * @param customerList An array of {@code Customer} objects
     */
    public static void mostSpendingCustomer(ArrayList<Customer> customerList) {
        int topHowMany = 10;

        // check if customer record is empty
        if (!Main.checkCustomerRecord(customerList)) {
            System.out.println("  No customer records found...");
            Main.pressAnyKeyToContinue();
            return;
        }

        ArrayList<Customer> custFiltered = new ArrayList<>();


        LocalDate startDate = reportInputStartDate("\n\n  Enter start date");
        LocalDate endDate = reportInputEndDate("\n  Enter end date", startDate);

        for (Customer customer : customerList){
            if(!customer.getBillHistory().isEmpty()){
                filterCustomerInDateRange(customer, custFiltered, startDate, endDate);
            }
        }

        double[] custTotalAmountSpent = new double[custFiltered.size()];

        for (int i = 0; i < custFiltered.size(); i ++){
            for(Billing billing : custFiltered.get(i).getBillHistory()){
                if(billing.getPaymentDate().compareTo(startDate) >= 0 && billing.getPaymentDate().compareTo(endDate) <= 0){
                    custTotalAmountSpent[i] += billing.getGrandTotal();
                }
            }
        }

        custSpendingSort(custFiltered, custTotalAmountSpent);
        ArrayList<String> petType = new ArrayList<>();

        System.out.println("\n\n                                        10 MOST SPENDING CUSTOMER       ");
        System.out.println("\n\n  From: " + startDate + "   To: " + endDate);
        System.out.println("\n  ----------------------------------------------------------------------------------------------------------");
        System.out.println("    Cust.ID         Customer Name                Email            Spent Amt (RM)             Pets           ");
        System.out.println("  ----------------------------------------------------------------------------------------------------------");

        if (custFiltered.size() < 10) {
            topHowMany = custFiltered.size()-1;
        }

        for (int i = 0; i < topHowMany; i ++){
            System.out.printf("     %-6s  |  %-21s  | %-20s  |   RM%8.2f     |", custFiltered.get(i).getID(), custFiltered.get(i).fullName(), custFiltered.get(i).getEmail(), custTotalAmountSpent[i]);
            for (int j = 0; j < custFiltered.get(i).getPets().size(); j ++){
                if(!petType.contains(custFiltered.get(i).getPets().get(j).getClass().getSimpleName())){
                    petType.add(custFiltered.get(i).getPets().get(j).getClass().getSimpleName());
                    if(j > 0){   System.out.print(","); }
                    System.out.printf(" %s", custFiltered.get(i).getPets().get(j).getClass().getSimpleName());
                }

            }
            petType.clear();
            System.out.println();
        }
        if (custFiltered.size() < 10) {
            System.out.println("\n  Not enough data for 10 top spending customer (displaying " + (topHowMany == -1 ? 0 : topHowMany) + ")");
        }
        System.out.println("  ----------------------------------------------------------------------------------------------------------");
        System.out.println();
        Main.pressAnyKeyToContinue();

    }

    /**
     * Add the customers who have billing history in a specified date range
     *
     * @param customer a {@code Customer} object
     * @param custFiltered an {@code ArrayList} to store selected customers
     * @param startDate Start date to filter
     * @param endDate End data to filter
     */
    public static void filterCustomerInDateRange(Customer customer, ArrayList<Customer> custFiltered, LocalDate startDate, LocalDate endDate){
        for(Billing billing : customer.getBillHistory()){
            if(billing.getPaymentDate().compareTo(startDate) >= 0 && billing.getPaymentDate().compareTo(endDate) <= 0){
                custFiltered.add(customer);
                return;
            }
        }
    }

    /**
     * Sort figures for top 10 customer spending report
     *
     * @param custFiltered An {@code ArrayList} of selected customer to be sorted accordingly with {@code figures}
     * @param figures The values to be sorted with bubble sort
     */
    // most spending customer sorting
    public static void custSpendingSort(ArrayList<Customer> custFiltered, double[] figures){
        double temp1;
        ArrayList<Customer> temp2 = new ArrayList<>();
        for (int i = 0; i < figures.length; i++)
        {
            for (int j = i + 1; j < figures.length; j++)
            {
                if (figures[i] < figures[j])
                {
                    temp1 = figures[i];
                    figures[i] = figures[j];
                    figures[j] = temp1;

                    temp2.add(custFiltered.get(i));
                    custFiltered.set(i, custFiltered.get(j));
                    custFiltered.set(j, temp2.get(0));
                    temp2.clear();
                }
            }
        }
    }

    /**
     * Generate and displays payment method report
     *
     * @param customerList An array of {@code Customer} objects
     */
    public static void paymentMethodReport(ArrayList<Customer> customerList){

        // check if customer record is empty
        if (!Main.checkCustomerRecord(customerList)) {
            System.out.println("  No customer records found...");
            Main.pressAnyKeyToContinue();
            return;
        }

        int [] paymentCount = {0,0};
        int [] ageCash = {0,0,0,0,0};
        int [] ageCard = {0,0,0,0,0};
        int totalCust;
        double cashAmount = 0;
        double cardAmount = 0;

        LocalDate startDate = reportInputStartDate("\n\n  Enter start date");
        LocalDate endDate = reportInputEndDate("\n  Enter end date", startDate);

        for (Customer customer : customerList){
            for(Billing billing : customer.getBillHistory()){
                if(billing.getPaymentDate().compareTo(startDate) >= 0 && billing.getPaymentDate().compareTo(endDate) <= 0){
                    if(billing.getPaymentMethod().equals("Cash")){
                        if (customer.getAge() > 18 && customer.getAge() < 30){
                            ageCash[0]++;
                        }
                        else if (customer.getAge() > 31 && customer.getAge() < 50){
                            ageCash[1]++;
                        }
                        else if (customer.getAge() > 51 && customer.getAge() < 70){
                            ageCash[2]++;
                        }
                        else if (customer.getAge() > 71 && customer.getAge() < 90){
                            ageCash[3]++;
                        }
                        else{
                            ageCash[4]++;
                        }
                        paymentCount[0]++;
                        cashAmount += billing.getGrandTotal();
                    }
                    else if(billing.getPaymentMethod().equals("Card")){
                        if (customer.getAge() > 18 && customer.getAge() < 30){
                            ageCard[0]++;
                        }
                        else if (customer.getAge() > 31 && customer.getAge() < 50){
                            ageCard[1]++;
                        }
                        else if (customer.getAge() > 51 && customer.getAge() < 70){
                            ageCard[2]++;
                        }
                        else if (customer.getAge() > 71 && customer.getAge() < 90){
                            ageCard[3]++;
                        }else{
                            ageCard[4]++;
                        }
                        paymentCount[1]++;
                        cardAmount += billing.getGrandTotal();
                    }
                }

            }
        }
        //Calculation
        totalCust = paymentCount[0] + paymentCount[1];
        double percentageCash = (double)paymentCount[0]/ totalCust * 100;
        double percentageCard = (double)paymentCount[1]/ totalCust * 100;
        double percentageCashAge0 = paymentCount[0] == 0 ? 0 : (double)ageCash[0]/paymentCount[0] * 100;
        double percentageCashAge1 = paymentCount[0] == 0 ? 0 : (double)ageCash[1]/paymentCount[0] * 100;
        double percentageCashAge2 = paymentCount[0] == 0 ? 0 : (double)ageCash[2]/paymentCount[0] * 100;
        double percentageCashAge3 = paymentCount[0] == 0 ? 0 : (double)ageCash[3]/paymentCount[0] * 100;
        double percentageCashAge4 = paymentCount[0] == 0 ? 0 : (double)ageCash[4]/paymentCount[0] * 100;

        double percentageCardAge0 = paymentCount[1] == 0 ? 0 : (double)ageCard[0]/paymentCount[1] * 100;
        double percentageCardAge1 = paymentCount[1] == 0 ? 0 : (double)ageCard[1]/paymentCount[1] * 100;
        double percentageCardAge2 = paymentCount[1] == 0 ? 0 : (double)ageCard[2]/paymentCount[1] * 100;
        double percentageCardAge3 = paymentCount[1] == 0 ? 0 : (double)ageCard[3]/paymentCount[1] * 100;
        double percentageCardAge4 = paymentCount[1] == 0 ? 0 : (double)ageCard[4]/paymentCount[1] * 100;

        // check if there is output
        if (totalCust == 0) {
            System.out.println("  No records found...");
            Main.pressAnyKeyToContinue();
            return;
        }

        //Output

        System.out.println("\n\n                           PAYMENT METHOD REPORT                      ");
        System.out.println("\n\n  From: " + startDate + "   To: " + endDate);
        System.out.printf("\n\n  Cash                      Total : RM%10.2f      Weight(%%) : %6.2f%%", cashAmount, percentageCash);
        System.out.print("\n  -----------------------------------------------------------------------");
        System.out.printf("\n  Age Range : 18 - 30   |  Amount : %3d           |               %6.2f%%",ageCash[0], percentageCashAge0);
        System.out.printf("\n              31 - 50   |           %3d           |               %6.2f%%",ageCash[1], percentageCashAge1);
        System.out.printf("\n              51 - 70   |           %3d           |               %6.2f%%",ageCash[2], percentageCashAge2);
        System.out.printf("\n              71 - 90   |           %3d           |               %6.2f%%",ageCash[3], percentageCashAge3);
        System.out.printf("\n              Others    |           %3d           |               %6.2f%%\n\n",ageCash[4], percentageCashAge4);
        System.out.printf("\n  Card                      Total : RM%10.2f      Weight(%%) : %6.2f%%", cardAmount, percentageCard);
        System.out.print("\n  -----------------------------------------------------------------------");
        System.out.printf("\n  Age Range : 18 - 30   |  Amount : %3d           |               %6.2f%%",ageCard[0], percentageCardAge0);
        System.out.printf("\n              31 - 50   |           %3d           |               %6.2f%%",ageCard[1], percentageCardAge1);
        System.out.printf("\n              51 - 70   |           %3d           |               %6.2f%%",ageCard[2], percentageCardAge2);
        System.out.printf("\n              71 - 90   |           %3d           |               %6.2f%%",ageCard[3], percentageCardAge3);
        System.out.printf("\n              Others    |           %3d           |               %6.2f%%\n\n",ageCard[4], percentageCardAge4);


        Main.pressAnyKeyToContinue();
    }
}

