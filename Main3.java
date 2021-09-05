import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is for storing report methods
 */

public class Main3 {
    // reports
    // customer demographic report
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
                    regionCount[0] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[0] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "North East" -> {
                    regionCount[1] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[1] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "East" -> {
                    regionCount[2] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[2] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South East" -> {
                    regionCount[3] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[3] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South" -> {
                    regionCount[4] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[4] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "South West" -> {
                    regionCount[5] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[5] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "West" -> {
                    regionCount[6] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[6] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "North West" -> {
                    regionCount[7] += calcRegionCount(customer, startDate, endDate);
                    regionTotalAmount[7] += calcRegionTotalAmount(customer, startDate, endDate);
                }
                case "Other states" -> {
                    regionCount[8] += calcRegionCount(customer, startDate, endDate);
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
        System.out.printf("\n  ------------------------------------------------------------------------");
        System.out.printf("\n        Region         Customer        Weight(%%)        Total Amount(RM) ");
        System.out.printf("\n  ------------------------------------------------------------------------\n");
        for(int i = 0; i < 9; i++){

            System.out.printf("    %-14s |   %4d      |      %5.2f%%       |    %10.2f\n", regionName[i], regionCount[i], 100.0*(double)regionCount[i]/(double)totalCustCount, regionTotalAmount[i]);
        }
        System.out.printf("  ------------------------------------------------------------------------\n");
        Main.pressAnyKeyToContinue();
    }

    public static int calcRegionCount(Customer c, LocalDate start, LocalDate end){
        for(int i = 0; i < c.getBillHistory().size(); i++){
            if(c.getBillHistory().get(i).getPaymentDate().compareTo(start) >= 0 && c.getBillHistory().get(i).getPaymentDate().compareTo(end) <= 0)
                return 1;
        }
        return 0;
    }

    public static double calcRegionTotalAmount(Customer c, LocalDate start, LocalDate end){
        double regionTotalAmount = 0;
        for(int i = 0; i < c.getBillHistory().size(); i++){
            if(c.getBillHistory().get(i).getPaymentDate().compareTo(start) >= 0 && c.getBillHistory().get(i).getPaymentDate().compareTo(end) <= 0)
                regionTotalAmount+= c.getBillHistory().get(i).getTotalAmount();
        }
        return regionTotalAmount;
    }


    public static void sourcePromoReport(ArrayList<Customer> customer) {
        ArrayList<Billing> billHistory= new ArrayList<Billing>();
        //boolean datechk=true;
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
                int count =0;
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
                        System.out.print(" " + max + "0 - "+ max +"9    |             " + maxNum);
                        System.out.printf("      |      %.2f%%",( (double) maxNum/sortSrcCnt[cnt] * 100));
                        System.out.printf("\n  %-10s "," ");
                        promoSrcAge[num][max]=-1;
                    }
                }
            }
        }
        System.out.println("\n");
        Main.pressAnyKeyToContinue();
    }

