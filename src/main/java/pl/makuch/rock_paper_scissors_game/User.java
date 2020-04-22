package pl.makuch.rock_paper_scissors_game;

public class User{

    private String username;
    private int userPoints;
    private int cpuPoints;

    public User(String userame) {
        this.username = userame;
        this.userPoints=0;
        this.cpuPoints=0;
    }

    public User() {
        this.username=null;
        this.userPoints=0;
        this.cpuPoints=0;
    }

    public User(String username, int userPoints, int cpuPoints) {
        this.username = username;
        this.userPoints = userPoints;
        this.cpuPoints = cpuPoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }

    public int getCpuPoints() {
        return cpuPoints;
    }

    public void setCpuPoints(int cpuPoints) {
        this.cpuPoints = cpuPoints;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userPoints=" + userPoints +
                ", cpuPoints=" + cpuPoints +
                '}';
    }

    public int getRank(){
        return userPoints-cpuPoints;
    }
}
