public class BusRoute {
    private Node<Station> route; // the route
    private Node<Station> lastStation; // last station on route

    // constructor
    public BusRoute(Station first, Station second) {
        this.route = new Node<>(first, new Node<>(second));
        this.lastStation = this.route.getNext();
    }

    /**
     * adds a new station at the end of the route
     * @param newStation new station
     */
    public void addStation(Station newStation) {
        // add new station
        this.lastStation.setNext(new Node<>(newStation));

        // update last
        this.lastStation = this.lastStation.getNext();
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
