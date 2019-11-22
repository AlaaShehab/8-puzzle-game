package Utils;

public class State {

    private String currentPuzzle;
    private int zeroPosition;
    private int cost;

    public State(String currentPuzzle, int zeroPosition) {
        this.currentPuzzle = currentPuzzle;
        this.zeroPosition = zeroPosition;
    }

    public State(String currentPuzzle, int zeroPosition, int cost) {
        this.currentPuzzle = currentPuzzle;
        this.zeroPosition = zeroPosition;
        this.cost = cost;
    }

    public State() {
    }

    public String getCurrentPuzzle() {
        return currentPuzzle;
    }

    public int getZeroPosition() {
        return zeroPosition;
    }

    public int getCost() { return cost; }

    public void setCurrentPuzzle(String currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }

    public void setZeroPosition(int zeroPosition) {
        this.zeroPosition = zeroPosition;
    }

    public void setCost(int cost) { this.cost = cost; }

    public State swapPositions(int newDirection) {
        State newState = new State();
        char[] newPuzzle = (this.getCurrentPuzzle()).toCharArray();

        char temp = newPuzzle[this.getZeroPosition()];
        newPuzzle[this.getZeroPosition()] = newPuzzle[newDirection];
        newPuzzle[newDirection] = temp;
        newState.setCurrentPuzzle(String.valueOf(newPuzzle));
        newState.setZeroPosition(newDirection);
        newState.setCost(this.cost + 1);
        return newState;
    }
    @Override
    public boolean equals(Object o) {

        State element = (State) o;
        return this.getCurrentPuzzle().equals(((State) o).getCurrentPuzzle());

    }
}