    // pet demographic report
    public static void petDemoReport(ArrayList<Customer> customerList) {
        double birdAverageAge = (double)Bird.getTotalBirdAge() / (double)Bird.getTotalBirdCount();
        double catAverageAge = (double)Cat.getTotalCatAge() / (double)Cat.getTotalCatCount();
        double dogAverageAge = (double)Dog.getTotalDogAge() / (double)Dog.getTotalDogCount();
        double rabbitAverageAge = (double)Rabbit.getTotalRabbitAge() / (double)Rabbit.getTotalRabbitCount();
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
            ArrayList<Billing> cbh = (ArrayList<Billing>) customer.getBillHistory().clone();
            for(int i = 0; i < cbh.size(); i++){
                for(int j = 0; j < cbh.get(i).getBillDetails().size(); j++){
                    if((cbh.get(i).getPaymentDate().isAfter(startDate) || cbh.get(i).getPaymentDate().equals(startDate)) && (cbh.get(i).getPaymentDate().equals(endDate) || cbh.get(i).getPaymentDate().isBefore(endDate))){
                        if(cbh.get(i).getBillDetails().get(j).getPet() instanceof Bird ){
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getAggressive()){
                                case LOW -> birdAggr[0]++;
                                case MEDIUM -> birdAggr[1]++;
                                case HIGH -> birdAggr[2]++;
                            }
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getSize()){
                                case XSMALL-> birdSize[0]++;
                                case SMALL -> birdSize[1]++;
                                case MEDIUM-> birdSize[2]++;
                                case LARGE -> birdSize[3]++;
                                case XLARGE -> birdSize[4]++;
                            }
                        }
                        else if(cbh.get(i).getBillDetails().get(j).getPet() instanceof Cat ){
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getAggressive()){
                                case LOW -> catAggr[0]++;
                                case MEDIUM -> catAggr[1]++;
                                case HIGH -> catAggr[2]++;
                            }
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getSize()){
                                case XSMALL-> catSize[0]++;
                                case SMALL -> catSize[1]++;
                                case MEDIUM-> catSize[2]++;
                                case LARGE -> catSize[3]++;
                                case XLARGE -> catSize[4]++;
                            }
                        }
                        else if(cbh.get(i).getBillDetails().get(j).getPet() instanceof Dog ){
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getAggressive()){
                                case LOW -> dogAggr[0]++;
                                case MEDIUM -> dogAggr[1]++;
                                case HIGH -> dogAggr[2]++;
                            }
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getSize()){
                                case XSMALL-> dogSize[0]++;
                                case SMALL -> dogSize[1]++;
                                case MEDIUM-> dogSize[2]++;
                                case LARGE -> dogSize[3]++;
                                case XLARGE -> dogSize[4]++;
                            }
                        }
                        else if(cbh.get(i).getBillDetails().get(j).getPet() instanceof Rabbit ){
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getAggressive()){
                                case LOW -> rabbitAggr[0]++;
                                case MEDIUM -> rabbitAggr[1]++;
                                case HIGH -> rabbitAggr[2]++;
                            }
                            switch (cbh.get(i).getBillDetails().get(j).getPet().getSize()){
                                case XSMALL-> rabbitSize[0]++;
                                case SMALL -> rabbitSize[1]++;
                                case MEDIUM-> rabbitSize[2]++;
                                case LARGE -> rabbitSize[3]++;
                                case XLARGE -> rabbitSize[4]++;
                            }
                        }
                        else{
                            System.out.println("  Error when sorting type of pet.");
                            break;
                        }
                    }

                }

            }
            cbh.clear();
        }

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
            System.out.printf("      %-8s > 6.2f%%", birdAggrName[i], 100.00 * (double)birdAggr[i] / (double)(birdAggr[0] + birdAggr[1] + birdAggr[2]));
            System.out.printf("     |    %-12s > %6.2f%%\n", birdSizeName[i], 100.00 * (double)birdSize[i] / (double)(birdSize[0] + birdSize[1] + birdSize[2] + birdSize[3] + birdSize[4]));
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            System.out.printf("                           |    %-12s > %6.2f%%\n", birdSizeName[i], 100.00 * (double)birdSize[i] / (double)(birdSize[0] + birdSize[1] + birdSize[2] + birdSize[3] + birdSize[4]));
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
            System.out.printf("      %-8s > 6.2f%%", catAggrName[i], 100.00 * (double)catAggr[i] / (double)(catAggr[0] + catAggr[1] + catAggr[2]));
            System.out.printf("     |    %-12s > %6.2f%%\n", catSizeName[i], 100.00 * (double)catSize[i] / (double)(catSize[0] + catSize[1] + catSize[2] + catSize[3] + catSize[4]));
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            System.out.printf("                           |    %-12s > %6.2f%%\n", catSizeName[i], 100.00 * (double)catSize[i] / (double)(catSize[0] + catSize[1] + catSize[2] + catSize[3] + catSize[4]));
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
            System.out.printf("      %-8s > 6.2f%%", dogAggrName[i], 100.00 * (double)dogAggr[i] / (double)(dogAggr[0] + dogAggr[1] + dogAggr[2]));
            System.out.printf("     |    %-12s > %6.2f%%\n", dogSizeName[i], 100.00 * (double)dogSize[i] / (double)(dogSize[0] + dogSize[1] + dogSize[2] + dogSize[3] + dogSize[4]));
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            System.out.printf("                           |    %-12s > %6.2f%%\n", dogSizeName[i], 100.00 * (double)dogSize[i] / (double)(dogSize[0] + dogSize[1] + dogSize[2] + dogSize[3] + dogSize[4]));
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
            System.out.printf("      %-8s > 6.2f%%", rabbitAggrName[i], 100.00 * (double)dogAggr[i] / (double)(dogAggr[0] + dogAggr[1] + dogAggr[2]));
            System.out.printf("     |    %-12s > %6.2f%%\n", rabbitSizeName[i], 100.00 * (double)rabbitSize[i] / (double)(rabbitSize[0] + rabbitSize[1] + rabbitSize[2] + rabbitSize[3] + rabbitSize[4]));
        }
        for(int i = 3; i < 5; i++){
            System.out.print("             |                     |");
            System.out.printf("                           |    %-12s > %6.2f%%\n", rabbitSizeName[i], 100.00 * (double)rabbitSize[i] / (double)(rabbitSize[0] + rabbitSize[1] + rabbitSize[2] + rabbitSize[3] + rabbitSize[4]));
        }

        System.out.println("             |                     |                           |                           ");
        System.out.println("  --------------------------------------------------------------------------------------------");
        Main.pressAnyKeyToContinue();
    }

    // pet demographic sorting
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

    public static LocalDate reportInputStartDate(String text){
        LocalDate startDate;
        do {
            try {
                System.out.println(text);
                startDate = LocalDate.of(Main.promptInt("  Year  > "), Main.promptInt("  Month > "), Main.promptInt("  Day   > "));

                if (startDate.isBefore(LocalDate.now().minusYears(4))) {
                    System.out.println("  Date entered must be after year 2018...");
                    continue;
                }
                return startDate;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        } while (true);
    }

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


    // report
    public static void mostSpendingCustomer(ArrayList<Customer> customerList) {

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

        for (int i = 0; i < 10; i ++){
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
        System.out.println("  ----------------------------------------------------------------------------------------------------------");
        System.out.println();
        Main.pressAnyKeyToContinue();

    }

    public static void filterCustomerInDateRange(Customer customer, ArrayList<Customer> custFiltered, LocalDate startDate, LocalDate endDate){
        for(Billing billing : customer.getBillHistory()){
            if(billing.getPaymentDate().compareTo(startDate) >= 0 && billing.getPaymentDate().compareTo(endDate) <= 0){
                custFiltered.add(customer);
                return;
            }
        }
    }

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

    //payment method report
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
        System.out.printf("\n  -----------------------------------------------------------------------");
        System.out.printf("\n  Age Range : 18 - 30   |  Amount : %3d           |               %6.2f%%",ageCash[0], percentageCashAge0);
        System.out.printf("\n              31 - 50   |           %3d           |               %6.2f%%",ageCash[1], percentageCashAge1);
        System.out.printf("\n              51 - 70   |           %3d           |               %6.2f%%",ageCash[2], percentageCashAge2);
        System.out.printf("\n              71 - 90   |           %3d           |               %6.2f%%",ageCash[3], percentageCashAge3);
        System.out.printf("\n              Others    |           %3d           |               %6.2f%%\n\n",ageCash[4], percentageCashAge4);
        System.out.printf("\n  Card                      Total : RM%10.2f      Weight(%%) : %6.2f%%", cardAmount, percentageCard);
        System.out.printf("\n  -----------------------------------------------------------------------");
        System.out.printf("\n  Age Range : 18 - 30   |  Amount : %3d           |               %6.2f%%",ageCard[0], percentageCardAge0);
        System.out.printf("\n              31 - 50   |           %3d           |               %6.2f%%",ageCard[1], percentageCardAge1);
        System.out.printf("\n              51 - 70   |           %3d           |               %6.2f%%",ageCard[2], percentageCardAge2);
        System.out.printf("\n              71 - 90   |           %3d           |               %6.2f%%",ageCard[3], percentageCardAge3);
        System.out.printf("\n              Others    |           %3d           |               %6.2f%%\n\n",ageCard[4], percentageCardAge4);


        Main.pressAnyKeyToContinue();
    }
}

