// package com.example.registri.config;

// import java.time.LocalDate;
// import java.time.Month;
// import java.util.List;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.example.registri.tables.degree.Degree;
// import com.example.registri.tables.degree.DegreeRepository;
// import com.example.registri.professor.Professor;
// import com.example.registri.professor.ProfessorRepository;
// import com.example.registri.subject.Subject;
// import com.example.registri.subject.SubjectRepository;

// @Configuration
// public class Config {

//     @Bean
//     CommandLineRunner commandLineRunner(DegreeRepository degreeRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository){
//         return args -> {
//             //Corsi di laurea
//             Degree info1 = new Degree(
//                 "Informatica triennale L-31", "Corso bellissimo"
//             );
//             Degree info2 = new Degree(
//                 "Informatica magistrale LM-18", "Corso super bellissimo"
//             );
//             degreeRepository.saveAll(List.of(info1,info2));

//             //Docenti
//             Professor prof1 = new Professor(
//                 "Giacomo", "Rossi", LocalDate.of(1970, Month.JANUARY, 5)
//             );
//             Professor prof2 = new Professor(
//                 "Mario", "Verdi", LocalDate.of(1969, Month.MAY, 5)
//             );
//             professorRepository.saveAll(List.of(prof1,prof2));
            
//             //Materie
//             Subject prog1 = new Subject(
//                 info1, "Programmazione 1", 9
//             );
//             Subject prog2 = new Subject(
//                 info1, "Programmazione 2", 9
//             );
//             subjectRepository.saveAll(List.of(prog1,prog2));


//         };
        
//     }
// }
