import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mrience.jobboard.app.App;
import pl.mrience.jobboard.domain.Address;
import pl.mrience.jobboard.domain.Company;
import pl.mrience.jobboard.domain.JobAd;
import pl.mrience.jobboard.domain.Keyword;
import pl.mrience.jobboard.repository.AddressRepository;
import pl.mrience.jobboard.repository.CompanyRepository;
import pl.mrience.jobboard.repository.KeywordRepository;

import java.util.Date;
import java.util.Set;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = App.class)
@ComponentScan(basePackages = "main.java.pl.mrience.jobboard")
public class RepositoryTest {

    private JobAd jobAd;
    private Keyword keyword;
    private Address address;
    private Company company;

    @Autowired
    JpaRepository jobAdRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    private void initEach() {

        keyword = new Keyword();
        keyword.setKeyword("keyword5");

        address = new Address.AddressBuilder()
                .street("dluga 10")
                .city("Krakow")
                .state("Poland")
                .postalCode("12-345")
                .build();

        company = new Company.CompanyBuilder()
                .nip("1234567890")
                .name("Name")
                .email("company@company.com")
                .password("zaqwsx")
                .build();

        jobAd = new JobAd.JobAdBuilder()
                .title("Job ad!!")
                .content("blah blah")
                .startDate(new Date())
                .expireDate(new Date())
                .addresses(Set.of(address))
                .keywords(Set.of(keyword))
                .company(company)
                .build();

        companyRepository.save(company);
        jobAdRepository.save(jobAd);
    }

    @AfterEach
    private void destructEach() {
        if (jobAdRepository.existsById(this.jobAd.getJobAdId()))
            jobAdRepository.deleteById(this.jobAd.getJobAdId());

        if (keywordRepository.existsById(this.keyword.getKeyword()))
            keywordRepository.deleteById(this.keyword.getKeyword());

        if (addressRepository.existsById(this.address.getAddressId()))
            addressRepository.deleteById(this.address.getAddressId());

        if (companyRepository.existsByNip(this.company.getNip()))
            companyRepository.deleteByNip(this.company.getNip());
    }

    @Test
    public void shouldSaveJobAdToDatabase() {
        Assertions.assertEquals(this.jobAd, jobAdRepository.getOne(this.jobAd.getJobAdId()));
        Assertions.assertFalse(jobAdRepository.findAll().isEmpty());
    }

    @Test
    public void shouldDeleteJobAdsWhenDeleteCompany() {
        Assertions.assertTrue(jobAdRepository.existsById(this.jobAd.getJobAdId()));
        companyRepository.delete(this.company);
        Assertions.assertFalse(jobAdRepository.existsById(this.jobAd.getJobAdId()));
    }

    @Test
    public void shouldNotDeleteCompanyAndAddressWhenDeleteJobAd() {
        jobAdRepository.delete(this.jobAd);
        Assertions.assertFalse(companyRepository.findAll().isEmpty());
    }

    @Test
    public void shouldDeleteAddressWhenDeleteJobAd() {
        Assertions.assertTrue(addressRepository.existsById(this.address.getAddressId()));
        jobAdRepository.delete(this.jobAd);
        Assertions.assertFalse(addressRepository.existsById(this.address.getAddressId()));
    }

    @Test
    public void shouldNotDeleteJobAdWhenDeleteAddress() {
        addressRepository.deleteById(this.address.getAddressId());
        Assertions.assertTrue(jobAdRepository.existsById(this.jobAd.getJobAdId()));
    }

    @Test
    public void shouldNotDeleteKeywordWhenDeleteJobAd() {
        jobAdRepository.delete(this.jobAd);
        Assertions.assertTrue(keywordRepository.existsById(keyword.getKeyword()));
    }

    @Test
    public void shouldNotDeleteJobAdWhenDeleteKeyword() {
        keywordRepository.deleteById(this.keyword.getKeyword());
        Assertions.assertTrue(jobAdRepository.existsById(this.jobAd.getJobAdId()));
    }

}
