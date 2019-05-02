package sample.Person;

public class Work {
    private String position;
    private int salary;
    private String workingDays;

    public Work(String position, int salary, String workingDays) {
        this.position = position;
        this.salary = salary;
        this.workingDays = workingDays;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getWorkingDays() {
        return workingDays;
    }
}
