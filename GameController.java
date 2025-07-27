public class GameController {
    private boolean[] buttons = new boolean[4];
    
    public GameController (){
    for(int i = 0; i<4; i++) {
            buttons[i] = false;
        }
    }

    public boolean isPressed (int buttonNumber) {
        if(buttonNumber < 1|| buttonNumber > 4){
            System.out.println("Asking for wrong button: " + buttonNumber);
            return false;
        }
        return buttons[buttonNumber - 1];
    }

    public void setButton (int buttonNumber) {
        if(buttonNumber < 1|| buttonNumber > 4){
            System.out.println("Pressing wrong button: " + buttonNumber);
            return;
        }
        buttons[buttonNumber-1] = true;
    }

    public void releaseButton (int buttonNumber) {
        if(buttonNumber < 1|| buttonNumber > 4){
            System.out.println("Releasing wrong button: " + buttonNumber);
            return;
        }
        buttons[buttonNumber-1] = false;
    }
}
