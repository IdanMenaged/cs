public class BusRoute {
    private Node<Station> route; // the route

    // constructor
    public BusRoute(Station first, Station second) {
        this.route = new Node<>(first, new Node<>(second));
    }

    /**
     * adds a new station at the end of the route
     * @param newStation new station
     */
    public void addStation(Station newStation) {
        // get last station
        Node<Station> route = this.route;
        while (route.hasNext()) {
            route = route.getNext();
        }

        // add new station
        route.setNext(new Node<>(newStation));
    }

    /**
     * @return length of the route
     */
    public double routeLength() {
        double out = 0;

        // go over route and add distances
        Node<Station> q = this.route, p = this.route.getNext();

        while (p != null) {
            out += p.getValue().distance(q.getValue());

            q = p;
            p = p.getNext();
        }

        return out;
    }
}
