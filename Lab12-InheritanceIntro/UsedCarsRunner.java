public class UsedCarsRunner
{
    public static void main(String[] args)
    {
        Inventory total = new Inventory();
        Car acura = new Car("Acura MDX", 2022, 47200, 33.5);
        Car mercedes = new Car("Mercedes-Benz GLA-Class", 2022, 36400, 37.1);
        Truck ford = new Truck("Ford Maverick", 2022, 20000, 2000);
        Truck chevy = new Truck("Chevrolet Silverado 2500HD", 2022, 36600, 14500);
        Vehicle yamaha = new Motorcycle("Yamaha Tenere 700", 2021, 10000, "street legal");
        System.out.println("Great MPG test: " + acura.greatMPG());
        System.out.println("Great MPG test (2): " + mercedes.greatMPG());
        System.out.println("Can truck tow boat: " + ford.canTowBoat());
        System.out.println("Can truck tow boat (2): " + chevy.canTowBoat());
        System.out.println();
        System.out.println();
        total.addVehicle(acura);
        total.addVehicle(mercedes);
        total.addVehicle(ford);
        total.addVehicle(chevy);
        total.addVehicle(yamaha);
        total.listInventory();
    }
}
