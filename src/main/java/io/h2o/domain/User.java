package io.h2o.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "First Name can not be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last Name can not be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email is not valid")
    @Column(name = "email", nullable = false)
    private String email;


    @NotNull(message = "Date of Birth must have a value")
//    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull(message = "Date of Joining must have a value")
    @Column(name = "doj", nullable = false)
    private LocalDate doj;

    @NotBlank(message = "City can not be blank")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "PIN CODE can not be blank")
//    @Size(min = 6, max = 6 , message = "PIN CODE should be 6 digits only")
    @Pattern(regexp="[\\d]{6}", message = "PIN CODE should be 6 digits number only")
    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;

}
