package com.uliana.petclinic.bean;

import com.uliana.petclinic.jms.VisitRequestProducer;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("visitRequestBean")
@ViewScoped
public class VisitRequestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private VisitRequestProducer producer;

    private Long petId;
    private String visitDate;
    private String reason;

    public String send() {
        producer.sendVisitRequest(petId, visitDate, reason);
        petId = null;
        visitDate = null;
        reason = null;
        return "listView.xhtml?faces-redirect=true";
    }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
