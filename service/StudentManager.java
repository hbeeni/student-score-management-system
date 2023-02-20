package service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import dto.Student;

public class StudentManager {
	private List<Student> students;

	public StudentManager() {
		students = new LinkedList<>();
	}

	boolean checkSameStudent(Student student) {
		ListIterator<Student> lit = students.listIterator();

		boolean result = false;

		while (lit.hasNext()) {
			if ((lit.next().getId()).equals(student.getId())) {
				result = true;
			}
		}

		return result;
	}

	boolean checkSameStudent(String id) {
		ListIterator<Student> lit = students.listIterator();

		boolean result = false;

		while (lit.hasNext()) {
			if ((lit.next().getId()).equals(id)) {
				result = true;
			}
		}

		return result;
	}

	public boolean InsertStudent(Student st) {
		if (checkSameStudent(st))
			return false;

		students.add(st);
		return true;
	}

	public Student searchStudent(String id) {
		Student result = null;

		if (!checkSameStudent(id))
			return result;

		for (Student student : students) {
			if ((student.getId()).equals(id)) {
				result = student;
				break;
			}
		}

		return result;
	}

	public List<Student> searchAllStudents() {
		return students;
	}

	int[] searchStudentScore(String id) {
		Student student = searchStudent(id);
		int[] scores;

		if (student == null) {
			scores = null;
		} else {
			scores = student.getScores();
		}

		return scores;
	}

	public boolean changeScore(Student student, int subject, int score) {
		boolean result = false;

		if (!checkSameStudent(student))
			return result;

		if (subject >= 1 && subject <= 4) {
			switch (subject) {
			case 1:
				student.setKorScore(score);
				break;
			case 2:
				student.setMathScore(score);
				break;
			case 3:
				student.setEngScore(score);
				break;
			case 4:
				student.setSciScore(score);
				break;
			}

			result = true;
		}

		return result;
	}

	public boolean isEmptyStudentsList() {
		boolean result = false;

		if (students.size() == 0) {
			result = true;
		}

		return result;
	}

	public boolean deleteStudent(String id) {
		if (!checkSameStudent(id)) {
			return false;
		}

		students.remove(searchStudent(id));

		return true;
	}

	public void deleteAllStudents() {
		students.clear();
	}
}