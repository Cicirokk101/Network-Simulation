public class Timer implements Event{
    //private vars
    private static int nextId = 0; // for the timers id
    private int id;
    private int dur;

    private int insertion_time;

    //add constructor here
    public Timer(){

    }

    public Timer( int tDur){
        dur = tDur;
        this.id = Timer.nextId;
        Timer.nextId++;
    }


    //this for the Event Interface

    //setting methods
    @Override
    public void setInsertionTime(int currentTime) {
        insertion_time = currentTime;
    }


    //getter methods
    @Override
    public int getInsertionTime() {
        return insertion_time;
    }

    @Override
    public int getArrivalTime() {
        return dur + getInsertionTime();
    }

    @Override
    public void cancel(int currentTime) {//Timer <timer_id> canceled at time: <sim_time>
        System.out.println("Timer " + this.id + " canceled at time: "+ currentTime);
    }

    @Override
    public void handle() { //Timer <timer_id> handled (arrival time: <arrival_time>)
        System.out.println("Timer " + this.id +" handled (arrival time: " + getArrivalTime() + ")");

    }


    public String toString(){// Timer <timer_id> (insertion time: <insertion_time>,arrival time: <arrival_time>)

        return "Timer "+ this.id + " (insertion time: " + insertion_time + ", arrival time: " + getArrivalTime() + ")";
    }


}
