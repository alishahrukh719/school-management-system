package com.example.studentapi;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SchoolDataController {
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository classRepository;
    private final SchoolSubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final FeePaymentRepository feePaymentRepository;
    private final NoticeRepository noticeRepository;
    private final AttendanceRecordRepository attendanceRepository;

    public SchoolDataController(
            TeacherRepository teacherRepository,
            SchoolClassRepository classRepository,
            SchoolSubjectRepository subjectRepository,
            ExamRepository examRepository,
            FeePaymentRepository feePaymentRepository,
            NoticeRepository noticeRepository,
            AttendanceRecordRepository attendanceRepository) {
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.feePaymentRepository = feePaymentRepository;
        this.noticeRepository = noticeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @PostConstruct
    void seedSampleData() {
        if (teacherRepository.count() == 0) {
            Teacher teacher = new Teacher();
            teacher.setId("teacher-sample");
            teacher.setName("Dr. Rajeev Kumar");
            teacher.setEid("T-001");
            teacher.setSubject("Mathematics");
            teacher.setClasses("9A,10A,11A");
            teacher.setExp("12 yrs");
            teacher.setStatus("Active");
            teacherRepository.save(teacher);
        }
        if (classRepository.count() == 0) {
            SchoolClass schoolClass = new SchoolClass();
            schoolClass.setId("class-sample");
            schoolClass.setName("Class 10-A");
            schoolClass.setTeacher("Dr. Rajeev Kumar");
            schoolClass.setStudents(35);
            schoolClass.setRoom("R-310");
            classRepository.save(schoolClass);
        }
        if (subjectRepository.count() == 0) {
            SchoolSubject subject = new SchoolSubject();
            subject.setId("subject-sample");
            subject.setName("Mathematics");
            subject.setCode("MATH-01");
            subject.setTeacher("Dr. Rajeev Kumar");
            subject.setClasses("8,9,10,11,12");
            subject.setType("Core");
            subjectRepository.save(subject);
        }
        if (examRepository.count() == 0) {
            Exam exam = new Exam();
            exam.setId("exam-sample");
            exam.setTitle("Math Mid-Term");
            exam.setSubject("Mathematics");
            exam.setClassName("Class 10-A");
            exam.setDate("2026-06-05");
            exam.setMaxMarks(100);
            exam.setPassMarks(33);
            examRepository.save(exam);
        }
        if (feePaymentRepository.count() == 0) {
            FeePayment feePayment = new FeePayment();
            feePayment.setId("fee-sample");
            feePayment.setStudent("Sample Student (sample-id)");
            feePayment.setAmount("12500");
            feePayment.setDate("2026-05-31");
            feePayment.setStatus("Paid");
            feePayment.setFeeType("Tuition");
            feePayment.setPaymentMode("Online");
            feePayment.setReceiptNo("RCP-2026-001");
            feePaymentRepository.save(feePayment);
        }
        if (noticeRepository.count() == 0) {
            Notice notice = new Notice();
            notice.setId("notice-sample");
            notice.setTitle("Welcome to EduManage");
            notice.setBody("All modules are connected to the Spring Boot backend.");
            notice.setCategory("General");
            notice.setTarget("All");
            notice.setDate("2026-05-31");
            notice.setUrgent(false);
            noticeRepository.save(notice);
        }
    }

    @GetMapping("/teachers")
    public List<Teacher> teachers() { return teacherRepository.findAll(); }
    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) { return create(teacherRepository, teacher); }
    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) { return delete(teacherRepository, id); }

    @GetMapping("/classes")
    public List<SchoolClass> classes() { return classRepository.findAll(); }
    @PostMapping("/classes")
    public ResponseEntity<SchoolClass> createClass(@RequestBody SchoolClass schoolClass) { return create(classRepository, schoolClass); }
    @PutMapping("/classes/{id}")
    public ResponseEntity<SchoolClass> updateClass(@PathVariable String id, @RequestBody SchoolClass schoolClass) {
        schoolClass.setId(id);
        return ResponseEntity.ok(classRepository.save(schoolClass));
    }
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable String id) { return delete(classRepository, id); }

    @GetMapping("/subjects")
    public List<SchoolSubject> subjects() { return subjectRepository.findAll(); }
    @PostMapping("/subjects")
    public ResponseEntity<SchoolSubject> createSubject(@RequestBody SchoolSubject subject) { return create(subjectRepository, subject); }
    @PutMapping("/subjects/{id}")
    public ResponseEntity<SchoolSubject> updateSubject(@PathVariable String id, @RequestBody SchoolSubject subject) {
        subject.setId(id);
        return ResponseEntity.ok(subjectRepository.save(subject));
    }
    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) { return delete(subjectRepository, id); }

    @GetMapping("/exams")
    public List<Exam> exams() { return examRepository.findAll(); }
    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) { return create(examRepository, exam); }
    @PutMapping("/exams/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable String id, @RequestBody Exam exam) {
        exam.setId(id);
        return ResponseEntity.ok(examRepository.save(exam));
    }
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable String id) { return delete(examRepository, id); }

    @GetMapping("/fees")
    public List<FeePayment> fees() { return feePaymentRepository.findAll(); }
    @PostMapping("/fees")
    public ResponseEntity<FeePayment> createFee(@RequestBody FeePayment feePayment) { return create(feePaymentRepository, feePayment); }
    @PutMapping("/fees/{id}")
    public ResponseEntity<FeePayment> updateFee(@PathVariable String id, @RequestBody FeePayment feePayment) {
        feePayment.setId(id);
        return ResponseEntity.ok(feePaymentRepository.save(feePayment));
    }
    @DeleteMapping("/fees/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable String id) { return delete(feePaymentRepository, id); }

    @GetMapping("/notices")
    public List<Notice> notices() { return noticeRepository.findAll(); }
    @PostMapping("/notices")
    public ResponseEntity<Notice> createNotice(@RequestBody Notice notice) { return create(noticeRepository, notice); }
    @PutMapping("/notices/{id}")
    public ResponseEntity<Notice> updateNotice(@PathVariable String id, @RequestBody Notice notice) {
        notice.setId(id);
        return ResponseEntity.ok(noticeRepository.save(notice));
    }
    @DeleteMapping("/notices/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable String id) { return delete(noticeRepository, id); }

    @GetMapping("/attendance")
    public List<AttendanceRecord> attendance(@RequestParam(required = false) String date) {
        return date == null || date.isBlank()
                ? attendanceRepository.findAll()
                : attendanceRepository.findByDate(date);
    }

    @PostMapping("/attendance")
    public List<AttendanceRecord> saveAttendance(@RequestBody List<AttendanceRecord> records) {
        records.forEach(record -> {
            if (record.getId() == null || record.getId().isBlank()) {
                record.setId(record.getDate() + "-" + record.getStudentId());
            }
        });
        return attendanceRepository.saveAll(records);
    }

    private <T extends Identified> ResponseEntity<T> create(JpaRepository<T, String> repository, T item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(UUID.randomUUID().toString());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }

    private ResponseEntity<Void> delete(JpaRepository<?, String> repository, String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    interface Identified {
        String getId();
        void setId(String id);
    }
}
