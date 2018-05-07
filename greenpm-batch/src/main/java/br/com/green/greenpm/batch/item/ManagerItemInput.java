package br.com.green.greenpm.batch.item;

public class ManagerItemInput {
    
    private String pmCod;
    
    private String pm;
    
    private String pmEmail;
    
    private String pmSkills;
    
    public ManagerItemInput() {
        
    }

    public String getPmCod() {
        return pmCod;
    }

    public void setPmCod(String pmCod) {
        this.pmCod = pmCod;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPmEmail() {
        return pmEmail;
    }

    public void setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
    }

    public String getPmSkills() {
        return pmSkills;
    }

    public void setPmSkills(String pmSkills) {
        this.pmSkills = pmSkills;
    }
}