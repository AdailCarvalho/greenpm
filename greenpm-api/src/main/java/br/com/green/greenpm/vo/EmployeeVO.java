package br.com.green.greenpm.vo;

/**
 * 
 * @author Adail Carvalho
 * 
 * @since 2018-04-25
 *
 */
public class EmployeeVO {
    
    private String codEmployee;
    
    private String name;
    
    private String team;
    
    private String email;
    
    private String skill;
    
    public EmployeeVO() {
        
    }

    public EmployeeVO(String name, String team, String skill) {
        this.name = name;
        this.team = team;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodEmployee() {
        return codEmployee;
    }

    public void setCodEmployee(String codEmployee) {
        this.codEmployee = codEmployee;
    }

    @Override
    public String toString() {
        return "EmployeeVO [codEmployee=" + codEmployee + ", name=" + name + ", team=" + team + ", email=" + email
                + ", skill=" + skill + "]";
    }
}