
import java.util.*;

public class main {
    private static String[] names = { "Ahmet", "Ayşe", "Mehmet", "Fatma", "Mustafa", "Zeynep", "Ali", "Elif", "Hüseyin",
            "Emine",
            "Murat", "Hatice", "Yusuf", "Merve", "Hasan", "İrem", "Osman", "Ecrin", "Burak", "Esra", "Can", "Büşra",
            "Emre", "Kübra", "Volkan", "Zehra", "Furkan", "Gamze", "Kerem", "Sibel" , "Tuğberk"};
    private static String[] surnames = { "Yilmaz", "Kaya", "Demir", "Çelik", "Şahin", "Yildiz", "Yildirim", "Öztürk",
            "Aydin",
            "Arslan", "Doğan", "Kiliç", "Aslan", "Çetin", "Kara", "Koç", "Kurt", "Özdemir", "Polat", "Can", "Ateş",
            "Avci", "Güler", "Tekin", "Kaplan", "Aksoy", "Bulut", "Taş", "Şen", "Korkmaz" };

            private static ArrayList<aVehicle> vehicles = new ArrayList<>();
            private static ArrayList<bStaff> staffs = new ArrayList<>();// All staff in company
            private static ArrayList<Integer> plateNumbers = new ArrayList<>();
            private static ArrayList<bStaff> availableStaffs = new ArrayList<>();// For hire staff
            private static ArrayList<bStaff> unassignedStaffs = new ArrayList<>();
            private static ArrayList<aVehicle> availableVehicles = new ArrayList<>();
            private static ArrayList<aVehicle> vehicleWithStaffs = new ArrayList<>();
            private static ArrayList<bReceiver> receviers = new ArrayList<>();
            private static ArrayList<aVehicle> vehicleWithDriver = new ArrayList<>();
            private static ArrayList<bDriver> unassignedDrivers = new ArrayList<>();
            private static ArrayList<bStaff> driversAndCarriers = new ArrayList<>();

            private static boolean isStaffCreated = false;
            private static int totalPackages = 0;
            private static int  hiredStaffs=0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int money = 500;
        int choice1;
        int choice2;

