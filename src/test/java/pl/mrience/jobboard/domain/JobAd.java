package pl.mrience.jobboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "job_ads")
@Getter
@Setter
@NoArgsConstructor
public class JobAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ad_id")
    private Integer jobAdId;

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "start_date")
    private Date startDate;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expire_date")
    private Date expireDate;

    @NotBlank
    private String content;

    @Size(max = 255)
    private String linkToForm;

    @NotBlank
    private Integer nip;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "nip")
    private Company company;
}
