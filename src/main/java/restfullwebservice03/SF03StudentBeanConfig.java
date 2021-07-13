package restfullwebservice03;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SF03StudentBeanConfig {

	@Bean
	CommandLineRunner commandLineRunner1(SF03StudentBeanRepository studentRepo) {
		
		return args -> {
			SF03StudentBean aliCan = new SF03StudentBean(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2002, Month.JANUARY, 21));
			SF03StudentBean veliHan = new SF03StudentBean(102L, "Veli Han", "vh@gmail.com", LocalDate.of(2000, Month.MAY, 2));
			SF03StudentBean maryStar = new SF03StudentBean(103L, "Mary Star", "ms@gmail.com", LocalDate.of(2001, Month.FEBRUARY, 12));
			
			studentRepo.saveAll(List.of(aliCan, veliHan, maryStar));
		};
	}

}