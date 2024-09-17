package entities;

public class PharmacyPosition {
    private int positionId;
    private String position;

    public PharmacyPosition( String position) {
        this.position = position;
    }

    public int getPositionId() {
        return positionId;
    }

    public String getPosition() {
        return position;
    }
}
