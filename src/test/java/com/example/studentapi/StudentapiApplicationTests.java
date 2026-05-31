package com.example.studentapi;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ StudentController.class, HomeController.class, ApiExceptionHandler.class })
class StudentapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private StudentRepository studentRepository;

	@BeforeEach
	void setUpRepository() {
		Student sample = student("sample-id", "Sample Student", "sample@example.com");

		when(studentRepository.findAll()).thenReturn(List.of(sample));
		when(studentRepository.findById("sample-id")).thenReturn(Optional.of(sample));
		when(studentRepository.findById("102")).thenReturn(Optional.empty());
		when(studentRepository.findById("104")).thenReturn(Optional.empty());
		when(studentRepository.existsById("sample-id")).thenReturn(true);
		when(studentRepository.existsById("101")).thenReturn(false);
		when(studentRepository.existsById("103")).thenReturn(false);
		when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));
	}

	@Test
	void homeShowsApiIsRunning() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Student API is running"));
	}

	@Test
	void getStudentWorksWithStudentsAlias() throws Exception {
		mockMvc.perform(get("/students/sample-id"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("sample-id"));
	}

	@Test
	void postCreatesStudent() throws Exception {
		mockMvc.perform(post("/api/students")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"201\",\"name\":\"Asha\",\"email\":\"asha@example.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("201"))
				.andExpect(jsonPath("$.name").value("Asha"));
	}

	@Test
	void postCreatesStudentFromFormFields() throws Exception {
		mockMvc.perform(post("/api/students")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "202")
				.param("name", "Ravi")
				.param("email", "ravi@example.com"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("202"))
				.andExpect(jsonPath("$.email").value("ravi@example.com"));
	}

	@Test
	void putCreatesStudentWhenIdDoesNotExist() throws Exception {
		mockMvc.perform(put("/api/students/101")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Asha\",\"email\":\"asha@example.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("101"));
	}

	@Test
	void putCreatesStudentFromFormFields() throws Exception {
		mockMvc.perform(put("/api/students/103")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "Meena")
				.param("email", "meena@example.com"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("103"))
				.andExpect(jsonPath("$.name").value("Meena"));
	}

	@Test
	void patchCreatesStudentWhenIdDoesNotExist() throws Exception {
		mockMvc.perform(patch("/students/102")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"sam@example.com\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("102"))
				.andExpect(jsonPath("$.email").value("sam@example.com"));
	}

	@Test
	void patchCreatesStudentFromFormFields() throws Exception {
		mockMvc.perform(patch("/students/104")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("email", "kiran@example.com"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("104"))
				.andExpect(jsonPath("$.email").value("kiran@example.com"));
	}

	@Test
	void deleteRemovesStudent() throws Exception {
		mockMvc.perform(delete("/api/students/sample-id"))
				.andExpect(status().isNoContent());
	}

	private Student student(String id, String name, String email) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		return student;
	}

}
