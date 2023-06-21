package sg.edu.nus.iss.paf_d22workshop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RSVP {
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comment;
}
