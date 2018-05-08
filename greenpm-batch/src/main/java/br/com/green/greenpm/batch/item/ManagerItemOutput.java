package br.com.green.greenpm.batch.item;

public class ManagerItemOutput {
    
    private Long id;
    
    private String pmCod;

    private String pmName;
    
    private String pmSkills;
    
    private String pmEmail;
    
    private String persistedBy;
    
    public ManagerItemOutput() {
        persistedBy = "BATCH";
    }

    public ManagerItemOutput(Long id) {
        this.id = id;
    }

    public ManagerItemOutput(Long id, String pmCod, String pmName, String pmSkills, String pmEmail) {
        this.id = id;
        this.pmCod = pmCod;
        this.pmName = pmName;
        this.pmSkills = pmSkills;
        this.pmEmail = pmEmail;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPmCod() {
        return pmCod;
    }

    public void setPmCod(String pmCod) {
        this.pmCod = pmCod;
    }

    public String getPmName() {
        return pmName;
    }

    public void setPmName(String pmName) {
        this.pmName = pmName;
    }

    public String getPmSkills() {
        return pmSkills;
    }

    public void setPmSkills(String pmSkills) {
        this.pmSkills = pmSkills;
    }

    public String getPmEmail() {
        return pmEmail;
    }

    public void setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
    }

    public String getPersistedBy() {
        return persistedBy;
    }

    public void setPersistedBy(String persistedBy) {
        this.persistedBy = persistedBy;
    }

    @Override
    public String toString() {
        return "ManagerItemOutput [id=" + id + ", pmCod=" + pmCod + ", pmName=" + pmName + ", pmSkills=" + pmSkills
                + ", pmEmail=" + pmEmail + ", persistedBy=" + persistedBy + "]";
    }
}