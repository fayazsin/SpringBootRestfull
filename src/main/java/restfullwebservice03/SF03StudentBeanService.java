package restfullwebservice03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SF03StudentBeanService {
	
	private SF03StudentBeanRepository studentRepo;
	@Autowired
	public SF03StudentBeanService(SF03StudentBeanRepository studentRepo) {
		this.studentRepo = studentRepo;
	}
	public List<SF03StudentBean> listStudents(){
		return studentRepo.findAll();
	}
	
	public SF03StudentBean selectStudentById(Long id) {
		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}
		else return new SF03StudentBean();
	}
	public String deleteStudentById(Long id) {
		if(!studentRepo.existsById(id)) {
			throw new IllegalStateException(id +" does not exist");
		}
		else {studentRepo.deleteById(id);
		return "Student whose id is "+ id + " is successfully deleted";
		}
	}
	public SF03StudentBean updateStudent(Long id,SF03StudentBean newStudent) {
		SF03StudentBean existingStudentById = studentRepo
				.findById(id)
				.orElseThrow(()->new IllegalStateException(id + "  id  does not exist..."));
		
		String name = existingStudentById.getName();
		if(newStudent.getName()==null) {
			existingStudentById.setName(null);
		}else if(existingStudentById.getName()==null) {
			existingStudentById.setName(newStudent.getName());
		}else if(!name.equals(newStudent.getName())) {
			existingStudentById.setName(newStudent.getName());
		}
		
		Optional<SF03StudentBean> existingStudentByEmail = studentRepo.findSF03StudentBeanByEmail(newStudent.getEmail());
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Email is taken, cannot be used again...");
		}
		else if(newStudent.getEmail()==null) {
			throw new IllegalStateException("Email is required please enter your mail...");
		}
		else if(!newStudent.getEmail().contains("@")) {
			throw new IllegalArgumentException("Email is not valid, please check it...");
		}else if (!existingStudentById.getEmail().equals(newStudent.getEmail())) {
			existingStudentById.setEmail(newStudent.getEmail());
		}
		if(Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
			throw new IllegalStateException("Date of Birth cannot be selected from future...");
		}
		else if (!existingStudentById.getDob().equals(newStudent.getDob())) {
			existingStudentById.setDob(newStudent.getDob());
		}
		existingStudentById.setAge(newStudent.getAge());
		existingStudentById.setErrMsg("No error...");
		return studentRepo.save(existingStudentById);
		}
		 
		public SF03StudentBean updateStdPartially (Long id, SF03StudentBean newStudent) {
		SF03StudentBean existingStudentById = studentRepo
														.findById(id)
														.orElseThrow(()->new IllegalStateException(id + "  id  does not exist..."));
		
		if(newStudent.getName()!=null) {
			existingStudentById.setName(newStudent.getName());
		}
		if(newStudent.getEmail()==null) {
			newStudent.setEmail("");
		}
		Optional<SF03StudentBean> existingStudentByEmail = studentRepo.findSF03StudentBeanByEmail(newStudent.getEmail());
		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Email is taken, cannot be used again...");
		}
		else if(newStudent.getEmail()!="" && newStudent.getEmail().contains("@")) {
			throw new IllegalArgumentException("Email is not valid, please check it...");
		}
		else if(newStudent.getEmail()!="") {
			existingStudentById.setEmail(newStudent.getEmail());
		}
		if (newStudent.getDob()!=null) {
			existingStudentById.setDob(newStudent.getDob());
		}
		return studentRepo.save(existingStudentById);
		}
		
		//This method adding a new Student
		public SF03StudentBean addNewStudent(SF03StudentBean newStudent) throws ClassNotFoundException, SQLException {
			Optional<SF03StudentBean> existingStudentByEmail = studentRepo.findSF03StudentBeanByEmail(newStudent.getEmail());
			
			if(existingStudentByEmail.isPresent()) {
				throw new IllegalStateException("Email exists in DB, email must be unique...");
			}
			if(newStudent.getName()==null) {
				throw new IllegalStateException("Name must be entered for new students...");
			}
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "Fdb", "Fatih1453");
			
			Statement st = con.createStatement();
			
			String sql = "SELECT max(id) FROM students";
			ResultSet result = st.executeQuery(sql);
			
			Long maxId = 0L;
			
			while(result.next()) {
				maxId = result.getLong(1);
			}
			
			newStudent.setId(maxId + 1);
			newStudent.setAge(newStudent.getAge());
			newStudent.setErrMsg("No error...");
			
			return studentRepo.save(newStudent);
			
			
		}
		
}
