package View;

public class Students {
    public int id;
    public String fullName,city,sex,birthday;
    public Double mathPoint,physicsPoint,chemistryPoint;
    public Students(){

    }
    public Students(int id,String fullName,String birthday,String city,String sex,Double mathPoint,Double physicsPoint,Double chemistryPoint){
        this.id = id ;
        this.fullName = fullName;
        this.birthday = birthday;
        this.city = city;
        this.sex = sex;
        this.mathPoint = mathPoint;
        this.physicsPoint = physicsPoint;
        this.chemistryPoint = chemistryPoint;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setMathPoint(Double mathPoint) {
        this.mathPoint = mathPoint;
    }

    public void setPhysicsPoint(Double physicsPoint) {
        this.physicsPoint = physicsPoint;
    }

    public void setChemistryPoint(Double chemistryPoint) {
        this.chemistryPoint = chemistryPoint;
    }

    public Double getPhysicsPoint() {
        return physicsPoint;
    }

    public Double getChemistryPoint() {
        return chemistryPoint;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCity() {
        return city;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public Double getMathPoint() {
        return mathPoint;
    }
}
