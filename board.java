public class board extends Sendable {
    @Override
public void initSendable(SendableBuilder builder) {
   builder.addDoubleProperty("Velocity", this::getVelocity, null);
   builder.addDoubleProperty("position", this::getPosition, this::setPosition);
   builder.addDoubleProperty("power", ()->power, null); // lambda function to return a local variable
   builder.addDoubleProperty("remaining", ()->target-getPosition(),null); // lambda function variable
}


}
