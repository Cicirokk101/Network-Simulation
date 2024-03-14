public class ArrayEventList implements FutureEventList{
    //    there needs to be an array here to store the events
    private Event[] events;
    private static final int beg_size = 5;
    private int size;
    private int simulationTime;
    //constructor
    public ArrayEventList() {
        events = new Event[beg_size];
        size = 0;
        simulationTime = 0;
    }
    private void resize() { // resize the list if the # of timer go over capacity
        Event[] newEventList = new Event[events.length * 2];
        System.arraycopy(events, 0, newEventList, 0, size);
        events = newEventList;
    }
    @Override
    public Event removeFirst() { // remove the first element in the list
        if (size == 0){
            return null;

        }
        Event e0 = events[0];
        System.arraycopy(events, 1, events, 0, size - 1);
        size--;
        simulationTime = e0.getArrivalTime();

        return e0 ;

    }

    @Override
    public boolean remove(Event e) {
        //
        //printList();
        return this.remove( e, 0, size-1);
    }

    private boolean remove( Event e, int r, int l){
        if (r > l ) {

            return false;
        }

        int mid = (l+r )  / 2;

        if (events[mid].getArrivalTime() == e.getArrivalTime()){

            int j = 0;
            for (int i = size-1; i > mid; i--) {

                events[i-1] = events[i];

            }

            size--;
            return true;
        }

        else if (events[mid].getArrivalTime() > e.getArrivalTime()){
            return remove(e, r, mid - 1);
        }
        else{
            return remove( e,mid + 1, l );
        }



    }







    @Override
    public void insert(Event e) {
        e.setInsertionTime(getSimulationTime());

        if(size == events.length){
            resize();
        }
        events[size] = e;
        size++;

        // sort the timers based on which timer is the smallest

        for (int i = 1; i < size; i++) {
            Event key = events[i];
            int j = i - 1;
            while (j >= 0 && events[j].getArrivalTime() > key.getArrivalTime()) {
                events[j + 1] = events[j];
                j = j - 1;
            }
            events[j + 1] = key;
        }
    }



    @Override
    public int size() {

        return size;// how many elements are in the array
    }

    @Override
    public int capacity() {
        return events.length;// the length of the array
    }

    @Override
    public int getSimulationTime() {
        return simulationTime;
    }
}

