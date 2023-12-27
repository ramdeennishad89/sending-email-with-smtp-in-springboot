package com.example.sendemail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDatails {
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