        while (money > 0) {
            System.out.println(money);
            System.out.println();
            System.out.print(
                    "1.Buy a vehicle\n2.List owned vehicles\n3.Sell a vehicle\n4.Hire Staff\n5.Assign Staff\n6.Unassign Staff\n7.Distribute Staff\n8.Fire Staff\n9.End Turn\nChoice: ");
            choice1 = in.nextInt();

            if (choice1 == 1) {
                int plate;
                int amount;
                System.out.println("\nMoney: " + money + "\n");
                System.out.print(
                        "    Type       Cargo Capacity     Staff Capacity     Cost     Speed\n1.Biycycle          5                  1             100       10\n2.Car               20                 2             2000      50\n3.Helicopter        50                 2             15000     100\n4.Truck             200                5             7000      20\n5.Van               100                4             4000      20\nEnter car type: ");
                choice2 = in.nextInt();
                System.out.print("Enter the number of vehicles to be purchased: ");
                amount = in.nextInt();
                if (choice2 == 1 && money > amount * 100) {
                    for (int x = amount; x > 0; x--) {
                        plate = createPlateNumber();
                        aBicycle bicycle = new aBicycle(plate);
                        vehicles.add(bicycle);
                        money -= 100;
                    }
                } else if (choice2 == 2 && money > amount * 2000) {
                    for (int x = amount; x > 0; x--) {
                        plate = createPlateNumber();
                        aCar car = new aCar(plate);
                        vehicles.add(car);
                        money -= 2000;
                    }
                } else if (choice2 == 3 && money > amount * 15000) {
                    for (int x = amount; x > 0; x--) {
                        plate = createPlateNumber();
                        aHelicopter helicopter = new aHelicopter(plate);
                        vehicles.add(helicopter);
                        money -= 15000;
                    }
                } else if (choice2 == 4 && money > amount * 7000) {
                    for (int x = amount; x > 0; x--) {
                        plate = createPlateNumber();
                        aTruck truck = new aTruck(plate);
                        vehicles.add(truck);
                        money -= 7000;
                    }
                } else if (choice2 == 5 && money > amount * 4000) {
                    for (int x = amount; x > 0; x--) {
                        plate = createPlateNumber();
                        aVan van = new aVan(plate);
                        vehicles.add(van);
                        money -= 4000;
                    }
                } else {
                    System.out.println("Not enough money or invalid choice.");
                }

            } else if (choice1 == 2) {
                
                setVehicleWithStaff();
                for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println((i + 1) + " Vehicle: " + vehicles.get(i) + " Staffs: "
                            + vehicles.get(i).getWorkers()+" Profit: "+ vehicles.get(i).getProfit());
                }
            } else if (choice1 == 3) {
                System.out.println("Owned Vehicles");
                for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println((i + 1) + ". " + vehicles.get(i));
                }
                System.out.print("Enter your choice: ");
                int sellChoice = (in.nextInt() - 1);
                aVehicle vehicleToSell = vehicles.get(sellChoice); 
                money += vehicleToSell.getCost() / 2;

               
                for (bStaff worker : vehicleToSell.getWorkers()) {
                    worker.setIsAvailable(true);
                }
                vehicleToSell.getWorkers().clear(); 
                vehicles.remove(sellChoice); 

            } else if (choice1 == 4) {
                int carrierAmonut;
                int receiverAmount;
                int driverAmount;
                int contractorCarrierAmonut;
                int contractorReceiverAmount;
                int contractorDriverAmount;
                
                if(hiredStaffs>=5){
                    System.out.println("You hired 5 staff this round");
                    continue;
                }
                
                if (!(isStaffCreated)) {
                    Random rand = new Random();
                    carrierAmonut = rand.nextInt(0, 6);
                    receiverAmount = rand.nextInt(0, 6 - carrierAmonut);
                    driverAmount = 5 - carrierAmonut - receiverAmount;
                    
                    int contractorStaffNumber = rand.nextInt(0, 6); 
                    int remainingContractors = contractorStaffNumber; 

                  
                    if (remainingContractors > 0) {
                        contractorCarrierAmonut = rand.nextInt(0, remainingContractors + 1); 
                    } else {
                        contractorCarrierAmonut = 0;
                    }
                    remainingContractors -= contractorCarrierAmonut; 

                    
                    if (remainingContractors > 0) {
                        contractorReceiverAmount = rand.nextInt(0, remainingContractors + 1);
                    } else {
                        contractorReceiverAmount = 0;
                    }
                    remainingContractors -= contractorReceiverAmount; 

                    
                    contractorDriverAmount = remainingContractors;


                    for (int i = 0; i < carrierAmonut; i++) {
                        bCarrier carrier = new bCarrier(firstName(), secondName(),true, randomCreator(0, 6),
                                randomCreator(5, 26));
                        availableStaffs.add(carrier);
                    }
                    for (int i = 0; i < receiverAmount; i++) {
                        bReceiver receiver = new bReceiver(firstName(), secondName(),true, randomCreator(10, 21));
                        availableStaffs.add(receiver);
                    }
                    for (int i = 0; i < driverAmount; i++) {
                        bDriver driver = new bDriver(firstName(), secondName(),true, randomCreator(10, 101),
                                randomCreator(3, 11));
                        availableStaffs.add(driver);
                    }

                    for (int i = 0; i < contractorCarrierAmonut; i++) {
                        bCarrier carrier = new bCarrier(firstName(), secondName(),false, randomCreator(0, 6),
                                randomCreator(5, 26));
                        availableStaffs.add(carrier);
                    }
                    for (int i = 0; i < contractorReceiverAmount; i++) {
                        bReceiver receiver = new bReceiver(firstName(), secondName(),false, randomCreator(10, 21));
                        availableStaffs.add(receiver);
                    }
                    for (int i = 0; i < contractorDriverAmount; i++) {
                        bDriver driver = new bDriver(firstName(), secondName(),false, randomCreator(10, 101),
                                randomCreator(3, 11));
                        availableStaffs.add(driver);
                    }
                    
                    isStaffCreated = true;
                }
                System.out.println("\n Select a staff");
                for (int i = 0; i < availableStaffs.size(); i++) {
                    System.out.println((i + 1) + ". " + availableStaffs.get(i));
                }
                
                System.out.print("Enter number: ");
                int wantedStaff = (in.nextInt() - 1);
                
                if (wantedStaff >= 0 && wantedStaff < availableStaffs.size()) {
                    bStaff selectedStaff = availableStaffs.get(wantedStaff);
                
                    // is permemnnat
                    if (!selectedStaff.getIsPermenant()) {
                        int hiringFee = (int) Math.ceil(selectedStaff.getSalary() * 0.6); 
                        
                        if (money >= hiringFee) { // enough money?
                            money -= hiringFee;
                            System.out.println("Contractor hired. Fee paid: " + hiringFee + "$");
                            
                            
                            staffs.add(selectedStaff);
                            availableStaffs.remove(selectedStaff); 
                            hiredStaffs++;
                        } else {
                            System.out.println("Not enough money to pay the hiring fee! Required: " + hiringFee + "$");
                        }
                    } else { // permenant
                        staffs.add(selectedStaff);
                        availableStaffs.remove(selectedStaff);
                        hiredStaffs++;
                        System.out.println("Permanent staff hired.");
                    }
                } else {
                    System.out.println("Invalid selection.");
                }
            } else if (choice1 == 5) {
                int staffChoice;
                int vehicleChoise;

                findUnassignedStaff(); 
                
                if (unassignedStaffs.isEmpty()) {
                    System.out.println("There are no unassigned drivers or carriers.");
                } else {
                    System.out.println("Unassigned Staff:");
                    for (int i = 0; i < unassignedStaffs.size(); i++) {
                        System.out.println((i + 1) + ". " + unassignedStaffs.get(i));
                    }
                    System.out.print("Select staff to assign: ");
                    staffChoice = (in.nextInt() - 1);

                    
                    if (staffChoice < 0 || staffChoice >= unassignedStaffs.size()){
                        System.out.println("Invalid staff selection.");
                        continue;
                    }
                    bStaff selectedStaff = unassignedStaffs.get(staffChoice);

                    findAvailableVehicles(); 
                    if(availableVehicles.isEmpty()){
                        System.out.println("There are no vehicles with available capacity.");
                        continue;
                    }

                    System.out.println("Available Vehicles:");
                    for (int i = 0; i < availableVehicles.size(); i++) {
                        System.out.println((i + 1) + ". " + availableVehicles.get(i));
                    }
                    System.out.print("Select vehicle to assign to: ");
                    vehicleChoise = (in.nextInt() - 1);
                    
                    if (vehicleChoise < 0 || vehicleChoise >= availableVehicles.size()){
                        System.out.println("Invalid vehicle selection.");
                        continue;
                    }
                    aVehicle selectedVehicle = availableVehicles.get(vehicleChoise);
                    
                    
                    aVehicle oldVehicle = findStaffsVehicle(selectedStaff);
                    if (oldVehicle != null) {
                        oldVehicle.getWorkers().remove(selectedStaff);
                    }
                    
                    
                    selectedVehicle.setWorker(selectedStaff);
                    selectedStaff.setIsAvailable(false);
                    System.out.println(selectedStaff.getName() + " has been assigned to " + selectedVehicle);
                }
            } else if (choice1 == 6) {
                setVehicleWithStaff();
                System.out.println("Assigned Staffs");
                for (int i = 0; i < vehicleWithStaffs.size(); i++) {
                    System.out.println((i + 1) + " Vehicle: " + vehicleWithStaffs.get(i) + " Staffs: "
                            + vehicleWithStaffs.get(i).getWorkers());
                }
                System.out.print("Enter vehicle choice: ");
                int vehicleChoice = (in.nextInt() - 1);
                for (int i = 0; i < vehicleWithStaffs.get(vehicleChoice).getWorkers().size(); i++) {
                    System.out.println((i + 1) + ". " + vehicleWithStaffs.get(vehicleChoice).getWorkers().get(i));
                }
                System.out.print("Enter staff choice: ");
                int staffChoice = (in.nextInt() - 1);

                vehicleWithStaffs.get(vehicleChoice).getWorkers().get(staffChoice).setIsAvailable(true);
                vehicleWithStaffs.get(vehicleChoice).getWorkers().remove(staffChoice);
            } else if (choice1 == 7) {
                findAvailableVehicles();
                setVehicleWithDriver();
                setUnassignedDrivers();
                availableVehicles.removeAll(vehicleWithDriver);
                for (int i = 0; i < availableVehicles.size() && 0 < unassignedDrivers.size(); i++) {
                    availableVehicles.get(i).setWorker(unassignedDrivers.get(0));
                    unassignedDrivers.get(0).setIsAvailable(false);
                    unassignedDrivers.remove(0);
                }
                findAvailableVehicles();
                findUnassignedStaff();
                for (int i = 0; i < availableVehicles.size() && 0 < unassignedStaffs.size(); i++) {
                    for (int j = 0; j < (availableVehicles.get(i).getStaffCapacity()
                            - availableVehicles.get(i).getWorkers().size()); j++) {
                        if (unassignedStaffs.isEmpty()) {
                            break;
                        }

                        availableVehicles.get(i).setWorker(unassignedStaffs.get(0));
                        unassignedStaffs.get(0).setIsAvailable(false);
                        unassignedStaffs.remove(0);
                    }
                }

                System.out.println("Unemployed staff were assigned to duties");

            } else if (choice1 == 8) {
                System.out.println("Current Staffs");
                for (int i = 0; i < staffs.size(); i++) {
                    System.out.println((i + 1) + ". " + staffs.get(i));
                }
                System.out.print("Choice: ");
                int wantedStaffIndex = (in.nextInt() - 1);
                if (wantedStaffIndex >= 0 && wantedStaffIndex < staffs.size()) {
                    bStaff staffToFire = staffs.get(wantedStaffIndex);
                    
                    
                    aVehicle assignedVehicle = findStaffsVehicle(staffToFire);
                    if (assignedVehicle != null) {
                        assignedVehicle.getWorkers().remove(staffToFire);
                    }
                    
                    
                    staffs.remove(wantedStaffIndex);
                    System.out.println("Staff has been fired.");
                } else {
                    System.out.println("Invalid selection.");
            }

            } else if (choice1 == 9) {
              
                setReceviers();
                setVehicleWithDriver();
                int profit;

                
                for (int i = 0; i < staffs.size(); i++) {
                    if(staffs.get(i).getIsPermenant()){
                        int salary = staffs.get(i).getSalary();
                        money -= salary;
                    }
                }
                
                
                for (int i = 0; i < receviers.size(); i++) {
                    totalPackages += receviers.get(i).getReceivingCapacity();
                }

                
                for (int i = 0; i < vehicleWithDriver.size(); i++) {
                    int maxSpeed = findMaxSpeed(vehicleWithDriver.get(i));
                    int cargoCapacity = findCargoCapacity(vehicleWithDriver.get(i));
                    int priceBoost = findPriceBoost(vehicleWithDriver.get(i));

                    if (cargoCapacity < totalPackages) {
                        profit = cargoCapacity * priceBoost * maxSpeed / 10;
                        vehicleWithDriver.get(i).addProfit(profit);
                        money += profit;
                        totalPackages -= cargoCapacity;
                    } else {
                        profit = totalPackages * priceBoost * maxSpeed / 10;
                        vehicleWithDriver.get(i).addProfit(profit);
                        money += profit;
                        totalPackages = 0;
                    }
                }
                
                
                if (!staffs.isEmpty()) { 
                    Random rand = new Random();
                    int randomRaise = rand.nextInt(staffs.size()); 
                    staffs.get(randomRaise).increaseSalary(5);
                }

                
                for (int i = staffs.size() - 1; i >= 0; i--) { 
                    bStaff staffToRemove = staffs.get(i); 
                    if (!staffToRemove.getIsPermenant()) {
                        aVehicle assignedVehicle = findStaffsVehicle(staffToRemove);
                        if (assignedVehicle != null) {
                            assignedVehicle.getWorkers().remove(staffToRemove);
                        }
                        staffs.remove(i); 
                    }
                }
                
               
                availableStaffs.clear();
                isStaffCreated = false;
                hiredStaffs = 0;
            }
        }
    }

    public static int createPlateNumber() {
        Random rand = new Random();
        int random;
        boolean x = true;
        while (x) {
            random = rand.nextInt(1, 10000);
            if (!(plateNumbers.contains(random))) {
                plateNumbers.add(random);
                return random;
            }
        }
        return -1;
    }

    public static void setVehicleWithStaff() {
        vehicleWithStaffs.clear();

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getWorkers().size() > 0) {
                vehicleWithStaffs.add(vehicles.get(i));
            }
        }

    }

    public static void setVehicleWithDriver() {
        vehicleWithDriver.clear();
        for (int i = 0; i < vehicles.size(); i++) {
            for (int j = 0; j < vehicles.get(i).getWorkers().size(); j++) {
                if (vehicles.get(i).getWorkers().get(j) instanceof bDriver) {
                    if (!vehicleWithDriver.contains(vehicles.get(i))) {
                        vehicleWithDriver.add(vehicles.get(i));
                    }
                }
            }

        }
    }

    public static void setReceviers() {
        receviers.clear();
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i) instanceof bReceiver) {
                receviers.add((bReceiver) staffs.get(i));

            }
        }
    }

    public static void setDriversAndCarriers() {
        driversAndCarriers.clear();
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i) instanceof bCarrier) {
                driversAndCarriers.add((bCarrier) staffs.get(i));

            }
            if (staffs.get(i) instanceof bDriver) {
                driversAndCarriers.add((bDriver) staffs.get(i));

            }
        }
    }

    public static void setUnassignedDrivers() {
        unassignedDrivers.clear();
        for (int i = 0; i < staffs.size(); i++) {
            if ((staffs.get(i) instanceof bDriver) && staffs.get(i).isAvailable) {
                unassignedDrivers.add((bDriver) staffs.get(i));

            }
        }
    }

    public static int findMaxSpeed(aVehicle vehicle) {
        int max = 0;
        int speed = 0;
        for (int i = 0; i < vehicle.getWorkers().size(); i++) {
            if (vehicle.getWorkers().get(i) instanceof bDriver) {
                speed = ((bDriver) vehicle.getWorkers().get(i)).getSpeed();
                if (speed > max) {
                    max = speed;
                }
            }
        }
        return Math.min(max, vehicle.getSpeed());
    }

    public static int findCargoCapacity(aVehicle vehicle) {
        int cargoCapacity = 0;
        for (int i = 0; i < vehicle.getWorkers().size(); i++) {
            if (vehicle.getWorkers().get(i) instanceof bDriver) {
                cargoCapacity += ((bDriver) vehicle.getWorkers().get(i)).getDeliverCapacity();
            }
            if (vehicle.getWorkers().get(i) instanceof bCarrier) {
                cargoCapacity += ((bCarrier) vehicle.getWorkers().get(i)).getDeliverCapacity();
            }

        }
        if (cargoCapacity > vehicle.getCargoCapacity()) {
            return vehicle.getCargoCapacity();
        } else {
            return cargoCapacity;
        }

    }

    public static int findPriceBoost(aVehicle vehicle) {
        int priceBoost = 0;
        for (int i = 0; i < vehicle.getWorkers().size(); i++) {

            if (vehicle.getWorkers().get(i) instanceof bCarrier) {
                priceBoost += ((bCarrier) vehicle.getWorkers().get(i)).getPriceBoost();
            }
        }
        if (priceBoost == 0) {
            return 1;
        } else {
            return priceBoost;
        }
    }

    public static String firstName() {
        Random rand = new Random();
        int random = rand.nextInt(0, names.length);
        return names[random];
    }

    public static String secondName() {
        Random rand = new Random();
        int random = rand.nextInt(0, surnames.length);
        return surnames[random];
    }

    public static int randomCreator(int bound1, int bound2) {
        Random rand = new Random();
        int random = rand.nextInt(bound1, (bound2 + 1));
        return random;
    }

    public static void findUnassignedStaff() {
        unassignedStaffs.clear();
        for (int i = 0; i < staffs.size(); i++) {
            if ((staffs.get(i) instanceof bDriver || staffs.get(i) instanceof bCarrier)
                    && staffs.get(i).getIsAvailable()) {
                unassignedStaffs.add(staffs.get(i));
            }
        }
    }

    public static void findAvailableVehicles() {
        availableVehicles.clear();
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getStaffCapacity() != vehicles.get(i).getWorkers().size()) {
                availableVehicles.add(vehicles.get(i));
            }
        }
    }

    public static aVehicle findStaffsVehicle(bStaff staff) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getWorkers().contains(staff)) {
                return vehicles.get(i);
            }
        }
        return null;
    }

}