package com.example.javaitmo2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {
    private String registrationDocument;
    private Date dateStart;
    private Date dateEnd;

    public OwnerRequest(String registrationDocument, Date dateStart) {
        this.registrationDocument = registrationDocument;
        this.dateStart = dateStart;
    }
}
