package C42.main;

/**
 * Created by saurav on 11/8/14.
 */
public class Car {

    private String color;
    private String regNo;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!regNo.equals(car.regNo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}
