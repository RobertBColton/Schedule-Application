public abstract class Rule {

    // Represents a schedule that is impossible to
    // realize. For example, an instructor scheduled
    // for two simultaneous classes.
    //
    public final static int IMPOSSIBLE = 1000000;

    // This operation returns a fitness score for the
    // provided schedule. Higher values indicate a less
    // fit schedule. A value of 0 indicates a perfect fit.
    //
    public int getFitness(Schedule schedule); 

}
public class SpecificRule extends Rule {

    // This operation returns a fitness score for the
    // provided schedule. Higher values indicate a less
    // fit schedule. A value of 0 indicates a perfect fit.
    //
    public int getFitness(Schedule schedule); 
}
public class InstructorConflict implements Rule {
    
    // This operation returns 0 if no instructors
    // are scheduled for simultaneous classes and
    // IMPOSSIBLE otherwise.
    //
    public int getFitness(Schedule schedule) {
        ...
    }

}