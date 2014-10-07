package Server;

public class SensorData {

    private final int id;
    private final int steps;
    private final double distance;
    private final double calories;

    public SensorData(int id, int steps, double distance, double calories) {
        this.id = id;
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id
    }

    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int steps){
        this.steps = steps;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public double getCalories(){
        return this.calories;
    }

    public void setCalories(double calories){
        this.calories = calories
    }
}