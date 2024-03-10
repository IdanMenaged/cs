public class Program {
    /**
     * construct route (0, 2) -> (1, 4) -> (5, 4) -> (3, 1) -> (5, 0)
     * print route length
     */
    public static void main(String[] args) {
        // construct route
        BusRoute route = new BusRoute(new Station(0, 2), new Station(1, 4));
        route.addStation(new Station(5, 4));
        route.addStation(new Station(3, 1));
        route.addStation(new Station(5, 0));

        // print length
        System.out.println("route length: " + route.routeLength());
    }
}
