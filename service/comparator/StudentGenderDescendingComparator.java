package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentGenderDescendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o2.getGender().compareTo(o1.getGender());
	}
}
